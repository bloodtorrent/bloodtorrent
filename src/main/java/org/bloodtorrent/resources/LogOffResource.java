package org.bloodtorrent.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import org.bloodtorrent.ResourceManager;
import org.bloodtorrent.dto.User;
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
@Path("/logoff")
@Produces(MediaType.TEXT_HTML)
public class LogOffResource {
    private final SessionManager sessionManager;

    public LogOffResource(SessionManager httpSessionManager) {
        sessionManager = httpSessionManager;
    }

    @GET
    @UnitOfWork
    public CommonView forwardMainPage(@CookieParam("JSESSIONID") String sessionID) {
        HttpSession session = getSession(sessionID);
        session.removeAttribute("user");
        MainResource mainResource = ResourceManager.getInstance().find(MainResource.class).get();
        CommonView commonView = mainResource.forwardMainPage(sessionID);
        return commonView;
    }

    public HttpSession getSession(String sessionID) {
        return sessionManager.getHttpSession(sessionID);
    }
}