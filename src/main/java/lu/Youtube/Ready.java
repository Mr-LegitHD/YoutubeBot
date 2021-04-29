package lu.Youtube;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Ready extends ListenerAdapter {
    @Override
    public void onReady(final ReadyEvent event) {

        //Bot Status
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            event.getJDA().getPresence().setActivity(Activity.watching(event.getJDA().getGuildById(Secrets.SERVERID).getMemberCount()+" Users"));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            event.getJDA().getPresence().setActivity(Activity.watching(event.getJDA().getGuildById(Secrets.SERVERID).getMemberCount()+" Users"));
        };
        executor.scheduleWithFixedDelay(task, 0,5, TimeUnit.SECONDS);

    }
}
