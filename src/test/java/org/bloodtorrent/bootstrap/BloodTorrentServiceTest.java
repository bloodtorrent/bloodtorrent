package org.bloodtorrent.bootstrap;

import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/20/13
 * Time: 10:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class BloodTorrentServiceTest {
    BloodTorrentService service;
    Bootstrap<SimpleConfiguration> bootstrap;
    SimpleConfiguration config;
    Environment environment;

    @Before
    public void before() throws IOException {
        service = mock(BloodTorrentService.class);
        bootstrap = mock(Bootstrap.class);
        config = mock(SimpleConfiguration.class);
        environment = mock(Environment.class);
    }

    @Ignore
    public void thisDoesNotTestInitialize() throws Exception {
        verify(service).initialize(bootstrap);
    }

    @Ignore
    public void thisDoesNotTestRun() throws Exception {
        mockPersistenceLayer(service);
        verify(service).run(config, environment);
    }

    private void mockPersistenceLayer(BloodTorrentService bloodTorrentService) {
        SimpleHibernateBundle mock = mock(SimpleHibernateBundle.class);
        SessionFactory sessionFactory = mock(SessionFactory.class);
        when(mock.getSessionFactory()).thenReturn(sessionFactory);
        bloodTorrentService.setHibernateBundle(mock);
    }

    @Test
    public void shouldAddCustom404ToEnvironment() throws ClassNotFoundException {
        BloodTorrentCustom404 custom404 = new BloodTorrentCustom404();
        BloodTorrentService bloodTorrentService = new BloodTorrentService(custom404);
        mockPersistenceLayer(bloodTorrentService);

        bloodTorrentService.run(config, environment);

        verify(environment).addProvider(custom404);
    }
}
