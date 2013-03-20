package org.bloodtorrent.bootstrap;

import org.bloodtorrent.dto.BloodRequest;
import org.bloodtorrent.dto.Person;
import org.bloodtorrent.dto.User;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;


/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/20/13
 * Time: 10:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleHibernateBundleTest {
    SimpleHibernateBundle bundle;

    @Test
    public void shouldGatherEntitiesWithinPackage(){
        bundle = new SimpleHibernateBundle("org.bloodtorrent");
        assertTrue(Arrays.asList(bundle.getEntityClasses()).contains(User.class));
        assertTrue(Arrays.asList(bundle.getEntityClasses()).contains(BloodRequest.class));
        assertTrue(Arrays.asList(bundle.getEntityClasses()).contains(Person.class));
    }
}
