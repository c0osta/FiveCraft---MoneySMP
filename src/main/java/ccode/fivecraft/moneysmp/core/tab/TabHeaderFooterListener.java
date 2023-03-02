package ccode.fivecraft.moneysmp.core.tab;

import ccode.fivecraft.moneysmp.core.FiveCore;
import ccode.fivecraft.moneysmp.core.configs.TabHeaderFooterConfig;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class TabHeaderFooterListener implements Listener {
    private final FiveCore plugin;

    public TabHeaderFooterListener(FiveCore plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, (Plugin)this.plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent event) {
       // event.getPlayer().setPlayerListHeaderFooter(TabHeaderFooterConfig.tabHeaderFooterProfile.header, TabHeaderFooterConfig.tabHeaderFooterProfile.footer);

    }
}
