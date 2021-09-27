package me.darkwinged.essentialsz;

public enum Permission
{
    GLOBAL("EssentialsZ.*"),
    RELOAD("EssentialsZ.Reload"),

    ECONOMY("EssentialsZ.Economy"),
    BALANCE("EssentialsZ.Balance"),
    BALANCE_OTHER("EssentialsZ.Balance.Other"),
    PAY("EssentialsZ.Pay"),
    WITHDRAW("EssentialsZ.Withdraw"),
    SELLWAND("EssentialsZ.Sellwand"),

    SELL("EssentialsZ.Sell"),
    SELLHAND("EssentialsZ.Sellhand"),
    AUTOSELL("EssentialsZ.Autosell"),
    CHESTSELL("EssentialsZ.Chestsell"),

    DAY("EssentialsZ.Time.Day"),
    NIGHT("EssentialsZ.Time.Night"),
    CLEAR("EssentialsZ.Weather.Sun"),
    STORM("EssentialsZ.Weather.Storm"),

    HAT("EssentialsZ.Hat"),
    CRAFT("EssentialsZ.Craft"),
    CONDENSE("EssentialsZ.Condense"),
    SUICIDE("EssentialsZ.Suicide");

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
