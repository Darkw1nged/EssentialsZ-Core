package me.darkwinged.essentialsz;

import me.darkwinged.essentialsz.customelements.CustomInventory;
import me.darkwinged.essentialsz.customelements.CustomItems;
import me.darkwinged.essentialsz.libraries.CustomConfig;
import me.darkwinged.essentialsz.libraries.PlaceHolders;
import me.darkwinged.essentialsz.libraries.TicksPerSecond;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class EssentialsZAPI extends JavaPlugin {

    public CustomConfig InventoriesFile = new CustomConfig(this, "inventories", "API");
    public CustomConfig ItemsFile = new CustomConfig(this, "items", "API");

    public static EssentialsZAPI getInstance;
    public Utils utils;
    public CustomInventory customInventory;
    public CustomItems customItems;

    public void onEnable() {
        getInstance = this;

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceHolders().register();
        }

        utils = new Utils();
        customInventory = new CustomInventory();
        customItems = new CustomItems();

        InventoriesFile.saveDefaultConfig();
        ItemsFile.saveDefaultConfig();

        customInventory.loadInventories();
        customItems.loadItems();

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new TicksPerSecond(), 100L, 1L);

    }

    public void onDisable() {
        customInventory.saveInventories();
        customItems.saveItems();
    }

}
