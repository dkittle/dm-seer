package ca.kittle;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.EnumSet;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application
{
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        logger.info("Starting DM Seer application");
        logger.info("Environment variables:");
        logger.info("RDS_HOSTNAME {}", System.getenv("RDS_HOSTNAME"));
        logger.info("PORT {}", System.getenv("PORT"));
        logger.info("Bot Token {}", System.getenv("COUNTER_BOT_TOKEN"));
        startWebServer();

        DdbProxy proxy = new DdbProxy();
        //https://ddb.ac/characters/54736595/H0hA0L
        proxy.getCharacter("54736595");


        logger.info("Hopefully starting up Discord Listener");
        startDiscordListener();
//        DdbProxy proxy = new DdbProxy();
//        //https://ddb.ac/characters/54736595/H0hA0L
//        proxy.getCharacter("54736595/H0hA0L");
    }

    private static void startWebServer() {
        Server server = new Server(5000);
        server.setHandler(new WebListener());
        logger.info("Starting DM Seer web server on port 5000");
        try {
            server.start();
            logger.info("Started web server");
//            server.join();
        } catch(Exception e) {
            logger.error("Problem starting web server", e);
        }
        logger.info("Finished with web server");
    }

    private static void startDiscordListener() {
        String token = System.getenv("COUNTER_BOT_TOKEN");
        logger.info("Starting up Discord Listener");
        if (token != null && !token.isBlank())
            logger.info("A bot token was found.");
        else
            logger.info("Bot token is missing.");

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
                    // On this builder, you are adding all your event listeners and session configuration
                    .addEventListeners(new DiscordListener())
                    // You can do lots of configuration before starting, checkout all the setters on the JDABuilder class!
                    .setActivity(Activity.watching("your messages"))
                    // Once you're done configuring your jda instance, call build to start and login the bot.
                    .build();

            logger.info("Discord Listener attached");
            jda.getRestPing().queue(ping ->
                    // shows ping in milliseconds
                    logger.info("Logged in with ping: " + ping)
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
