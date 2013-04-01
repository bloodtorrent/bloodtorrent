package org.bloodtorrent.resources;

import org.bloodtorrent.repository.CatchPhraseRepository;
import org.bloodtorrent.repository.SuccessStoryRepository;
import org.bloodtorrent.view.CommonView;
import org.eclipse.jetty.server.SessionManager;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/26/13
 * Time: 10:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoginFailResourceTest {

    private SuccessStoryRepository successStoryRepository = mock(SuccessStoryRepository.class);
    private CatchPhraseRepository catchPhraseRepository = mock(CatchPhraseRepository.class);
    private SessionManager sessionManager = mock(SessionManager.class);
    private String MESSAGE = "The E-mail or password you entered is incorrect.";
    private LoginFailResource logOffResource = new LoginFailResource();
    @Before
    public void init() {
        MainResource mainResource = new MainResource(sessionManager);
        mainResource.setSuccessStoryResource(new SuccessStoryResource(successStoryRepository));
        mainResource.setCatchPhraseResource(new CatchPhraseResource(catchPhraseRepository));
        logOffResource.setMainResource(mainResource);
    }

    @Test
    public void shouldReturnMainView() {

        CommonView commonView = logOffResource.forwardMainPage("");
        assertThat(commonView.getMessage(), is(MESSAGE));
    }
}
