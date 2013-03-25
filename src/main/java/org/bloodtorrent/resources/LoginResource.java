package org.bloodtorrent.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import org.bloodtorrent.ResourceManager;
import org.bloodtorrent.dto.SuccessStory;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.repository.UsersRepository;
import org.bloodtorrent.view.CommonView;
import org.bloodtorrent.view.MainView;
import org.eclipse.jetty.server.SessionManager;

import javax.servlet.http.HttpSession;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;

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
    private final SessionManager sessionManager;
    private final UsersRepository usersRepository;

    public LoginResource(SessionManager httpSessionManager, UsersRepository usersRepository) {
        sessionManager = httpSessionManager;
        this.usersRepository = usersRepository;
    }

    @GET
    @UnitOfWork
    public String forwardMainPage(@CookieParam("JSESSIONID") String sessionID) {
        HttpSession session = getSession(sessionID);
        if(session != null){
            String email = (String) session.getAttribute("email");
            String password = (String) session.getAttribute("password");
            session.removeAttribute("email");
            session.removeAttribute("password");

            User user = usersRepository.get(email);
            if(user != null && password.equals(user.getPassword())) {
                session.setAttribute("user", user);

                return (user.getIsAdmin()=='Y'? "<html><meta http-equiv=\"refresh\" content=\"0;url=/admin\" /></html>"
                        : "<html><meta http-equiv=\"refresh\" content=\"0;url=/\" /></html>");
            }
        }

        return "<html><meta http-equiv=\"refresh\" content=\"0;url=/loginfail\" /></html>";
    }

    public HttpSession getSession(String sessionID) {
        return sessionManager.getHttpSession(sessionID);
    }
}