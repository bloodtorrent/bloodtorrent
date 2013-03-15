package org.bloodtorrent.bootstrap;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import com.yammer.dropwizard.views.ViewBundle;
import org.bloodtorrent.dto.Person;
import org.bloodtorrent.repository.PersonRepository;
import org.bloodtorrent.resources.PersonResource;

public class BloodTorrentService extends Service<SimpleConfiguration> {
    private HibernateBundle hibernate;
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
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(SimpleConfiguration config,
                    Environment environment) throws ClassNotFoundException {
        final PersonRepository repository = new PersonRepository(hibernate.getSessionFactory());
        environment.addResource(new PersonResource(repository));
    }
}