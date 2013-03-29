package org.bloodtorrent.resources;

import com.sun.jersey.core.header.FormDataContentDisposition;
import org.bloodtorrent.IllegalDataException;
import org.bloodtorrent.dto.SuccessStory;
import org.bloodtorrent.repository.SuccessStoryRepository;
import org.bloodtorrent.view.SuccessStoryView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.*;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
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

    @Mock
    private SuccessStoryRepository repository;

    private SuccessStoryResource resource;

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Before
    public void setUpResource() {
        resource = new SuccessStoryResource(repository);
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

    public void shouldCreateNewSuccessStoryWithAttachedImageFile() throws IOException {
        final ArrayList<SuccessStory> storyContainer = new ArrayList<SuccessStory>();
        final String FILE_CONTENT = "Test string for input string";
        StringBufferInputStream inputStream = new StringBufferInputStream(FILE_CONTENT);
        FormDataContentDisposition contentDisposition = mock(FormDataContentDisposition.class);
        String fileName = "testfile.jpg";
        when(contentDisposition.getFileName()).thenReturn(fileName);
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                storyContainer.add((SuccessStory) invocation.getArguments()[0]);
                return null;
            }
        }).when(repository).insert(Mockito.any(SuccessStory.class));

        resource.createSuccessStory("title", "summary", "description", inputStream, contentDisposition);

        assertThat(storyContainer.size(), is(1));

        SuccessStory story = storyContainer.get(0);
        assertThat(story.getTitle(), is("title"));
        assertThat(story.getSummary(), is("summary"));
        assertThat(story.getDescription(), is("description"));

        File attachFile = new File(SuccessStoryResource.UPLOAD_DIR + File.separator + story.getThumbnailPath());
        assertThat(attachFile.exists(), is(true));

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(attachFile)));
        String fileContent = br.readLine();
        assertThat(fileContent, is(FILE_CONTENT));
    }
}
