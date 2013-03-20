package org.bloodtorrent.resources;

import org.bloodtorrent.view.CommonView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/20/13
 * Time: 1:10 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/html/{pageName}")
@Produces(MediaType.TEXT_HTML)
public class CommonResource {
    @GET
    public CommonView showPage(@PathParam("pageName") String pageName) {
        return new CommonView("/html/" + pageName + ".ftl");
    }
}
