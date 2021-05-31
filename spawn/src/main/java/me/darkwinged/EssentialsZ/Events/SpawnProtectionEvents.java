package me.darkwinged.EssentialsZ.Events;

import me.darkwinged.EssentialsZ.EssentialsZSpawn;
import me.darkwinged.EssentialsZ.Utils.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class SpawnProtectionEvents implements Listener {

    private final EssentialsZSpawn plugin = EssentialsZSpawn.getInstance;

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (plugin.Location_Spawn.isEmpty()) return;
        Location Spawn = plugin.Location_Spawn.get(0);
        Location ConorOne = plugin.Spawn_Conor_One.get(Spawn);
        Location ConorTwo = plugin.Spawn_Conor_Two.get(Spawn);

        if (!player.getWorld().equals(Spawn.getWorld())) return;

        if (player.getLocation().getBlockY() >= ConorTwo.getBlockY() - 1 && player.getLocation().getBlockX() >= ConorTwo.getBlockX() - 1 &&
                player.getLocation().getBlockZ() >= ConorTwo.getBlockZ() - 1 && player.getLocation().getBlockY() <= ConorOne.getBlockY() + 1 &&
                player.getLocation().getX() <= ConorOne.getBlockX() + 1 && player.getLocation().getBlockZ() <= ConorOne.getBlockZ() + 1) {

            if (plugin.InRegion.isEmpty() || !plugin.InRegion.contains(player.getUniqueId())) {
                plugin.InRegion.add(player.getUniqueId());
            }
        } else {
            plugin.InRegion.remove(player.getUniqueId());
        }
    }

    // FLAG :: Build
    @EventHandler
    public void Build_Break(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!plugin.isEnabled) return;
        if (plugin.ConfigFile.getConfig().getBoolean("Teleportation.Settings.Spawn.Flags.Cancel-Build", true)) {
            if (plugin.InRegion.contains(player.getUniqueId())) {
                if (player.hasPermission("EssentialsZ.Spawn.bypass") || player.hasPermission("EssentialsZ.Spawn.*")) return;
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void Build_Place(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!plugin.isEnabled) return;
        if (plugin.ConfigFile.getConfig().getBoolean("Teleportation.Settings.Spawn.Flags.Cancel-Build", true)) {
            if (plugin.InRegion.contains(player.getUniqueId())) {
                if (player.hasPermission("EssentialsZ.Spawn.bypass") || player.hasPermission("EssentialsZ.Spawn.*")) return;
                event.setCancelled(true);
            }
        }
    }

    // FLAG :: PVP
    @EventHandler
    public void PVP(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();
        if (!plugin.isEnabled) return;
        if (plugin.ConfigFile.getConfig().getBoolean("Teleportation.Settings.Spawn.Flags.Cancel-PVP", true)) {
            if (plugin.InRegion.contains(player.getUniqueId())) {
                if (event.getDamager() instanceof Player) {
                    if (event.getDamager().hasPermission("EssentialsZ.Spawn.bypass") || event.getDamager().hasPermission("EssentialsZ.Spawn.*")) return;
                    if (event.getEntity() instanceof Player) {
                        event.setCancelled(true);
                        player.sendMessage(Utils.chat("&cHey! You can not pvp in a protected area!"));
                    }
                }
            }
        }
    }

    // FLAG :: Mob-Spawning
    @EventHandler
    public void MobSpawn(CreatureSpawnEvent event) {
        Entity entity = event.getEntity();
        if (!plugin.isEnabled) return;
        if (plugin.ConfigFile.getConfig().getBoolean("Teleportation.Settings.Spawn.Flags.Cancel-Mob-Spawning", true)) {
            if (entity instanceof Monster) {
                if (plugin.Location_Spawn.isEmpty()) return;
                Location Spawn = plugin.Location_Spawn.get(0);
                Location ConorOne = plugin.Spawn_Conor_One.get(Spawn);
                Location ConorTwo = plugin.Spawn_Conor_Two.get(Spawn);
                if (!entity.getWorld().equals(Spawn.getWorld())) return;
                if (entity.getLocation().getBlockY() >= ConorTwo.getBlockY() - 1 && entity.getLocation().getBlockX() >= ConorTwo.getBlockX() - 1 &&
                        entity.getLocation().getBlockZ() >= ConorTwo.getBlockZ() - 1 && entity.getLocation().getBlockY() <= ConorOne.getBlockY() + 1 &&
                        entity.getLocation().getX() <= ConorOne.getBlockX() + 1 && entity.getLocation().getBlockZ() <= ConorOne.getBlockZ() + 1) {
                    event.setCancelled(true);
                } else if (entity.getLocation().getBlockY() >= ConorTwo.getBlockY() - 1 && entity.getLocation().getBlockX() >= ConorTwo.getBlockX() - 1 &&
                        entity.getLocation().getBlockZ() >= ConorTwo.getBlockZ() - 1 && entity.getLocation().getBlockY() <= ConorOne.getBlockY() + 1 &&
                        entity.getLocation().getX() <= ConorOne.getBlockX() + 1 && entity.getLocation().getBlockZ() <= ConorOne.getBlockZ() + 1 &&
                        event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.NATURAL)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    // FLAG :: Animal-Spawning
    @EventHandler
    public void AnimalSpawn(CreatureSpawnEvent event) {
        Entity entity = event.getEntity();
        if (!plugin.isEnabled) return;
        if (plugin.ConfigFile.getConfig().getBoolean("Teleportation.Settings.Spawn.Flags.Cancel-Animal-Spawning", true)) {
            if (event.getEntity() instanceof Animals) {
                if (plugin.Location_Spawn.isEmpty()) return;
                Location Spawn = plugin.Location_Spawn.get(0);
                Location ConorOne = plugin.Spawn_Conor_One.get(Spawn);
                Location ConorTwo = plugin.Spawn_Conor_Two.get(Spawn);
                if (!entity.getWorld().equals(Spawn.getWorld())) return;
                if (entity.getLocation().getBlockY() >= ConorTwo.getBlockY() - 1 && entity.getLocation().getBlockX() >= ConorTwo.getBlockX() - 1 &&
                        entity.getLocation().getBlockZ() >= ConorTwo.getBlockZ() - 1 && entity.getLocation().getBlockY() <= ConorOne.getBlockY() + 1 &&
                        entity.getLocation().getX() <= ConorOne.getBlockX() + 1 && entity.getLocation().getBlockZ() <= ConorOne.getBlockZ() + 1) {
                    event.setCancelled(true);
                } else if (entity.getLocation().getBlockY() >= ConorTwo.getBlockY() - 1 && entity.getLocation().getBlockX() >= ConorTwo.getBlockX() - 1 &&
                        entity.getLocation().getBlockZ() >= ConorTwo.getBlockZ() - 1 && entity.getLocation().getBlockY() <= ConorOne.getBlockY() + 1 &&
                        entity.getLocation().getX() <= ConorOne.getBlockX() + 1 && entity.getLocation().getBlockZ() <= ConorOne.getBlockZ() + 1 &&
                        event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.NATURAL)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    // FLAG :: Explosions
    @EventHandler
    public void Explosions(EntityExplodeEvent event) {
        Entity entity = event.getEntity();
        if (!plugin.isEnabled) return;
        if (plugin.ConfigFile.getConfig().getBoolean("Teleportation.Settings.Spawn.Flags.Cancel-Explosions", true)) {
            if (plugin.Location_Spawn.isEmpty()) return;
            Location Spawn = plugin.Location_Spawn.get(0);
            Location ConorOne = plugin.Spawn_Conor_One.get(Spawn);
            Location ConorTwo = plugin.Spawn_Conor_Two.get(Spawn);
            if (!entity.getWorld().equals(Spawn.getWorld())) return;
            if (entity.getLocation().getBlockY() >= ConorTwo.getBlockY() - 1 && entity.getLocation().getBlockX() >= ConorTwo.getBlockX() - 1 &&
                    entity.getLocation().getBlockZ() >= ConorTwo.getBlockZ() - 1 && entity.getLocation().getBlockY() <= ConorOne.getBlockY() + 1 &&
                    entity.getLocation().getX() <= ConorOne.getBlockX() + 1 && entity.getLocation().getBlockZ() <= ConorOne.getBlockZ() + 1) {
                event.setCancelled(true);
            }
        }
    }

    // FLAG :: Mob-Grief
    @EventHandler
    public void Grief(EntityChangeBlockEvent event) {
        Entity entity = event.getEntity();
        if (!plugin.isEnabled) return;
        if (plugin.ConfigFile.getConfig().getBoolean("Teleportation.Settings.Spawn.Flags.Cancel-Mob-Grief", true)) {
            if (plugin.Location_Spawn.isEmpty()) return;
            Location Spawn = plugin.Location_Spawn.get(0);
            Location ConorOne = plugin.Spawn_Conor_One.get(Spawn);
            Location ConorTwo = plugin.Spawn_Conor_Two.get(Spawn);
            if (!entity.getWorld().equals(Spawn.getWorld())) return;
            if (entity.getLocation().getBlockY() >= ConorTwo.getBlockY() - 1 && entity.getLocation().getBlockX() >= ConorTwo.getBlockX() - 1 &&
                    entity.getLocation().getBlockZ() >= ConorTwo.getBlockZ() - 1 && entity.getLocation().getBlockY() <= ConorOne.getBlockY() + 1 &&
                    entity.getLocation().getX() <= ConorOne.getBlockX() + 1 && entity.getLocation().getBlockZ() <= ConorOne.getBlockZ() + 1) {
                event.setCancelled(true);
            }
        }
    }

    // FLAG :: Chest-Access
    @EventHandler
    public void Interact_Chest(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!plugin.isEnabled) return;
        if (plugin.ConfigFile.getConfig().getBoolean("Teleportation.Settings.Spawn.Flags.Cancel-Chest-Access", true)) {
            if (plugin.InRegion.contains(player.getUniqueId())) {
                if (player.hasPermission("EssentialsZ.Spawn.bypass") || player.hasPermission("EssentialsZ.Spawn.*")) return;
                if (event.getClickedBlock() == null) return;
                if (event.getClickedBlock().getType().equals(Material.CHEST) || event.getClickedBlock().getType().equals(Material.TRAPPED_CHEST) ||
                        event.getClickedBlock().getType().equals(Material.ENDER_CHEST)) event.setCancelled(true);
            }
        }
    }

    // FLAG :: Interact
    @EventHandler
    public void Interact(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!plugin.isEnabled) return;
        if (plugin.ConfigFile.getConfig().getBoolean("Teleportation.Settings.Spawn.Flags.Cancel-Interact", true)) {
            if (plugin.InRegion.contains(player.getUniqueId())) {
                if (player.hasPermission("EssentialsZ.Spawn.bypass") || player.hasPermission("EssentialsZ.Spawn.*")) return;
                if (event.getClickedBlock() == null) return;
                if (event.getClickedBlock().getType().equals(Material.CHEST) || event.getClickedBlock().getType().equals(Material.TRAPPED_CHEST) ||
                        event.getClickedBlock().getType().equals(Material.ENDER_CHEST)) return;
                event.setCancelled(true);
                player.sendMessage(Utils.chat("&cHey! You can not do that!"));
            }
        }
    }

    // FLAG :: Redstone
    @EventHandler
    public void Redstone(BlockRedstoneEvent event) {
        Block block = event.getBlock();
        if (!plugin.isEnabled) return;
        if (plugin.ConfigFile.getConfig().getBoolean("Teleportation.Settings.Spawn.Flags.Cancel-Redstone", false)) {
            if (plugin.Location_Spawn.isEmpty()) return;
            Location Spawn = plugin.Location_Spawn.get(0);
            Location ConorOne = plugin.Spawn_Conor_One.get(Spawn);
            Location ConorTwo = plugin.Spawn_Conor_Two.get(Spawn);
            if (!block.getWorld().equals(Spawn.getWorld())) return;
            if (block.getLocation().getBlockY() >= ConorTwo.getBlockY() - 1 && block.getLocation().getBlockX() >= ConorTwo.getBlockX() - 1 &&
                    block.getLocation().getBlockZ() >= ConorTwo.getBlockZ() - 1 && block.getLocation().getBlockY() <= ConorOne.getBlockY() + 1 &&
                    block.getLocation().getX() <= ConorOne.getBlockX() + 1 && block.getLocation().getBlockZ() <= ConorOne.getBlockZ() + 1) {
                event.setNewCurrent(0);
            }
        }
    }

    // FLAG :: Chat
    @EventHandler
    public void Chat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (!plugin.isEnabled) return;
        if (plugin.ConfigFile.getConfig().getBoolean("Teleportation.Settings.Spawn.Flags.Cancel-Chat", true)) {
            if (plugin.InRegion.contains(player.getUniqueId())) {
                if (player.hasPermission("EssentialsZ.Spawn.bypass") || player.hasPermission("EssentialsZ.Spawn.*")) return;
                event.setCancelled(true);
                player.sendMessage(Utils.chat("&cHey! Hey you can not do that!"));
            }
        }
    }

    // FLAG :: Teleport
    @EventHandler
    public void Teleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (!plugin.isEnabled) return;
        if (plugin.ConfigFile.getConfig().getBoolean("Teleportation.Settings.Spawn.Flags.Cancel-Teleport", true)) {
            if (plugin.InRegion.contains(player.getUniqueId())) {
                if (player.hasPermission("EssentialsZ.Spawn.bypass") || player.hasPermission("EssentialsZ.Spawn.*")) return;
                event.setCancelled(true);
            }
        }
    }

    // FLAG :: Hunger
    @EventHandler
    public void Hunger(FoodLevelChangeEvent event) {
        Player player = (Player) event.getEntity();
        if (!plugin.isEnabled) return;
        if (plugin.ConfigFile.getConfig().getBoolean("Teleportation.Settings.Spawn.Flags.Lose-Hunger", true)) {
            if (plugin.InRegion.contains(player.getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

}
