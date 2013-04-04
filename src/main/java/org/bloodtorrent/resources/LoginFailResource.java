package org.bloodtorrent.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bloodtorrent.view.CommonView;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static lombok.AccessLevel.PROTECTED;

@Path("/loginfail")
@Produces(MediaType.TEXT_HTML)
@NoArgsConstructor(access = PROTECTED)
public class LoginFailResource {

    private static final String LOG_IN_FAIL_MESSAGE = "The E-mail or password you entered is incorrect.";

    @Setter
    private MainResource mainResource;

    @GET
    @UnitOfWork
    public CommonView forwardMainPage(@CookieParam("JSESSIONID") String sessionID) {
        CommonView commonView = mainResource.forwardMainPage(sessionID);
        commonView.setMessage(LOG_IN_FAIL_MESSAGE);
        return commonView;
    }
}