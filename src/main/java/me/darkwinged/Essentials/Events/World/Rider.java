package me.darkwinged.Essentials.Events.World;

import me.darkwinged.Essentials.Libaries.Lang.Permissions;
import me.darkwinged.Essentials.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class Rider implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void interact(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (plugin.getConfig().getBoolean("World Events.Ride", true)) {
            if (player.hasPermission(Permissions.RidePlayer) || player.hasPermission(Permissions.GlobalOverwrite)) {
                if (event.getRightClicked() instanceof Player) {
                    Entity target = event.getRightClicked();
                    Location loc = new Location(target.getWorld(), target.getLocation().getX(), target.getLocation().getY() - 5, target.getLocation().getZ());

                    ArmorStand armorStand = target.getWorld().spawn(loc, ArmorStand.class);
                    armorStand.setAI(false);
                    armorStand.setBasePlate(false);
                    armorStand.setCanPickupItems(false);
                    armorStand.setCollidable(false);
                    armorStand.setGravity(false);
                    armorStand.setInvulnerable(true);
                    armorStand.setRemoveWhenFarAway(true);
                    armorStand.setSilent(true);
                    armorStand.setVisible(false);
                    armorStand.setSmall(true);
                    armorStand.setHealth(2);
                    armorStand.setMaxHealth(2);
                    armorStand.addPassenger(player);

                    Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                        public void run() {
                            if (armorStand.getPassengers().isEmpty()) {
                                armorStand.remove();
                            }
                        }
                    }, 0L, 20);

                    target.addPassenger(armorStand);
                }
            }
        }
    }



}
