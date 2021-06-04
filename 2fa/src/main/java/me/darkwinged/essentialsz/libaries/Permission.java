package me.darkwinged.essentialsz.libaries;

public enum Permission
{
    Authenticator("EssentialsZ.Auth");

    private final String permissionNode;

    Permission(String permissionNode)
    {
        this.permissionNode = permissionNode;
    }

    public String getPermissionNode()
    {
        return permissionNode;
    }
}
