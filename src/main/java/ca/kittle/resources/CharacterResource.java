package ca.kittle.resources;

import ca.kittle.services.CharacterService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/character")
public class CharacterResource {

    private static final Logger logger = LoggerFactory.getLogger(CharacterResource.class);

    private final CharacterService character = new CharacterService();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCharacter(@PathParam("id") final String id) {
        logger.info("Getting character {}", id);
        return Response.ok(character.getCharacter(id)).build();
    }
}
