package ccode.fivecraft.moneysmp.core.inventory.listeners;

import ccode.fivecraft.moneysmp.core.helpers.ChatHelper;
import ccode.fivecraft.moneysmp.core.inventory.actions.IAction;
import ccode.fivecraft.moneysmp.core.inventory.actions.InventoryGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventoryListener implements Listener {
    private static final Map<Inventory, InventoryGUI> inventories = new HashMap<>();

    private final HashMap<UUID, Long> times = new HashMap<>();

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player)e.getWhoClicked();
        InventoryGUI gui = inventories.get(e.getInventory());
        if (gui == null)
            return;
        e.setCancelled(true);
        IAction action = (IAction)gui.getActions().get(e.getRawSlot());
        if (action != null) {
            Long time = this.times.get(player.getUniqueId());
            if (time != null && time > System.currentTimeMillis()) {
                ChatHelper.sendMessage((CommandSender)player, "&cZwolnij! Nie możesz tak szybko klikać lolz");
            } else {
                this.times.put(player.getUniqueId(), System.currentTimeMillis() + 500L);
                action.execute(player, e.getInventory(), e.getRawSlot(), e.getInventory().getItem(e.getRawSlot()));
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        InventoryGUI gui = inventories.get(event.getInventory());
        if (gui == null)
            return;
        inventories.remove(event.getInventory());
    }

    public static Map<Inventory, InventoryGUI> getInventories() {
        return inventories;
    }

}
