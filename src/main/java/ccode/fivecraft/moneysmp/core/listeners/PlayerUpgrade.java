package ccode.fivecraft.moneysmp.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerUpgrade implements Listener {

    private Map<UUID, Integer> playerLevels = new HashMap<>();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (playerLevels.containsKey(player.getUniqueId())) {
            int level = playerLevels.get(player.getUniqueId());
            double moneyMultiplier = 1 + level * 0.05;
            double money = Math.random() * moneyMultiplier;
            // dodaj wygenerowaną kasę do gracza
            // ...
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // sprawdź czy gracz ma już ulepszenia
        if (!playerLevels.containsKey(player.getUniqueId())) {
            playerLevels.put(player.getUniqueId(), 0);
        }
    }

    public boolean upgradePlayer(Player player) {
        int level = playerLevels.get(player.getUniqueId());
        if (level < 4) {
            // koszt ulepszenia
            double cost = (level + 1) * 100.0;
            // sprawdź czy gracz ma wystarczającą ilość kasy
            // ...
            // usuń koszt ulepszenia z konta gracza
            // ...
            playerLevels.put(player.getUniqueId(), level + 1);
            // wyświetl komunikat o ulepszeniu
            player.sendMessage("Ulepszono postać na poziom " + (level + 1));
            if (level + 1 == 2) {
                double moneyMultiplier = 1 + (level + 1) * 0.05 + 0.1;
            } else if (level + 1 == 2) {
                double moneyMultiplier = 1 + (level + 1) * 0.05 + 0.1;
            } else {
                double moneyMultiplier = 1 + (level + 1) * 0.05;
            }
            return true;
        } else {
            player.sendMessage("Twoja postać ma już maksymalny poziom.");
            return false;
        }
    }
}