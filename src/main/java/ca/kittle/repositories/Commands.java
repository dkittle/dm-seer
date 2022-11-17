package ca.kittle.repositories;

//import net.dv8tion.jda.api.entities.Guild;
//import net.dv8tion.jda.api.entities.Message;
//import net.dv8tion.jda.api.entities.TextChannel;
//import net.dv8tion.jda.api.entities.User;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class Commands {
//    private static Logger logger = LoggerFactory.getLogger(Commands.class);
//
//
//    public static void countMessage(Guild guild, User user) {
//        String guildId = guild.getId();
//        String userId = user.getId();
//
//        logger.info("Got a message for " + userId);
//
//        try {
//            //Make sure a counter exists for the user
//            statement.executeUpdate("INSERT INTO counter(guildid, userid, count)" +
//                    "SELECT '" + guildId + "', '" + userId + "', 0 " +
//                    "WHERE NOT EXISTS (SELECT 1 FROM counter WHERE guildid = '" + guildId + "' AND userid = '" + userId + "')");
//
//            //Add one to the counter
//            statement.executeUpdate("UPDATE counter " +
//                    "SET count = count + 1 " +
//                    "WHERE guildid = '" + guildId + "' " +
//                    "AND userid = '" + userId + "'");
//
//        } catch (SQLException e) {
//            logger.error("Caught an exception: ", e);
//        }
//
//    }
//    public static void countCommand(User user, Message message, TextChannel channel) {
//
//
//        //Make sure a user is mentioned
//        if (message.getMentionedUsers().isEmpty()) {
//            channel.sendMessage("Please mention at least one user.").queue();
//            return;
//        }
//
//        Message messageToEdit = channel.sendMessage("Retrieving message count(s)...").complete();
//
//        StringBuilder messageToSend = new StringBuilder();
//        for (User mentionedUser : message.getMentionedUsers()) {
//            try {
//                //Get user's row
//                ResultSet resultSet = Main.statement.executeQuery("SELECT * FROM counter " +
//                        "WHERE guildid = '" + message.getGuild().getId() + "' " +
//                        "AND userid = '" + mentionedUser.getId() + "'");
//                if (!resultSet.next()) {
//                    //No results
//                    messageToSend.append("No results for " + mentionedUser.getAsMention() + ". They probably haven't sent any messages.\n");
//                    continue;
//                }
//                //There are results
//                //Get user's count
//                int countIndex = resultSet.findColumn("count");
//                int count = resultSet.getInt(countIndex);
//
//                //Add user to message
//                messageToSend.append(mentionedUser.getAsMention() + ": " + count + " messages.\n");
//
//            } catch (SQLException throwables) {
//                logger.error("Caught an exception: ", throwables);
//            }
//        }
//        //Send the message
//        messageToEdit.editMessage(messageToSend).queue();
//    }
//}
