package me.darkwinged.essentialsz;

import me.darkwinged.essentialsz.commands.CommandRegistry;
import me.darkwinged.essentialsz.commands.decorator.factory.PlayersOnlyDecoratorFactory;
import me.darkwinged.essentialsz.commands.processor.CommandProcessor;
import me.darkwinged.essentialsz.commands.processor.annotation.CommandAnnotationProcessor;
import me.darkwinged.essentialsz.customelements.CustomInventory;
import me.darkwinged.essentialsz.customelements.CustomItems;
import me.darkwinged.essentialsz.inject.BukkitServicesInjector;
import me.darkwinged.essentialsz.inject.ServicesInjector;
import me.darkwinged.essentialsz.libraries.CustomConfig;
import me.darkwinged.essentialsz.libraries.PlaceHolders;
import me.darkwinged.essentialsz.libraries.TicksPerSecond;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
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

        ServicesManager servicesManager = getServer().getServicesManager();
        ServicesInjector servicesInjector = new BukkitServicesInjector(servicesManager);
        getServer().getServicesManager().register(ServicesInjector.class, servicesInjector, this, ServicePriority.Normal);

        registerService(PlayersOnlyDecoratorFactory.class);

        registerService(CommandProcessor.class, CommandAnnotationProcessor.class);
        registerService(CommandRegistry.class);
    }

    public void onDisable() {
        customInventory.saveInventories();
        customItems.saveItems();
    }

    private <T> T registerService(Class<T> serviceClass)
    {
        return registerService(serviceClass, serviceClass);
    }

    private <T> T registerService(Class<T> serviceClass, Class<? extends T> implementationClass)
    {
        ServicesManager  servicesManager = getServer().getServicesManager();
        ServicesInjector injector        = servicesManager.load(ServicesInjector.class);
        T instance = injector.createInstance(implementationClass);
        servicesManager.register(serviceClass, instance, this, ServicePriority.Normal);
        return instance;
    }
}
