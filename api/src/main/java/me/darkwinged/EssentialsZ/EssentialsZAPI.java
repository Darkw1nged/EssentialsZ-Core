package me.darkwinged.EssentialsZ;

import me.darkwinged.EssentialsZ.CustomElements.CustomInventory;
import me.darkwinged.EssentialsZ.CustomElements.CustomItems;
import me.darkwinged.EssentialsZ.Libaries.CustomConfig;
import me.darkwinged.EssentialsZ.Libaries.PlaceHolders;
import me.darkwinged.EssentialsZ.Libaries.TicksPerSecond;
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
