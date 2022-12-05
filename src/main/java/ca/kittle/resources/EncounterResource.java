package ca.kittle.resources;

import ca.kittle.models.app.Encounter;
import ca.kittle.repositories.EncounterRepository;
import ca.kittle.repositories.NotFoundException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/encounter")
public class EncounterResource {

    private static final Logger logger = LoggerFactory.getLogger(EncounterResource.class);

    private final EncounterRepository encounters = new EncounterRepository();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encounter(@PathParam("id") final long id) {
        logger.debug("Retrieve encounter.");
        return Response.ok(encounters.encounter(id)).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEncounter(Encounter encounter) {
        logger.debug("Adding encounter.");
        encounters.createEncounter(encounter);
        return Response.status(Response.Status.CREATED).build();
    }

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

}
