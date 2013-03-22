package org.bloodtorrent.view;

import org.bloodtorrent.dto.Location;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 22
 * Time: 오전 9:21
 * To change this template use File | Settings | File Templates.
 */
public class LocationViewTest {
    @Test
    public void testGetLocation() throws Exception {
        Location location = Mockito.mock(Location.class);
        LocationView view = new LocationView(location);
        assertNotNull(view.getLocation());
        assertNotNull(view.getTemplateName());
    }
}
