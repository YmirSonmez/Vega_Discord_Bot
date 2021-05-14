package me.ymir.vega.commands;


import net.dv8tion.jda.api.Permission;

import java.util.Arrays;
import java.util.List;

public enum CommandPermissions {
    ADMIN(Arrays.asList("310707683990962176"), null),
    PLAYER(null,null),
    GUILD_OWNER(null, Permission.MANAGE_PERMISSIONS);



    private final List<String> adminIDs;
    private final Permission permission;

    CommandPermissions(List<String> adminIDs, Permission permission) {
        this.adminIDs = adminIDs;
        this.permission = permission;
    }

    public List<String> getAdminIDs() {
        return adminIDs;
    }

    public Permission getPermission() {
        return permission;
    }
}
