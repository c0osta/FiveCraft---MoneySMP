package ccode.fivecraft.moneysmp.core.listeners;

import ccode.fivecraft.moneysmp.core.FiveCore;
import ccode.fivecraft.moneysmp.core.helpers.*;
import ccode.fivecraft.moneysmp.core.inventory.gui.RandomTeleportGUI;
import ccode.fivecraft.moneysmp.core.users.User;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;

public class BrushListener implements Listener
{
    private final FiveCore plugin;
    private static final int TIME_LIMIT = 900; // czas w sekundach (15 minut * 60 sekund)


    public BrushListener(FiveCore plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, (Plugin)this.plugin);
    }

    ItemStack brushx2 = new ItemBuilder(Material.BLAZE_ROD).displayname(ChatHelper.colored("§x§f§f§0§0§0§0b§x§f§e§2§4§0§6r§x§f§d§4§8§0§cu§x§f§c§6§c§1§2s§x§f§c§9§1§1§8h §x§f§b§b§5§1§e2§x§f§a§d§9§2§4x§x§f§9§f§d§2§a2")).lore(ChatHelper.colored("&fUderz w blok, a w jego zasięgu\n&fzostaną wykopane bloki!")).enchant(Enchantment.DURABILITY, 10).glow().flag(ItemFlag.HIDE_ENCHANTS).build();
    ItemStack brushx3 = new ItemBuilder(Material.BLAZE_ROD).displayname(ChatHelper.colored("§x§f§f§0§0§0§0b§x§f§e§2§4§0§6r§x§f§d§4§8§0§cu§x§f§c§6§c§1§2s§x§f§c§9§1§1§8h §x§f§b§b§5§1§e3§x§f§a§d§9§2§4x§x§f§9§f§d§2§a3")).lore(ChatHelper.colored("&fUderz w blok, a w jego zasięgu\n&fzostaną wykopane bloki!")).enchant(Enchantment.DURABILITY, 10).glow().flag(ItemFlag.HIDE_ENCHANTS).build();
    ItemStack brushx4 = new ItemBuilder(Material.BLAZE_ROD).displayname(ChatHelper.colored("§x§f§f§0§0§0§0b§x§f§e§2§4§0§6r§x§f§d§4§8§0§cu§x§f§c§6§c§1§2s§x§f§c§9§1§1§8h §x§f§b§b§5§1§e4§x§f§a§d§9§2§4x§x§f§9§f§d§2§a4")).lore(ChatHelper.colored("&fUderz w blok, a w jego zasięgu\n&fzostaną wykopane bloki!")).enchant(Enchantment.DURABILITY, 10).glow().flag(ItemFlag.HIDE_ENCHANTS).build();
    ItemStack brushx5 = new ItemBuilder(Material.BLAZE_ROD).displayname(ChatHelper.colored("§x§f§f§0§0§0§0b§x§f§e§2§4§0§6r§x§f§d§4§8§0§cu§x§f§c§6§c§1§2s§x§f§c§9§1§1§8h §x§f§b§b§5§1§e5§x§f§a§d§9§2§4x§x§f§9§f§d§2§a5")).lore(ChatHelper.colored("&fUderz w blok, a w jego zasięgu\n&fzostaną wykopane bloki!")).enchant(Enchantment.DURABILITY, 10).glow().flag(ItemFlag.HIDE_ENCHANTS).build();
    ItemStack brushx6 = new ItemBuilder(Material.BLAZE_ROD).displayname(ChatHelper.colored("§x§f§f§0§0§0§0b§x§f§e§2§4§0§6r§x§f§d§4§8§0§cu§x§f§c§6§c§1§2s§x§f§c§9§1§1§8h §x§f§b§b§5§1§e6§x§f§a§d§9§2§4x§x§f§9§f§d§2§a6")).lore(ChatHelper.colored("&fUderz w blok, a w jego zasięgu\n&fzostaną wykopane bloki!")).enchant(Enchantment.DURABILITY, 10).glow().flag(ItemFlag.HIDE_ENCHANTS).build();
    ItemStack brushx7 = new ItemBuilder(Material.BLAZE_ROD).displayname(ChatHelper.colored("§x§f§f§0§0§0§0b§x§f§e§2§4§0§6r§x§f§d§4§8§0§cu§x§f§c§6§c§1§2s§x§f§c§9§1§1§8h §x§f§b§b§5§1§e7§x§f§a§d§9§2§4x§x§f§9§f§d§2§a7")).lore(ChatHelper.colored("&fUderz w blok, a w jego zasięgu\n&fzostaną wykopane bloki!")).enchant(Enchantment.DURABILITY, 10).glow().flag(ItemFlag.HIDE_ENCHANTS).build();

    @EventHandler
    public void join(PlayerJoinEvent event) {
        event.getPlayer().getInventory().addItem(brushx2);
        event.getPlayer().getInventory().addItem(brushx3);
        event.getPlayer().getInventory().addItem(brushx4);
        event.getPlayer().getInventory().addItem(brushx5);
        event.getPlayer().getInventory().addItem(brushx6);
        event.getPlayer().getInventory().addItem(brushx7);

    }

    @EventHandler
    public void onPlayerInteract2(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        User user = FiveCore.getInstance().getUserManager().findUserByUniqueId(event.getPlayer().getUniqueId());
        if (player.getInventory().getItemInMainHand().isSimilar(brushx2)
                && event.getAction() == Action.LEFT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock();
            if (clickedBlock != null) {
                //clickedBlock.getWorld().dropItemNaturally(clickedBlock.getLocation(), new ItemStack(clickedBlock.getType()));
                int clickedBlockX = clickedBlock.getX();
                int clickedBlockY = clickedBlock.getY();
                int clickedBlockZ = clickedBlock.getZ();
                for (int x = clickedBlockX; x < clickedBlockX + 2; x++) {
                    for (int y = clickedBlockY; y < clickedBlockY + 2; y++) {
                        for (int z = clickedBlockZ; z < clickedBlockZ + 2; z++) {
                            Block block = clickedBlock.getWorld().getBlockAt(x, y, z);
                            if (block.getType() == Material.BEDROCK) {
                                continue;
                            }
                            if (block.getType() != Material.AIR) {
                                block.breakNaturally();
                                //block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(block.getType()));
                            }
                            block.setType(Material.AIR);
                            if(RandomHelper.getChance(0.001)) {
                                user.getUserStats().setMoney_pln(0.01);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§01 &r&7zł! &aGratulacje!"));
                            }
                            if(RandomHelper.getChance(0.0001)) {
                                user.getUserStats().setMoney_pln(0.05);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§05 &r&7zł! &aGratulacje!"));
                            }
                            if(RandomHelper.getChance(0.0001)) {
                                user.getUserStats().setMoney_pln(0.10);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§31§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));
                            }
                            if(RandomHelper.getChance(0.00001)) {
                                user.getUserStats().setMoney_pln(0.25);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§32§x§8§0§f§d§7§05 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000001)) {
                                user.getUserStats().setMoney_pln(0.50);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§35§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000001)) {
                                user.getUserStats().setMoney_pln(0.75);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§37§x§8§0§f§d§7§05 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000001)) {
                                user.getUserStats().setMoney_pln(1.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§81§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000000001)) {
                                user.getUserStats().setMoney_pln(2.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§82§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000000001)) {
                                user.getUserStats().setMoney_pln(3.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§83§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.0000000000001)) {
                                user.getUserStats().setMoney_pln(4.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§84§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000000000000001)) {
                                user.getUserStats().setMoney_pln(5.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§85§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000000000000001)) {
                                user.getUserStats().setMoney_pln(6.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§86§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.0000000000000000001)) {
                                user.getUserStats().setMoney_pln(7.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§87§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000000000000000000001)) {
                                user.getUserStats().setMoney_pln(8.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§88§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000000000000000000001)) {
                                user.getUserStats().setMoney_pln(9.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§89§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.0000000000000000000000001)) {
                                user.getUserStats().setMoney_pln(10.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§810§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));
                            }
                        }
                    }
                }
            }
        }
        if (player.getInventory().getItemInMainHand().isSimilar(brushx3)
                && event.getAction() == Action.LEFT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock();

            if (clickedBlock != null) {
                int clickedBlockX = clickedBlock.getX();
                int clickedBlockY = clickedBlock.getY();
                int clickedBlockZ = clickedBlock.getZ();
                for (int x = clickedBlockX; x < clickedBlockX + 3; x++) {
                    for (int y = clickedBlockY; y < clickedBlockY + 3; y++) {
                        for (int z = clickedBlockZ; z < clickedBlockZ + 3; z++) {
                            Block block = clickedBlock.getWorld().getBlockAt(x, y, z);
                            block.setType(Material.AIR);
                            if(RandomHelper.getChance(0.001)) {
                                user.getUserStats().setMoney_pln(0.01);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§01 &r&7zł! &aGratulacje!"));
                            }
                            if(RandomHelper.getChance(0.0001)) {
                                user.getUserStats().setMoney_pln(0.05);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§05 &r&7zł! &aGratulacje!"));
                            }
                            if(RandomHelper.getChance(0.0001)) {
                                user.getUserStats().setMoney_pln(0.10);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§31§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));
                            }
                            if(RandomHelper.getChance(0.00001)) {
                                user.getUserStats().setMoney_pln(0.25);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§32§x§8§0§f§d§7§05 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000001)) {
                                user.getUserStats().setMoney_pln(0.50);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§35§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000001)) {
                                user.getUserStats().setMoney_pln(0.75);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§37§x§8§0§f§d§7§05 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000001)) {
                                user.getUserStats().setMoney_pln(1.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§81§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000000001)) {
                                user.getUserStats().setMoney_pln(2.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§82§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000000001)) {
                                user.getUserStats().setMoney_pln(3.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§83§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.0000000000001)) {
                                user.getUserStats().setMoney_pln(4.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§84§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000000000000001)) {
                                user.getUserStats().setMoney_pln(5.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§85§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000000000000001)) {
                                user.getUserStats().setMoney_pln(6.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§86§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.0000000000000000001)) {
                                user.getUserStats().setMoney_pln(7.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§87§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000000000000000000001)) {
                                user.getUserStats().setMoney_pln(8.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§88§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000000000000000000001)) {
                                user.getUserStats().setMoney_pln(9.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§89§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.0000000000000000000000001)) {
                                user.getUserStats().setMoney_pln(10.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§810§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));
                            }
                        }
                    }
                }
            }
        }
        if (player.getInventory().getItemInMainHand().isSimilar(brushx4)
                && event.getAction() == Action.LEFT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock();
            if (clickedBlock != null) {
                clickedBlock.getWorld().dropItemNaturally(clickedBlock.getLocation(), new ItemStack(clickedBlock.getType()));
                int clickedBlockX = clickedBlock.getX();
                int clickedBlockY = clickedBlock.getY();
                int clickedBlockZ = clickedBlock.getZ();
                for (int x = clickedBlockX; x < clickedBlockX + 4; x++) {
                    for (int y = clickedBlockY; y < clickedBlockY + 4; y++) {
                        for (int z = clickedBlockZ; z < clickedBlockZ + 4; z++) {
                            Block block = clickedBlock.getWorld().getBlockAt(x, y, z);
                            block.setType(Material.AIR);

                            if(RandomHelper.getChance(0.001)) {
                                user.getUserStats().setMoney_pln(0.01);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§01 &r&7zł! &aGratulacje!"));
                            }
                            if(RandomHelper.getChance(0.0001)) {
                                user.getUserStats().setMoney_pln(0.05);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§05 &r&7zł! &aGratulacje!"));
                            }
                            if(RandomHelper.getChance(0.0001)) {
                                user.getUserStats().setMoney_pln(0.10);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§31§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));
                            }
                            if(RandomHelper.getChance(0.00001)) {
                                user.getUserStats().setMoney_pln(0.25);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§32§x§8§0§f§d§7§05 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000001)) {
                                user.getUserStats().setMoney_pln(0.50);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§35§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000001)) {
                                user.getUserStats().setMoney_pln(0.75);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§37§x§8§0§f§d§7§05 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000001)) {
                                user.getUserStats().setMoney_pln(1.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§81§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000000001)) {
                                user.getUserStats().setMoney_pln(2.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§82§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000000001)) {
                                user.getUserStats().setMoney_pln(3.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§83§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.0000000000001)) {
                                user.getUserStats().setMoney_pln(4.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§84§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000000000000001)) {
                                user.getUserStats().setMoney_pln(5.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§85§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000000000000001)) {
                                user.getUserStats().setMoney_pln(6.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§86§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.0000000000000000001)) {
                                user.getUserStats().setMoney_pln(7.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§87§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000000000000000000001)) {
                                user.getUserStats().setMoney_pln(8.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§88§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000000000000000000001)) {
                                user.getUserStats().setMoney_pln(9.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§89§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.0000000000000000000000001)) {
                                user.getUserStats().setMoney_pln(10.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§810§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));
                            }
                        }
                    }
                }
            }
        }
        if (player.getInventory().getItemInMainHand().isSimilar(brushx5)
                && event.getAction() == Action.LEFT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock();
            if (clickedBlock != null) {
                clickedBlock.getWorld().dropItemNaturally(clickedBlock.getLocation(), new ItemStack(clickedBlock.getType()));
                int clickedBlockX = clickedBlock.getX();
                int clickedBlockY = clickedBlock.getY();
                int clickedBlockZ = clickedBlock.getZ();
                for (int x = clickedBlockX; x < clickedBlockX + 5; x++) {
                    for (int y = clickedBlockY; y < clickedBlockY + 5; y++) {
                        for (int z = clickedBlockZ; z < clickedBlockZ + 5; z++) {
                            Block block = clickedBlock.getWorld().getBlockAt(x, y, z);
                            block.setType(Material.AIR);

                            if(RandomHelper.getChance(0.001)) {
                                user.getUserStats().setMoney_pln(0.01);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§01 &r&7zł! &aGratulacje!"));
                            }
                            if(RandomHelper.getChance(0.0001)) {
                                user.getUserStats().setMoney_pln(0.05);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§05 &r&7zł! &aGratulacje!"));
                            }
                            if(RandomHelper.getChance(0.0001)) {
                                user.getUserStats().setMoney_pln(0.10);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§31§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));
                            }
                            if(RandomHelper.getChance(0.00001)) {
                                user.getUserStats().setMoney_pln(0.25);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§32§x§8§0§f§d§7§05 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000001)) {
                                user.getUserStats().setMoney_pln(0.50);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§35§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000001)) {
                                user.getUserStats().setMoney_pln(0.75);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§37§x§8§0§f§d§7§05 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000001)) {
                                user.getUserStats().setMoney_pln(1.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§81§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000000001)) {
                                user.getUserStats().setMoney_pln(2.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§82§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000000001)) {
                                user.getUserStats().setMoney_pln(3.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§83§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.0000000000001)) {
                                user.getUserStats().setMoney_pln(4.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§84§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000000000000001)) {
                                user.getUserStats().setMoney_pln(5.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§85§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000000000000001)) {
                                user.getUserStats().setMoney_pln(6.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§86§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.0000000000000000001)) {
                                user.getUserStats().setMoney_pln(7.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§87§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000000000000000000001)) {
                                user.getUserStats().setMoney_pln(8.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§88§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000000000000000000001)) {
                                user.getUserStats().setMoney_pln(9.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§89§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.0000000000000000000000001)) {
                                user.getUserStats().setMoney_pln(10.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§810§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));
                            }
                        }
                    }
                }
            }
        }
        if (player.getInventory().getItemInMainHand().isSimilar(brushx6)
                && event.getAction() == Action.LEFT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock();
            if (clickedBlock != null) {
                clickedBlock.getWorld().dropItemNaturally(clickedBlock.getLocation(), new ItemStack(clickedBlock.getType()));
                int clickedBlockX = clickedBlock.getX();
                int clickedBlockY = clickedBlock.getY();
                int clickedBlockZ = clickedBlock.getZ();
                for (int x = clickedBlockX; x < clickedBlockX + 6; x++) {
                    for (int y = clickedBlockY; y < clickedBlockY + 6; y++) {
                        for (int z = clickedBlockZ; z < clickedBlockZ + 6; z++) {
                            Block block = clickedBlock.getWorld().getBlockAt(x, y, z);
                            block.setType(Material.AIR);

                            if(RandomHelper.getChance(0.001)) {
                                user.getUserStats().setMoney_pln(0.01);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§01 &r&7zł! &aGratulacje!"));
                            }
                            if(RandomHelper.getChance(0.0001)) {
                                user.getUserStats().setMoney_pln(0.05);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§05 &r&7zł! &aGratulacje!"));
                            }
                            if(RandomHelper.getChance(0.0001)) {
                                user.getUserStats().setMoney_pln(0.10);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§31§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));
                            }
                            if(RandomHelper.getChance(0.00001)) {
                                user.getUserStats().setMoney_pln(0.25);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§32§x§8§0§f§d§7§05 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000001)) {
                                user.getUserStats().setMoney_pln(0.50);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§35§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000001)) {
                                user.getUserStats().setMoney_pln(0.75);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§37§x§8§0§f§d§7§05 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000001)) {
                                user.getUserStats().setMoney_pln(1.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§81§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000000001)) {
                                user.getUserStats().setMoney_pln(2.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§82§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000000001)) {
                                user.getUserStats().setMoney_pln(3.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§83§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.0000000000001)) {
                                user.getUserStats().setMoney_pln(4.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§84§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000000000000001)) {
                                user.getUserStats().setMoney_pln(5.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§85§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000000000000001)) {
                                user.getUserStats().setMoney_pln(6.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§86§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.0000000000000000001)) {
                                user.getUserStats().setMoney_pln(7.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§87§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000000000000000000001)) {
                                user.getUserStats().setMoney_pln(8.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§88§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000000000000000000001)) {
                                user.getUserStats().setMoney_pln(9.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§89§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.0000000000000000000000001)) {
                                user.getUserStats().setMoney_pln(10.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§810§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));
                            }
                        }
                    }
                }
            }
        }
        if (player.getInventory().getItemInMainHand().isSimilar(brushx7)
                && event.getAction() == Action.LEFT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock();
            if (clickedBlock != null) {
                clickedBlock.getWorld().dropItemNaturally(clickedBlock.getLocation(), new ItemStack(clickedBlock.getType()));
                int clickedBlockX = clickedBlock.getX();
                int clickedBlockY = clickedBlock.getY();
                int clickedBlockZ = clickedBlock.getZ();
                for (int x = clickedBlockX; x < clickedBlockX + 7; x++) {
                    for (int y = clickedBlockY; y < clickedBlockY + 7; y++) {
                        for (int z = clickedBlockZ; z < clickedBlockZ + 7; z++) {
                            Block block = clickedBlock.getWorld().getBlockAt(x, y, z);
                            block.setType(Material.AIR);
                            if(RandomHelper.getChance(0.001)) {
                                user.getUserStats().setMoney_pln(0.01);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§01 &r&7zł! &aGratulacje!"));
                            }
                            if(RandomHelper.getChance(0.0001)) {
                                user.getUserStats().setMoney_pln(0.05);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§05 &r&7zł! &aGratulacje!"));
                            }
                            if(RandomHelper.getChance(0.0001)) {
                                user.getUserStats().setMoney_pln(0.10);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§31§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));
                            }
                            if(RandomHelper.getChance(0.00001)) {
                                user.getUserStats().setMoney_pln(0.25);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§32§x§8§0§f§d§7§05 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000001)) {
                                user.getUserStats().setMoney_pln(0.50);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§35§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000001)) {
                                user.getUserStats().setMoney_pln(0.75);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§80§x§5§d§f§c§3§5.§x§6§f§f§c§5§37§x§8§0§f§d§7§05 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000001)) {
                                user.getUserStats().setMoney_pln(1.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§81§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000000001)) {
                                user.getUserStats().setMoney_pln(2.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§82§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000000001)) {
                                user.getUserStats().setMoney_pln(3.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§83§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.0000000000001)) {
                                user.getUserStats().setMoney_pln(4.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§84§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000000000000001)) {
                                user.getUserStats().setMoney_pln(5.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§85§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000000000000001)) {
                                user.getUserStats().setMoney_pln(6.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§86§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.0000000000000000001)) {
                                user.getUserStats().setMoney_pln(7.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§87§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.000000000000000000001)) {
                                user.getUserStats().setMoney_pln(8.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§88§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.00000000000000000000001)) {
                                user.getUserStats().setMoney_pln(9.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§89§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));

                            }
                            if(RandomHelper.getChance(0.0000000000000000000000001)) {
                                user.getUserStats().setMoney_pln(10.00);
                                player.sendTitle("", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§810§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));
                            }
                        }
                    }
                }
            }
        }
    }

    /*/@EventHandler
    public void Brush(PlayerInteractEvent event) {
        User user = plugin.getUserManager().findUserByUniqueId(event.getPlayer().getUniqueId());
        if(event.getClickedBlock().getType() == Material.SPONGE) {
            RandomTeleportGUI.openGUI(event.getPlayer());
        }
    }/*/
}
