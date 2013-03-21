package view;

import org.bloodtorrent.view.UserView;
import org.junit.Test;
import org.bloodtorrent.dto.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 21
 * Time: 오후 1:10
 * To change this template use File | Settings | File Templates.
 */
public class UserViewTest {

    @Test
    public void shouldGetUser(){
        User user = new User();
        UserView userView = new UserView(user);

        assertThat(userView.getuser(), is(user));
    }
}
