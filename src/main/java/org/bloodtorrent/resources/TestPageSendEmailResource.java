package org.bloodtorrent.resources;

import lombok.NoArgsConstructor;
import org.bloodtorrent.view.CommonView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static lombok.AccessLevel.PROTECTED;

@Path("/testSendEmail")
@Produces(MediaType.TEXT_HTML)
@NoArgsConstructor(access = PROTECTED)
public class TestPageSendEmailResource {

    @GET
    public CommonView forwardToTestPage() {
        CommonView commonView = new CommonView("/ftl/testSendEmail.ftl");
        return commonView;  //To change body of created methods use File | Settings | File Templates.
    }
}
