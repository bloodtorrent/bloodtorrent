package org.bloodtorrent.view;

import com.yammer.dropwizard.views.View;
import lombok.Getter;
import lombok.Setter;
import org.bloodtorrent.dto.User;

import java.util.ResourceBundle;

@SuppressWarnings("PMD.UnusedPrivateField")
public class CommonView extends View {

    @Getter @Setter
    private User user;
    @Getter @Setter
    private String message;

    @Getter
    private ResourceBundle printStrings = ResourceBundle.getBundle("properties/bloodtorrentStrings");

    @Getter
    private ResourceBundle printUrls = ResourceBundle.getBundle("properties/bloodtorrentUrls");

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