package lu.Youtube;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.time.Instant;

public class MessageLog extends ListenerAdapter {

    //Message Log
    public void onGuildMessageReceived(final GuildMessageReceivedEvent event) {

        String channelid = event.getChannel().getId();
        String member = event.getMessage().getAuthor().getAsTag();
        String message = event.getMessage().getContentRaw();
        String messageid = event.getMessageId();
        String jumpurl = event.getMessage().getJumpUrl();

        //Not log the log itself
        if (event.getChannel().getId().equals(Secrets.LOGCHANNELID)) return;
        if (event.getAuthor().isBot())return;

        //Embed
        final EmbedBuilder log = new EmbedBuilder();
        log.setTimestamp(Instant.now());
        log.setColor(Color.GREEN);
        log.setTitle("Message Log");
        log.addField("Channel","<#"+channelid+">",false);
        log.addField("Member",member,false);
        log.addField("Message",message,false);
        log.addField("Message ID",messageid,false);
        log.addField("JumpUrl","[Click to jump to message]("+jumpurl+")",false);
        event.getJDA().getGuildById(Secrets.SERVERID).getTextChannelById(Secrets.LOGCHANNELID).sendMessage(log.build()).queue();
        log.clear();


    }
}
