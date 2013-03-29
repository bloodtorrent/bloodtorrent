package org.bloodtorrent.dto;

import org.bloodtorrent.IllegalDataException;
import org.bloodtorrent.repository.SuccessStoryRepository;
import org.bloodtorrent.resources.SuccessStoryResource;
import org.bloodtorrent.view.SuccessStoryView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 29
 * Time: 오전 7:25
 * To change this template use File | Settings | File Templates.
 */
public class SuccessStoryTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    private SuccessStory createNewSuccessStory() {
        SuccessStory story = new SuccessStory();
        story.setTitle("Sample Story");
        story.setDescription("Hello, world!");
        return story;
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
