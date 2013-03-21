package org.bloodtorrent.resources;

import org.bloodtorrent.view.CommonView;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/21/13
 * Time: 11:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainResourceTest {

    @Test
    public void shouldReturnMainView() {
        MainResource mainResource = mock(MainResource.class);
        CommonView commonView = mock(CommonView.class);
        when(mainResource.forwardMainPage()).thenReturn(commonView);

        assertThat(mainResource.forwardMainPage(), is(commonView));
    }
}
