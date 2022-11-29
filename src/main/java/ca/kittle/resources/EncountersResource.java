package ca.kittle.resources;

import ca.kittle.repositories.EncounterRepository;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/encounters")
public class EncounterResource {

    private static final Logger logger = LoggerFactory.getLogger(EncounterResource.class);

    private final EncounterRepository encounters = new EncounterRepository();

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encounters() {
        logger.debug("Getting active campaigns");
        return Response.ok(encounters.encounters()).build();
    }

}
