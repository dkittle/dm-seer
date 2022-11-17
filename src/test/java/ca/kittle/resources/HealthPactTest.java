package ca.kittle.resources;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "HealthProvider")
public class HealthPactTest {

    @Test
    void test(MockServer mockServer) throws IOException {
        var response = ClientBuilder.newClient().target(mockServer.getUrl() + "/api/health").request().get();
        assert (response.getStatus() == 200);
        response.close();
    }

    @Pact(provider="HealthProvider", consumer="test_consumer")
    public RequestResponsePact createHealthPact(PactDslWithProvider builder) {
        return builder
                .given("health test state")
                .uponReceiving("HealthPathTest test interaction")
                .path("/api/health")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("{\"code\":\"OK\",\"message\":\"all okay\"}")
                .toPact();
    }

}
