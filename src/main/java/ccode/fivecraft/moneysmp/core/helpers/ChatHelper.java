package ccode.fivecraft.moneysmp.core.helpers;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ChatHelper
{
    public static String colored(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }


    public static String[] color(String[] message) {
        for (int i = 0; i < message.length; i++)
            message[i] = colored(message[i]);
        return message;
    }

    public static List<String> color(List<String> message) {
        List<String> strings = message;
        for (int i = 0; i < strings.size(); i++)
            strings.set(i, colored(strings.get(i)));
        return strings;
    }

    public static boolean sendMessage(CommandSender commandSender, String message) {
        commandSender.sendMessage(colored(message));
        return true;
    }


    public static void sendMessage(CommandSender sender, String... messages) {
        sender.sendMessage((String[]) Arrays.<String>stream(messages).filter(Objects::nonNull).map(ChatHelper::colored)
                .toArray(x$0 -> new String[x$0]));
    }

    public static void sendMessage(Collection<? extends Player> players, List<?> text) {
        for (Object string : text)
            sendMessage(players, colored(String.valueOf(string)));
    }

    public static void sendMessage(Collection<? extends Player> players, String text) {
        players.forEach(player -> sendMessage((CommandSender)player, new String[] { text }));
    }

    public static void sendMessage(CommandSender sender, List<String> strings) {
        strings.forEach(text -> sendMessage(sender, new String[] { text }));
    }


}
