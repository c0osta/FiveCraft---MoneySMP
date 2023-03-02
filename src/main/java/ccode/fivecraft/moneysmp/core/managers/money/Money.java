package ccode.fivecraft.moneysmp.core.managers.money;


import org.bukkit.Location;

public class Money
{
    private int amount;
    private String podlozil;

    public Money(int amount, String podlozil) {
        this.amount = amount;
        this.podlozil = podlozil;
    }

    public int getAmount() {
        return amount;
    }

    public String getPodlozil() {
        return podlozil;
    }
}
