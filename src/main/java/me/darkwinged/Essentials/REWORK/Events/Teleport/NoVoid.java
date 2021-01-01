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
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class NoVoid implements Listener {

    private SpawnFile spawnFile;
    private Main plugin;
    public NoVoid(SpawnFile spawnFile, Main plugin) {
        this.spawnFile = spawnFile;
        this.plugin = plugin;
    }

    @EventHandler
    public void antivoid(PlayerMoveEvent event) {
        if (plugin.getConfig().getBoolean("Teleportation", true)) {
            if (plugin.getConfig().getBoolean("No Void", true)) {
                Player player = event.getPlayer();
                if (player.getLocation().getY() <= -4) {
                    if (!spawnFile.getConfig().contains("Spawn.world")) {
                        player.teleport(player.getWorld().getSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
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
                    player.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
                    player.setFallDistance(0F);
                }
            }
        }

    }

}
