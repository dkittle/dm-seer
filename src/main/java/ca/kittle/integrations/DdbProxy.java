package ca.kittle.integrations;

import ca.kittle.models.integrations.CharacterResponse;
import ca.kittle.models.integrations.DdbAuthToken;
import ca.kittle.models.integrations.DdbCharacter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class DdbProxy {

    private static final Logger logger = LoggerFactory.getLogger(DdbProxy.class);

    private static final String DDB_DOMAIN = "https://character-service.dndbeyond.com/character/v5";
    private static final String DDB_AUTH_SERVICE = "https://auth-service.dndbeyond.com/v1/cobalt-token";
    private static final String DDB_TOKEN_NAME = "COBALT_TOKEN";
    private static String AUTH_TOKEN = "";

    private RestClient characterClient;

    private boolean connect(String restEndpoint) {
        logger.info("Connecting to D&D Beyond");
        AUTH_TOKEN = System.getenv(DDB_TOKEN_NAME);
        if (AUTH_TOKEN == null || AUTH_TOKEN.isEmpty())
            logger.warn("No value for cobalt token in environment.");

        characterClient = new RestClient(restEndpoint);
        return true;
    }

    private void authenticate() {
        var cobaltToken = Optional.ofNullable(System.getenv(DDB_TOKEN_NAME)).orElseThrow(
                () -> new IllegalArgumentException(DDB_TOKEN_NAME + " is not set in the environment"));
        var authClient = new RestClient(DDB_AUTH_SERVICE);
        var headers = new MultivaluedHashMap<String, Object>() {{
            put("Content-Type", List.of("application/json"));
            put("Cookie", List.of("CobaltSession=" + cobaltToken));
        }};
        Response response = authClient.post(headers, null);
        logger.info("Response status {}", response.getStatusInfo());
        DdbAuthToken authData = response.readEntity(DdbAuthToken.class);
        AUTH_TOKEN = authData.token();
        logger.info("Response message {}", authData);
        authClient.disconnect(response);
    }

    public DdbCharacter getCharacter(String id) {
        logger.info("Preparing to retrieve character {}", id);
        authenticate();
        if (!connect(DDB_DOMAIN + "/character/" + id))
            return null;
        Response response = characterClient.get();
        logger.info("Getting character {}", id);
        logger.info("Response status {}", response.getStatusInfo());
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

        characterClient.disconnect(response);
        return null;
    }
}
