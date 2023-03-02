package ccode.fivecraft.moneysmp.core;

import ccode.fivecraft.moneysmp.core.helpers.ChatHelper;
import ccode.fivecraft.moneysmp.core.users.User;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.UUID;

public class LvlCommand implements CommandExecutor
{
    public static Inventory customInventory = Bukkit.createInventory(null, 9, "Custom GUI");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player)sender;
        User user = FiveCore.getInstance().getUserManager().findUserByUniqueId(p.getUniqueId());
        p.sendMessage(ChatHelper.colored("&7Posiadasz obecnie &c" + user.getUserLevel().getLevel() + " &7poziom!"));
        p.sendMessage(ChatHelper.colored("&7Do nastepnego poziomu brakuje Ci &c" + ((int)((user.getUserLevel().getLevel() + 1) * 0.5D * 1000.0D) - user.getUserLevel().getExp())));
        return false;
    }
}
