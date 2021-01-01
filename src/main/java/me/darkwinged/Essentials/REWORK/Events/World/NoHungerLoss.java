package me.darkwinged.Essentials.REWORK.Events.World;

import me.darkwinged.Essentials.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class NoHungerLoss implements Listener {

    private Main plugin;
    public NoHungerLoss(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void HungerLoss(FoodLevelChangeEvent event) {
        if (plugin.getConfig().getBoolean("Hunger Loss", true)) {
            event.setCancelled(true);
        }
    }
}
