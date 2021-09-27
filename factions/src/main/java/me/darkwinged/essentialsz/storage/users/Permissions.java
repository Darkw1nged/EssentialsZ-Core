package me.darkwinged.essentialsz.storage.users;

public enum Permissions {

    ADMIN_BYPASS("Factions.Bypass");

    private final String permissionNode;

    Permissions(String permissionNode)
    {
        this.permissionNode = permissionNode;
    }

    public String getPermissionNode()
    {
        return permissionNode;
    }

}
