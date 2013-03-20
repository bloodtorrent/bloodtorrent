package org.bloodtorrent.bootstrap;

import com.google.common.collect.Iterables;
import org.bloodtorrent.dto.BloodReq;
import org.bloodtorrent.dto.Person;
import org.bloodtorrent.dto.User;
import org.hamcrest.collection.IsArrayContaining;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matcher.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
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
        assertTrue(Arrays.asList(bundle.getEntityClasses()).contains(BloodReq.class));
        assertTrue(Arrays.asList(bundle.getEntityClasses()).contains(Person.class));
    }
}
