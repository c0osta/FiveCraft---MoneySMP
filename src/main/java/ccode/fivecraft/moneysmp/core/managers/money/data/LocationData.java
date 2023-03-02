package ccode.fivecraft.moneysmp.core.managers.money.data;

public class LocationData
{
    private int amount;

    public LocationData() {
        amount = 0;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public int getAmount() {
        return amount;
    }

}
