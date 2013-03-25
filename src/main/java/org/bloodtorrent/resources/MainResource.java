package org.bloodtorrent.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
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
@Path("/")
@Produces(MediaType.TEXT_HTML)
public class MainResource {

    private final SessionManager sessionManager;
    private User user;

    public MainResource(SessionManager httpSessionManager) {
        sessionManager = httpSessionManager;
    }

    @GET
    @UnitOfWork
    public CommonView forwardMainPage(@CookieParam("JSESSIONID") String sessionID) {
        HttpSession session = getSession(sessionID);
        if(session != null && session.getAttribute("user") !=  null){
            user = (User) session.getAttribute("user");
            return new CommonView("/ftl/main.ftl", user);
        }
        return new CommonView("/ftl/main.ftl");
    }

    public HttpSession getSession(String sessionID) {
        try{
            return sessionManager.getHttpSession(sessionID);
        }catch(Exception e){
            return null;
        }
    }
}