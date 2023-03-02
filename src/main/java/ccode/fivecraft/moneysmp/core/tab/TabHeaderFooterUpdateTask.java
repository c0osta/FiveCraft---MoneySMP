package ccode.fivecraft.moneysmp.core.tab;

import ccode.fivecraft.moneysmp.core.FiveCore;
import ccode.fivecraft.moneysmp.core.helpers.ChatHelper;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class TabHeaderFooterUpdateTask implements Runnable {
    private final FiveCore plugin;
    private final List<ChatColor> gradientColors;
    private int gradientIndex;
    private final String header;
    private final String footer;
    private double time;


    public TabHeaderFooterUpdateTask(FiveCore plugin, String header, String footer) {
        this.plugin = plugin;
        this.header = header;
        this.footer = footer;
        gradientColors = Arrays.asList(ChatColor.RED, ChatColor.GOLD, ChatColor.YELLOW, ChatColor.GREEN, ChatColor.BLUE, ChatColor.DARK_PURPLE);
        gradientIndex = 0;
    }

    @Override
    public void run() {
        String headerText = "§x§2§a§f§b§1§0§lM§x§3§d§f§b§2§1§lo§x§5§1§f§c§3§3§ln§x§6§4§f§c§4§4§le§x§7§8§f§c§5§6§ly §x§8§b§f§c§6§7§lS§x§9§f§f§d§7§9§lM§x§b§2§f§d§8§a§lP";
        String headerText2 = "§x§0§8§4§c§f§b§lF§x§1§9§5§d§f§b§li§x§2§9§6§d§f§b§lv§x§3§a§7§e§f§c§le§x§4§a§8§f§f§c§lC§x§5§b§a§0§f§c§lo§x§6§b§b§0§f§c§lr§x§7§c§c§1§f§c§le§x§8§c§d§2§f§d§l.§x§9§d§e§2§f§d§lp§x§a§d§f§3§f§d§ll";

        for (Player player : plugin.getServer().getOnlinePlayers()) {
            String boldText = "§x§0§8§4§c§f§b§lF§x§1§9§5§d§f§b§li§x§2§9§6§d§f§b§lv§x§3§a§7§e§f§c§le§x§4§a§8§f§f§c§lC§x§5§b§a§0§f§c§lo§x§6§b§b§0§f§c§lr§x§7§c§c§1§f§c§le§x§8§c§d§2§f§d§l.§x§9§d§e§2§f§d§lp§x§a§d§f§3§f§d§ll";
            String gradientText = getWaveGradient(boldText, 2.0, 10.0, 1.0, ChatColor.GREEN, ChatColor.WHITE);
            String header = ChatHelper.colored(ChatColor.BOLD + headerText2 + "\n\n" + ChatColor.BOLD + headerText + "\n&7Zarabiaj siano grając w Minecraft!\n");
            String footer = ChatHelper.colored("\n&7Sklep: &fdsadsa &8| &7Sklep: &fdsadsa\n&7Graczy: &f" + Bukkit.getOnlinePlayers().size() + " &8| &7Ping: &a" + player.getPing() + "ms");
            player.setPlayerListHeader(header);
            player.setPlayerListFooter(footer);
        }
    }

    public void start() {
        Bukkit.getScheduler().runTaskTimer(plugin, this, 0L, 1L);
    }



    public static String getWaveGradient2(String text, double time, double amplitude, double frequency, ChatColor start, ChatColor end) {
        StringBuilder builder = new StringBuilder();
        int steps = text.length() - 1;
        int startColor = start.getColor().getRGB();
        int endColor = end.getColor().getRGB();

        for (int i = 0; i < text.length(); i++) {
            double progress = (double) i / steps;
            int red = (int) ((int) (startColor >> 16 & 0xFF) + (int) ((endColor >> 16 & 0xFF) - (startColor >> 16 & 0xFF)) * progress);
            int green = (int) ((int) (startColor >> 8 & 0xFF) + (int) ((endColor >> 8 & 0xFF) - (startColor >> 8 & 0xFF)) * progress);
            int blue = (int) ((int) (startColor & 0xFF) + (int) ((endColor & 0xFF) - (startColor & 0xFF)) * progress);

            int offset = (int) Math.round(amplitude * Math.sin(2 * Math.PI * frequency * ((double) System.currentTimeMillis() / 1000.0) + (2 * Math.PI * i / steps)));
            ChatColor color = ChatColor.of(new Color(red, green, blue));
            ChatColor waveColor = ChatColor.of(new Color(Math.min(255, red + offset), Math.min(255, green + offset), Math.min(255, blue + offset)));

            builder.append(waveColor);
            builder.append(text.charAt(i));
            builder.append(color);
        }

        return builder.toString();
    }

    public static String getWaveGradient(String text, double time, double amplitude, double frequency, ChatColor start, ChatColor end) {
        StringBuilder builder = new StringBuilder();
        int steps = text.length() - 1;
        int startColor = start.getColor().getRGB();
        int endColor = end.getColor().getRGB();

        for (int i = 0; i < text.length(); i++) {
            double progress = (double) i / steps;
            int red = (int) ((int) (startColor >> 16 & 0xFF) + (int) ((endColor >> 16 & 0xFF) - (startColor >> 16 & 0xFF)) * progress);
            int green = (int) ((int) (startColor >> 8 & 0xFF) + (int) ((endColor >> 8 & 0xFF) - (startColor >> 8 & 0xFF)) * progress);
            int blue = (int) ((int) (startColor & 0xFF) + (int) ((endColor & 0xFF) - (startColor & 0xFF)) * progress);

            int offset = (int) Math.round(amplitude * Math.sin(2 * Math.PI * frequency * ((double) System.currentTimeMillis() / 1000.0) + (2 * Math.PI * i / steps)));
            ChatColor color = ChatColor.of(new java.awt.Color(red, green, blue));
            ChatColor waveColor = ChatColor.of(new Color(Math.min(255, red + offset), Math.min(255, green + offset), Math.min(255, blue + offset)));

            builder.append(waveColor);
            builder.append(text.charAt(i));
            builder.append(color);
        }

        return builder.toString();
    }

    private String getGradient(String text, ChatColor start, ChatColor end) {
        StringBuilder gradientText = new StringBuilder();
        int length = text.length();
        float[] startComponents = start.getColor().getComponents(null);
        float[] endComponents = end.getColor().getComponents(null);
        float[] currentComponents = new float[3];
        float stepR = (endComponents[0] - startComponents[0]) / length;
        float stepG = (endComponents[1] - startComponents[1]) / length;
        float stepB = (endComponents[2] - startComponents[2]) / length;

        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            currentComponents[0] = startComponents[0] + (i * stepR);
            currentComponents[1] = startComponents[1] + (i * stepG);
            currentComponents[2] = startComponents[2] + (i * stepB);
            int color = (new Color(currentComponents[0], currentComponents[1], currentComponents[2])).getRGB();
            gradientText.append(ChatColor.of(new Color(color)).toString()).append(c);
        }

        return gradientText.toString();
    }

    public static String getGradient(String text, ChatColor start, ChatColor end, boolean bold) {
        StringBuilder builder = new StringBuilder();
        int steps = text.length() - 1;
        int startColor = start.getColor().getRGB();
        int endColor = end.getColor().getRGB();

        for (int i = 0; i < text.length(); i++) {
            double progress = (double) i / steps;
            int red = (int) ((int) (startColor >> 16 & 0xFF) + (int) ((endColor >> 16 & 0xFF) - (startColor >> 16 & 0xFF)) * progress);
            int green = (int) ((int) (startColor >> 8 & 0xFF) + (int) ((endColor >> 8 & 0xFF) - (startColor >> 8 & 0xFF)) * progress);
            int blue = (int) ((int) (startColor & 0xFF) + (int) ((endColor & 0xFF) - (startColor & 0xFF)) * progress);

            ChatColor color = ChatColor.of(new java.awt.Color(red, green, blue));
            if (bold) {
                builder.append(ChatColor.BOLD);
            }
            builder.append(color);
            builder.append(text.charAt(i));
        }

        return builder.toString();
    }
}
