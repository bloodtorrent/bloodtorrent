package org.bloodtorrent.view;

import com.yammer.dropwizard.views.View;
import lombok.Getter;
import org.bloodtorrent.dto.User;

import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 19
 * Time: 오전 9:31
 * To change this template use File | Settings | File Templates.
 */
public class BloodRequestView extends CommonView {
    @Getter
    private List<User> donors;

    public BloodRequestView(List<User> donors) {
        super("/ftl/thankyouForRequest.ftl");
        this.donors = donors;
    }
}
