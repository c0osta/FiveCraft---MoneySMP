package ccode.fivecraft.moneysmp.core;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BrushCommand implements CommandExecutor
{
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

        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Poprawne użycie: /dajbrush <gracz> <brush>");
            return false;
        }

        String playerName = args[0];
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            sender.sendMessage(ChatColor.RED + "Nie znaleziono gracza o nazwie " + playerName);
            return false;
        }


        return true;
    }
}
