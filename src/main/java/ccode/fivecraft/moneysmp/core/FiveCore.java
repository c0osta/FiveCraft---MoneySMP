package ccode.fivecraft.moneysmp.core;

import ccode.fivecraft.moneysmp.core.inventory.listeners.InventoryListener;
import ccode.fivecraft.moneysmp.core.listeners.BlockListener;
import ccode.fivecraft.moneysmp.core.listeners.BrushListener;
import ccode.fivecraft.moneysmp.core.listeners.PlayerNameTagListener;
import ccode.fivecraft.moneysmp.core.listeners.UserListener;
import ccode.fivecraft.moneysmp.core.managers.money.managers.MoneyManager;
import ccode.fivecraft.moneysmp.core.nametag.NameTagListeners;
import ccode.fivecraft.moneysmp.core.nametag.NameTagManager;
import ccode.fivecraft.moneysmp.core.nametag.NameTagUpdateTask;
import ccode.fivecraft.moneysmp.core.scoreboard.ScoreboardListeners;
import ccode.fivecraft.moneysmp.core.scoreboard.ScoreboardManager;
import ccode.fivecraft.moneysmp.core.scoreboard.ScoreboardProfile;
import ccode.fivecraft.moneysmp.core.scoreboard.ScoreboardUpdateTask;
import ccode.fivecraft.moneysmp.core.scoreboard.sectors.SpawnScoreboardProfile;
import ccode.fivecraft.moneysmp.core.tab.TabHeaderFooterListener;
import ccode.fivecraft.moneysmp.core.tab.TabHeaderFooterUpdateTask;
import ccode.fivecraft.moneysmp.core.users.UserManager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class FiveCore extends JavaPlugin {

    public static FiveCore instance;

    public static FiveCore getInstance() {
        return instance;
    }

    private ScheduledExecutorService scheduledExecutorService;

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }

    private ScoreboardManager scoreboardManager;

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    private UserManager userManager;

    public UserManager getUserManager() {
        return userManager;
    }

    private  TabHeaderFooterUpdateTask tabHeaderFooterUpdateTask;

    private MoneyManager moneyManager;

    public MoneyManager getMoneyManager() {
        return moneyManager;
    }

    public NameTagManager nameTagManager;

    public NameTagManager getNameTagManager() {
        return nameTagManager;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    public static HashMap<String, ItemStack> playerBrushes = new HashMap<>(); // przechowuje testowe brush'e dla każdego gracza
    public static  HashMap<String, BukkitRunnable> playerTasks = new HashMap<>(); // przechowuje zadania (tasks) dla każdego gracza, aby po 15 minutach usunąć brush

    @Override
    public void onEnable() {
        getLogger().info("Plugin stworzony przez: costa#4977 ");
        FiveCore.this.scheduledExecutorService = Executors.newScheduledThreadPool(2);
        FiveCore.this.scoreboardManager = new ScoreboardManager();
        FiveCore.this.userManager = new UserManager(this);
        tabHeaderFooterUpdateTask = new TabHeaderFooterUpdateTask(this, "§lGradient Header", "§aPlugin testowy");
        this.nameTagManager = new NameTagManager();
        new NameTagListeners(this);
        new NameTagUpdateTask(this);
        new PlayerNameTagListener(this);
        new TabHeaderFooterListener(this);
        new SpawnScoreboardProfile(this);
        new ScoreboardListeners(this);
        new ScoreboardUpdateTask(this);
        new BrushListener(this);
        new UserListener(this);
        getServer().getPluginCommand("lvl").setExecutor(new LvlCommand());
        getServer().getPluginCommand("podloz").setExecutor(new PodlozCommand());
        getServer().getPluginManager().registerEvents(new BlockListener(), this);

        tabHeaderFooterUpdateTask.start();
        Config.load();

        getScoreboardManager().setSelectedScoreboardProfile((ScoreboardProfile)new SpawnScoreboardProfile(this));

        getServer().getPluginManager().registerEvents( new InventoryListener(), this);

    }

    @Override
    public void onDisable() {
        for (String playerName : playerBrushes.keySet()) {
            BukkitRunnable task = playerTasks.get(playerName);
            if (task != null) {
                task.cancel();
            }
            removeBrush(playerName);
        }
    }

    // metoda do usuwania brush'a dla gracza
    public static void removeBrush(String playerName) {
        playerBrushes.remove(playerName);
    }

}
