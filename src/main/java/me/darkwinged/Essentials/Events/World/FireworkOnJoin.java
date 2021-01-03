package me.darkwinged.Essentials.Events.World;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.darkwinged.Essentials.Main;

public class FireworkOnJoin implements Listener {

    private Main plugin;
    public FireworkOnJoin(Main plugin) { this.plugin = plugin; }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Location loc = player.getLocation();
        if (plugin.getConfig().getBoolean("Firework On Player Join", true)) {
            if (plugin.getConfig().getBoolean("Spawn On Player Join", true)) {
                player.getWorld().spawnEntity(loc, EntityType.FIREWORK);
            }
        }
    }

}
