package ccode.fivecraft.moneysmp.core;

import ccode.fivecraft.moneysmp.core.helpers.ChatHelper;
import ccode.fivecraft.moneysmp.core.helpers.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class TestBrushCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("testowybrush")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cTa komenda jest tylko dla graczy!");
                return true;
            }
            Player player = (Player) sender;
            if (FiveCore.playerBrushes.containsKey(player.getName())) {
                player.sendMessage("§cMasz już testowy brush!");
                return true;
            }
            // tworzymy i dodajemy brush do ekwipunku gracza
            ItemStack brush = new ItemBuilder(Material.BLAZE_ROD).displayname(ChatHelper.colored("§x§f§f§0§0§0§0t§x§f§e§1§7§0§4e§x§f§e§2§e§0§8s§x§f§d§4§5§0§bt§x§f§d§5§c§0§fo§x§f§c§7§3§1§3w§x§f§c§8§a§1§7y §x§f§b§a§1§1§bb§x§f§b§b§8§1§fr§x§f§a§c§f§2§2u§x§f§a§e§6§2§6s§x§f§9§f§d§2§ah")).lore(ChatHelper.colored("&fUderz w blok, a w jego zasięgu\n&fzostaną wykopane bloki!")).enchant(Enchantment.DURABILITY, 10).glow().flag(ItemFlag.HIDE_ENCHANTS).build();
            player.getInventory().addItem(brush);
            FiveCore.playerBrushes.put(player.getName(), brush);
            // tworzymy i uruchamiamy task dla gracza, który usunie brush po 15 minutach
            BukkitRunnable task = new BukkitRunnable() {
                @Override
                public void run() {
                    FiveCore.removeBrush(player.getName());
                    player.sendMessage("§cTwój testowy brush przestał działać!");
                }
            };
            task.runTaskLater(FiveCore.getInstance(), 20 * 60 * 15); // uruchamiamy task za 15 minut (20 ticków * 60 sekund * 15 minut)
        }
        return false;
    }
}
