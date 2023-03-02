package ccode.fivecraft.moneysmp.core.configs;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class ScoreBoardConfig
{
    @SerializedName("spawn_scoreboard")
    public static ScoreboardProfile spawnScoreboardProfileWrapper = new ScoreboardProfile();

    public static final class ScoreboardProfile {
        @SerializedName("title")
        public String spawnScoreboardTitle = "§x§0§8§4§c§f§b§lF§x§1§9§5§d§f§b§li§x§2§9§6§d§f§b§lv§x§3§a§7§e§f§c§le§x§4§a§8§f§f§c§lC§x§5§b§a§0§f§c§lo§x§6§b§b§0§f§c§lr§x§7§c§c§1§f§c§le§x§8§c§d§2§f§d§l.§x§9§d§e§2§f§d§lp§x§a§d§f§3§f§d§ll";

        @SerializedName("lines")
        public List<String> spawnScoreboardEntries = Arrays.asList(
                new String[] { "",
                        "&7× &7Hajs: &f{MONEY_PLN} PLN",
                        "&7× &7Monety: &f${MONEY_VIRTUAL}",
                        "&7× &7Poziom kopania: &f{LVL_AMOUNT}",
                        "&7× &7Wykopane bloki: &f{BLOCK_AMOUNT}",
                        "&7× &7Czas gry: &f{PLAY_TIME}",
                        "&7× &7Ilość zabójstw: &f{KILL_AMOUNT}", ""});
    }
}
