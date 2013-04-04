package org.bloodtorrent.bootstrap;

import com.yammer.dropwizard.config.Environment;
import org.bloodtorrent.resources.BloodRequestResource;
import org.bloodtorrent.resources.MainResource;
import org.bloodtorrent.resources.ResourceCreator;
import org.bloodtorrent.servlet.LoginServlet;
import org.eclipse.jetty.server.SessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BloodTorrentServiceTest {
    @Mock
    SimpleConfiguration configuration;
    @Mock
    Environment environment;
    @Mock
    SessionHandler httpSessionHandler;
    @Mock
    SessionManager httpSessionManager;
    @Mock
    ResourceCreator resourceCreator;
    @Mock
    SimpleHibernateBundle hibernateBundle;
    @Mock
    SessionFactory hibernateSessionFactory;

    @Before
    public void setup() {
        when(hibernateBundle.getSessionFactory()).thenReturn(hibernateSessionFactory);
        when(httpSessionHandler.getSessionManager()).thenReturn(httpSessionManager);
        Set<Object> noResources = newHashSet();
        when(resourceCreator.createResources(any(SessionFactory.class), any(SimpleConfiguration.class),
                                             any(SessionManager.class))).thenReturn(noResources);
    }

    @Test
    public void shouldAddCustom404ToEnvironment() {
        BloodTorrentCustom404 custom404 = new BloodTorrentCustom404();
        BloodTorrentService bloodTorrentService = new BloodTorrentService(hibernateBundle, httpSessionHandler, custom404, resourceCreator);

        bloodTorrentService.run(null, environment);

        verify(environment).addProvider(custom404);
    }

    @Test
    public void shouldAddSessionHandlerToEnvironment() {
        BloodTorrentService bloodTorrentService = new BloodTorrentService(hibernateBundle, httpSessionHandler, null, resourceCreator);

        bloodTorrentService.run(null, environment);

        verify(environment).setSessionHandler(httpSessionHandler);
    }

    @Test
    public void shouldAddLoginServletToEnvironment() {
        BloodTorrentService bloodTorrentService = new BloodTorrentService(hibernateBundle, httpSessionHandler, null, resourceCreator);

        bloodTorrentService.run(null, environment);

        verify(environment).addServlet(any(LoginServlet.class), eq(LoginServlet.URI_PATH));
    }

    @Test
    public void shouldCreateResources() {
        BloodTorrentService bloodTorrentService = new BloodTorrentService(hibernateBundle, httpSessionHandler, null, resourceCreator);

        bloodTorrentService.run(configuration, environment);

        verify(resourceCreator).createResources(hibernateSessionFactory, configuration, httpSessionManager);
    }

    @Test
    public void shouldAddAllCreatedResourcesToEnvironment() {
        MainResource mainResource = mock(MainResource.class);
        BloodRequestResource bloodRequestResource = mock(BloodRequestResource.class);
        BloodTorrentService bloodTorrentService = serviceForResources(newHashSet(mainResource, bloodRequestResource));

        bloodTorrentService.run(configuration, environment);

        verify(environment).addResource(mainResource);
        verify(environment).addResource(bloodRequestResource);
    }

    @Test
    public void shouldAddTwoResourcesToEnvironment() {
        HashSet<Object> twoResources = newHashSet(new Object(), new Object());
        BloodTorrentService bloodTorrentService = serviceForResources(twoResources);

        bloodTorrentService.run(configuration, environment);

        verify(environment, times(2)).addResource(anyObject());
    }


    @Test
    public void shouldAddFourResourcesToEnvironment() {
        HashSet<Object> fourResources = newHashSet(new Object(), new Object(), new Object(), new Object());
        BloodTorrentService bloodTorrentService = serviceForResources(fourResources);

        bloodTorrentService.run(configuration, environment);

        verify(environment, times(4)).addResource(anyObject());
    }

    private BloodTorrentService serviceForResources(HashSet<Object> resourceSet) {
        BloodTorrentService bloodTorrentService = new BloodTorrentService(hibernateBundle, httpSessionHandler, null, resourceCreator);
        when(resourceCreator.createResources(any(SessionFactory.class), any(SimpleConfiguration.class), any(SessionManager.class)))
                .thenReturn(resourceSet);
        return bloodTorrentService;
    }
}
