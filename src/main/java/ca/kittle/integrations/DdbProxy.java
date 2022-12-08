package ca.kittle.integrations;

import ca.kittle.models.integrations.*;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class DdbProxy {

    private static final Logger logger = LoggerFactory.getLogger(DdbProxy.class);

    private static final String USER_AGENT =
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 13_0_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36";

    private static final String DDB_DOMAIN = "https://character-service.dndbeyond.com/character/v5";
    private static final String DDB_AUTH_SERVICE = "https://auth-service.dndbeyond.com/v1/cobalt-token";
    private static final String DDB_CAMPAIGN_SERVICE = "https://www.dndbeyond.com/api/campaign/active-campaigns";

    private static final String DDB_ENCOUNTER_SERVICE = "https://encounter-service.dndbeyond.com/v1/encounters";

    private static final String DDB_TOKEN_NAME = "COBALT_TOKEN";
    private String authToken = "";

    private RestClient characterClient;

    private boolean connect(String restEndpoint) {
        logger.info("Connecting to D&D Beyond");
        characterClient = new RestClient(restEndpoint);
        return true;
    }

    private void authenticate() {
        if (authToken != null && !authToken.isEmpty())
            return;
        var cobaltToken = Optional.ofNullable(System.getenv(DDB_TOKEN_NAME)).orElseThrow(
                () -> new IllegalArgumentException(DDB_TOKEN_NAME + " is not set in the environment"));
        var authClient = new RestClient(DDB_AUTH_SERVICE);
        var headers = new MultivaluedHashMap<String, Object>() {{
            put("Content-Type", List.of("application/json"));
//            put("Cookie", List.of("CobaltSession=" + cobaltToken));
        }};
        Response response = authClient.post(headers, null, List.of(new Cookie("CobaltSession", cobaltToken)));
        DdbAuthToken authData = response.readEntity(DdbAuthToken.class);
        authToken = authData.token();
        logger.info("Auth response message {}", authData.token());
        authClient.disconnect(response);
    }

    public DdbCharacter getCharacter(String id) {
        logger.debug("Retrieving DDB character {}", id);
        authenticate();
        if (!connect(DDB_DOMAIN + "/character/" + id))
            return null;
        Response response = characterClient.get();
        CharacterResponse characterData = response.readEntity(CharacterResponse.class);
        logger.debug("Response status {}", response.getStatus());
        logger.debug("Response message {}", characterData.message());
        DdbCharacter character = characterData.data();
        logger.debug("Username {}", character.username());
        logger.debug("Str {}", character.strength());
        logger.debug("Dex {}", character.dexterity());
        logger.debug("Con {}", character.constitution());
        logger.debug("Int {}", character.intelligence());
        logger.debug("Wis {}", character.wisdom());
        logger.debug("Cha {}", character.charisma());
        characterClient.disconnect(response);
        return null;
    }
    public DdbEncounter getEncounter(String id) {
        logger.debug("Retrieving DDB encounter {}", id);
        authenticate();
        var encounterClient = new RestClient(DDB_ENCOUNTER_SERVICE + "/" + id);
        var headers = headersWithAuth();
        Response response = encounterClient.get(headers, null);
        DdbEncounterResponse ddbResponse = response.readEntity(DdbEncounterResponse.class);
        logger.debug("Response status {}", response.getStatus());
        DdbEncounter encounter = ddbResponse.data();
        logger.info("Response message {}", encounter);
        encounterClient.disconnect(response);
        return encounter;
    }

    public String getEncounters() {
        logger.debug("Retrieving all DDB encounters");
        authenticate();
        var encounterClient = new RestClient(DDB_ENCOUNTER_SERVICE);
        var headers = headersWithAuth();
        Response response = encounterClient.get(headers, null);
        String ddbResponse = response.readEntity(String.class);
        logger.debug("Response status {}", response.getStatus());
        logger.info("Response message {}", ddbResponse);
        encounterClient.disconnect(response);
        return "";
    }

    public String getActiveCampaigns() {
        var cobaltToken = Optional.ofNullable(System.getenv(DDB_TOKEN_NAME)).orElseThrow(
                () -> new IllegalArgumentException(DDB_TOKEN_NAME + " is not set in the environment"));
        authenticate();

        var campaignClient = new RestClient(DDB_CAMPAIGN_SERVICE);
        var headers = new MultivaluedHashMap<String, Object>() {{
            put("User-Agent", List.of("Combat Proxy for DDB"));
            put("Accept", List.of("*/*"));
            put("Authorization", List.of("Bearer " + authToken));
        }};
//        var cookies = List.of(new Cookie("CobaltSession", cobaltToken), new Cookie("cobalt-token", authToken));

//        Response response = campaignClient.get(headers, cookies);
        Response response = campaignClient.get(headers, null);
        logger.info("Response status {}", response.getStatusInfo());
        String result = response.readEntity(String.class);
        logger.info("Foo " + response.getHeaderString("Location"));
        logger.info("Campaigns: {}", result);
        campaignClient.disconnect(response);
        return result;
    }

    private MultivaluedHashMap<String, Object> headersWithAuth() {
        return new MultivaluedHashMap<String, Object>() {{
            put("User-Agent", List.of(USER_AGENT));
            put("Accept", List.of("*/*"));
            put("Authorization", List.of("Bearer " + authToken));
        }};
    }

}
