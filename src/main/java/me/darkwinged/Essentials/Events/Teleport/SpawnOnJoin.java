package me.darkwinged.Essentials.Events.Teleport;

import me.darkwinged.Essentials.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.util.Vector;

public class SpawnOnJoin implements Listener {

	private Main plugin;
    public SpawnOnJoin(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (plugin.getConfig().getBoolean("Teleportation", true)) {
            if (plugin.getConfig().getBoolean("Teleportation.Settings.Spawn onJoin", true)) {
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

}
