package ccode.fivecraft.moneysmp.core.users.permissions;

import ccode.fivecraft.moneysmp.core.users.User;

import java.util.Set;

public class Permission
{
    User user;

    //lista permisji
    private final Set<String> permissions;


    public Permission(Set<String> permissions) {
        this.permissions = permissions;
    }
}
