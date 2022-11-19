package ca.kittle.resources;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import ca.kittle.integrations.RestClient;
import ca.kittle.models.Health;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.HashMap;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "HealthProvider")
public class HealthConsumerPactTest {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static String okJson = "";


    @BeforeAll
    static void createOkString() {
        try {
            okJson = mapper.writeValueAsString(Health.OK);
        } catch (JsonProcessingException ignored) {
        }
    }

    @Test
    void testConsumer(MockServer mockServer) throws IOException {
//        RestClient client =
        jakarta.ws.rs.core.Response response = new RestClient(mockServer.getUrl() + "/api/health").get();
//        var response = ClientBuilder.newClient().target(mockServer.getUrl() + "/api/health").request().get();
//        assert (response.getStatusInfo() == 200);
//        System.out.println("response " + response.readEntity(String.class));
        var health = response.readEntity(Health.class);
        assert ("OK".equals(health.code()));
        response.close();
    }

    @Pact(provider = "HealthProvider", consumer = "HealthConsumer")
    public RequestResponsePact createHealthPact(PactDslWithProvider builder) {
        return builder
                .given("health test state")
                .uponReceiving("HealthPathTest test interaction")
                .path("/api/health")
                .method("GET")
                .willRespondWith()
                .headers(new HashMap<String, String>() {{
                    put("Content-Type", "application/json");
                }})
                .status(200)
                .body(okJson)
                .toPact();
    }

}
