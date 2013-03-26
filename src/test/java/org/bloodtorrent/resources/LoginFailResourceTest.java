package org.bloodtorrent.resources;

import org.bloodtorrent.ResourceManager;
import org.bloodtorrent.repository.SuccessStoryRepository;
import org.bloodtorrent.view.CommonView;
import org.eclipse.jetty.server.SessionManager;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/26/13
 * Time: 10:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoginFailResourceTest {

    private SuccessStoryRepository successStoryRepository = mock(SuccessStoryRepository.class);
    private SessionManager sessionManager = mock(SessionManager.class);
    private String MESSAGE = "The E-mail or password you entered is incorrect.";

    @Before
    public void init() {
        MainResource mainResource = new MainResource(sessionManager);
        mainResource.setSuccessStoryResource(new SuccessStoryResource(successStoryRepository));
        ResourceManager.getInstance().add(mainResource);
    }

    @Test
    public void shouldReturnMainView() {
        LoginFailResource logOffResource = new LoginFailResource();
        CommonView commonView = logOffResource.forwardMainPage("");
        assertThat(commonView.getMessage(), is(MESSAGE));
    }
}
