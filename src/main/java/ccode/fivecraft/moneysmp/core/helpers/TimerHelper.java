package ccode.fivecraft.moneysmp.core.helpers;

import ccode.fivecraft.moneysmp.core.FiveCore;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TimerHelper implements Runnable
{
    private static final Map<UUID, BukkitTask> timerTaskMap = new ConcurrentHashMap<>();

    private FiveCore plugin;

    private Player player;

    private Location startLocation;

    private Location targetLocation;

    private int seconds;

    public TimerHelper(FiveCore plugin, Player player, Location startLocation, Location targetLocation, int seconds) {
        this.plugin = plugin;
        this.player = player;
        this.startLocation = startLocation;
        this.targetLocation = targetLocation;
        this.seconds = seconds;
    }

    public TimerHelper() {}

    public static void addTimer(Player player, Location targetLocation, int seconds) {
        //if (player.hasPermission("platform-timer-bypass")) {
        //    ChatHelper.sendMessage((CommandSender)player, new String[] { "&aTeleportacja powiodła się!" });
        //    player.teleport(targetLocation);
        //    return;
        //}
        removeTimer(player.getUniqueId());
        timerTaskMap.put(player.getUniqueId(), Bukkit.getScheduler()
                .runTaskTimerAsynchronously((Plugin) FiveCore.getInstance(), new TimerHelper(
                        FiveCore.getInstance(), player, player.getLocation(), targetLocation, seconds), 0L, 20L));
        ChatHelper.sendMessage((CommandSender)player, new String[] { "&bTeleportacja nastąpi za: &c{SECONDS}s\n&4Nie ruszaj sie!".replace("{SECONDS}",
                String.valueOf(seconds)) });
    }

    public static void removeTimer(UUID uniqueId) {
        BukkitTask task = timerTaskMap.remove(uniqueId);
        if (task != null)
            task.cancel();
    }

    @Override
    public synchronized void run() {
        if (!this.player.isOnline()) {
            this.removeTimer(this.player.getUniqueId());
            return;
        }
        Location location = this.player.getLocation();
        if (!LocationHelper.isSameLocationXZ(this.startLocation, location)) {
            //player.removePotionEffect(PotionEffectType.CONFUSION);
            //player.removePotionEffect(PotionEffectType.BLINDNESS);
            ChatHelper.sendMessage((CommandSender)this.player, new String[] { "&cTeleportacja przerwana!" });
            this.removeTimer(this.player.getUniqueId());
            return;
        }
        if (this.seconds <= 0) {
            ChatHelper.sendMessage((CommandSender)this.player, new String[] { "&aTeleportacja powiodła się!"});
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatHelper.colored("&aTeleportacja powiodła się!")));
            this.removeTimer(this.player.getUniqueId());
          //  player.removePotionEffect(PotionEffectType.CONFUSION);
           // player.removePotionEffect(PotionEffectType.BLINDNESS);
            this.plugin.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, () -> this.player.teleport(this.targetLocation));
            return;
        }
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatHelper.colored("&bTeleportacja nastąpi za: &c{SECONDS}s".replace("{SECONDS}", TimeHelper.timeToString(this.seconds)))));
        this.seconds--;
    }
}
