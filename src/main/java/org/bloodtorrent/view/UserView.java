package org.bloodtorrent.view;

import com.yammer.dropwizard.views.View;
import org.bloodtorrent.dto.User;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 14
 * Time: 오후 2:57
 * To change this template use File | Settings | File Templates.
 */
public class UserView extends View {
    private final User user;

    public UserView(User user) {
        super("/ftl/donorRegist.ftl");
        this.user = user;
    }

    public User getuser() {
        return user;
    }

}
