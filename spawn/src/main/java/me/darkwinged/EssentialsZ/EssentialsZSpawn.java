package me.darkwinged.EssentialsZ;

import me.darkwinged.EssentialsZ.Commands.cmd_SetSpawn;
import me.darkwinged.EssentialsZ.Commands.cmd_Spawn;
import me.darkwinged.EssentialsZ.Utils.CustomConfig;
import me.darkwinged.EssentialsZ.Utils.SpawnAPI;
import me.darkwinged.EssentialsZ.Events.SpawnProtectionEvents;
import me.darkwinged.EssentialsZ.Utils.MetricsLite;
import me.darkwinged.EssentialsZ.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;

public final class EssentialsZSpawn extends JavaPlugin {

    public SpawnAPI spawnAPI;
    public static EssentialsZSpawn getInstance;
    public CustomConfig SpawnLocation = new CustomConfig(this, "Spawn", "");
    public CustomConfig MessagesFile = new CustomConfig(this, "Messages", "");
    public CustomConfig ConfigFile = new CustomConfig(this, "config", "");
    public ArrayList<UUID> InRegion = new ArrayList<>();
    public Boolean isEnabled = ConfigFile.getConfig().getBoolean("Teleportation.enabled");

    public int radius = getConfig().getInt("Spawn.Settings.Size") / 2;

    public void onEnable() {
        getInstance = this;
        spawnAPI = new SpawnAPI();

        registerCommands();
        registerEvents();

        MetricsLite metricsLite = new MetricsLite(this, 9811);
        getServer().getConsoleSender().sendMessage(Utils.chat("&aEssentialsZ Spawn has been enabled!"));
    }

    public void onDisable() {
        getServer().getConsoleSender().sendMessage(Utils.chat("&cEssentialsZ Spawn has been disabled!"));
    }

    public void registerCommands() {
        getCommand("setspawn").setExecutor(new cmd_SetSpawn());
        getCommand("spawn").setExecutor(new cmd_Spawn());
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new SpawnProtectionEvents(), this);
    }

    public final List<Location> Location_Spawn = new ArrayList<>();
    public final Map<Location, Location> Spawn_Conor_One = new HashMap<>();
    public final Map<Location, Location> Spawn_Conor_Two = new HashMap<>();

    private void loadSpawn() {
        if (SpawnLocation.getConfig().contains("world")) {
            World world = Bukkit.getWorld(SpawnLocation.getConfig().getString("world"));
            int x = SpawnLocation.getConfig().getInt("x");
            int y = SpawnLocation.getConfig().getInt("y");
            int z = SpawnLocation.getConfig().getInt("z");
            int yaw = SpawnLocation.getConfig().getInt("yaw");
            int pitch = SpawnLocation.getConfig().getInt("pitch");

            Location loc = new Location(world, x, y, z, yaw, pitch);
            Location conor1 = new Location(world, x + radius, y + radius, z + radius, yaw, pitch);
            Location conor2 = new Location(world, x - radius, y - radius, z - radius, yaw, pitch);
            Location_Spawn.add(loc);
            Spawn_Conor_One.put(loc, conor1);
            Spawn_Conor_Two.put(loc, conor2);
        }
    }

}
