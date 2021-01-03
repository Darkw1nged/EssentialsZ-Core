package me.darkwinged.Essentials.Events.Teleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.darkwinged.Essentials.Main;
import org.bukkit.util.Vector;

public class SpawnOnJoin implements Listener {

	private Main plugin;
    public SpawnOnJoin(Main plugin) {
        this.plugin = plugin; }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (plugin.getConfig().getBoolean("Teleportation", true)) {
            if (plugin.getConfig().getBoolean("Spawn On Player Join", true)) {
                if (!plugin.SpawnFile.getConfig().contains("Spawn.world")) {
                    player.teleport(player.getWorld().getSpawnLocation());
                    Vector vec = new Vector(0, 0, 0);
                    player.setVelocity(vec);
                    player.setFallDistance(0F);
                    return;
                }
                FileConfiguration spawn = plugin.SpawnFile.getConfig();
                World world = Bukkit.getWorld(spawn.getString("Spawn.world"));
                double x = spawn.getDouble("Spawn.x");
                double y = spawn.getDouble("Spawn.y");
                double z = spawn.getDouble("Spawn.z");
                float yaw = (float) spawn.getDouble("Spawn.yaw");
                float pitch = (float) spawn.getDouble("Spawn.pitch");
                Location loc = new Location(world, x, y, z, yaw, pitch);
                player.teleport(loc);
                Vector vec = new Vector(0, 0, 0);
                player.setVelocity(vec);
                player.setFallDistance(0F);
            }
        }
    }

}
