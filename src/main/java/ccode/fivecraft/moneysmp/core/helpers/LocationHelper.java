package ccode.fivecraft.moneysmp.core.helpers;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class LocationHelper
{
    public static List<Player> getPlayersInRadiusNoStonePlate(Location location, int distance) {
        List<Player> players = new ArrayList<>();
        for (Player all : location.getWorld().getPlayers()) {
            if (location.distance(all.getLocation()) <= distance)
                players.add(all);
        }
        return players;
    }

    public static boolean isSameLocationXZ(Location from, Location to) {
        return (from.getBlockX() == to.getBlockX() && from
                .getBlockZ() == to.getBlockZ());
    }

    public static boolean isSameLocationXYZ(Location from, Location to) {
        return (from.getBlockX() == to.getBlockX() && from
                .getBlockY() == to.getBlockY() && from
                .getBlockZ() == to.getBlockZ());
    }

    public static int getRandInt(int min, int max) throws IllegalArgumentException {
        return rand.nextInt(max - min + 1) + min;
    }

    public static Double getRandDouble(double min, double max) throws IllegalArgumentException {
        return Double.valueOf(rand.nextDouble() * (max - min) + min);
    }

    public static Float getRandFloat(float min, float max) throws IllegalArgumentException {
        return Float.valueOf(rand.nextFloat() * (max - min) + min);
    }

    public static boolean getChance(double chance) {
        return (chance >= 100.0D || chance >= getRandDouble(0.0D, 100.0D).doubleValue());
    }

    private static final Random rand = ThreadLocalRandom.current();
}
