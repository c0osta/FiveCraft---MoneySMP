package ccode.fivecraft.moneysmp.core.scoreboard;

import ccode.fivecraft.moneysmp.core.FiveCore;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.TimeUnit;

public final class ScoreboardUpdateTask extends BukkitRunnable {
    private final FiveCore plugin;

    public ScoreboardUpdateTask(FiveCore plugin) {
        this.plugin = plugin;
        this.plugin.getScheduledExecutorService().scheduleAtFixedRate((Runnable)this, 1L, 1L, TimeUnit.SECONDS);
    }

    public void run() {
        for (ScoreboardPlayer scoreboardPlayer : this.plugin.getScoreboardManager()
                .getScoreboardCollection())
            scoreboardPlayer.update(false);
    }
}
