package org.bloodtorrent.resources;

import org.bloodtorrent.IllegalDataException;
import org.bloodtorrent.dto.SuccessStory;
import org.bloodtorrent.repository.SuccessStoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
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

    @Test(expected = IllegalDataException.class)
    public void shouldThrowExceptionWhenThereAreFourStories() throws IllegalDataException {
        when(repository.list()).thenReturn(Arrays.asList(createNewSuccessStory(), createNewSuccessStory(), createNewSuccessStory(), createNewSuccessStory()));

        resource.getSuccessStoriesBriefly().size();
    }

    @Test
    public void titleShouldNotBeEmpty() {
        SuccessStory story = createNewSuccessStory();
        setDummyString(story, "title", 0);
        Set<ConstraintViolation<SuccessStory>> constraintViolations = validator.validateProperty(story, "title");
        assertThat(constraintViolations.size(), is(1));
    }

    @Test
    public void summaryShouldNotBeEmpty() {
        SuccessStory story = createNewSuccessStory();
        setDummyString(story, "summary", 0);
        Set<ConstraintViolation<SuccessStory>> constraintViolations = validator.validateProperty(story, "summary");
        assertThat(constraintViolations.size(), is(1));
    }

    @Test
    public void summaryShouldNotBeMoreThan100Characters() {
        SuccessStory story = createNewSuccessStory();
        setDummyString(story, "summary", 101);
        Set<ConstraintViolation<SuccessStory>> constraintViolations = validator.validateProperty(story, "summary");
        assertThat(constraintViolations.size(), is(1));
    }

    @Test
    public void descriptionShouldNotBeEmpty() {
        SuccessStory story = createNewSuccessStory();
        setDummyString(story, "description", 0);
        Set<ConstraintViolation<SuccessStory>> constraintViolations = validator.validateProperty(story, "description");
        assertThat(constraintViolations.size(), is(1));
    }

	@Test
	public void thumbnailPathShouldBeFilled() {
		SuccessStory story = createNewSuccessStory();
		setDummyString(story, "thumbnailPath", 0);

		Set<ConstraintViolation<SuccessStory>> constraintViolations = validator.validateProperty(story, "thumbnailPath");
		assertThat(constraintViolations.size(), is(1));

		setDummyString(story, "thumbnailPath", 10);
		constraintViolations = validator.validateProperty(story, "thumbnailPath");
		assertThat(constraintViolations.size(), is(0));
	}

	@Test
	public void visualResourcePathShouldBeFilled() {
		SuccessStory story = createNewSuccessStory();
		setDummyString(story, "visualResourcePath", 0);

		Set<ConstraintViolation<SuccessStory>> constraintViolations = validator.validateProperty(story, "visualResourcePath");
		assertThat(constraintViolations.size(), is(1));

		setDummyString(story, "visualResourcePath", 10);
		constraintViolations = validator.validateProperty(story, "visualResourcePath");
		assertThat(constraintViolations.size(), is(0));
	}











    private <T> void setDummyString(T t, String property, int num) {
        String dummyText = makeDummyString(num);
        invokeMethod(t, property, dummyText);
    }

    private <T> void setDummyNumericString(T t, String property, int num) {
        String dummyNumericText = makeDummyNumericString(num);
        invokeMethod(t, property, dummyNumericText);
    }

    private <T> void invokeMethod(T t, String property, String dummyText) {
        for (Method method : t.getClass().getMethods()) {
            String methodName = method.getName();
            if(methodName.startsWith("set") && methodName.toLowerCase().endsWith(property.toLowerCase())) {
                try {
                    method.invoke(t, dummyText);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String makeDummyString(int num, String dummyChar) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0 ; i < num ; i++){
            stringBuilder.append(dummyChar);
        }
        return stringBuilder.toString();
    }

    private String makeDummyString(int num) {
        return makeDummyString(num, "*");
    }

    private String makeDummyNumericString(int num) {
        return makeDummyString(num, "1");
    }
}
