package org.bloodtorrent.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import lombok.Setter;
import org.bloodtorrent.IllegalDataException;
import org.bloodtorrent.ResourceManager;
import org.bloodtorrent.dto.SuccessStory;
import org.bloodtorrent.dto.User;
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
import java.util.List;

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

    @Setter
    private SuccessStoryResource successStoryResource;

    private User user;

    public MainResource(SessionManager httpSessionManager) {
        sessionManager = httpSessionManager;
    }

    @GET
    @UnitOfWork
    public CommonView forwardMainPage(@CookieParam("JSESSIONID") String sessionID) {
        HttpSession session = getSession(sessionID);
        List<SuccessStory> successStories = getSuccessStories();
        if(session != null && session.getAttribute("user") !=  null){
            user = (User) session.getAttribute("user");
            return new MainView(user, successStories);
        }
        return new MainView(successStories);
    }

    private List<SuccessStory> getSuccessStories() {
        if(successStoryResource == null)
            gatherSuccessStoryResource();
        try {
            return successStoryResource.getSuccessStoriesBriefly();
        } catch (IllegalDataException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private void gatherSuccessStoryResource() {
        successStoryResource = ResourceManager.getInstance().find(SuccessStoryResource.class).get();
    }

    public HttpSession getSession(String sessionID) {
        try{
            return sessionManager.getHttpSession(sessionID);
        }catch(Exception e){
            return null;
        }
    }
}