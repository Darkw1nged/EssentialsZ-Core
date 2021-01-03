package me.darkwinged.Essentials.Events.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class NoHopperCraft implements Listener {

    private Main plugin;
    public NoHopperCraft(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCraft(CraftItemEvent event) {
        Player player = (Player)event.getWhoClicked();
        if (plugin.getConfig().getBoolean("Hopper Craft", true)) {
            if (event.getRecipe().getResult().getType() == Material.HOPPER) {
                if (event.getWhoClicked().hasPermission(Permissions.bypass) || event.getWhoClicked().hasPermission(Permissions.GlobalOverwrite))
                    return;
                event.setCancelled(true);
                if (plugin.getConfig().getBoolean("Hopper Craft Message", true)) {
                    player.sendMessage(ChatColor.RED + "You can not craft hoppers!");
                }
            }
        }
    }

}
