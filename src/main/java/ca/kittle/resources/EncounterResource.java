package ca.kittle.resources;

import ca.kittle.models.app.Encounter;
import ca.kittle.repositories.EncounterRepository;
import ca.kittle.repositories.NotFoundException;
import ca.kittle.services.EncounterService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Path("/encounter")
public class EncounterResource {

    private static final Logger logger = LoggerFactory.getLogger(EncounterResource.class);

    private final EncounterRepository encounters = new EncounterRepository();

    private final EncounterService encounterService = new EncounterService();

    @GET
    @Path("/ddb/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encounter(@PathParam("id") final String id) {
        logger.debug("Retrieve DDB encounter.");
        var encounter = encounterService.encounter(id);
//        if (encounter.isEmpty())
//            return Response.status(Response.Status.NOT_FOUND).entity("Encounter " + id + " not found.").build();
//        return Response.ok(encounter.get()).build();
        return Response.ok(encounter).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encounter(@PathParam("id") final long id) {
        logger.debug("Retrieve encounter.");
        var encounter = encounters.encounter(id);
        if (encounter.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).entity("Encounter " + id + " not found.").build();
        return Response.ok(encounter.get()).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEncounter(Encounter encounter) {
        logger.debug("Creating encounter.");
        if (!encounters.createEncounter(encounter))
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Could not create encounter").build();
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/{encounterId}/creature/{creatureId}/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCreature(@PathParam("encounterId") long encounterId, @PathParam("creatureId") long creatureId, @PathParam("number") int number) {
        if (!encounters.addCreature(encounterId, creatureId, number))
            return Response.status(Response.Status.NOT_FOUND).entity("Could not find encounter or creature").build();
        return Response.ok().build();
    }

}
