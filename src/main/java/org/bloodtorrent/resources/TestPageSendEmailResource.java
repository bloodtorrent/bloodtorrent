package org.bloodtorrent.resources;

import org.bloodtorrent.view.CommonView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/28/13
 * Time: 2:25 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/testSendEmail")
@Produces(MediaType.TEXT_HTML)
public class TestPageSendEmailResource {

    @GET
    public CommonView forwardToTestPage() {
        CommonView commonView = new CommonView("/ftl/testSendEmail.ftl");
        return commonView;  //To change body of created methods use File | Settings | File Templates.
    }
}
