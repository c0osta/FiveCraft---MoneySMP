package ccode.fivecraft.moneysmp.core.listeners;

import ccode.fivecraft.moneysmp.core.FiveCore;
import ccode.fivecraft.moneysmp.core.PodlozCommand;
import ccode.fivecraft.moneysmp.core.helpers.ChatHelper;
import ccode.fivecraft.moneysmp.core.helpers.RandomHelper;
import ccode.fivecraft.moneysmp.core.managers.money.Money;
import ccode.fivecraft.moneysmp.core.managers.money.data.LocationData;
import ccode.fivecraft.moneysmp.core.users.User;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Map;
import java.util.Random;

public class BlockListener implements Listener {

    private final Random random = new Random();

    @EventHandler
    public void onFindMoney(BlockBreakEvent event) {
        Player player = event.getPlayer();
        User user = FiveCore.getInstance().getUserManager().findUserByUniqueId(event.getPlayer().getUniqueId());
        Block block = event.getBlock();
        Location blockLocation = event.getBlock().getLocation();
        int moneyAmount = PodlozCommand.locationToMoney.getOrDefault(blockLocation, 0);
        if (moneyAmount > 0) {
            String playerName = event.getPlayer().getName();
            user.getUserStats().setMoney_pln(moneyAmount);
            player.sendTitle("", ChatHelper.colored("&7Znaleźiono &a" +moneyAmount+ " &r&7zł! &aGratulacje!"));

            PodlozCommand.locationToMoney.remove(blockLocation);
            System.out.println(playerName + " znalazł " + moneyAmount + " kasy na lokalizacji " + blockLocation.toString());
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        User user = FiveCore.getInstance().getUserManager().findUserByUniqueId(event.getPlayer().getUniqueId());
        Location location = event.getBlock().getLocation();
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