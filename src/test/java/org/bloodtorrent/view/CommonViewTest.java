package org.bloodtorrent.view;

import org.bloodtorrent.dto.User;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 21
 * Time: 오후 1:10
 * To change this template use File | Settings | File Templates.
 */
public class CommonViewTest {

    private String viewPath = "/ftl/main.ftl";
    private String message = "message";

    @Test
    public void shouldInstantiateCommonViewWithViewPath() {
        CommonView commonView = new CommonView(viewPath);
        assertThat(commonView.getTemplateName(), is(viewPath));
    }

    @Test
    public void shouldInstantiateCommonViewWithUser() {
        User user = new User();
        CommonView commonView = new CommonView(viewPath, user);
        assertThat(commonView.getUser(), is(user));
    }

    @Test
    public void shouldInstantiateCommonViewWithMessage(){
        CommonView commonView = new CommonView(viewPath, message);
        assertThat(commonView.getTemplateName(), is(viewPath));
        assertThat(commonView.getMessage(), is(message));
    }
}
