package ca.kittle.resources;

import ca.kittle.models.app.Result;
import ca.kittle.repositories.EncounterRepository;
import ca.kittle.repositories.NotFoundException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/encounters")
public class EncountersResource {

    private static final Logger logger = LoggerFactory.getLogger(EncountersResource.class);

    private final EncounterRepository encounters = new EncounterRepository();

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encounters() {
        logger.debug("Getting encounters");
        return Response.ok(encounters.encounters()).build();
    }

}
