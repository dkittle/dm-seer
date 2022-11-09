package ca.kittle;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebListener extends AbstractHandler {
    private static Logger logger = LoggerFactory.getLogger(WebListener.class);

    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        ResponseContext context = ResponseContext.parseFromString(request.getPathInfo());

        int responseCode = switch (context.noun.toLowerCase()) {
            case "health" -> {
                logger.info("health check");
                yield HttpServletResponse.SC_OK;
            }
            case "character" -> {
//                retrieveCharacter(id);
                logger.info("Noun: " + context.noun + ", id:" + context.id);
                response.getWriter().println("Noun: " + context.noun + ", id:" + context.id);
                yield HttpServletResponse.SC_OK;
            }

            default -> {
                yield HttpServletResponse.SC_OK;
            }
        };

        response.setStatus(responseCode);
        baseRequest.setHandled(true);
    }

    public record ResponseContext(String noun, String id) {
        public ResponseContext {
            Objects.requireNonNull(noun);
        }

        public ResponseContext(String noun) {
            this(noun, "");
        }

        static ResponseContext parseFromString(String pathInfo) {
            logger.trace("Parsing request path '{}'", pathInfo);
            Pattern p = Pattern.compile("^/*([A-Za-z0-9.\\-]*)/*([A-Za-z0-9.\\-]*)");
            Matcher m = p.matcher(pathInfo);
            if (m.matches()) {
                if (m.groupCount() == 2) {
                    return new ResponseContext(m.group(1), m.group(2));
                } else {
                    return new ResponseContext((m.group(1)));
                }
            }
            return new ResponseContext("", "");
        }
    }

}

