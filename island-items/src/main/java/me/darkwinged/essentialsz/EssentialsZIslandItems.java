package me.darkwinged.essentialsz;

import me.darkwinged.essentialsz.data.Player_Data;
import me.darkwinged.essentialsz.island_chest.gui.struts.listeners.IChest_Chunk_Listener;
import me.darkwinged.essentialsz.island_chest.gui.struts.listeners.IChest_Menu_Listener;
import me.darkwinged.essentialsz.island_chest.gui.struts.listeners.IChest_Sign_Listener;
import me.darkwinged.essentialsz.libaries.MetricsLite;
import me.darkwinged.essentialsz.libaries.storage.CustomConfig;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class EssentialsZIslandItems extends JavaPlugin {

    public static EssentialsZIslandItems getInstance;
    public EssentialsZAPI essentialsZAPI = (EssentialsZAPI) Bukkit.getServer().getPluginManager().getPlugin("EssentialsZAPI");
    private static Economy econ = null;

    public CustomConfig Messages = new CustomConfig(this, "Messages", true);
    public CustomConfig Worth = new CustomConfig(this, "Worth", true);

    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("EssentialsZAPI") == null) {
            getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cDisabling plugin, No EssentialsZ API Found."));
            getServer().getPluginManager().disablePlugin(this);
        }
        if (!setupEconomy() ) {
            getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cDisabling plugin, No Vault Plugin Found."));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getInstance = this;
        registerEvents();
        Messages.saveDefaultConfig();

        new MetricsLite(this, 9811);
        getServer().getConsoleSender().sendMessage(essentialsZAPI.utils.chat("&aEssentialsZ Items has been enabled!"));
    }

    public void onDisable() {
        getServer().getConsoleSender().sendMessage(essentialsZAPI.utils.chat("&cEssentialsZ Items has been disabled!"));
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new Player_Data(), this);

        getServer().getPluginManager().registerEvents(new IChest_Sign_Listener(), this);
        getServer().getPluginManager().registerEvents(new IChest_Menu_Listener(), this);
        getServer().getPluginManager().registerEvents(new IChest_Chunk_Listener(), this);
    }

    // Economy Setup
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public Economy getEconomy() {
        return econ;
    }

}
