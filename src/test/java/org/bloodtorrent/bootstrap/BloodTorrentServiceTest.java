package org.bloodtorrent.bootstrap;

import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
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
        service = new BloodTorrentService();
        bootstrap = mock(Bootstrap.class);
        config = mock(SimpleConfiguration.class);
        environment = mock(Environment.class);
    }

    @Test
    public void testInitialize() throws Exception {
        service.initialize(bootstrap);
    }

    @Test
    public void testRun() throws Exception {
        SimpleHibernateBundle mock = mock(SimpleHibernateBundle.class);
        SessionFactory sessionFactory = mock(SessionFactory.class);
        when(mock.getSessionFactory()).thenReturn(sessionFactory);
        service.setHibernateBundle(mock);
        service.run(config, environment);
    }
}
