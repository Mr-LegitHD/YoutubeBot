package lu.Youtube;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

import javax.security.auth.login.LoginException;

public class MainClass {
    public static JDA jda;

    public static void main(final String[] args) throws LoginException {
        //Starting Up
        final JDABuilder builder = JDABuilder.createDefault(Secrets.TOKEN);
        builder.setAutoReconnect(true);
        builder.setStatus(OnlineStatus.ONLINE);
        System.out.println("Bot online");

        //Listeners
        builder.addEventListeners(new MessageLog());
        builder.addEventListeners(new Couting());
        builder.addEventListeners(new Ready());
        builder.addEventListeners(new Profile());
        builder.build();
    }
}
