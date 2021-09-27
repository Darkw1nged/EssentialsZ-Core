package me.darkwinged.essentialsz;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class EssentialsZFactions extends JavaPlugin {

    public static EssentialsZFactions getInstance;
    public EssentialsZAPI essentialsZAPI = (EssentialsZAPI) Bukkit.getServer().getPluginManager().getPlugin("EssentialsZAPI");

    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("EssentialsZAPI") == null) {
            getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cDisabling plugin, No EssentialsZ API Found."));
            getServer().getPluginManager().disablePlugin(this);
        }
        getInstance = this;

        registerCommands();
        registerEvents();
        getServer().getConsoleSender().sendMessage(essentialsZAPI.utils.chat("&aEssentialsZ Factions has been enabled!"));
    }

    public void onDisable() {
        getServer().getConsoleSender().sendMessage(essentialsZAPI.utils.chat("&cEssentialsZ Factions has been disabled!"));
    }

    public void registerCommands() {
    }

    public void registerEvents() {

    }


}
