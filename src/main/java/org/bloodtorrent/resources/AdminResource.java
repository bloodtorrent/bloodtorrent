package org.bloodtorrent.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import lombok.Setter;
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
@Path("/admin")
@Produces(MediaType.TEXT_HTML)
public class AdminResource {

    private final SessionManager sessionManager;

    @Setter
    private MainResource mainResource;

    protected AdminResource(SessionManager httpSessionManager) {
        sessionManager = httpSessionManager;
    }

    @GET
    @UnitOfWork
    public CommonView forwardAdminPage(@CookieParam("JSESSIONID") String sessionID) {
        HttpSession session = getSession(sessionID);
        User user = (User) session.getAttribute("user");
        CommonView commonView = mainResource.forwardMainPage(sessionID);
        if(session != null && user != null) {
            user = (User) session.getAttribute("user");
            commonView.setUser(user);
            return (user.getIsAdmin()=='Y' ? new CommonView("/ftl/admin.ftl", user) : commonView);
        }
        return commonView;
    }

    public HttpSession getSession(String sessionID) {
        return sessionManager.getHttpSession(sessionID);
    }
}