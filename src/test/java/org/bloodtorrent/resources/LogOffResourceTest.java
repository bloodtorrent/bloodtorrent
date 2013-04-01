package org.bloodtorrent.resources;

import org.bloodtorrent.repository.CatchPhraseRepository;
import org.bloodtorrent.repository.SuccessStoryRepository;
import org.bloodtorrent.view.CommonView;
import org.eclipse.jetty.server.SessionManager;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/22/13
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogOffResourceTest {

    private String SESSION_ID = "SESSION_ID";
    private SessionManager sessionManager = mock(SessionManager.class);
    private HttpSession httpSession = mock(HttpSession.class);
    private SuccessStoryRepository successStoryRepository = mock(SuccessStoryRepository.class);
    private CatchPhraseRepository catchPhraseRepository = mock(CatchPhraseRepository.class);
    private MainResource mainResource;
    private LogOffResource logoffResource;
    @Before
    public void init() {
        when(sessionManager.getHttpSession(SESSION_ID)).thenReturn(httpSession);
        mainResource = new MainResource(sessionManager);
        mainResource.setSuccessStoryResource(new SuccessStoryResource(successStoryRepository));
        mainResource.setCatchPhraseResource(new CatchPhraseResource(catchPhraseRepository));
        logoffResource = new LogOffResource(sessionManager);
        logoffResource.setMainResource(mainResource);
    }

    @Test
    public void shouldHaveNoSession() {
        logoffResource.forwardMainPage(SESSION_ID);
        verify(httpSession).removeAttribute("user");
    }

    @Test
    public void shouldReturnMainView() {
        CommonView adminView = logoffResource.forwardMainPage(SESSION_ID);
        assertThat(adminView.getTemplateName(), is("/ftl/main.ftl"));
    }
}
