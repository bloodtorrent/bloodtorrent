package org.bloodtorrent.view;

import com.yammer.dropwizard.views.View;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 19
 * Time: 오전 9:31
 * To change this template use File | Settings | File Templates.
 */
public class BloodReqView extends View {

    public BloodReqView() {
        super("/ftl/bloodReq.ftl");
    }

}
