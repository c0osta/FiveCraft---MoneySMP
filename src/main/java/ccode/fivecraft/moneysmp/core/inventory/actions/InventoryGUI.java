package ccode.fivecraft.moneysmp.core.inventory.actions;


import ccode.fivecraft.moneysmp.core.helpers.ItemBuilder;
import ccode.fivecraft.moneysmp.core.inventory.listeners.InventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class InventoryGUI implements Listener {
    private final Inventory inventory;

    private final HashMap<Integer, IAction> actions;

    private final HashMap<UUID, Long> times = new HashMap<>();

    private final ItemStack emptyGlass;

    public InventoryGUI(String title, int rows) {
        this.inventory = Bukkit.createInventory(null, rows * 9, ChatColor.translateAlternateColorCodes('&', title));
        this.actions = new HashMap<>();
        this.emptyGlass = (new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, 1)).build();
        InventoryListener.getInventories().put(this.inventory, this);
    }

    public InventoryGUI clone() {
        InventoryGUI inventoryGUI = new InventoryGUI(this.inventory.toString(), this.inventory.getSize() / 9);
        int i = 0;
        for (ItemStack content : this.inventory.getContents()) {
            inventoryGUI.setItem(i, content, this.actions.get(Integer.valueOf(i)));
            i++;
        }
        return inventoryGUI;
    }

    public void setItem(int slot, ItemStack itemStack, IAction clickAction) {
        slot = (slot > this.inventory.getSize()) ? (slot % this.inventory.getSize()) : slot;
        this.inventory.setItem(slot, itemStack);
        this.actions.put(Integer.valueOf(slot), clickAction);
    }

    public void setEmptyItem(ItemStack is) {
        for (int i = 0; i < this.inventory.getSize(); i++) {
            if (this.inventory.getItem(i) == null)
                this.inventory.setItem(i, is);
        }
    }

    public void setEmptyItem() {
        for (int i = 0; i < this.inventory.getSize(); i++) {
            if (this.inventory.getItem(i) == null)
                this.inventory.setItem(i, this.emptyGlass);
        }
    }

    public InventoryGUI setAllItems(ItemStack[] items) {
        for (int i = 0; i < this.inventory.getSize(); i++)
            this.inventory.setItem(i, items[i]);
        return this;
    }

    public InventoryGUI setAction(int slot, IAction action) {
        if (action != null) {
            IAction iAction = this.actions.put(Integer.valueOf(slot), action);
        } else {
            this.actions.remove(Integer.valueOf(slot));
        }
        return this;
    }

    public InventoryGUI setOpenAction(IAction action) {
        if (action != null) {
            this.actions.put(Integer.valueOf(-1), action);
        } else {
            this.actions.remove(Integer.valueOf(-1));
        }
        return this;
    }

    public ItemStack getItem(int i) {
        return this.inventory.getItem(i);
    }

    public InventoryGUI setCloseAction(IAction action) {
        if (action != null) {
            IAction iAction = this.actions.put(Integer.valueOf(-2), action);
        } else {
            this.actions.remove(Integer.valueOf(-2));
        }
        return this;
    }

    public void openInventory(Player player) {
        player.openInventory(this.inventory);
    }

    public InventoryGUI openInventory(Player[] players) {
        for (Player player : players)
            openInventory(player);
        return this;
    }

    public InventoryGUI openInventory(Collection<? extends Player> players) {
        for (Player player : players)
            openInventory(player);
        return this;
    }

    public void addItem(ItemStack is) {
        this.inventory.addItem(new ItemStack[] { is });
    }

    public void addItem(ItemStack is, IAction clickAction) {
        for (int i = 0; i < this.inventory.getSize(); i++) {
            if (this.inventory.getItem(i) == null) {
                this.inventory.setItem(i, is);
                this.actions.put(Integer.valueOf(i), clickAction);
                return;
            }
        }
    }

    public Inventory get() {
        return this.inventory;
    }

    public ItemStack getEmptyGlass() {
        return this.emptyGlass;
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        if (e.getInventory().equals(this.inventory)) {
            IAction action = this.actions.get(Integer.valueOf(-1));
            if (action != null)
                action.execute((Player)e.getPlayer(), e.getInventory(), -1, null);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory().equals(this.inventory)) {
            IAction action = this.actions.get(Integer.valueOf(-2));
            if (action != null)
                action.execute((Player)e.getPlayer(), e.getInventory(), -1, null);
        }
    }

    @EventHandler
    public void onInventoryInteractEvent(InventoryInteractEvent e) {
        if (this.inventory.equals(e.getInventory()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryDragEvent(InventoryDragEvent e) {
        if (e.getInventory().equals(this.inventory))
            e.setCancelled(true);
    }

    public HashMap<Integer, IAction> getActions() {
        return this.actions;
    }

    public HashMap<UUID, Long> getTimes() {
        return this.times;
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}