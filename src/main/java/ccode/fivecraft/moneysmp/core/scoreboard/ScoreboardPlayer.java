package ccode.fivecraft.moneysmp.core.scoreboard;


import ccode.fivecraft.moneysmp.core.helpers.ChatHelper;
import ccode.fivecraft.moneysmp.core.tab.TabHeaderFooterUpdateTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.List;
import java.util.Objects;

public final class ScoreboardPlayer {
    private static final int MAX_LINE_LENGTH = Integer.parseInt(
            System.getProperty("ccode.max_sidebar_line_length", "64"));

    private final Player player;

    private final Scoreboard scoreboard;

    private ScoreboardProfile profile;

    private boolean firstInitialized;

    private int lastSentCount = -1;

    public ScoreboardPlayer(Player player, ScoreboardProfile profile) {
        this.player = player;
        this.profile = profile;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        player.setScoreboard(this.scoreboard);
    }

    public void setProfile(ScoreboardProfile profile) {
        this.profile = profile;
    }

    public void update(boolean ignoreInitialization) {
        if (!this.firstInitialized && !ignoreInitialization)
            return;
        Objective objective = getOrCreateObjective();
        String title = this.profile.getTitle(this.player);
        List<String> entries = this.profile.getEntries(this.player);
        objective.setDisplayName(ChatHelper.colored(title));
        int i;
        for (i = 0; i < entries.size(); i++) {
            Team team = getOrCreateTeam(ChatColor.stripColor(title.substring(0, 5)) + i, i);
            String[] split = split(ChatHelper.colored(entries.get(entries.size() - i - 1)));
            team.setPrefix(split[0]);
            team.setSuffix(split[1]);
            objective.getScore(getNameForIndex(i)).setScore(i + 1);
        }
        if (this.lastSentCount != -1)
            for (i = 0; i < this.lastSentCount - entries.size(); i++)
                removeLine(entries.size() + i);
        this.lastSentCount = entries.size();
        if (!this.firstInitialized)
            this.firstInitialized = true;
    }

    public void remove() {
        this.scoreboard.getTeams().forEach(Team::unregister);
        this.scoreboard.getObjectives().forEach(Objective::unregister);
    }

    private void removeLine(int i) {
        String name = getNameForIndex(i);
        this.scoreboard.resetScores(name);
        Team team = getOrCreateTeam(name, i);
        team.unregister();
    }

    private String getNameForIndex(int i) {
        return ChatColor.values()[i].toString() + ChatColor.RESET;
    }

    private Objective getOrCreateObjective() {
        Objective objective = this.scoreboard.getObjective("ccode");
        if (Objects.isNull(objective)) {
            objective = this.scoreboard.registerNewObjective("ccode", "dummy");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        }
        return objective;
    }

    private Team getOrCreateTeam(String name, int i) {
        Team team = this.scoreboard.getTeam(name);
        if (Objects.isNull(team)) {
            team = this.scoreboard.registerNewTeam(name);
            team.addEntry(getNameForIndex(i));
        }
        return team;
    }

    private String[] split(String input) {
        if (input.length() > MAX_LINE_LENGTH) {
            String suffix, prefix = input.substring(0, MAX_LINE_LENGTH);
            int lastColorIndex = prefix.lastIndexOf('§');
            if (lastColorIndex >= MAX_LINE_LENGTH - 2) {
                prefix = prefix.substring(0, lastColorIndex);
                suffix = ChatColor.getLastColors(input.substring(0, MAX_LINE_LENGTH + 1)) + input.substring(lastColorIndex + 2);
            } else {
                suffix = ChatColor.getLastColors(prefix) + input.substring(MAX_LINE_LENGTH);
            }
            if (suffix.length() > MAX_LINE_LENGTH)
                suffix = suffix.substring(0, MAX_LINE_LENGTH);
            return new String[] { prefix, suffix };
        }
        return new String[] { input, "" };
    }
}
