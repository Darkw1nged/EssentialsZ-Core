package me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Telepotation;

import me.darkwinged.Essentials.Main;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.logging.Level;

public class HomesFile {

    private FileConfiguration customConfig;
    private File customConfigFile;
    private Main plugin;
    private String configName;

    public HomesFile(Main plugin, String configName) {
        this.plugin = plugin;
        this.configName = configName;
    }

    public void reloadConfig() {
        if (this.customConfigFile == null)
            this.customConfigFile = new File(this.plugin.getDataFolder(), String.valueOf(this.configName) + ".yml");
        this.customConfig = (FileConfiguration) YamlConfiguration.loadConfiguration(this.customConfigFile);
        Reader defConfigStream = null;
        try {
            defConfigStream = new InputStreamReader(this.plugin.getResource(String.valueOf(this.configName) + ".yml"), "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            this.customConfig.setDefaults((Configuration)defConfig);
        }
    }

    public FileConfiguration getConfig() {
        if (this.customConfig == null)
            reloadConfig();
        return this.customConfig;
    }

    public void saveConfig() {
        if (this.customConfig == null || this.customConfigFile == null)
            return;
        try {
            getConfig().save(this.customConfigFile);
        } catch (IOException ex) {
            this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.customConfigFile, ex);
        }
    }

    public void saveDefaultConfig() {
        if (this.customConfigFile == null)
            this.customConfigFile = new File(this.plugin.getDataFolder(), String.valueOf(this.configName) + ".yml");
        if (!this.customConfigFile.exists())
            this.plugin.saveResource(String.valueOf(this.configName) + ".yml", false);
    }

}
