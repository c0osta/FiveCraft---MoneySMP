package ccode.fivecraft.moneysmp.core.nametag;

import ccode.fivecraft.moneysmp.core.FiveCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public final class NameTagUpdateTask implements Runnable {
    private final FiveCore plugin;

    public NameTagUpdateTask(FiveCore plugin) {
        this.plugin = plugin;
        this.plugin.getScheduledExecutorService().scheduleAtFixedRate(this, 5L, 5L, TimeUnit.SECONDS);
    }

    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            this.plugin.getNameTagManager().updateNameTag(player);
        }
    }
}