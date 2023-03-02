package ccode.fivecraft.moneysmp.core.nametag;

import ccode.fivecraft.moneysmp.core.FiveCore;
import ccode.fivecraft.moneysmp.core.helpers.ChatHelper;
import ccode.fivecraft.moneysmp.core.users.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class NameTagManager
{


    private static Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

    public void createNameTag(Player player) {
        if (scoreboard.getTeam(player.getName()) == null)
            scoreboard.registerNewTeam(player.getName());
        Team team = scoreboard.getTeam(player.getName());
        team.setPrefix(ChatHelper.colored("&7"));
        team.setSuffix("");
        team.addEntry(player.getName());
        //if (!team.hasEntry(player.getName())) {
        //    team.addEntry(player.getName());
       // }
    }

    public void updateNameTag(Player player) {
        User user = FiveCore.getInstance().getUserManager().findUserByUniqueId(player.getUniqueId());
        Team team = scoreboard.getTeam(player.getName());
        if(team != null) {
            FiveCore.getInstance().getLogger().info("[NameTag] Updating " + team.getName() + " for " + player.getName());
            for (Player requester : Bukkit.getOnlinePlayers()) {
                NameTagPlayerEvent event = new NameTagPlayerEvent(player, requester, "", "");
                Bukkit.getPluginManager().callEvent(event);
                if (event.getPrefix().length() >= 64)
                    event.setPrefix(event.getPrefix().substring(0, 63));
                if (event.getSuffix().length() >= 64)
                    event.setSuffix(event.getSuffix().substring(0, 63));
                team.setPrefix(ChatHelper.colored(event.getPrefix()));
                team.setSuffix(ChatHelper.colored(event.getSuffix()));
                team.addEntry(requester.getName());
            }
        }
    }

    public void removeBoard(Player user) {
        if (scoreboard.getTeam(user.getName()) == null)
            return;
        Team team = scoreboard.getTeam(user.getName());
        if (team != null) {
            team.removeEntry(user.getName());
            team.unregister();
        }
    }
}

