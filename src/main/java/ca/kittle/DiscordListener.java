package ca.kittle;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EnumSet;

public class DiscordListener extends ListenerAdapter {

    private static Logger logger = LoggerFactory.getLogger(DiscordListener.class);
    //    public static final String HEART = "\u2764";
    public static final Emoji HEART = Emoji.fromUnicode("U+2764");


    // This overrides the method called onMessageReceived in the ListenerAdapter class
    // Your IDE (such as intellij or eclipse) can automatically generate this override for you, by simply typing "onMessage" and auto-completing it!
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // The user who sent the message
        User author = event.getAuthor();
        // This is a special class called a "union", which allows you to perform specialization to more concrete types such as TextChannel or NewsChannel
        MessageChannelUnion channel = event.getChannel();
        // The actual message sent by the user, this can also be a message the bot sent itself, since you *do* receive your own messages after all
        Message message = event.getMessage();

        // Check whether the message was sent in a guild / server
        if (event.isFromGuild()) {
            // This is a message from a server
            logger.info(String.format("[%s] [%#s] %#s: %s\n",
                    event.getGuild().getName(), // The name of the server the user sent the message in, this is generally referred to as "guild" in the API
                    channel, // The %#s makes use of the channel name and displays as something like #general
                    author,  // The %#s makes use of User#getAsTag which results in something like Minn#6688
                    message.getContentDisplay() // This removes any unwanted mention syntax and converts it to a readable string
            ));
        } else {
            // This is a message from a private channel
            logger.info(String.format("[direct] %#s: %s\n",
                    author, // same as above
                    message.getContentDisplay()
            ));
        }

        // Using specialization, you can check concrete types of the channel union

        if (channel.getType() == ChannelType.TEXT) {
            logger.info("The channel topic is " + channel.asTextChannel().getTopic());
        }

        if (channel.getType().isThread()) {
            logger.info("This thread is part of channel #" +
                    channel.asThreadChannel()  // Cast the channel union to thread
                            .getParentChannel() // Get the parent of that thread, which is the channel it was created in (like forum or text channel)
                            .getName()          // And then print out the name of that channel
            );
        }
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (event.getEmoji().equals(HEART))
            logger.info("A user loved a message!");
    }

}
