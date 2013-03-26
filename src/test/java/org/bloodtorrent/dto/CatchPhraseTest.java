package org.bloodtorrent.dto;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/26/13
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class CatchPhraseTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    public void imageShouldNotBeEmpty() {
        CatchPhrase catchPhrase = new CatchPhrase();
        setDummyString(catchPhrase, "imagePath", 0);
        Set<ConstraintViolation<CatchPhrase>> constraintViolations = validator.validateProperty(catchPhrase, "imagePath");
        assertThat(constraintViolations.size(), is(2));
    }

    @Test
    public void imageShouldBePathPattern() {
        CatchPhrase catchPhrase = new CatchPhrase();
        catchPhrase.setImagePath("/images/test.png");
        Set<ConstraintViolation<CatchPhrase>> constraintViolations = validator.validateProperty(catchPhrase, "imagePath");
        assertThat(constraintViolations.size(), is(0));
        catchPhrase.setImagePath("/test.png");
        constraintViolations = validator.validateProperty(catchPhrase, "imagePath");
        assertThat(constraintViolations.size(), is(0));
    }

    @Test
    public void illegalImagePatternShouldBeNotAllowed() {
        CatchPhrase catchPhrase = new CatchPhrase();
        catchPhrase.setImagePath("test.png");
        Set<ConstraintViolation<CatchPhrase>> constraintViolations = validator.validateProperty(catchPhrase, "imagePath");
        assertThat(constraintViolations.size(), is(1));
        catchPhrase.setImagePath("/images/test");
        constraintViolations = validator.validateProperty(catchPhrase, "imagePath");
        assertThat(constraintViolations.size(), is(1));
        catchPhrase.setImagePath("test");
        constraintViolations = validator.validateProperty(catchPhrase, "imagePath");
        assertThat(constraintViolations.size(), is(1));
        catchPhrase.setImagePath("-$;");
        constraintViolations = validator.validateProperty(catchPhrase, "imagePath");
        assertThat(constraintViolations.size(), is(1));
        catchPhrase.setImagePath("/images/^_^.gif");
        constraintViolations = validator.validateProperty(catchPhrase, "imagePath");
        assertThat(constraintViolations.size(), is(1));
        catchPhrase.setImagePath("/images/lord_of_the_ring.avi");
        constraintViolations = validator.validateProperty(catchPhrase, "imagePath");
        assertThat(constraintViolations.size(), is(1));
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

    private <T> void setDummyString(T t, String property, int num) {
        String dummyText = makeDummyString(num);
        invokeMethod(t, property, dummyText);
    }

    private <T> void setDummyNumericString(T t, String property, int num) {
        String dummyNumericText = makeDummyNumericString(num);
        invokeMethod(t, property, dummyNumericText);
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
