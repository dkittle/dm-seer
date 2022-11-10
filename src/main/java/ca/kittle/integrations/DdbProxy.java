package ca.kittle.integrations;

import ca.kittle.models.CharacterResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.kittle.models.DdbCharacter;

public class DdbProxy {

    private static Logger logger = LoggerFactory.getLogger(DdbProxy.class);

    private static final String DDB_DOMAIN = "https://character-service.dndbeyond.com/character/v5";
    private static final String DDB_TOKEN_NAME = "COBALT_TOKEN";
    private static String AUTH_TOKEN = "";

    RestClient client;

    private boolean connect(String restEndpoint) {
        logger.info("Connecting to D&D Beyond");
        AUTH_TOKEN = System.getenv("COUNTER_BOT_TOKEN");
        if(AUTH_TOKEN == null || AUTH_TOKEN.isEmpty())
            logger.warn("No value for cobalt token in environment.");

        client = new RestClient(restEndpoint);
        return true;
    }

    public DdbCharacter getCharacter(String id) {
        logger.info("Preparing to retrieve character {}", id);
        if (connect(DDB_DOMAIN + "/character/" + id) == false)
            return null;
        Response response = client.connect();
        logger.info("Getting character {}", id);
//        List<Character> characters = response.readEntity(new GenericType<List<Character>>() {});
//        response.getEntity();
//        logger.info("Characters {}", characters.toString());
        logger.info("Response status {}", response.getStatusInfo());
//        String jsonResponse = response.readEntity(String.class);
        CharacterResponse characterData = response.readEntity(CharacterResponse.class);
        logger.info("Response message {}", characterData.message());
        final ObjectMapper mapper = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT);
//        try {
//            var deserialized = mapper.readValue(jsonResponse, CharacterResponse.class);
            logger.info("Response message {}", characterData.message());
            DdbCharacter character = characterData.data();
            logger.info("Username {}", character.username());
            logger.info("Str {}", character.strength());
            logger.info("Dex {}", character.dexterity());
            logger.info("Con {}", character.constitution());
            logger.info("Int {}", character.intelligence());
            logger.info("Wis {}", character.wisdom());
            logger.info("Cha {}", character.charisma());
//        } catch (JsonProcessingException e) {
//            logger.warn("Cannot parse character response", e);
//        }
        client.disconnect(response);
        return null;
    }
}
