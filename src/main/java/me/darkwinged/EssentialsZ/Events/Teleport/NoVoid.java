package me.darkwinged.EssentialsZ.Events.Teleport;

import me.darkwinged.EssentialsZ.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class NoVoid implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void antiVoid(PlayerMoveEvent event) {
        if (plugin.getConfig().getBoolean("Teleportation.enabled", true)) {
            if (plugin.getConfig().getBoolean("Teleportation.Settings.No Void", true)) {
                Player player = event.getPlayer();
                if (player.getLocation().getY() <= -4) {
                    if (plugin.essentialsZSpawn.Location_Spawn.isEmpty()) {
                        player.teleport(player.getWorld().getSpawnLocation());
                        Vector vec = new Vector(0, 0, 0);
                        player.setVelocity(vec);
                        player.setFallDistance(0F);
                        return;
                    }
                    plugin.essentialsZSpawn.spawnAPI.TeleportToSpawn(player);
                    Vector vec = new Vector(0, 0, 0);
                    player.setVelocity(vec);
                    player.setFallDistance(0F);
                }
            }
        }

    }

}
