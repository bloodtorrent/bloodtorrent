package org.bloodtorrent.view;

import com.yammer.dropwizard.views.View;
import org.apache.commons.lang3.ArrayUtils;
import org.bloodtorrent.dto.User;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 14
 * Time: 오후 2:57
 * To change this template use File | Settings | File Templates.
 */
public class RegistrationResultView extends View {
    private final String result;
    private final List<String> messages;

    public RegistrationResultView(String result) {
        this(result, new ArrayList<String>());
    }

    public RegistrationResultView(String result, List<String> messages) {
        super("/ftl/registrationResult.ftl");
        this.result = result;
        this.messages = messages;
    }

    public RegistrationResultView(String result, String message) {
        super("/ftl/registrationResult.ftl");
        this.result = result;
        messages = new ArrayList<String>();
        messages.add(message);
    }

    public String getResult() {
        return result;
    }

    public List<String> getMessages() {
        return messages;
    }
}
