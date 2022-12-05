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

    @POST
    @Path("/{encounterId}/creature/{creatureId}/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCreature(@PathParam("encounterId") long encounterId, @PathParam("creatureId") long creatureId, @PathParam("number") int number) {
        logger.debug("Adding creature {} to encounter.", creatureId);
        try {
            encounters.addCreature(encounterId, creatureId, number);
        }
        catch (NotFoundException e) {
            logger.error(e.getMessage(), e);
            return Response.status(404).build();
        }
        return Response.ok().build();
    }
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encounters() {
        logger.debug("Getting active campaigns");
        return Response.ok(encounters.encounters()).build();
    }

}
