package ccode.fivecraft.moneysmp.core.scoreboard;

import ccode.fivecraft.moneysmp.core.FiveCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public final class ScoreboardListeners implements Listener {
    private final FiveCore plugin;

    public ScoreboardListeners(FiveCore plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, (Plugin)this.plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent event) {
        this.plugin.getScoreboardManager().createScoreboard(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onQuit(PlayerQuitEvent event) {
        this.plugin.getScoreboardManager().removeScoreboard(event.getPlayer().getUniqueId());
    }
}
