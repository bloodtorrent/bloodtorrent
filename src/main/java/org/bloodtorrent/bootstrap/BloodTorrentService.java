package org.bloodtorrent.bootstrap;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.ViewBundle;
import org.bloodtorrent.resources.ResourceCreator;
import org.bloodtorrent.servlet.LoginServlet;
import org.eclipse.jetty.server.session.SessionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class BloodTorrentService extends Service<SimpleConfiguration> {
    private static Logger logger = LoggerFactory.getLogger(BloodTorrentService.class);

    private final SimpleHibernateBundle hibernateBundle;
    private final SessionHandler httpSessionHandler;
    private final BloodTorrentCustom404 custom404;
    private final ResourceCreator resourceCreator;

    public BloodTorrentService(SimpleHibernateBundle hibernateBundle, SessionHandler httpSessionHandler, BloodTorrentCustom404 custom404, ResourceCreator resourceCreator) {
        this.hibernateBundle = hibernateBundle;
        this.httpSessionHandler = httpSessionHandler;
        this.custom404 = custom404;
        this.resourceCreator = resourceCreator;
    }

    public static void main(String[] args) {
	  try {
        new BloodTorrentService(new SimpleHibernateBundle("org.bloodtorrent"), new SessionHandler(), new BloodTorrentCustom404(), new ResourceCreator()).run(args);
	  }
	  catch(Exception ex) {
	    logger.error("Running BloodTorrent Service failed with exception.", ex);
	  }
    }


    @Override
    public void initialize(Bootstrap<SimpleConfiguration> bootstrap) {
        bootstrap.setName("bloodtorrent");
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle("/images"));
        bootstrap.addBundle(new AssetsBundle("/css"));
        bootstrap.addBundle(new AssetsBundle("/js"));
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(SimpleConfiguration configuration, Environment environment) {
        environment.addProvider(custom404);
        environment.setSessionHandler(httpSessionHandler);
        environment.addServlet(new LoginServlet(), LoginServlet.URI_PATH);
        createResourcesAndAddToEnvironment(configuration, environment);
    }

    private void createResourcesAndAddToEnvironment(SimpleConfiguration configuration, Environment environment) {
        Set<Object> resources = resourceCreator.createResources(hibernateBundle.getSessionFactory(), configuration, httpSessionHandler.getSessionManager());
        for (Object resource : resources) {
            environment.addResource(resource);
        }
    }
}