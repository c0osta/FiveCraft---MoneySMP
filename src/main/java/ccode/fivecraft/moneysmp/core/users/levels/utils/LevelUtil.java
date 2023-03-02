package ccode.fivecraft.moneysmp.core.users.levels.utils;

import ccode.fivecraft.moneysmp.core.FiveCore;
import ccode.fivecraft.moneysmp.core.helpers.ChatHelper;
import ccode.fivecraft.moneysmp.core.users.User;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LevelUtil
{
    private static List<Integer> lvls = new ArrayList<>();

    public static int toLvLUP(Player p) {
        User user = FiveCore.getInstance().getUserManager().findUserByUniqueId(p.getUniqueId());
        return (int)((user.getUserLevel().getLevel() + 1) * 0.5D * 1000.0D);
    }

    public static void checkLevel(Player p) {
        User user = FiveCore.getInstance().getUserManager().findUserByUniqueId(p.getUniqueId());
        if (user.getUserLevel().getExp() > toLvLUP(p)) {
            user.getUserLevel().setExp(0);
            user.getUserLevel().setLevel(user.getUserLevel().getLevel() + 1);
            p.sendMessage(ChatHelper.colored("&7Awansowales na &6{LEVEL} &7poziom!".replace("{LEVEL}", user.getUserLevel().getLevel() + "")));
            if (lvls.contains(user.getUserLevel().getLevel()))
                Bukkit.broadcastMessage(ChatHelper.colored("&7Gracz &6{PLAYER} &7awansowal na &6{LVL} &7poziom kopania!".replace("{PLAYER}", p.getName()).replace("{LVL}", user.getUserLevel().getLevel() + "")));
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5.0F, 5.0F);
        }
    }

    static {
        lvls.add(10);
        lvls.add(25);
        lvls.add(40);
        lvls.add(50);
        lvls.add(75);
        lvls.add(100);
    }
}
