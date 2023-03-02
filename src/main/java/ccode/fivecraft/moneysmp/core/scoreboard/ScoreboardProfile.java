package ccode.fivecraft.moneysmp.core.scoreboard;

import org.bukkit.entity.Player;

import java.util.List;

public interface ScoreboardProfile {
    List<String> getEntries(Player paramPlayer);

    String getTitle(Player paramPlayer);
}
