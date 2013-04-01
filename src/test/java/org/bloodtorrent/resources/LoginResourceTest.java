package org.bloodtorrent.resources;

import org.bloodtorrent.ResourceManager;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.repository.SuccessStoryRepository;
import org.bloodtorrent.repository.UsersRepository;
import org.eclipse.jetty.server.SessionManager;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/22/13
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginResourceTest {

    private final String ADMIN_EMAIL = "administrator@bloodtorrent.org";
    private final String ADMIN_PASSWORD = "p@ssw0rd";
    private final String NONADMIN_EMAIL = "nobody@bloodtorrent.org";
    private final String NONADMIN_PASSWORD = "1234";
    private String SESSION_ID = "SESSION_ID";
    private SessionManager sessionManager = mock(SessionManager.class);
    private HttpSession httpSession = mock(HttpSession.class);
    private UsersRepository usersRepository = mock(UsersRepository.class);
    private SuccessStoryRepository successStoryRepository = mock(SuccessStoryRepository.class);

    @Before
    public void init() {
        User adminUser = new User();
        adminUser.setId(ADMIN_EMAIL);
        adminUser.setPassword(ADMIN_PASSWORD);
        adminUser.setIsAdmin('Y');
        User nonAdminUser = new User();
        nonAdminUser.setId("nobody@bloodtorrent.org");
        nonAdminUser.setPassword("1234");
        nonAdminUser.setIsAdmin('N');

        when(sessionManager.getHttpSession(SESSION_ID)).thenReturn(httpSession);
        when(usersRepository.get(ADMIN_EMAIL)).thenReturn(adminUser);
        when(usersRepository.get(NONADMIN_EMAIL)).thenReturn(nonAdminUser);
    }

    @Test
    public void shouldReturnRefreshingHtmlForAdmin() {
        when(httpSession.getAttribute("email")).thenReturn(ADMIN_EMAIL);
        when(httpSession.getAttribute("password")).thenReturn(ADMIN_PASSWORD);
        LoginResource loginResource = new LoginResource(sessionManager, usersRepository);
//        String html = loginResource.forwardMainPage(SESSION_ID);
//        assertThat(html, is("<html><meta http-equiv=\"refresh\" content=\"0;url=/admin\" /></html>"));
    }
    @Test
    public void shouldReturnRefreshingHtmlForNonAdmin() {
        when(httpSession.getAttribute("email")).thenReturn(NONADMIN_EMAIL);
        when(httpSession.getAttribute("password")).thenReturn(NONADMIN_PASSWORD);
        LoginResource loginResource = new LoginResource(sessionManager, usersRepository);
        Response response = loginResource.forwardMainPage(SESSION_ID);
        assertThat(response.getStatus(), is(302));
    }
    @Test
    public void shouldReturnRefreshingHtmlForLoginFail() {
        LoginResource loginResource = new LoginResource(sessionManager, usersRepository);
        Response response = loginResource.forwardMainPage(SESSION_ID);
        assertThat(response.getStatus(), is(302));
    }
}
