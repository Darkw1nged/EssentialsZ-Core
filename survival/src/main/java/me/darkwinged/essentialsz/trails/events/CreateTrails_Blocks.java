package me.darkwinged.essentialsz.trails.events;

import me.darkwinged.essentialsz.EssentialsZSurvival;
import me.darkwinged.essentialsz.trails.Trails_Manager;
import me.darkwinged.essentialsz.trails.utils.Blocks;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

public class CreateTrails_Blocks implements Listener {

    private final EssentialsZSurvival plugin = EssentialsZSurvival.getInstance;
    BukkitTask changes;
    int change;

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Location loc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() - 1, player.getLocation().getZ());
        Block block = loc.getBlock();
        Material old = block.getType();
        Location old_loc = block.getLocation();
        if (!Trails_Manager.PlayerTrail_Blocks.containsKey(uuid)) {
            // TEMP
            Trails_Manager.PlayerTrail_Blocks.put(uuid, Blocks.NETHERRACK);
            return;
        }

        switch (Trails_Manager.PlayerTrail_Blocks.get(uuid)) {
            case BEDROCK:
                /*change = new BukkitRunnable() {
                    public void run() {
                        if (block.getType() != Material.BEDROCK) {
                            block.setType(Material.BEDROCK);
                            return;
                        }
                        block.setType(old);
                        change.cancel();
                    }
                }.runTaskTimer(plugin, 0L, 20L);*/

                break;
            case NETHERRACK:
                change = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                    /*
                        TODO make method for blacklisted blocks.
                     */
                    if (block.getType() == Material.AIR || block.getType() == Material.WATER || block.getType() == Material.LAVA
                    || block.getType() == Material.LADDER || block.getType() == Material.CAULDRON) return;

                    if (block.getType() != Material.NETHERRACK) {
                        block.setType(Material.NETHERRACK);
                    } else {
                        block.setType(old);
                        Bukkit.getScheduler().cancelTask(change);
                    }
                }, 5L);
                break;
            case DIAMOND_ORE:
                /*change = new BukkitRunnable() {
                    public void run() {
                        if (block.getType() != Material.DIAMOND_ORE) {
                            block.setType(Material.DIAMOND_ORE);
                            return;
                        }
                        block.setType(old);
                        change.cancel();
                    }
                }.runTaskTimer(plugin, 0L, 20L);*/
                break;
            case GREEN_GLASS:
                /*change = new BukkitRunnable() {
                    public void run() {
                        if (block.getType() != Material.GREEN_STAINED_GLASS) {
                            block.setType(Material.GREEN_STAINED_GLASS);
                            return;
                        }
                        block.setType(old);
                        change.cancel();
                    }
                }.runTaskTimer(plugin, 0L, 20L);*/
                break;
            case BLUE_GLASS:
                /*change = new BukkitRunnable() {
                    public void run() {
                        if (block.getType() != Material.BLUE_STAINED_GLASS) {
                            block.setType(Material.BLUE_STAINED_GLASS);
                            return;
                        }
                        block.setType(old);
                        change.cancel();
                    }
                }.runTaskTimer(plugin, 0L, 20L);*/
                break;
            case RED_GLASS:
                /*change = new BukkitRunnable() {
                    public void run() {
                        if (block.getType() != Material.RED_STAINED_GLASS) {
                            block.setType(Material.RED_STAINED_GLASS);
                            return;
                        }
                        block.setType(old);
                        change.cancel();
                    }
                }.runTaskTimer(plugin, 0L, 20L);*/
                break;
        }

    }

}
