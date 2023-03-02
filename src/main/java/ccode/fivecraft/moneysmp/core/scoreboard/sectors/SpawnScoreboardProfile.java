package ccode.fivecraft.moneysmp.core.scoreboard.sectors;

import ccode.fivecraft.moneysmp.core.FiveCore;
import ccode.fivecraft.moneysmp.core.configs.ScoreBoardConfig;
import ccode.fivecraft.moneysmp.core.helpers.TimeHelper;
import ccode.fivecraft.moneysmp.core.scoreboard.ScoreboardProfile;
import ccode.fivecraft.moneysmp.core.users.User;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpawnScoreboardProfile implements ScoreboardProfile {
    private final FiveCore plugin;

    public SpawnScoreboardProfile(FiveCore plugin) {
        this.plugin = plugin;
    }

    public List<String> getEntries(Player player) {
        List<String> formattedEntries = new ArrayList<>();
        User user = this.plugin.getUserManager().findUserByUniqueId(player.getUniqueId());
        for (String entry : ScoreBoardConfig.spawnScoreboardProfileWrapper.spawnScoreboardEntries) {
            entry = entry.replace("{PLAY_TIME}", TimeHelper.timeToString(player.getPlayerTime()));
            entry = entry.replace("{BLOCK_AMOUNT}", String.valueOf(user.getUserStats().getBlock_amount()));
            entry = entry.replace("{LVL_AMOUNT}", String.valueOf(user.getUserLevel().getLevel()));
            entry = entry.replace("{KILL_AMOUNT}", String.valueOf(user.getUserStats().getKills_amount()));
            entry = entry.replace("{MONEY_VIRTUAL}", String.valueOf(user.getUserStats().getMoney()));
            entry = entry.replace("{MONEY_PLN}", String.valueOf(user.getUserStats().getMoney_pln()));

            formattedEntries.add(entry);
        }
        return formattedEntries;
    }

    public String getTitle(Player player) {
        return ScoreBoardConfig.spawnScoreboardProfileWrapper.spawnScoreboardTitle;
    }

    private String format(double tps) {
        return ((tps > 18.0D) ? ChatColor.GREEN : ((tps > 16.0D) ? ChatColor.YELLOW : ChatColor.RED).toString()) + ((tps > 20.0D) ? "*" : "") +
                Math.min(Math.round(tps * 100.0D) / 100.0D, 20.0D);
    }
}
