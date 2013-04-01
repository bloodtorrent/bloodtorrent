package org.bloodtorrent.resources;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.yammer.dropwizard.views.View;
import org.bloodtorrent.IllegalDataException;
import org.bloodtorrent.dto.SuccessStory;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.repository.SuccessStoryRepository;
import org.bloodtorrent.view.ResultView;
import org.bloodtorrent.view.SuccessStoryView;
import org.eclipse.jetty.server.SessionManager;
import org.junit.Before;
import org.junit.Test;
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
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.*;

import static org.bloodtorrent.util.TestUtil.makeDummyString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 22
 * Time: 오전 11:19
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class SuccessStoryResourceTest {

    private final String ADMIN_SESSION = "ADMIN_SESSION";
    private final String NONADMIN_SESSION = "NON_ADMIN_SESSION";
    private final static String ATTACH_FILE_NAME = "testfile.jpg";

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
        when(adminSession.getAttribute("user")).thenReturn(adminUser);
        when(sessionManager.getHttpSession(NONADMIN_SESSION)).thenReturn(nonAdminSession);
        when(nonAdminSession.getAttribute("user")).thenReturn(nonAdminUser);
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
		SuccessStoryView view = resource.getSuccessStory(id);
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

        View view = resource.createSuccessStory(makeDummyString(150), makeDummyString(150), "description", inputStream, contentDisposition);

        assertThat(view instanceof SuccessStoryView, is(true));
        assertThat(storyContainer.size(), is(1));

        SuccessStory story = storyContainer.get(0);
        assertThat(story.getTitle(), is(makeDummyString(150)));
        assertThat(story.getSummary(), is(makeDummyString(150)));
        assertThat(story.getDescription(), is("description"));

        File attachFile = new File(SuccessStoryResource.UPLOAD_DIR + File.separator + story.getThumbnailPath());
        assertThat(attachFile.exists(), is(true));

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(attachFile)));
        String fileContent = br.readLine();
        assertThat(fileContent, is(FILE_CONTENT));
    }

    @Test
    public void shouldReturnResultViewWithErrorMessagesWhenTitleValidationFailed() throws IOException {
        View view = resource.createSuccessStory("", "summary", "description", inputStream, contentDisposition);
        assertThat(view instanceof ResultView, is(true));
    }

    @Test
    public void shouldReturnResultViewWithErrorMessagesWhenTitleLengthValidationFailed() throws IOException {
        View view = resource.createSuccessStory(makeDummyString(151), "summary", "description", inputStream, contentDisposition);
        assertThat(view instanceof ResultView, is(true));
    }

    @Test
    public void shouldReturnResultViewWithErrorMessagesWhenSummaryLengthValidationFailed() throws IOException {
        View view = resource.createSuccessStory("title", makeDummyString(151), "description", inputStream, contentDisposition);
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
        assertThat(response.getStatus(), is(302));
    }

}
