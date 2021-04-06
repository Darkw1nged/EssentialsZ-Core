package me.darkwinged.Essentials.Events.World;

import me.darkwinged.Essentials.Libaries.Lang.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.darkwinged.Essentials.Main;

public class NoItemDropAndPickup implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if (plugin.getConfig().getBoolean("Cancel Events.Item Pickup", true)) {
            if (player.hasPermission(Permissions.bypass) || player.hasPermission(Permissions.GlobalOverwrite))
                return;
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (plugin.getConfig().getBoolean("Cancel Events.Item Drop", true)) {
            if (player.hasPermission(Permissions.bypass) || player.hasPermission(Permissions.GlobalOverwrite))
                return;
            event.setCancelled(true);
        }
    }

}
