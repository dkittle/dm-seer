package ca.kittle.resources;

import ca.kittle.models.app.Campaign;
import ca.kittle.models.app.Location;
import ca.kittle.repositories.LocationRepository;
import ca.kittle.repositories.NotFoundException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/location")
public class LocationResource {
    private static final Logger logger = LoggerFactory.getLogger(LocationResource.class);

    private final LocationRepository locations = new LocationRepository();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response location(@PathParam("id") final long id) {
        logger.debug("Get location");
        Location location = null;
        try {
            location = locations.location(id);
        }
        catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(location).build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response location(@QueryParam("name") final String name) {
        logger.debug("Get location by name");
        Location location = null;
        try {
            location = locations.locationByName(name);
        }
        catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(location).build();
    }
}



