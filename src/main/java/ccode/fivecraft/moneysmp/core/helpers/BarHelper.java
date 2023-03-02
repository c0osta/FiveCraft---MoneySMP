package ccode.fivecraft.moneysmp.core.helpers;


import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BarHelper
{


    public static Map<UUID, BossBar> userBarSet = new HashMap<>(128);



    public static void addBossBar(Player player, String title, BarColor color, BarStyle style, float progress) {
        BossBar bossBar = userBarSet.computeIfAbsent(player.getUniqueId(), playerId -> Bukkit.createBossBar(title, color, style));
        bossBar.setTitle(title);
        bossBar.setProgress(progress);
        bossBar.addPlayer(player);
    }

    public static void removeBossBar(Player player) {
        BossBar bossBar = userBarSet.get(player.getUniqueId());
        if (bossBar != null) {
            bossBar.removePlayer(player);
            userBarSet.remove(player.getUniqueId());
        }
    }

    public static boolean hasBossBar(Player player) {
        return userBarSet.containsKey(player.getUniqueId());
    }
}
