package org.bloodtorrent.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.repository.UsersRepository;
import org.eclipse.jetty.server.SessionManager;

import javax.servlet.http.HttpSession;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 20
 * Time: 오후 12:06
 * To change this template use File | Settings | File Templates.
 */
@Path("/logindb")
@Produces(MediaType.TEXT_HTML)
public class LoginResource {
    public static final String URI_SUCCESS_STORY_LIST = "/successStory/list";
    public static final String PATH_LOGIN_FAIL = "/loginfail";
    private final SessionManager sessionManager;
    private final UsersRepository usersRepository;

    public LoginResource(SessionManager httpSessionManager, UsersRepository usersRepository) {
        sessionManager = httpSessionManager;
        this.usersRepository = usersRepository;
    }

    @GET
    @UnitOfWork
    public Response forwardMainPage(@CookieParam("JSESSIONID") String sessionID) {
        HttpSession session = getSession(sessionID);
        if(session != null){
            String email = (String) session.getAttribute("email");
            String password = (String) session.getAttribute("password");
            email = email != null ? email.toLowerCase() : null;
            session.removeAttribute("email");
            session.removeAttribute("password");

            User user = usersRepository.get(email);
            if(user != null && password.equals(user.getPassword())) {
                session.setAttribute("user", user);

                return Response.seeOther(URI.create('Y' == user.getIsAdmin() ? URI_SUCCESS_STORY_LIST : "/")).status(302).build();
            }
        }

        return Response.seeOther(URI.create(PATH_LOGIN_FAIL)).status(302).build();
    }

    public HttpSession getSession(String sessionID) {
        return sessionManager.getHttpSession(sessionID);
    }
}