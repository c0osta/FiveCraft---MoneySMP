package ccode.fivecraft.moneysmp.core.users.levels;

import ccode.fivecraft.moneysmp.core.users.User;

public class UserLevel
{
    User user;
    int exp, level;
    public UserLevel(User user) {
        this.user = user;
        this.exp = 0;
        this.level = 1;
    }

    public User getUser() {
        return user;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
