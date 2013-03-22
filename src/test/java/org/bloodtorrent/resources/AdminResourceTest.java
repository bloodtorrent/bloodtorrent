package org.bloodtorrent.resources;

import org.bloodtorrent.dto.User;
import org.bloodtorrent.view.CommonView;
import org.eclipse.jetty.server.SessionManager;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/22/13
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdminResourceTest {

    private String ADMIN_ID = "Administrator";
    private String NON_ADMIN_ID = "someone@bloodtorrent.org";
    private String ADMIN_SESSION_ID = "ADMIN_SESSION_ID";
    private String NON_ADMIN_SESSION_ID = "NON_ADMIN_SESSION_ID";
    private String NULL_SESSION_ID = "NULL_SESSION_ID";
    private SessionManager sessionManager = mock(SessionManager.class);
    private HttpSession adminHttpSession = mock(HttpSession.class);
    private HttpSession nonAdminHttpSession = mock(HttpSession.class);
    private HttpSession nullHttpSession = mock(HttpSession.class);

    @Before
    public void init() {
        User adminUser = new User();
        adminUser.setId(ADMIN_ID);
        User nonAdminUser = new User();
        nonAdminUser.setId(NON_ADMIN_ID);
        when(sessionManager.getHttpSession(ADMIN_SESSION_ID)).thenReturn(adminHttpSession);
        when(sessionManager.getHttpSession(NON_ADMIN_SESSION_ID)).thenReturn(nonAdminHttpSession);
        when(sessionManager.getHttpSession(NULL_SESSION_ID)).thenReturn(nullHttpSession);
        when(adminHttpSession.getAttribute("adminCheck")).thenReturn(ADMIN_ID);
        when(adminHttpSession.getAttribute("user")).thenReturn(adminUser);
        when(nonAdminHttpSession.getAttribute("adminCheck")).thenReturn(null);
        when(nonAdminHttpSession.getAttribute("user")).thenReturn(nonAdminUser);
        when(nullHttpSession.getAttribute("adminCheck")).thenReturn(null);
        when(nullHttpSession.getAttribute("user")).thenReturn(null);
    }

    @Test
    public void shouldReturnAdminViewWhenAdminIsLoggedIn() {
        AdminResource adminResource = new AdminResource(sessionManager);
        CommonView adminView = adminResource.forwardAdminPage(ADMIN_SESSION_ID);
        assertThat(adminView.getTemplateName(), is("/ftl/admin.ftl"));
        assertThat(adminView.getUser().getId(), is(ADMIN_ID));
    }

    @Test
    public void shouldReturnMainViewWhenAdminIsNotLoggedIn() {
        AdminResource adminResource = new AdminResource(sessionManager);
        CommonView adminView = adminResource.forwardAdminPage(NON_ADMIN_SESSION_ID);
        assertThat(adminView.getTemplateName(), is("/ftl/main.ftl"));
        assertNotEquals(ADMIN_ID, adminView.getUser().getId());
    }

    @Test
    public void shouldReturnMainViewWhenNoUserLoggedIn() {
        AdminResource adminResource = new AdminResource(sessionManager);
        CommonView adminView = adminResource.forwardAdminPage(NULL_SESSION_ID);
        assertThat(adminView.getTemplateName(), is("/ftl/main.ftl"));
        assertNull(adminView.getUser());
    }
}
