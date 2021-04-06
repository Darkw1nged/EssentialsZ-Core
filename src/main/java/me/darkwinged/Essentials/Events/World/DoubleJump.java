package me.darkwinged.Essentials.Events.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Libaries.Lang.Permissions;
import me.darkwinged.Essentials.Libaries.Lang.Utils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class DoubleJump implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("World Events.Double Jump", true)) {
            Player player = event.getPlayer();
            if (Utils.Fly_List.contains(player.getUniqueId())) return;
            if (player.hasPermission(Permissions.GlobalOverwrite) || player.hasPermission(Permissions.DoubleJump)) {
                player.setAllowFlight(true);
            }
        }
    }

    @EventHandler
    public void onPlayerDoubleJump(PlayerToggleFlightEvent event) {
        if (plugin.getConfig().getBoolean("World Events.Double Jump", true)) {
            Player player = event.getPlayer();
            if (Utils.Fly_List.contains(player.getUniqueId())) return;
            if (player.hasPermission(Permissions.GlobalOverwrite) || player.hasPermission(Permissions.DoubleJump)) {
                if (player.getGameMode() != GameMode.CREATIVE) {
                    event.setCancelled(true);
                    Block block = player.getWorld().getBlockAt(player.getLocation().subtract(0.0D, 2.0D, 0.0D));
                    if (!block.getType().equals(Material.AIR)) {
                        Vector vector = player.getLocation().getDirection().multiply(1).setY(1);
                        player.setVelocity(vector);
                    }
                } else if (player.getGameMode() == GameMode.CREATIVE) {
                    player.setFlying(true);
                    player.setAllowFlight(true);
                } else if (player.getGameMode() != GameMode.SPECTATOR) {
                    event.setCancelled(true);
                    Block block = player.getWorld().getBlockAt(player.getLocation().subtract(0.0D, 2.0D, 0.0D));
                    if (!block.getType().equals(Material.AIR)) {
                        Vector vector = player.getLocation().getDirection().multiply(1).setY(1);
                        player.setVelocity(vector);
                    }
                } else if (player.getGameMode() == GameMode.SPECTATOR) {
                    event.setCancelled(true);
                }
            }
        }
    }

}
