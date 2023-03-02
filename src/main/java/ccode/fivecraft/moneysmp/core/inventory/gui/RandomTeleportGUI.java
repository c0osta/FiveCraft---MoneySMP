package ccode.fivecraft.moneysmp.core.inventory.gui;

import ccode.fivecraft.moneysmp.core.helpers.ChatHelper;
import ccode.fivecraft.moneysmp.core.helpers.ItemBuilder;
import ccode.fivecraft.moneysmp.core.inventory.actions.IAction;
import ccode.fivecraft.moneysmp.core.inventory.actions.InventoryGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RandomTeleportGUI
{
    public static InventoryGUI openGUI(Player player) {
        InventoryGUI gui = new InventoryGUI("&7Wybierz losowy teleport!", 1);
        ItemStack rtpPlayer = new ItemBuilder(Material.COBBLESTONE).displayname(ChatHelper.colored(" ")).lore(new String[] {ChatHelper.colored("       &aLosowy teleport"), ChatHelper.colored(" "), ChatHelper.colored("   &7Teleportuje maksymalnie do &a3000 &7kratek"), ChatHelper.colored("    &7Teleport dostępny dla rangi: &fGRACZ  "), ChatHelper.colored(""), ChatHelper.colored("  &2Kliknij, aby sie przeteleportować ") }).build();
        ItemStack rtpVIP = new ItemBuilder(Material.COBBLESTONE).displayname(ChatHelper.colored(" ")).lore(new String[] {ChatHelper.colored("       &aLosowy teleport"), ChatHelper.colored(" "), ChatHelper.colored("  &7Teleportuje maksymalnie do &a4000 &7kratek"), ChatHelper.colored("    &7Teleport dostępny dla rangi: &6VIP    "), ChatHelper.colored(""), ChatHelper.colored("    &2Kliknij, aby sie przeteleportować ") }).build();
        ItemStack rtpSVIP = new ItemBuilder(Material.COBBLESTONE).displayname(ChatHelper.colored(" ")).lore(new String[] {ChatHelper.colored("       &aLosowy teleport"), ChatHelper.colored(" "), ChatHelper.colored("  &7Teleportuje maksymalnie do &a6000 &7kratek"), ChatHelper.colored("   &7Teleport dostępny dla rangi: &5SVIP   "), ChatHelper.colored(""), ChatHelper.colored("   &2Kliknij, aby sie przeteleportować ") }).build();
        ItemStack rtpSPONSOR = new ItemBuilder(Material.COBBLESTONE).displayname(ChatHelper.colored(" ")).lore(new String[] {ChatHelper.colored("       &aLosowy teleport"), ChatHelper.colored(" "), ChatHelper.colored("  &7Teleportuje maksymalnie do &a8000 &7kratek"), ChatHelper.colored("    &7Teleport dostępny dla rangi: &3SPONSOR    "), ChatHelper.colored(""), ChatHelper.colored("    &2Kliknij, aby sie przeteleportować ") }).build();
        ItemStack rtpFIVE = new ItemBuilder(Material.COBBLESTONE).displayname(ChatHelper.colored(" ")).lore(new String[] {ChatHelper.colored("       &aLosowy teleport"), ChatHelper.colored(" "), ChatHelper.colored("  &7Teleportuje maksymalnie do &a10000 &7kratek"), ChatHelper.colored("  &7Teleport dostępny dla rangi: &bFIVE   "), ChatHelper.colored(""), ChatHelper.colored("   &2Kliknij, aby sie przeteleportować ") }).build();

        gui.setItem(2, rtpPlayer, new IAction() {
            @Override
            public void execute(Player paramPlayer, Inventory paramInventory, int paramInt, ItemStack paramItemStack) {

            }
        });
        gui.setItem(3, rtpVIP, null);
        gui.setItem(4, rtpSVIP, null);
        gui.setItem(5, rtpSPONSOR, null);
        gui.setItem(6, rtpFIVE, null);
        gui.setEmptyItem();
        gui.openInventory(player);
        return gui;
    }
}
