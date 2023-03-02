package ccode.fivecraft.moneysmp.core;

import ccode.fivecraft.moneysmp.core.helpers.ChatHelper;
import ccode.fivecraft.moneysmp.core.inventory.actions.InventoryGUI;
import ccode.fivecraft.moneysmp.core.managers.money.Money;
import ccode.fivecraft.moneysmp.core.managers.money.data.LocationData;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class PodlozCommand implements CommandExecutor {

    public static Map<Location, Integer> locationToMoney = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Komenda jest dostępna tylko dla graczy!");
            return true;
        }

        if (!sender.isOp()) {
            sender.sendMessage("Nie masz uprawnień do wykonania tej komendy!");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("Użycie: /podloz <ilosc>");
            return true;
        }

        Player player = (Player) sender;

        try {
            int amount = Integer.parseInt(args[0]);
            Location blockLocation = player.getLocation().getBlock().getLocation();
            locationToMoney.put(blockLocation, amount);
            sender.sendMessage("Podłożyłeś " + amount + " kasy na lokalizacji " + blockLocation.toString());
            System.out.println("Podłożono " + amount + " kasy na lokalizacji " + blockLocation.toString());
            return true;
        } catch (NumberFormatException e) {
            player.sendMessage("Nieprawidłowa ilość!");
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
            // wyświetl listę wszystkich lokacji, w których została podłożona kasa
            for (Map.Entry<Location, Integer> entry : locationToMoney.entrySet()) {
                Location location = entry.getKey();
                Integer info = entry.getValue();

                player.sendMessage(location.toString() + " - " + info);
            }
            return true;
        }

        return true;
    }
}