package ccode.fivecraft.moneysmp.core.helpers;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Objects;
import java.util.function.Function;

public class ItemHelper
{
    public static boolean hasItem(Player player, ItemStack firstItemStack, int needed) {
        return (countItemAmount(player, firstItemStack) >= needed);
    }

    public static int countItemAmount(final Player player, final ItemStack firstItemStack) {
        final Function<ItemStack, Boolean> comparatorFunc =
                !firstItemStack.hasItemMeta() ? secondItemStack ->
                        Objects.equals(secondItemStack.getType(), firstItemStack.getType())
                                && secondItemStack.getDurability() == firstItemStack.getDurability()
                        : secondItemStack -> secondItemStack.isSimilar(firstItemStack);
        int amount = 0;
        for (final ItemStack secondItemStack : player.getInventory().getContents()) {
            if (secondItemStack != null && comparatorFunc.apply(secondItemStack)) {
                amount += secondItemStack.getAmount();
            }
        }

        return amount;
    }

    public static void removeItem(Player player, ItemStack firstItemStack, int amount) {
        Function<ItemStack, Boolean> comparatorFunc = !firstItemStack.hasItemMeta() ? (secondItemStack -> (Objects.equals(secondItemStack.getType(), firstItemStack.getType()) && secondItemStack.getDurability() == firstItemStack.getDurability())) : (secondItemStack -> Boolean.valueOf(secondItemStack.isSimilar(firstItemStack)));
        PlayerInventory inventory = player.getInventory();
        ItemStack[] itemStacks = inventory.getContents();
        int removed = amount;
        for (int slot = 0; slot < itemStacks.length; slot++) {
            ItemStack secondItemStack = itemStacks[slot];
            if (!Objects.isNull(secondItemStack) && (Boolean) comparatorFunc.apply(secondItemStack)) {
                int contentAmount = secondItemStack.getAmount();
                if (contentAmount <= removed) {
                    inventory.clear(slot);
                    removed -= contentAmount;
                } else {
                    secondItemStack.setAmount(contentAmount - removed);
                    removed = 0;
                }
                if (removed <= 0)
                    break;
            }
        }
    }

}
