package me.darkwinged.essentialsz;

import me.darkwinged.essentialsz.events.AuthEvent;
import me.darkwinged.essentialsz.events.AuthJoin;
import me.darkwinged.essentialsz.libaries.MetricsLite;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class EssentialsZ2FA extends JavaPlugin implements Listener {

    public static EssentialsZ2FA getInstance;
    public EssentialsZAPI essentialsZAPI = (EssentialsZAPI) Bukkit.getServer().getPluginManager().getPlugin("EssentialsZAPI");
    public List<UUID> AuthLogin = new ArrayList<>();

    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("EssentialsZAPI") == null) {
            getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cDisabling plugin, No EssentialsZ API Found."));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getInstance = this;
        new MetricsLite(this, 9811);

        loadConfig();
        getServer().getPluginManager().registerEvents(new AuthJoin(), this);
        getServer().getPluginManager().registerEvents(new AuthEvent(), this);

        getServer().getConsoleSender().sendMessage(essentialsZAPI.utils.chat("&aEssentialsZ 2FA has been enabled."));
    }

    public void onDisable() {
        getServer().getConsoleSender().sendMessage(essentialsZAPI.utils.chat("&cEssentialsZ 2FA has been disabled."));
        saveConfig();
    }

    private FileConfiguration config;
    private File cfile;
    public void loadConfig() {
        config = getConfig();
        config.options().copyDefaults(true);
        saveDefaultConfig();
        cfile = new File(getDataFolder(), "config.yml");
    }

}
