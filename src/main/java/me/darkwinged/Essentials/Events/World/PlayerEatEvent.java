package me.darkwinged.Essentials.Events.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class PlayerEatEvent implements Listener {

    private Main plugin;
    public PlayerEatEvent(Main plguin) {
        this.plugin = plguin;
    }

    public HashMap<UUID, Integer> cooldownTime_Normal = new HashMap<>();
    public HashMap<UUID, Integer> cooldownTime_Enchanted = new HashMap<>();

    @EventHandler
    public void EatCooldown(PlayerItemConsumeEvent event) {
        if (plugin.getConfig().getBoolean("Golden_Apple_Cooldown", true)) {
            Player player = event.getPlayer();
            ItemStack item = event.getItem();
            if (player.hasPermission(Permissions.bypass) || player.hasPermission(Permissions.GlobalOverwrite))
                return;
            if (item.getType().equals(Material.GOLDEN_APPLE)) {
                if (cooldownTime_Normal.containsKey(player.getUniqueId())) {
                    player.sendMessage(ErrorMessages.CooldownItem);
                    event.setCancelled(true);
                    player.updateInventory();
                    return;
                }
                cooldownTime_Normal.put(player.getUniqueId(), plugin.getConfig().getInt("Golden_Apple_Cooldown_Time"));
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
        if (plugin.getConfig().getBoolean("Enchanted_Golden_Apple_Cooldown", true)) {
            Player player = event.getPlayer();
            ItemStack item = event.getItem();
            if (player.hasPermission(Permissions.bypass) || player.hasPermission(Permissions.GlobalOverwrite))
                return;
            if (item.getType().equals(Material.ENCHANTED_GOLDEN_APPLE)) {
                if (cooldownTime_Enchanted.containsKey(player.getUniqueId())) {
                    player.sendMessage(ErrorMessages.CooldownItem);
                    event.setCancelled(true);
                    player.updateInventory();
                    return;
                }
                cooldownTime_Enchanted.put(player.getUniqueId(), plugin.getConfig().getInt("Enchanted_Golden_Apple_Cooldown_Time"));
                new BukkitRunnable() {
                    public void run() {
                        if (!cooldownTime_Enchanted.containsKey(player.getUniqueId())) return;
                        if (cooldownTime_Enchanted.get(player.getUniqueId()) <= 0) {
                            cooldownTime_Enchanted.remove(player.getUniqueId());
                            cancel();
                            return;
                        }
                        // Removing 1 from the count
                        cooldownTime_Enchanted.put(player.getUniqueId(), cooldownTime_Enchanted.get(player.getUniqueId()) - 1);
                    }
                }.runTaskTimer(plugin, 0L, 20L);
            }
        }
    }

}
