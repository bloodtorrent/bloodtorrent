package org.bloodtorrent.resources;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 22
 * Time: 오전 9:18
 * To change this template use File | Settings | File Templates.
 */
public class LocationResourceTest {
    @Test
    public void testGetLocation() throws Exception {
        LocationResource locationResource = new LocationResource();
        assertNotNull(locationResource.getLocation("address", "city", "state"));
    }
}
