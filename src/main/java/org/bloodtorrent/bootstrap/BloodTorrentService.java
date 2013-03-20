package org.bloodtorrent.bootstrap;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.ViewBundle;
import org.bloodtorrent.repository.BloodRequestRepository;
import org.bloodtorrent.repository.PersonRepository;
import org.bloodtorrent.repository.UsersRepository;
import org.bloodtorrent.resources.BloodRequestResource;
import org.bloodtorrent.resources.MainResource;
import org.bloodtorrent.resources.PersonResource;
import org.bloodtorrent.resources.UsersResource;
import org.hibernate.SessionFactory;

public class BloodTorrentService extends Service<SimpleConfiguration> {
    private SimpleHibernateBundle hibernateBundle = new SimpleHibernateBundle("org.bloodtorrent");

    public static void main(String[] args) throws Exception {
        new BloodTorrentService().run(args);
    }

    public void setHibernateBundle(SimpleHibernateBundle hibernateBundle) {
        this.hibernateBundle = hibernateBundle;
    }

    @Override
    public void initialize(Bootstrap<SimpleConfiguration> bootstrap) {
        bootstrap.setName("bloodtorrent");
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(SimpleConfiguration config,
                    Environment environment) throws ClassNotFoundException {
        SessionFactory sessionFactory = hibernateBundle.getSessionFactory();
        final PersonRepository repository = new PersonRepository(sessionFactory);
        final UsersRepository userRepository = new UsersRepository(sessionFactory);
        final BloodRequestRepository bloodReqRepository = new BloodRequestRepository(sessionFactory);
        environment.addResource(new MainResource());
        environment.addResource(new PersonResource(repository));
        environment.addResource(new UsersResource(userRepository));
        environment.addResource(new BloodRequestResource(bloodReqRepository));
    }
}