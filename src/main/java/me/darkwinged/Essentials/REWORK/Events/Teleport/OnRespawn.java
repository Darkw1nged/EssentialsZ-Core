package me.darkwinged.Essentials.REWORK.Events.Teleport;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Telepotation.SpawnFile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.util.Vector;

public class OnRespawn implements Listener {

    private SpawnFile spawnFile;
    private Main plugin;
    public OnRespawn(SpawnFile spawnFile, Main plugin) {
        this.spawnFile = spawnFile;
        this.plugin = plugin;
    }

    @EventHandler
    public void Respawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (plugin.getConfig().getBoolean("Teleportation", true)) {
            if (!spawnFile.getConfig().contains("Spawn.world")) {
                player.teleport(player.getWorld().getSpawnLocation());
                Vector vec = new Vector(0, 0, 0);
                player.setVelocity(vec);
                player.setFallDistance(0F);
                return;
            }
            FileConfiguration spawn = spawnFile.getConfig();
            World world = Bukkit.getWorld(spawn.getString("Spawn.world"));
            double x = spawn.getDouble("Spawn.x");
            double y = spawn.getDouble("Spawn.y");
            double z = spawn.getDouble("Spawn.z");
            float yaw = (float) spawn.getDouble("Spawn.yaw");
            float pitch = (float) spawn.getDouble("Spawn.pitch");
            Location loc = new Location(world, x, y, z, yaw, pitch);
            event.setRespawnLocation(loc);
            Vector vec = new Vector(0, 0, 0);
            player.setVelocity(vec);
            player.setFallDistance(0F);
        }
    }

}
