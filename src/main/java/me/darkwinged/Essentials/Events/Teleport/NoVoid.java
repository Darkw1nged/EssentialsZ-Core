package me.darkwinged.Essentials.Events.Teleport;

import me.darkwinged.Essentials.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class NoVoid implements Listener {

    private Main plugin;
    public NoVoid(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void antivoid(PlayerMoveEvent event) {
        if (plugin.getConfig().getBoolean("Teleportation", true)) {
            /*if (plugin.getConfig().getBoolean("No Void", true)) {
                Player player = event.getPlayer();
                if (player.getLocation().getY() <= -4) {
                    if (!plugin.SpawnFile.getConfig().contains("Spawn.world")) {
                        player.teleport(player.getWorld().getSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
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
                    player.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
                    player.setFallDistance(0F);
                }
            }*/
        }

    }

}
