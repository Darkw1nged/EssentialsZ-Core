package me.darkwinged.Essentials.Events.Teleport;

import me.darkwinged.Essentials.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.util.Vector;

public class OnRespawn implements Listener {

    private Main plugin;
    public OnRespawn(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void Respawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (plugin.getConfig().getBoolean("Teleportation", true)) {
            assert plugin.essentialsZSpawn != null;
            if (plugin.essentialsZSpawn.api.getSpawn() == null) {
                player.teleport(player.getWorld().getSpawnLocation());
                Vector vec = new Vector(0, 0, 0);
                player.setVelocity(vec);
                player.setFallDistance(0F);
                return;
            }
            player.teleport(plugin.essentialsZSpawn.api.getSpawn());
            Vector vec = new Vector(0, 0, 0);
            player.setVelocity(vec);
            player.setFallDistance(0F);
        }
    }

}
