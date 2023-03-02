package ccode.fivecraft.moneysmp.core.helpers;

import org.apache.commons.lang.Validate;

import java.util.Random;

public class RandomHelper
{
    private static Random rand = new Random();

    public static int getRandInt(int min, int max) throws IllegalArgumentException {
        Validate.isTrue((max > min), "Max can't be smaller than min!");
        return rand.nextInt(max - min + 1) + min;
    }

    public static Double getRandDouble(double min, double max) throws IllegalArgumentException {
        Validate.isTrue((max > min), "Max can't be smaller than min!");
        return Double.valueOf(rand.nextDouble() * (max - min) + min);
    }

    public static Float getRandFloat(float min, float max) throws IllegalArgumentException {
        Validate.isTrue((max > min), "Max can't be smaller than min!");
        return Float.valueOf(rand.nextFloat() * (max - min) + min);
    }

    public static boolean getChance(double chance) {
        return (chance >= 100.0D || chance >= getRandDouble(0.0D, 100.0D).doubleValue());
    }

    public static void main(String[] args) {}

    public static int floor(double value) {
        int i = (int)value;
        return (value < i) ? (i - 1) : i;
    }

    public static double round(double value, int decimals) {
        double p = Math.pow(10.0D, decimals);
        return Math.round(value * p) / p;
    }
}
