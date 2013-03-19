package org.bloodtorrent.bootstrap;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import com.yammer.dropwizard.views.ViewBundle;
import org.bloodtorrent.dto.BloodReq;
import org.bloodtorrent.dto.Person;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.repository.BloodReqRepository;
import org.bloodtorrent.repository.PersonRepository;
import org.bloodtorrent.repository.UsersRepository;
import org.bloodtorrent.resources.BloodReqResource;
import org.bloodtorrent.resources.PersonResource;
import org.bloodtorrent.resources.UsersResource;

public class BloodTorrentService extends Service<SimpleConfiguration> {
    private HibernateBundle hibernate;
    private HibernateBundle userhibernate;
    private HibernateBundle bloodReqHibernate;
    public static void main(String[] args) throws Exception {
        new BloodTorrentService().run(args);
    }

    @Override
    public void initialize(Bootstrap<SimpleConfiguration> bootstrap) {
        bootstrap.setName("bloodtorrent");
        bootstrap.addBundle(new ViewBundle());
        hibernate = new HibernateBundle<SimpleConfiguration>(Person.class) {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(SimpleConfiguration configuration) {
                return configuration.getDatabaseConfiguration();
            }
        };
        userhibernate = new HibernateBundle<SimpleConfiguration>(User.class) {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(SimpleConfiguration configuration) {
                return configuration.getDatabaseConfiguration();
            }
        };
        bloodReqHibernate = new HibernateBundle<SimpleConfiguration>(BloodReq.class) {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(SimpleConfiguration configuration) {
                return configuration.getDatabaseConfiguration();
            }
        };
        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(userhibernate);
        bootstrap.addBundle(bloodReqHibernate);
    }

    @Override
    public void run(SimpleConfiguration config,
                    Environment environment) throws ClassNotFoundException {
        final PersonRepository repository = new PersonRepository(hibernate.getSessionFactory());
        final UsersRepository userRepository = new UsersRepository(userhibernate.getSessionFactory());
        final BloodReqRepository bloodReqRepository = new BloodReqRepository(bloodReqHibernate.getSessionFactory());
        environment.addResource(new PersonResource(repository));
        environment.addResource(new UsersResource(userRepository));
        environment.addResource(new BloodReqResource(bloodReqRepository));
    }
}