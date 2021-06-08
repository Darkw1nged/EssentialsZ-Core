package me.darkwinged.essentialsz;

import me.darkwinged.essentialsz.Events.Chat;
import me.darkwinged.essentialsz.Events.NotifyFriend;
import me.darkwinged.essentialsz.Events.PlayerData;
import me.darkwinged.essentialsz.Libaries.MetricsLite;
import me.darkwinged.essentialsz.libaries.util.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class EssentialsZFriends extends JavaPlugin {

    public static EssentialsZFriends getInstance;
    public EssentialsZAPI essentialsZAPI = (EssentialsZAPI) Bukkit.getServer().getPluginManager().getPlugin("EssentialsZAPI");

    public CustomConfig Messages = new CustomConfig(this, "Messages", true);

    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("EssentialsZAPI") == null) {
            getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cDisabling plugin, No EssentialsZ API Found."));
            getServer().getPluginManager().disablePlugin(this);
        }
        getInstance = this;
        registerEvents();
        Messages.saveDefaultConfig();

        new MetricsLite(this, 9811);
        getServer().getConsoleSender().sendMessage(essentialsZAPI.utils.chat("&aEssentialsZ Friends has been enabled!"));
    }

    public void onDisable() {
        getServer().getConsoleSender().sendMessage(essentialsZAPI.utils.chat("&cEssentialsZ Friends has been disabled!"));
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerData(), this);
        getServer().getPluginManager().registerEvents(new NotifyFriend(), this);
        getServer().getPluginManager().registerEvents(new Chat(), this);
    }

}
