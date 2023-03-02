package ccode.fivecraft.moneysmp.core.nametag;


import ccode.fivecraft.moneysmp.core.FiveCore;
import ccode.fivecraft.moneysmp.core.users.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.TimeUnit;

public final class NameTagListeners implements Listener {
    private final FiveCore plugin;

    public NameTagListeners(FiveCore plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, (Plugin)this.plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent event) {
        this.plugin.getScheduledExecutorService().schedule(() -> {
            this.plugin.getNameTagManager().createNameTag(event.getPlayer());
            this.plugin.getNameTagManager().updateNameTag(event.getPlayer());
        },2L, TimeUnit.SECONDS);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onQuit(PlayerQuitEvent event) {
        User user = this.plugin.getUserManager().findUserByUniqueId(event.getPlayer().getUniqueId());
        this.plugin.getNameTagManager().removeBoard(event.getPlayer());
    }
}