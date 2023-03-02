package ccode.fivecraft.moneysmp.core.users.stats;

import ccode.fivecraft.moneysmp.core.users.User;

public class UserStats
{
    User user;

    int block_amount;

    int kills_amount;

    double money_pln;

    double money;

    public UserStats(User user) {
        this.user = user;
        this.block_amount = 0;
        this.money_pln = 0;
        this.money = 0;
        this.kills_amount = 0;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money += money;
    }

    public double getMoney_pln() {
        return money_pln;
    }

    public void setMoney_pln(double money_pln) {
        this.money_pln += money_pln;
    }

    public int getBlock_amount() {
        return block_amount;
    }

    public void setBlock_amount(int block_amount) {
        this.block_amount += block_amount;
    }

    public int getKills_amount() {
        return kills_amount;
    }

    public void setKills_amount(int kills_amount) {
        this.kills_amount += kills_amount;
    }
}
