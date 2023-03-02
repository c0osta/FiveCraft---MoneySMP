package ccode.fivecraft.moneysmp.core.listeners;

import ccode.fivecraft.moneysmp.core.FiveCore;
import ccode.fivecraft.moneysmp.core.nametag.NameTagPlayerEvent;
import ccode.fivecraft.moneysmp.core.users.User;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.awt.*;

public class PlayerNameTagListener implements Listener
{
    FiveCore plugin;

    public PlayerNameTagListener(FiveCore plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onNameTag(final NameTagPlayerEvent event) {
        if(event.getPlayer().isOp()) {
            event.setPrefix(getGradient("Właściciel", ChatColor.RED, ChatColor.YELLOW));
        }
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
}
