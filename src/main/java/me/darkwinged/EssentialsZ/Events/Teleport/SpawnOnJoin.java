package me.darkwinged.EssentialsZ.Events.Teleport;

import me.darkwinged.EssentialsZ.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.util.Vector;

public class SpawnOnJoin implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (plugin.getConfig().getBoolean("Teleportation.enabled", true)) {
            if (plugin.getConfig().getBoolean("Teleportation.Settings.Spawn onJoin", true)) {
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
