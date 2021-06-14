package me.darkwinged.essentialsz;

import me.darkwinged.essentialsz.chat.Chat_Manager;
import me.darkwinged.essentialsz.libaries.util.CustomConfig;
import me.darkwinged.essentialsz.miscellaneous.OnePlayerSleep;
import me.darkwinged.essentialsz.miscellaneous.WildCommand;
import me.darkwinged.essentialsz.trails.Trails_Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class EssentialsZSurvival extends JavaPlugin {

    public static EssentialsZSurvival getInstance;
    public EssentialsZAPI essentialsZAPI = (EssentialsZAPI) Bukkit.getServer().getPluginManager().getPlugin("EssentialsZAPI");

    public CustomConfig Messages = new CustomConfig(this, "Messages", true);

    public Chat_Manager chatManager;
    public Trails_Manager trailsManager;

    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("EssentialsZAPI") == null) {
            getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cDisabling plugin, No EssentialsZ API Found."));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getInstance = this;
        Messages.saveDefaultConfig();

        chatManager = new Chat_Manager(this);
        trailsManager = new Trails_Manager(this);

        loadMiscellaneous();
        loadConfig();
    }

    public void onDisable() {

    }

    public void loadConfig() {
        // Loading the config and custom files to the server
        FileConfiguration config = getConfig();
        config.options().copyDefaults(true);
        saveDefaultConfig();
        new File(getDataFolder(), "config.yml");
    }

    public void loadMiscellaneous() {
        getServer().getPluginManager().registerEvents(new OnePlayerSleep(), this);
        getCommand("wild").setExecutor(new WildCommand());
    }

}
