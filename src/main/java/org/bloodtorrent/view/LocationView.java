package org.bloodtorrent.view;

import com.yammer.dropwizard.views.View;
import org.bloodtorrent.dto.Location;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 21
 * Time: 오후 5:28
 * To change this template use File | Settings | File Templates.
 */
public class LocationView extends CommonView {
    private final Location location;

    public LocationView(Location location) {
        super("/ftl/location.ftl");
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
