package org.bloodtorrent.util;

import org.bloodtorrent.util.exception.AmbiguousException;
import org.hamcrest.CoreMatchers;
import java.lang.RuntimeException;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 4/9/13
 * Time: 10:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class AmbiguousExceptionTest {
    @Test
    public void shouldExtendRuntimeException() {
        assertThat(new AmbiguousException(), instanceOf(RuntimeException.class));
    }
    @Test
    public void shouldBeAbleToBeCreatedWithMessage() {
        String message = "test message";
        AmbiguousException e = new AmbiguousException(message);
        assertThat(e.getMessage(), is(message));
    }
    @Test
    public void shouldBeAbleToBeCreatedWithCausingException() {
        Throwable cause = new Exception("cause");
        AmbiguousException e = new AmbiguousException(cause);
        assertThat(e.getCause(), is(cause));
    }
}
