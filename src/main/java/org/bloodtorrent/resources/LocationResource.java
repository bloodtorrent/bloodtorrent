package org.bloodtorrent.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import org.bloodtorrent.dto.Location;
import org.bloodtorrent.view.LocationView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/location")
@Produces(MediaType.TEXT_HTML)
public class LocationResource {

    public LocationResource() {
    }

    @GET
    @UnitOfWork
    public LocationView getLocation(@QueryParam("address") String address
            , @QueryParam("city") String city
            , @QueryParam("state") String state ) {
        Location location = new Location(address,  city, state);
        return new LocationView(location);
    }
}