package org.bloodtorrent.view;

import com.yammer.dropwizard.views.View;
import org.bloodtorrent.dto.User;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 14
 * Time: 오후 3:20
 * To change this template use File | Settings | File Templates.
 */
public class CommonView extends View {

    private User user;

    public CommonView(String viewPath) {
        super(viewPath);
    }

    public CommonView(String viewPath, User user){
        super(viewPath);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}