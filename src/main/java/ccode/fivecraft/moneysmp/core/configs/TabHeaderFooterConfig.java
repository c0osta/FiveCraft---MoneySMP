package ccode.fivecraft.moneysmp.core.configs;

import ccode.fivecraft.moneysmp.core.helpers.ChatHelper;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class TabHeaderFooterConfig
{
    @SerializedName("spawn_scoreboard")
    public static TabHeaderFooterProfile tabHeaderFooterProfile = new TabHeaderFooterProfile();

    public static final class TabHeaderFooterProfile {
        @SerializedName("title")
        public String header = ChatHelper.colored("\n&a&lMONEY SMP\n&7Graj i zarabiaj siano w Minecraft");

        @SerializedName("lines")
        public String footer = ChatHelper.colored("to jest footer");
    }
}
