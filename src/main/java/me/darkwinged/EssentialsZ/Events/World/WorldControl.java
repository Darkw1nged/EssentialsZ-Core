package me.darkwinged.EssentialsZ.Events.World;

import me.darkwinged.EssentialsZ.Main;
import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Libaries.Lang.Utils;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class WorldControl implements Listener {

    private final Main plugin = Main.getInstance;

    // Cancel Fall damage
    @EventHandler
    public void Fall(EntityDamageEvent event) {
        if (plugin.getConfig().getBoolean("Cancel Events.Fall Damage", true)) {
            if (event.getEntity() instanceof Player) {
                Player player = (Player)event.getEntity();
                if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                    player.setFallDistance(0F);
                    event.setCancelled(true);
                }
            }
        }
    }

    // Cancel Hunger loss
    @EventHandler
    public void HungerLoss(FoodLevelChangeEvent event) {
        if (plugin.getConfig().getBoolean("Cancel Events.Hunger", true)) {
            event.setCancelled(true);
        }
    }

    // Cancel Hopper craft
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCraft(CraftItemEvent event) {
        Player player = (Player)event.getWhoClicked();
        if (plugin.getConfig().getBoolean("Cancel Events.Hopper Craft", true)) {
            if (event.getRecipe().getResult().getType() == Material.HOPPER) {
                if (event.getWhoClicked().hasPermission(Permissions.bypass) || event.getWhoClicked().hasPermission(Permissions.GlobalOverwrite))
                    return;
                event.setCancelled(true);
                if (plugin.getConfig().getBoolean("Cancel Events.Hopper Craft Message", true)) {
                    player.sendMessage(ChatColor.RED + "You can not craft hoppers!");
                }
            }
        }
    }

    // Cancel Experience
    @EventHandler
    public void MobKill(EntityDeathEvent event) {
        if (plugin.getConfig().getBoolean("Cancel Events.Experience", true)) {
            event.setDroppedExp(0);
        }
    }

    @EventHandler
    public void OreMine(BlockBreakEvent event) {
        if (plugin.getConfig().getBoolean("Cancel Events.Experience", true)) {
            event.setExpToDrop(0);
        }
    }

    @EventHandler
    public void Breed(EntityBreedEvent event) {
        if (plugin.getConfig().getBoolean("Cancel Events.Experience", true)) {
            event.setExperience(0);
        }
    }

    @EventHandler
    public void EXPBottle(ExpBottleEvent event) {
        if (plugin.getConfig().getBoolean("Cancel Events.Experience", true)) {
            event.setExperience(0);
        }
    }

    @EventHandler
    public void Furnace(FurnaceExtractEvent event) {
        if (plugin.getConfig().getBoolean("Cancel Events.Experience", true)) {
            event.setExpToDrop(0);
        }
    }

    @EventHandler
    public void CancelPlayerChange(PlayerExpChangeEvent event) {
        if (plugin.getConfig().getBoolean("Cancel Events.Experience", true)) {
            event.setAmount(0);
        }
    }

    @EventHandler
    public void Fishing(PlayerFishEvent event) {
        if (plugin.getConfig().getBoolean("Cancel Events.Experience", true)) {
            event.setExpToDrop(0);
        }
    }

    // Command Cooldown
    HashMap<UUID, Integer> cooldown = new HashMap<>();
    HashMap<UUID, Integer> amount = new HashMap<>();

    @EventHandler
    public void commands(PlayerCommandPreprocessEvent event) {
        if (plugin.getConfig().getBoolean("Command_Cooldown")) {
            Player player = event.getPlayer();
            if (player.hasPermission(Permissions.bypass) || player.hasPermission(Permissions.GlobalOverwrite)) return;
            if (!amount.containsKey(player.getUniqueId())) {
                amount.put(player.getUniqueId(), 1);
                return;
            }
            if (amount.get(player.getUniqueId()) != plugin.getConfig().getInt("Command_Amount")) {
                amount.put(player.getUniqueId(), amount.get(player.getUniqueId()) + 1);
                return;
            }
            if (!cooldown.containsKey(player.getUniqueId())) {
                cooldown.put(player.getUniqueId(), plugin.getConfig().getInt("Command_Cooldown_Time"));
                new BukkitRunnable() {
                    public void run() {
                        if (!cooldown.containsKey(player.getUniqueId())) return;
                        if (cooldown.get(player.getUniqueId()) <= 0) {
                            cooldown.remove(player.getUniqueId());
                            amount.remove(player.getUniqueId());
                            cancel();
                            return;
                        }
                        // Removing 1 from the count
                        cooldown.put(player.getUniqueId(), cooldown.get(player.getUniqueId()) - 1);
                    }
                }.runTaskTimer(plugin, 0L, 20L);
                return;
            }
            Utils.Message(player, Errors.getErrors(Errors.Cooldown));
            event.setCancelled(true);
        }
    }

    // Firework onJoin
    @EventHandler
    public void FireworkOnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Location loc = player.getLocation();
        if (plugin.getConfig().getBoolean("Firework On Player Join", true)) {
            if (plugin.getConfig().getBoolean("Spawn On Player Join", true)) {
                player.getWorld().spawnEntity(loc, EntityType.FIREWORK);
            }
        }
    }

    // One Player Sleep
    @EventHandler
    public void onSleep(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (plugin.getConfig().getBoolean("World Events.One Player Sleep", true)) {
            if (world.getTime() >= 12541) {
                world.setTime(0L);
                world.setThundering(false);
                world.setStorm(false);
                event.setCancelled(true);
                Bukkit.broadcastMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("One Player Sleep")));
            } else {
                event.setCancelled(true);
                player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Already Day")));
            }
        }
    }

    // Silent Join
    @EventHandler
    public void VanishOnJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("Commands.Vanish", true)) {
            Player player = event.getPlayer();
            if (plugin.getConfig().getBoolean("World Events.Silent Join", true)) {
                if (player.hasPermission(Permissions.SilentJoin) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    Utils.invisible_list.add(player.getUniqueId());
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.hidePlayer(player);
                    }
                    event.setJoinMessage(null);
                }
            }
        }
    }

    // Eat Cooldown
    public HashMap<UUID, Integer> cooldownTime_Normal = new HashMap<>();
    public HashMap<UUID, Integer> cooldownTime_Enchanted = new HashMap<>();

    @EventHandler
    public void EatCooldown(PlayerItemConsumeEvent event) {
        if (plugin.getConfig().getBoolean("Cooldowns.Golden Apple", true)) {
            Player player = event.getPlayer();
            ItemStack item = event.getItem();
            if (player.hasPermission(Permissions.bypass) || player.hasPermission(Permissions.GlobalOverwrite))
                return;
            if (item.getType().equals(Material.GOLDEN_APPLE)) {
                if (cooldownTime_Normal.containsKey(player.getUniqueId())) {
                    Utils.Message(player, Errors.getErrors(Errors.CooldownItem));
                    event.setCancelled(true);
                    player.updateInventory();
                    return;
                }
                cooldownTime_Normal.put(player.getUniqueId(), plugin.getConfig().getInt("Cooldowns.Golden Apple Time"));
                new BukkitRunnable() {
                    public void run() {
                        if (!cooldownTime_Normal.containsKey(player.getUniqueId())) return;
                        if (cooldownTime_Normal.get(player.getUniqueId()) <= 0) {
                            cooldownTime_Normal.remove(player.getUniqueId());
                            cancel();
                            return;
                        }
                        // Removing 1 from the count
                        cooldownTime_Normal.put(player.getUniqueId(), cooldownTime_Normal.get(player.getUniqueId()) - 1);
                    }
                }.runTaskTimer(plugin, 0L, 20L);
            }
        }
    }

    // Enderpearl Cooldown
    public HashMap<UUID, Integer> cooldownTime = new HashMap<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (plugin.getConfig().getBoolean("Cooldowns.Enderpearl", true)) {
            Player player = event.getPlayer();
            if (player.getItemInHand().getType().equals(Material.ENDER_PEARL)) {
                if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (player.hasPermission(Permissions.bypass) || player.hasPermission(Permissions.GlobalOverwrite))
                        return;
                    if (cooldownTime.containsKey(player.getUniqueId())) {
                        player.sendMessage(Utils.chat(Errors.getErrors(Errors.CooldownItem)));
                        event.setCancelled(true);
                        player.updateInventory();
                        return;
                    }
                    cooldownTime.put(player.getUniqueId(), plugin.getConfig().getInt("Cooldowns.Enderpearl.Time"));
                    new BukkitRunnable() {
                        public void run() {
                            if (!cooldownTime.containsKey(player.getUniqueId())) return;
                            if (cooldownTime.get(player.getUniqueId()) <= 0) {
                                cooldownTime.remove(player.getUniqueId());
                                cancel();
                                return;
                            }
                            // Removing 1 from the count
                            cooldownTime.put(player.getUniqueId(), cooldownTime.get(player.getUniqueId()) - 1);
                        }
                    }.runTaskTimer(plugin, 0L, 20L);
                }
            }
        }
    }

    // Remove player from lists
    @EventHandler
    public void RemovePlayer(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Utils.invisible_list.remove(player.getUniqueId());
        Utils.GodMode_List.remove(player.getUniqueId());
        Utils.Autosell_List.remove(player.getUniqueId());
    }

    // Safe login
    @EventHandler
    public void StillFlyingOnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission(Permissions.SafeLogin) || player.hasPermission(Permissions.GlobalOverwrite)) {
            if (player.isFlying()) {
                player.setFlying(true);
                player.setAllowFlight(true);
            }
        }
    }

}
