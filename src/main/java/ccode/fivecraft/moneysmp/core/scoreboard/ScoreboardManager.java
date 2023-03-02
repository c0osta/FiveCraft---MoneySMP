package ccode.fivecraft.moneysmp.core.scoreboard;

import ccode.fivecraft.moneysmp.core.FiveCore;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.*;
import java.util.concurrent.TimeUnit;
//Autor scoreboarda nie został znaleźiony, nie wiem kto to zrobił ale działa to używam
public class ScoreboardManager {
    private final Map<UUID, ScoreboardPlayer> scoreboardMap = Maps.newConcurrentMap();

    private ScoreboardProfile selectedScoreboardProfile;

    public void setSelectedScoreboardProfile(ScoreboardProfile scoreboardProfile) {
        if (Objects.isNull(scoreboardProfile) && Objects.nonNull(this.selectedScoreboardProfile)) {
            for (ScoreboardPlayer player : this.scoreboardMap.values())
                player.remove();
            this.scoreboardMap.clear();
            this.selectedScoreboardProfile = null;
        } else if (Objects.nonNull(scoreboardProfile) && Objects.isNull(this.selectedScoreboardProfile)) {
            this.selectedScoreboardProfile = scoreboardProfile;
            for (Player player : Bukkit.getOnlinePlayers())
                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) FiveCore.getInstance(), () -> createScoreboard(player));
        }
    }

    public void createScoreboard(Player player) {
        if (Objects.isNull(this.selectedScoreboardProfile))
            return;
        ScoreboardPlayer scoreboardPlayer = new ScoreboardPlayer(player, this.selectedScoreboardProfile);
        this.scoreboardMap.put(player.getUniqueId(), scoreboardPlayer);
        FiveCore.getInstance().getScheduledExecutorService().schedule(() -> {
            if (player.isOnline())
                scoreboardPlayer.update(true);
        }, 2L, TimeUnit.SECONDS);
    }

    public void removeScoreboard(UUID uniqueId) {
        ScoreboardPlayer player = this.scoreboardMap.remove(uniqueId);
        if (Objects.nonNull(player))
            player.remove();
    }

    public ScoreboardPlayer getScoreboard(Player player) {
        return this.scoreboardMap.get(player.getUniqueId());
    }

    public Collection<ScoreboardPlayer> getScoreboardCollection() {
        return Collections.unmodifiableCollection(this.scoreboardMap.values());
    }
}
