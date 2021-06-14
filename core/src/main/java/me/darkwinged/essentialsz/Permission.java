package me.darkwinged.essentialsz;

public enum Permission
{
    GLOBAL("EssentialsZ.*"),
    RELOAD("EssentialsZ.Reload"),

    ECONOMY("EssentialsZ.Economy"),
    BALANCE("EssentialsZ.Balance"),
    BALANCE_OTHER("EssentialsZ.Balance.Other"),
    PAY("EssentialsZ.pay"),

    DAY("EssentialsZ.Time.Day"),
    NIGHT("EssentialsZ.Time.Night"),

    CLEAR("EssentialsZ.Weather.Sun"),
    STORM("EssentialsZ.Weather.Storm"),

    CONDENSE("EssentialsZ.Condense");

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
