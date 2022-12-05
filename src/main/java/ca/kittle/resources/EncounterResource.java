package ca.kittle.resources;

import ca.kittle.models.app.Encounter;
import ca.kittle.repositories.EncounterRepository;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/encounter")
public class EncounterResource {

    private static final Logger logger = LoggerFactory.getLogger(EncounterResource.class);

    private final EncounterRepository encounters = new EncounterRepository();

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEncounter(Encounter encounter) {
        logger.debug("Adding encounter.");
//        encounters.addEncounter(encounter);
        return Response.status(Response.Status.CREATED).build();
    }

}
