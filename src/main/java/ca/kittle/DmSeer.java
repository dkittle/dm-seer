package ca.kittle;

import ca.kittle.integrations.DdbProxy;
import ca.kittle.integrations.DiscordListener;

import java.util.EnumSet;

import jakarta.ws.rs.SeBootstrap;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DmSeer
{
    private static final Logger logger = LoggerFactory.getLogger(DmSeer.class);

    public static void main(final String[] args) throws Exception {
        logger.info("Starting DM Seer Application");
        var port = determinePortNumber();
        SeBootstrap.Configuration configuration = SeBootstrap.Configuration.builder()
                .host("localhost")
                .port(port)
                .protocol("http")
                .build();
        SeBootstrap.start(RestAPI.class, configuration)
                .thenAccept(instance -> {
                    instance.stopOnShutdown(stopResult -> logger.info("Stopped container {}", stopResult.unwrap(Object.class)));
                    logger.info("Container running at {}", instance.configuration().baseUri());
                    logger.info("Example: {}",
                            instance.configuration().baseUriBuilder().path("api/").build());
                    logger.info("Send SIGKILL to shutdown container");
                });
        startDiscordListener();
        Thread.currentThread().join();
    }

    private static int determinePortNumber() {

        int port = 5000;
        var env = System.getenv("PORT");
        if (env != null) {
            try {
                port = Integer.parseInt(env);
            } catch (NumberFormatException ignored) {
            }
        }
        return port;
    }

    private static void startDiscordListener() {
        String token = System.getenv("SEER_BOT_TOKEN");
        logger.info("Starting up Discord Listener");
        if (token != null && !token.isBlank())
            logger.debug("A bot token was found.");
        else
            logger.warn("Bot token is missing.");

        EnumSet<GatewayIntent> intents = EnumSet.of(
                // Enables MessageReceivedEvent for guild (also known as servers)
                GatewayIntent.GUILD_MESSAGES,
                // Enables the event for private channels (also known as direct messages)
                GatewayIntent.DIRECT_MESSAGES,
                // Enables access to message.getContentRaw()
                GatewayIntent.MESSAGE_CONTENT,
                // Enables MessageReactionAddEvent for guild
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                // Enables MessageReactionAddEvent for private channels
                GatewayIntent.DIRECT_MESSAGE_REACTIONS
        );

        try {
            // By using createLight(token, intents), we use a minimalistic cache profile (lower ram usage)
            // and only enable the provided set of intents. All other intents are disabled, so you won't receive events for those.
            logger.info("Building Discord listener");
            JDA jda = JDABuilder.createLight(token, intents)
                    .setEventPassthrough(true)
                    // On this builder, you are adding all your event listeners and session configuration
                    .addEventListeners(new DiscordListener())
                    // You can do lots of configuration before starting, checkout all the setters on the JDABuilder class!
                    .setActivity(Activity.watching("your messages"))
                    // Once you're done configuring your jda instance, call build to start and login the bot.
                    .build();

            logger.info("Discord Listener attached");
            jda.getRestPing().queue(ping ->
                    logger.info("Ping time to Discord server is {}ms ", ping)
            );

            // If you want to access the cache, you can use awaitReady() to block the main thread until the jda instance is fully loaded
            jda.awaitReady();

            // Now we can access the fully loaded cache and show some statistics or do other cache dependent things
            logger.info("Guilds: " + jda.getGuildCache().size());
        } catch (InterruptedException e) {
            logger.warn("Discord listener interrupted", e);
        }
    }
}
