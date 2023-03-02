package ccode.fivecraft.moneysmp.core.users;

import ccode.fivecraft.moneysmp.core.users.levels.UserLevel;
import ccode.fivecraft.moneysmp.core.users.stats.UserStats;

import java.util.UUID;

public class User
{
    private UUID uuid;

    private String nickName;

    private UserStats userStats;

    private UserLevel userLevel;

    public User(UUID uuid, String nickName) {
        this.uuid = uuid;
        this.nickName = nickName;
        this.userStats = new UserStats(this);
        this.userLevel = new UserLevel(this);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public UserStats getUserStats() {
        return userStats;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }
}

