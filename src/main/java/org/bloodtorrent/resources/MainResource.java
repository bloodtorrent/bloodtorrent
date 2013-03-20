package org.bloodtorrent.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import org.bloodtorrent.view.CommonView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 20
 * Time: 오후 12:06
 * To change this template use File | Settings | File Templates.
 */
@Path("/")
@Produces(MediaType.TEXT_HTML)
public class MainResource {

    @GET
    @UnitOfWork
    public CommonView forwardMainPage() {
        return new CommonView("/ftl/main.ftl");
    }
}
