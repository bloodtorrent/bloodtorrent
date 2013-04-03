package org.bloodtorrent.view;

import org.bloodtorrent.dto.SuccessStory;
import org.junit.Test;

import static junit.framework.Assert.assertSame;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/27/13
 * Time: 10:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class SuccessStoryViewTest {
	@Test
	public void shouldReturnSameSuccessStoryGivenToConstructor() {
		SuccessStory successStory = mock(SuccessStory.class);
        when(successStory.getDescription()).thenReturn("aaa");
		SuccessStoryView view = new SuccessStoryView(successStory);
		assertSame(view.getSuccessStory(), successStory);
	}

    @Test
    public void shouldChangeLineSepearatorOfDescriptionToBrTag(){
        SuccessStory successStory = new SuccessStory();
        successStory.setDescription("aaaaa\r\n\r\nbbbbb");
        SuccessStoryView view = new SuccessStoryView(successStory);
        assertThat(view.getSuccessStory().getDescription(), containsString("<br/>"));
    }
}
