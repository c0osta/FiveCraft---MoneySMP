package ccode.fivecraft.moneysmp.core.users;

import ccode.fivecraft.moneysmp.core.FiveCore;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager
{
    private final FiveCore plugin;

    private final Map<UUID, User> users;

    public UserManager(FiveCore plugin) {
        this.plugin = plugin;
        users = new HashMap<>();
    }

    public User registerUser(Player player) {
        User user = new User(player.getUniqueId(), player.getName());
        users.put(player.getUniqueId(), user);
        return user;
    }

    public User findUserByUniqueId(final UUID uniqueId) {
        return this.users.get(uniqueId);
    }

    public User findUserByNickname(final String nickname) {
        for (final User user : this.users.values()) {
            if (user.getNickName().equalsIgnoreCase(nickname)) {
                return user;
            }
        }
        return null;
    }


    public Map<UUID, User> getUsers() {
        return users;
    }
}
