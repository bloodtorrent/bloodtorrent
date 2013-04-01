package org.bloodtorrent.bootstrap;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.ViewBundle;
import org.bloodtorrent.repository.BloodRequestRepository;
import org.bloodtorrent.repository.CatchPhraseRepository;
import org.bloodtorrent.repository.SuccessStoryRepository;
import org.bloodtorrent.repository.UsersRepository;
import org.bloodtorrent.resources.*;
import org.bloodtorrent.servlet.LoginServlet;
import org.eclipse.jetty.server.SessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.hibernate.SessionFactory;

public class BloodTorrentService extends Service<SimpleConfiguration> {
    private SimpleHibernateBundle hibernateBundle = new SimpleHibernateBundle("org.bloodtorrent");

    public static void main(String[] args) {
	  try {
        new BloodTorrentService().run(args);
	  }
	  catch(Exception ex) {
	    System.out.println("Running BloodTorrent Service failed with exception.");
	    ex.printStackTrace();
	  }
    }

    public void setHibernateBundle(SimpleHibernateBundle hibernateBundle) {
        this.hibernateBundle = hibernateBundle;
    }

    @Override
    public void initialize(Bootstrap<SimpleConfiguration> bootstrap) {
        bootstrap.setName("bloodtorrent");
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle("/images"));
        bootstrap.addBundle(new AssetsBundle("/css"));
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(SimpleConfiguration config,
                    Environment environment) throws ClassNotFoundException {

        SessionHandler httpSessionHandler = new SessionHandler();
        SessionManager httpsSessionManager = httpSessionHandler.getSessionManager();
        environment.setSessionHandler(httpSessionHandler);
        environment.addServlet(new LoginServlet(), "/login");

        SessionFactory sessionFactory = hibernateBundle.getSessionFactory();
        final UsersRepository userRepository = new UsersRepository(sessionFactory);
        final BloodRequestRepository bloodReqRepository = new BloodRequestRepository(sessionFactory);
        final SuccessStoryRepository successStoryRepository = new SuccessStoryRepository(sessionFactory);
        final CatchPhraseRepository catchPhraseRepository = new CatchPhraseRepository(sessionFactory);

        NotifyDonorSendEmailResource mailResource = new NotifyDonorSendEmailResource();
        mailResource.setMailConfiguration(config.getMailConfiguration());

        MainResource mainResource = new MainResource(httpsSessionManager);
        SuccessStoryResource successStoryResource = new SuccessStoryResource(httpsSessionManager, successStoryRepository);
        mainResource.setSuccessStoryResource(successStoryResource);
        CatchPhraseResource catchPhraseResource = new CatchPhraseResource(catchPhraseRepository);
        mainResource.setCatchPhraseResource(catchPhraseResource);

        AdminResource adminResource = new AdminResource(httpsSessionManager);
        adminResource.setMainResource(mainResource);

        LogOffResource logOffResource = new LogOffResource(httpsSessionManager);
        logOffResource.setMainResource(mainResource);

        FindingMatchingDonorResource findingMatchingDonorResource = new FindingMatchingDonorResource(userRepository);

        BloodRequestResource bloodRequestResource = new BloodRequestResource(bloodReqRepository, mailResource);
        bloodRequestResource.setFindingMatchingDonorResource(findingMatchingDonorResource);

        LoginFailResource loginFailResource = new LoginFailResource();
        loginFailResource.setMainResource(mainResource);

        addResource(environment, mainResource);
        addResource(environment, adminResource) ;
        addResource(environment, logOffResource);
        addResource(environment, new UsersResource(userRepository));
        addResource(environment, bloodRequestResource);
        addResource(environment, successStoryResource);
        addResource(environment, catchPhraseResource);
        addResource(environment, new LoginResource(httpsSessionManager, userRepository));
        addResource(environment, loginFailResource);
        addResource(environment, findingMatchingDonorResource);
        addResource(environment, new TestPageSendEmailResource());
        addResource(environment, new TestSendEmailResource());
        addResource(environment, mailResource);
    }

    private void addResource(Environment environment, Object resource) {
        environment.addResource(resource);
    }
}