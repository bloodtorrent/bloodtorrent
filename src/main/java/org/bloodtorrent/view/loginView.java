package org.bloodtorrent.view;

import com.yammer.dropwizard.views.View;
import lombok.Getter;
import lombok.Setter;
import org.bloodtorrent.dto.SuccessStory;
import org.bloodtorrent.dto.User;

import java.util.List;

@SuppressWarnings("PMD.UnusedPrivateField")
public class LoginView extends View {

    private static final String PATH = "/ftl/login.ftl";

    public LoginView() {
        super(PATH);
    }
}

