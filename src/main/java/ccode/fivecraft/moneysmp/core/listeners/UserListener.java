package ccode.fivecraft.moneysmp.core.listeners;

import ccode.fivecraft.moneysmp.core.FiveCore;
import ccode.fivecraft.moneysmp.core.helpers.ChatHelper;
import ccode.fivecraft.moneysmp.core.helpers.RandomHelper;
import ccode.fivecraft.moneysmp.core.users.User;
import ccode.fivecraft.moneysmp.core.users.levels.utils.LevelUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static ccode.fivecraft.moneysmp.core.LvlCommand.customInventory;

public class UserListener implements Listener
{
    private final FiveCore plugin;

    public UserListener(FiveCore plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, (Plugin)this.plugin);
    }

    @EventHandler
    public void UserJoin(PlayerJoinEvent event) {
        User user = plugin.getUserManager().findUserByUniqueId(event.getPlayer().getUniqueId());
        if(user == null) {
            this.plugin.getUserManager().registerUser(event.getPlayer());
        }
        /*/TextComponent message = new TextComponent(ChatColor.GRAY + "Wejdź na nasz serwer Discord! ");
        TextComponent link = new TextComponent(ChatColor.BLUE + "KLIKNIJ");
        link.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, "https://discord.gg/EXAMPLE"));
        message.addExtra(link);
        event.getPlayer().spigot().sendMessage(ChatMessageType.CHAT, message);/*/


        /*/// Utwórz BossBar z tekstem "dziękujemy za zaufanie i za granie"
        BossBar bossBar = Bukkit.createBossBar("dziękujemy za zaufanie i za granie", BarColor.GREEN, BarStyle.SOLID);
        // Utwórz zadanie, które będzie wykonywane co 5 minut
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                // Ustaw progress na 0, co oznacza, że BossBar będzie w pełni zniknięty
                bossBar.setProgress(0.0);

                // Wyświetl BossBar graczom przez 10 sekund
                bossBar.setVisible(true);
                bossBar.removeAll();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    bossBar.addPlayer(player);
                }

                // Ustaw progress na 1.0 płynnie zmieniając wartość co tick
                double progress = 0.0;
                while (progress < 1.0) {
                    final double currentProgress = progress;
                    Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                        @Override
                        public void run() {
                            bossBar.setProgress(currentProgress);
                        }
                    }, (long) (200 * progress)); // 200 ticków = 10 sekund
                    progress += 0.01;
                }

                // Ustaw progress na 1.0, co oznacza, że BossBar jest w pełni widoczny
                bossBar.setProgress(1.0);

                // Ukryj BossBar po 10 sekundach
                Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                    @Override
                    public void run() {
                        bossBar.setVisible(false);
                    }
                }, 200L); // 200 ticków = 10 sekund
            }
        }, 0L, 5 * 60 * 20L); // 5 minut = 5 * 60 sekund = 5 * 60 * 20 ticków/*/
    }


    @EventHandler
    public void UserBreak(BlockBreakEvent event) {
        User user = plugin.getUserManager().findUserByUniqueId(event.getPlayer().getUniqueId());
        if(RandomHelper.getChance(0.000001)) {
            user.getUserStats().setMoney_pln(10.00);
            event.getPlayer().sendTitle("§x§2§a§f§b§1§0§lM§x§3§d§f§b§2§1§lo§x§5§1§f§c§3§3§ln§x§6§4§f§c§4§4§le§x§7§8§f§c§5§6§ly §x§8§b§f§c§6§7§lS§x§9§f§f§d§7§9§lM§x§b§2§f§d§8§a§lP", ChatHelper.colored("&7Znaleźiono §x§4§c§f§b§1§810§x§5§d§f§c§3§5.§x§6§f§f§c§5§30§x§8§0§f§d§7§00 &r&7zł! &aGratulacje!"));
        }
        user.getUserStats().setBlock_amount(1);
        if (!event.getBlock().getType().equals(Material.STONE))
            return;
        LevelUtil.checkLevel(event.getPlayer());
        int exp = 5;
        user.getUserLevel().setExp(user.getUserLevel().getExp() + exp);
    }

    private void upgradeItem(Player player, ItemStack item) {
        Random random = new Random();
        int chance = random.nextInt(2);

        if (chance == 1) {
            // Ulepszamy przedmiot
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
            item.setItemMeta(meta);
            player.sendMessage("Przedmiot został ulepszony!");
        } else {
            // Usuwamy przedmiot
            player.getInventory().remove(item);
            player.sendMessage("Nie udało się ulepszyć przedmiotu, został on usunięty.");
        }
    }

    @EventHandler
    public void onInventoryClick5(InventoryClickEvent event) {
        // Sprawdzamy, czy gracz kliknął na nasze GUI
        if (event.getInventory().getType() == InventoryType.HOPPER && event.getView().getTitle().equals("Upgrade")) {
            // Sprawdzamy, czy gracz przeciągnął item na slot 5
            if (event.getSlot() == 4) {
                // Sprawdzamy, czy przeciągnięty item to przedmiot
                ItemStack item = event.getCursor();
                if (item != null && item.getType() != Material.AIR) {
                    // Ulepszamy przedmiot
                    Player player = (Player) event.getWhoClicked();
                    upgradeItem(player, item);

                    // Usuwamy item z GUI
                    event.getInventory().setItem(event.getSlot(), new ItemStack(Material.AIR));

                    // Zamykamy GUI
                    player.closeInventory();
                }
            }

            // Zatrzymujemy event, aby item nie został przeniesiony do innego slotu
            event.setCancelled(true);
        }
    }

    //Testowy inv click do upgrade brusha
    private final Random random = new Random();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        // Sprawdź, czy inventory, w którym kliknął gracz, jest customowym GUI
        if (event.getInventory().equals(customInventory)) {
            int slot = event.getSlot();
            if(slot != 5 || slot != 7) {
                event.setCancelled(true);
            }
            if (slot == 5 || slot == 7) {
                // Sprawdź, czy gracz przeciąga patyk na slot 5 i kamień na slot 7
                ItemStack itemOnSlot5 = customInventory.getItem(5);
                ItemStack itemOnSlot7 = customInventory.getItem(7);
                if (itemOnSlot5 != null && itemOnSlot5.getType() == Material.STICK
                        && itemOnSlot7 != null && itemOnSlot7.getType() == Material.STONE) {
                    // Dodaj wełnę na slot 6
                    customInventory.setItem(6, new ItemStack(Material.WHITE_WOOL));
                    player.updateInventory();
                }
            } else if (slot == 6) {
                // Sprawdź, czy gracz kliknął na wełnę na slocie 6
                ItemStack clickedItem = event.getCurrentItem();
                if (clickedItem != null && clickedItem.getType() == Material.WHITE_WOOL) {
                    // Wylosuj 50% szansę na dostanie kilofa
                    if (random.nextDouble() < 0.5) {
                        // Usuń przedmioty z slotów 5, 6 i 7
                        customInventory.setItem(5, null);
                        customInventory.setItem(6, null);
                        customInventory.setItem(7, null);
                        player.updateInventory();
                        // Dodaj kilof do ekwipunku gracza
                        player.getInventory().addItem(new ItemStack(Material.DIAMOND_PICKAXE));
                        player.sendMessage(ChatColor.GREEN + "Gratulacje! Otrzymałeś diamentowy kilof.");
                    } else {
                        // Usuń tylko wełnę z slotu 6
                        customInventory.setItem(6, null);
                        player.updateInventory();
                        player.sendMessage(ChatColor.RED + "Nie udało ci się zdobyć diamentowego kilofa.");
                    }
                    event.setCancelled(true);
                }
            }
        }
    }

    /*/@EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // Sprawdź, czy inventory, w którym kliknął gracz, jest customowym GUI
        if (event.getInventory().equals(customInventory)) {
            // Sprawdź, czy gracz kliknął na jeden z dopuszczalnych slotów
            int slot = event.getSlot();
            if (slot == 5 || slot == 7) {
                // Pobierz przedmiot, który gracz próbuje umieścić w tym slocie
                ItemStack clickedItem = event.getCurrentItem();
                if (clickedItem == null || clickedItem.getType() == Material.AIR) {
                    // Gracz usunął przedmiot z dopuszczalnego slotu, co jest dozwolone
                    return;
                } else {
                    // Gracz próbuje umieścić przedmiot w dopuszczalnym slocie, więc zablokuj to tylko jeśli przedmiot nie został przeciągnięty z innego inventory
                    if (event.getClickedInventory() != null && event.getClickedInventory().getType() != InventoryType.PLAYER) {
                        event.setCancelled(true);
                        Player player = (Player) event.getWhoClicked();
                        player.sendMessage("Nie można umieścić tego przedmiotu w tym slocie!");
                    }
                }
            } else {
                // Gracz próbuje umieścić przedmiot w niedopuszczalnym slocie, więc zablokuj to i anuluj event
                event.setCancelled(true);
                Player player = (Player) event.getWhoClicked();
                player.sendMessage("Nie można umieścić przedmiotów w tym slocie!");
            }
        }
    }/*/

    //Testowe MysterBoxy podobne do MCCW
    private static final Random RANDOM = new Random();
    private static final List<String> ITEM_NAMES = Arrays.asList("Diamond", "Gold Ingot", "Emerald", "Iron Ingot");
    private static final int ITEM_COUNT = ITEM_NAMES.size();

    @EventHandler
    public void MysteryBox(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();
        if(block.getType() == Material.ZOMBIE_HEAD) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    block.setType(Material.AIR);
                    block.getLocation().getWorld().spawnParticle(Particle.CLOUD, block.getLocation(), 1);
                    block.getLocation().getWorld().playSound(block.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 2);
                    this.cancel();
                }
            }.runTaskTimer(plugin, 0, 1);
        }
    }

    @EventHandler
    public void onInteractMysteryBox(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            if (block.getType() == Material.CREEPER_HEAD) {
                event.setCancelled(true);
                Player player = event.getPlayer();
                Location location = block.getLocation().add(0.5, 0, 0.5);


                ArmorStand boxStand = (ArmorStand) block.getWorld().spawnEntity(location.clone().add(0, 0.5, 0), EntityType.ARMOR_STAND);
                boxStand.setGravity(false);
                boxStand.setSmall(true);
                boxStand.setVisible(false);
                boxStand.setHeadPose(new EulerAngle(0, 0, 0));
                boxStand.setCustomName(ChatColor.RED + "MysteryBox");
                boxStand.setCustomNameVisible(true);
                boxStand.setHelmet(new ItemStack(Material.CREEPER_HEAD));

                ArmorStand itemStand = (ArmorStand) block.getWorld().spawnEntity(location.clone().add(0, 0.25, 0), EntityType.ARMOR_STAND);
                itemStand.setGravity(false);
                itemStand.setSmall(true);
                itemStand.setVisible(false);
                itemStand.setHeadPose(new EulerAngle(0, 0, 0));
                itemStand.setCustomName(ChatColor.RED + "PREMIUM");
                itemStand.setCustomNameVisible(true);

                new BukkitRunnable() {
                    int ticks = 0;
                    boolean isPremium = true;
                    String itemName = null;
                    boolean hasWon = false;
                    ChatColor color = ChatColor.RED;

                    @Override
                    public void run() {
                        ticks++;

                        if (ticks > 20) {
                            isPremium = false;
                            itemName = ITEM_NAMES.get(RANDOM.nextInt(ITEM_COUNT));
                            itemStand.setCustomName(ChatColor.WHITE + itemName);
                            itemStand.setCustomNameVisible(true);
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
                        }

                        if (ticks > 40 && !hasWon) {
                            hasWon = true;
                            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                            itemStand.setCustomName(ChatColor.YELLOW + itemName);
                            itemStand.setCustomNameVisible(true);
                            new BukkitRunnable() {
                                int itemTicks = 0;
                                Particle particle = Particle.HEART;

                                @Override
                                public void run() {
                                    itemTicks++;
                                    if (itemTicks > 5) {
                                        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
                                        particle = Particle.EXPLOSION_LARGE;
                                    }
                                    if (itemTicks > 10) {
                                        itemStand.remove();
                                        boxStand.remove();
                                        this.cancel();
                                    }
                                    itemStand.getWorld().spawnParticle(particle, itemStand.getLocation(), 1);
                                }
                            }.runTaskTimer(plugin, 0, 1);
                        }

                        if (!hasWon && ticks % 4 == 0) {
                            boxStand.setHeadPose(boxStand.getHeadPose().setY(boxStand.getHeadPose().getY() + Math.toRadians(45)));
                            itemStand.setHeadPose(itemStand.getHeadPose().setY(itemStand.getHeadPose().getY() + Math.toRadians(45)));
                        }

                        if (ticks % 10 == 0) {
                            color = (color == ChatColor.RED) ? ChatColor.YELLOW : ChatColor.RED;
                            boxStand.setCustomName(color + "MysteryBox");
                            if (!hasWon) {
                                itemName = ITEM_NAMES.get(RANDOM.nextInt(ITEM_COUNT));
                                itemStand.setCustomName(ChatColor.WHITE + itemName);
                            }
                        }

                        if (hasWon) {
                            this.cancel();
                        }
                    }
                }.runTaskTimer(plugin, 0, 2);
            }
        }
    }
}

