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
public class RegistrationResultView extends View {
    private final String result;
    private final String detail;

    public RegistrationResultView(String result) {
        this(result, "");
    }

    public RegistrationResultView(String result, String detail) {
        super("/ftl/registrationResult.ftl");
        this.result = result;
        this.detail = detail;
    }

    public String getResult() {
        return result;
    }

    public String getDetail() {
        return detail;
    }
}
