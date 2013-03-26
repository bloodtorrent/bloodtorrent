package org.bloodtorrent.resources;

import org.bloodtorrent.dto.User;
import org.bloodtorrent.repository.CatchPhraseRepository;
import org.bloodtorrent.repository.SuccessStoryRepository;
import org.bloodtorrent.view.CommonView;
import org.eclipse.jetty.server.SessionManager;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/21/13
 * Time: 11:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainResourceTest {

    private String ADMIN_ID = "Administrator";
    private String USER_SESSION_ID = "USER_SESSION_ID";
    private String NULL_SESSION_ID = "NULL_SESSION_ID";
    private SessionManager sessionManager = mock(SessionManager.class);
    private HttpSession userHttpSession = mock(HttpSession.class);
    private HttpSession nullHttpSession = mock(HttpSession.class);
    private SuccessStoryRepository successStoryRepository = mock(SuccessStoryRepository.class);
    private CatchPhraseRepository catchPhraseRepository = mock(CatchPhraseRepository.class);
    private User user = new User();

    @Before
    public void init() {
        when(sessionManager.getHttpSession(USER_SESSION_ID)).thenReturn(userHttpSession);
        when(sessionManager.getHttpSession(NULL_SESSION_ID)).thenReturn(nullHttpSession);
        when(userHttpSession.getAttribute("adminCheck")).thenReturn(ADMIN_ID);
        when(nullHttpSession.getAttribute("adminCheck")).thenReturn(null);
        user.setId(ADMIN_ID);
        when(userHttpSession.getAttribute("user")).thenReturn(user);
        when(nullHttpSession.getAttribute("user")).thenReturn(null);
    }

    private MainResource createMockMainResource() {
        MainResource mainResource = new MainResource(sessionManager);
        mainResource.setSuccessStoryResource(new SuccessStoryResource(successStoryRepository));
        mainResource.setCatchPhraseResource(new CatchPhraseResource(catchPhraseRepository));
        return mainResource;
    }

    @Test
    public void shouldReturnMainView() {
        MainResource mainResource = createMockMainResource();
        assertThat(mainResource.forwardMainPage(USER_SESSION_ID).getTemplateName(), is("/ftl/main.ftl"));
        assertThat(mainResource.forwardMainPage(USER_SESSION_ID).getUser().getId(), is(user.getId()));
    }

    @Test
    public void shouldReturnHttpSession() {
        MainResource mainResource = createMockMainResource();
        assertThat(mainResource.getSession(USER_SESSION_ID), is(userHttpSession));
    }

    @Test
    public void shouldReturnMainViewWithUser() {
        MainResource mainResource = createMockMainResource();
        CommonView commonViewWithUser = mainResource.forwardMainPage(USER_SESSION_ID);
        assertThat(commonViewWithUser.getUser().getId(), is(user.getId()));
    }

    @Test
    public void shouldReturnMainViewWithNoUser() {
        MainResource mainResource = createMockMainResource();
        CommonView commonViewWithUser = mainResource.forwardMainPage(NULL_SESSION_ID);
        assertNull(commonViewWithUser.getUser());
    }
}
