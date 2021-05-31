package me.darkwinged.essentialsz.utils;

import me.darkwinged.essentialsz.EssentialsZSpawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SpawnAPI {

    private final EssentialsZSpawn plugin = EssentialsZSpawn.getInstance;

    public boolean isThereASpawn() {
        return !plugin.Location_Spawn.isEmpty();
    }

    public void TeleportToSpawn(Player player) {
        if (!plugin.SpawnLocation.getConfig().contains("world")) return;
        World world = Bukkit.getWorld(plugin.SpawnLocation.getConfig().getString("world"));
        if (player.getWorld() != world) {
            return;
        }

        int x = plugin.SpawnLocation.getConfig().getInt("x");
        int y = plugin.SpawnLocation.getConfig().getInt("y");
        int z = plugin.SpawnLocation.getConfig().getInt("z");
        int pitch = plugin.SpawnLocation.getConfig().getInt("pitch");
        int yaw = plugin.SpawnLocation.getConfig().getInt("yaw");

        Location spawn_loc = new Location(player.getWorld(), x, y, z, yaw, pitch);
        player.teleport(spawn_loc);
    }
    public void getSpawnConorOne(Location spawnLoc) { plugin.Spawn_Conor_One.get(spawnLoc); }
    public void getSpawnConorTwo(Location spawnLoc) { plugin.Spawn_Conor_Two.get(spawnLoc); }

}
