package me.darkwinged.essentialsz;

import me.darkwinged.essentialsz.commands.CommandRegistry;
import me.darkwinged.essentialsz.commands.decorator.factory.PlayersOnlyDecoratorFactory;
import me.darkwinged.essentialsz.commands.processor.CommandProcessor;
import me.darkwinged.essentialsz.commands.processor.annotation.CommandAnnotationProcessor;
import me.darkwinged.essentialsz.inject.BukkitServicesInjector;
import me.darkwinged.essentialsz.inject.ServicesInjector;
import me.darkwinged.essentialsz.libaries.util.Items;
import me.darkwinged.essentialsz.libaries.Utils;
import me.darkwinged.essentialsz.libaries.ui.Actionbar;
import me.darkwinged.essentialsz.libaries.ui.Bossbar;
import me.darkwinged.essentialsz.libaries.ui.Title;
import me.darkwinged.essentialsz.libaries.util.PlaceHolders;
import me.darkwinged.essentialsz.libaries.util.TicksPerSecond;
import me.darkwinged.essentialsz.message.MessageService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class EssentialsZAPI extends JavaPlugin {
    public static EssentialsZAPI getInstance;
    public Utils utils;
    public Items items;
    public Actionbar actionBar;
    public Bossbar bossbar;
    public Title title;

    public void onEnable() {
        getInstance = this;

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceHolders().register();
        }

        utils = new Utils();
        items = new Items();
        actionBar = new Actionbar();
        bossbar = new Bossbar();
        title = new Title();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new TicksPerSecond(), 100L, 1L);

        ServicesManager servicesManager = getServer().getServicesManager();
        ServicesInjector servicesInjector = new BukkitServicesInjector(servicesManager);
        getServer().getServicesManager().register(ServicesInjector.class, servicesInjector, this, ServicePriority.Normal);

        registerService(MessageService.class);

        registerService(PlayersOnlyDecoratorFactory.class);

        registerService(CommandProcessor.class, CommandAnnotationProcessor.class);
        registerService(CommandRegistry.class);
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
