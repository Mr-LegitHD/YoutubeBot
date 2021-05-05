package lu.Youtube;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Profile extends ListenerAdapter {
    public  void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Secrets.prefix+"profile")) {
            if (event.getAuthor().isBot()) return;
            User user = event.getAuthor();
            List<User> mentionedUser = event.getMessage().getMentionedUsers();
            if (mentionedUser.size() > 0) {
                User userTarget = mentionedUser.get(0);

                event.getGuild().retrieveMember(userTarget).queue(member -> {
                    //Embed
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle(userTarget.getName()+" | Profile");
                    embed.addField("Name",userTarget.getAsTag(),false);
                    embed.addField("ID",userTarget.getId(),false);
                    embed.addField("Account Created",userTarget.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME),false);
                    embed.addField("Guild Joined",member.getTimeJoined().format(DateTimeFormatter.RFC_1123_DATE_TIME),false);
                    embed.setColor(Color.yellow);
                    embed.setThumbnail(userTarget.getAvatarUrl());
                    event.getChannel().sendMessage(embed.build()).queue();
                    embed.clear();
                    event.getMessage().delete().queue();

                });
            } else {

            }
        }
    }
}
