package org.bloodtorrent.view;

import org.bloodtorrent.dto.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 21
 * Time: 오후 1:10
 * To change this template use File | Settings | File Templates.
 */
public class ResultViewTest {

    @Test
    public void shouldGetResult(){
        ResultView resultView = new ResultView("result message");
        assertThat(resultView.getResult(), is("result message"));
    }
    @Test
    public void shouldGetMessageWhenUseTwoStringParameter(){
       ResultView resultView =  new ResultView("result message", "result");
       assertThat(resultView.getMessages().size() , is(1));
    }

    @Test
    public void shouldGetResultWhenUseTwoStringParameter(){
        ResultView resultView =  new ResultView("result message", "result");
        assertThat(resultView.getResult(), is("result message"));
    }

    @Test
    public void shouldGetMessageWhenUseOneStringAndOneArraylistParameter(){
        List<String> messages = createMessages();
        ResultView resultView =  new ResultView("result message", messages);
        assertThat(resultView.getMessages(), is(messages));
    }

    @Test
    public void shouldGetResultWhenUseOneStringAndOneArraylistParameter(){
        List<String> messages = createMessages();
        ResultView resultView =  new ResultView("result message", messages);
        assertThat(resultView.getResult(), is("result message"));
    }

    private List<String> createMessages() {
        List<String> messageArray = new ArrayList<String>();
        messageArray.add("result");
        return messageArray;
    }
}
