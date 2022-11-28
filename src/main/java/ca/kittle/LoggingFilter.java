package ca.kittle;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingFilter implements ClientRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void filter(ClientRequestContext requestContext) {
        var cookies = requestContext.getCookies();
        for (String key: requestContext.getCookies().keySet())
            logger.debug("Cookie: {}='{}'", key, cookies.get(key));
    }
}
