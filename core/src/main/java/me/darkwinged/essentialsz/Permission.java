package me.darkwinged.essentialsz;

public enum Permission
{
    ;

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
