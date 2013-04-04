package org.bloodtorrent.bootstrap;

import com.yammer.dropwizard.ConfiguredBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.ConfigurationStrategy;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;
import org.reflections.Reflections;

import javax.persistence.Entity;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/20/13
 * Time: 10:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleHibernateBundle implements ConfiguredBundle<SimpleConfiguration>, ConfigurationStrategy<SimpleConfiguration>{
    private HibernateBundle delegateBundle;
    private Class<?>[] entityClasses;

    public SimpleHibernateBundle(String basePackage) {
        entityClasses = gatherEntitiesWithinPackage(basePackage);
        delegateBundle = new HibernateBundle<SimpleConfiguration>(entityClasses){
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(SimpleConfiguration configuration) {
                return configuration.getDatabaseConfiguration();
            }
        };
    }

    private Class<?>[] gatherEntitiesWithinPackage(String basePackage) {
        Reflections reflections = new Reflections(basePackage);
        Set<Class<? extends Object>> allClasses = reflections.getTypesAnnotatedWith(Entity.class);
        return allClasses.toArray(new Class<?>[allClasses.size()]);
    }

    @Override
    public void run(SimpleConfiguration simpleConfiguration, Environment environment) throws Exception {
        delegateBundle.run(simpleConfiguration, environment);
    }

    public Class<?>[] getEntityClasses() {
        return entityClasses;
    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
       delegateBundle.initialize(bootstrap);
    }

    @Override
    public DatabaseConfiguration getDatabaseConfiguration(SimpleConfiguration simpleConfiguration) {
        return delegateBundle.getDatabaseConfiguration(simpleConfiguration);
    }

    public SessionFactory getSessionFactory() {
        return delegateBundle.getSessionFactory();
    }

}
