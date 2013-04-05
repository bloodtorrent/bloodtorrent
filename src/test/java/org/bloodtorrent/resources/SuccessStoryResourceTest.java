package org.bloodtorrent.resources;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.yammer.dropwizard.views.View;
import org.bloodtorrent.IllegalDataException;
import org.bloodtorrent.dto.SuccessStory;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.repository.SuccessStoryRepository;
import org.bloodtorrent.view.LoginView;
import org.bloodtorrent.view.ResultView;
import org.bloodtorrent.view.SuccessStoryView;
import org.eclipse.jetty.server.SessionManager;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import javax.servlet.http.HttpSession;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.bloodtorrent.util.TestUtil.makeDummyString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static java.io.File.separator;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 22
 * Time: 오전 11:19
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class SuccessStoryResourceTest {

    public static final int STATUS_MOVED = 302;
    public static final int STATUS_NOT_FOUND = 404;
    public static final int STATUS_OK = 200;
    public static final String USER = "user";
    private final String ADMIN_SESSION = "ADMIN_SESSION";
    private final String NONADMIN_SESSION = "NON_ADMIN_SESSION";
    private final static String ATTACH_FILE_NAME = "testfile.jpg";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    @Mock
    private SuccessStoryRepository repository;
    @Mock
    private SessionManager sessionManager;
    @Mock
    private HttpSession adminSession;
    @Mock
    private HttpSession nonAdminSession;
    @Mock
    private FormDataContentDisposition contentDisposition;

    private SuccessStoryResource resource;
    private User adminUser;
    private User nonAdminUser;
    private final String FILE_CONTENT = "Test string for input string";
    private final StringBufferInputStream inputStream = new StringBufferInputStream(FILE_CONTENT);

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Before
    public void setUpResource() {
        resource = new SuccessStoryResource(sessionManager, repository);
        adminUser = new User();
        adminUser.setId("id");
        adminUser.setIsAdmin('Y');
        nonAdminUser = new User();
        nonAdminUser.setId("id");
        nonAdminUser.setIsAdmin('N');

        when(sessionManager.getHttpSession(ADMIN_SESSION)).thenReturn(adminSession);
        when(adminSession.getAttribute(USER)).thenReturn(adminUser);
        when(sessionManager.getHttpSession(NONADMIN_SESSION)).thenReturn(nonAdminSession);
        when(nonAdminSession.getAttribute(USER)).thenReturn(nonAdminUser);
        when(contentDisposition.getFileName()).thenReturn(ATTACH_FILE_NAME);
    }

	private SuccessStory createNewSuccessStory(String id) {
		SuccessStory story = createNewSuccessStory();
		story.setId(id);
		return story;
	}

    private SuccessStory createNewSuccessStory() {
        SuccessStory story = new SuccessStory();
        story.setTitle("Sample Story");
        story.setDescription("Hello, world!");
        return story;
    }

    @Test
    public void shouldBeZeroSizeWhenThereIsNoStory() throws IllegalDataException {
        assertThat(resource.getSuccessStoriesBriefly().isEmpty(), is(true));
    }

    @Test
    public void shouldBeOneSizeWhenThereIsOneStory() throws IllegalDataException {
        SuccessStory story = createNewSuccessStory();
        when(repository.list()).thenReturn(Arrays.asList(story));
        assertThat(resource.getSuccessStoriesBriefly().size(), is(1));
    }

    @Test
    public void shouldBeTwoSizeWhenThereAreTwoStories() throws IllegalDataException {
        SuccessStory story1 = createNewSuccessStory();
        SuccessStory story2 = createNewSuccessStory();
        List<SuccessStory> list = new ArrayList<SuccessStory>();
        list.add(story1);
        list.add(story2);
        when(repository.list()).thenReturn(list);

        assertThat(resource.getSuccessStoriesBriefly().size(), is(2));
    }

    @Test
    public void shouldThrowExceptionWhenThereAreFourStories() throws IllegalDataException {
        when(repository.list()).thenReturn(Arrays.asList(createNewSuccessStory(), createNewSuccessStory(), createNewSuccessStory(), createNewSuccessStory()));

        resource.getSuccessStoriesBriefly().size();
    }

	@Test
	public void shouldReturnSuccessStoryViewWithGivenSuccessStory(){
		String id = "One";
		SuccessStory story = createNewSuccessStory(id);
		when(repository.get(id)).thenReturn(story);
		SuccessStoryView view = resource.getSuccessStory(id, "");
		assertThat(view.getSuccessStory(), is(story));
	}

	@Test
	public void shouldReturnSuccessStoryWithGivenId() {
		String id = "One";
		SuccessStory story = createNewSuccessStory(id);
		when(repository.get(id)).thenReturn(story);
		assertThat(resource.get(id), is(story));
	}

    @Test
    public void shouldCreateNewSuccessStoryWithAttachedImageFile() throws IOException {
        final ArrayList<SuccessStory> storyContainer = new ArrayList<SuccessStory>();
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                storyContainer.add((SuccessStory) invocation.getArguments()[0]);
                return null;
            }
        }).when(repository).insert(Mockito.any(SuccessStory.class));

        View view = resource.createSuccessStory(ADMIN_SESSION, makeDummyString(150), makeDummyString(150), "description", inputStream, contentDisposition);

        assertThat(view instanceof SuccessStoryView, is(true));
        SuccessStoryView successStoryView = (SuccessStoryView) view;
        assertThat(successStoryView.getSavedSuccessFlag(), is(true));
        assertThat(storyContainer.size(), is(1));

        SuccessStory story = storyContainer.get(0);
        assertThat(story.getTitle(), is(makeDummyString(150)));
        assertThat(story.getSummary(), is(makeDummyString(150)));
        assertThat(story.getDescription(), is("description"));
        assertThat(story.getShowMainPage(), is("N"));

        File attachFile = new File(SuccessStoryResource.UPLOAD_DIR + File.separator + story.getThumbnailPath());
        assertThat(attachFile.exists(), is(true));

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(attachFile)));
        String fileContent = br.readLine();
        assertThat(fileContent, is(FILE_CONTENT));
    }

    @Test
    public void shouldReturnResultViewWithErrorMessagesWhenTitleValidationFailed() throws IOException {
        View view = resource.createSuccessStory(ADMIN_SESSION, "", "summary", "description", inputStream, contentDisposition);
        assertThat(view instanceof ResultView, is(true));
    }

    @Test
    public void shouldReturnResultViewWithErrorMessagesWhenTitleLengthValidationFailed() throws IOException {
        View view = resource.createSuccessStory(ADMIN_SESSION, makeDummyString(151), "summary", "description", inputStream, contentDisposition);
        assertThat(view instanceof ResultView, is(true));
    }

    @Test
    public void shouldReturnResultViewWithErrorMessagesWhenSummaryLengthValidationFailed() throws IOException {
        View view = resource.createSuccessStory(ADMIN_SESSION, "title", makeDummyString(251), "description", inputStream, contentDisposition);
        assertThat(view instanceof ResultView, is(true));
    }

    @Test
    public void shouldReturnSuccessStoryEditorViewWhenAdminIsLoggedIn() {
        Response response = resource.viewSuccessStoryEditor(ADMIN_SESSION);
        SuccessStoryView view = (SuccessStoryView) response.getEntity();
        assertThat(view.getTemplateName(), is("/ftl/successStoryEditor.ftl"));
    }

    @Test
    public void shouldReturnMainPageWhenAdminIsNotLoggedIn() {
        Response response = resource.viewSuccessStoryEditor(NONADMIN_SESSION);
        assertThat(response.getStatus(), is(STATUS_MOVED));
    }

    @Test
    public void shouldReturnImageFileWhenGivenFileNameIsCorrectJpeg() throws IOException {
        String fileName = String.format("test%s.jpg", sdf.format(new Date()));
        String filePath = "upload/" + fileName;
        File imageFile = new File(filePath);
        imageFile.createNewFile();
        imageFile.deleteOnExit();

        Response response = resource.loadImage(fileName);
        assertThat(response.getStatus(), is (STATUS_OK));
        assertThat((File) response.getEntity(), CoreMatchers.equalTo(imageFile));

        MultivaluedMap<String, Object> map = response.getMetadata();
        assertThat(map.getFirst("Content-Type").toString(), is("image/jpeg"));
    }

    @Test
    public void shouldReturnImageFileWhenGivenFileNameIsCorrectPng() throws IOException {
        String fileName = String.format("test%s.png", sdf.format(new Date()));
        String filePath = "upload/" + fileName;
        File imageFile = new File(filePath);
        imageFile.createNewFile();
        imageFile.deleteOnExit();

        Response response = resource.loadImage(fileName);
        assertThat(response.getStatus(), is (STATUS_OK));
        assertThat((File) response.getEntity(), equalTo(imageFile));

        MultivaluedMap<String, Object> map = response.getMetadata();
        assertThat(map.getFirst("Content-Type").toString(), is("image/png"));
    }

    @Test
    public void shouldReturnNotFoundErrorWhenGivenFileNameIsIncorrect() {
        Response response = resource.loadImage("nofile.jpg");
        assertThat(response.getStatus(), is (STATUS_NOT_FOUND));
    }

    @Test
    public void shouldReturnSuccessStoryViewWhenAdminIsLoggedIn() {
        Response response = resource.listSuccessStory(ADMIN_SESSION);
        assertThat(response.getStatus(), is (STATUS_OK));
        assertThat((SuccessStoryView) response.getEntity(), CoreMatchers.isA(SuccessStoryView.class));
    }

    @Test
    public void shouldRedirectToMainPageWhenAdminIsNotNoggedIn() {
        Response response = resource.listSuccessStory(NONADMIN_SESSION);
        assertThat(response.getStatus(), is(200));

        MultivaluedMap<String, Object> map = response.getMetadata();
        assertThat((LoginView)response.getEntity(), isA(LoginView.class));
    }

    @Test
    public void shouldCheckValueFromList () {
        List<String> checkStoryId = new ArrayList<String>();

        resource.selectForMain(ADMIN_SESSION, checkStoryId);
        verify(repository, never()).selectForMain(anyList());

        checkStoryId.add("story1");
        checkStoryId.add("story2");
        checkStoryId.add("story3");
        checkStoryId.add("story4");
        resource.selectForMain(ADMIN_SESSION, checkStoryId);
        verify(repository, never()).selectForMain(anyList());
    }

    @Test
    public void shouldUpdateDatabase() {
        List<String> checkStoryId = new ArrayList<String>();
        checkStoryId.add("story1");
        checkStoryId.add("story2");
        checkStoryId.add("story3");

        resource.selectForMain(ADMIN_SESSION, checkStoryId);
        verify(repository).selectForMain(checkStoryId);
    }

    @Test
    public void getSuccessStoryShouldGetUserFromSessionWhenSessionIdIsNotNull() {
        String id = "One";
        SuccessStory story = createNewSuccessStory(id);
        when(repository.get(id)).thenReturn(story);
        resource.getSuccessStory(id, ADMIN_SESSION);
        verify(adminSession).getAttribute(USER);
    }

    @Test
    public void getSuccessStoryShouldNotGetUserFromSessionWhenSessionIdIsNull() {
        String id = "One";
        SuccessStory story = createNewSuccessStory(id);
        when(repository.get(id)).thenReturn(story);
        resource.getSuccessStory(id, null);
        verify(adminSession, never()).getAttribute(USER);
    }

    @Rule
    public ExpectedException expectedEx =  ExpectedException.none();
    @Test
    public void createSuccessStoryShouldThrowExceptionWhenFileNameContainsPathSeparator() throws Exception {
        expectedEx.expect(IOException.class);
        expectedEx.expectMessage("File name must not contain separators");
        FormDataContentDisposition mockContent = mock(FormDataContentDisposition.class);
        when(mockContent.getFileName()).thenReturn(separator + "fully qualified path to " + separator + "some" + separator + "file");
        resource.createSuccessStory(ADMIN_SESSION, "Title: Does not matter", "Summary: Does not matter", "Description: Does not matter", mock(InputStream.class), mockContent);
    }

    @Test(expected = IOException.class)
    public void createSuccessStoryShouldThrowExceptionWhenSessionIdIsNull() throws Exception {
        String id = "One";
        SuccessStory story = createNewSuccessStory(id);
        when(repository.get(id)).thenReturn(story);
        resource.createSuccessStory(null, "Title: Does not matter", "Summary: Does not matter", "Description: Does not matter", mock(InputStream.class), mock(FormDataContentDisposition.class));
    }

    @Test(expected = IOException.class)
    public void createSuccessStoryShouldThrowExceptionWhenInputStreamIsNull() throws Exception {
        String id = "One";
        SuccessStory story = createNewSuccessStory(id);
        when(repository.get(id)).thenReturn(story);
        resource.createSuccessStory(ADMIN_SESSION, "Title: Does not matter", "Summary: Does not matter", "Description: Does not matter", null, mock(FormDataContentDisposition.class));
    }

    @Test(expected = IOException.class)
    public void createSuccessStoryShouldThrowExceptionWhenFormDataContentDispositionIsNull() throws Exception {
        String id = "One";
        SuccessStory story = createNewSuccessStory(id);
        when(repository.get(id)).thenReturn(story);
        InputStream mockInputStream = mock(InputStream.class);
        resource.createSuccessStory(ADMIN_SESSION, "Title: Does not matter", "Summary: Does not matter", "Description: Does not matter", mockInputStream, null);
    }

    @Test(expected = IOException.class)
    public void createSuccessStoryShouldThrowExceptionWhenUserIsNull() throws Exception {
        String id = "One";
        SuccessStory story = createNewSuccessStory(id);
        when(repository.get(id)).thenReturn(story);
        resource.createSuccessStory("NO_SUCH_USER_ID_IN_SESSION", "Title: Does not matter", "Summary: Does not matter", "Description: Does not matter", mock(InputStream.class), mock(FormDataContentDisposition.class));
    }
}
