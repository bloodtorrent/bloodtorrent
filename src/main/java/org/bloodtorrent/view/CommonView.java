package org.bloodtorrent.view;

import com.yammer.dropwizard.views.View;
import lombok.Getter;
import lombok.Setter;
import org.bloodtorrent.dto.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 14
 * Time: 오후 3:20
 * To change this template use File | Settings | File Templates.
 */
public class CommonView extends View {

    @Getter @Setter
    private User user;
    @Getter @Setter
    private String message;

    public CommonView(String viewPath) {
        super(viewPath);
    }

    public CommonView(String viewPath, User user){
        super(viewPath);
        this.user = user;
    }

    public CommonView(String viewPath, String message) {
        super(viewPath);
        this.message = message;
    }
}