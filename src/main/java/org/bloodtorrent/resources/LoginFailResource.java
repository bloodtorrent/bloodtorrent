package org.bloodtorrent.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import lombok.Setter;
import org.bloodtorrent.ResourceManager;
import org.bloodtorrent.view.CommonView;
import org.eclipse.jetty.server.SessionManager;

import javax.servlet.http.HttpSession;
import javax.ws.rs.CookieParam;
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
@Path("/loginfail")
@Produces(MediaType.TEXT_HTML)
public class LoginFailResource {

    private static final String LOG_IN_FAIL_MESSAGE = "The E-mail or password you entered is incorrect.";

    @Setter
    MainResource mainResource;

    @GET
    @UnitOfWork
    public CommonView forwardMainPage(@CookieParam("JSESSIONID") String sessionID) {
        CommonView commonView = mainResource.forwardMainPage(sessionID);
        commonView.setMessage(LOG_IN_FAIL_MESSAGE);
        return commonView;
    }
}