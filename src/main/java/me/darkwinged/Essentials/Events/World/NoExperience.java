package me.darkwinged.Essentials.Events.World;

import me.darkwinged.Essentials.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerFishEvent;

public class NoExperience implements Listener {

    private Main plugin;

    public NoExperience(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void MobKill(EntityDeathEvent event) {
        if (plugin.getConfig().getBoolean("No_Experience", true)) {
            event.setDroppedExp(0);
        }
    }

    @EventHandler
    public void OreMine(BlockBreakEvent event) {
        if (plugin.getConfig().getBoolean("No_Experience", true)) {
            event.setExpToDrop(0);
        }
    }

    @EventHandler
    public void Breed(EntityBreedEvent event) {
        if (plugin.getConfig().getBoolean("No_Experience", true)) {
            event.setExperience(0);
        }
    }

    @EventHandler
    public void EXPBottle(ExpBottleEvent event) {
        if (plugin.getConfig().getBoolean("No_Experience", true)) {
            event.setExperience(0);
        }
    }

    @EventHandler
    public void Furnace(FurnaceExtractEvent event) {
        if (plugin.getConfig().getBoolean("No_Experience", true)) {
            event.setExpToDrop(0);
        }
    }

    @EventHandler
    public void CancelPlayerChange(PlayerExpChangeEvent event) {
        if (plugin.getConfig().getBoolean("No_Experience", true)) {
            event.setAmount(0);
        }
    }

    @EventHandler
    public void Fishing(PlayerFishEvent event) {
        if (plugin.getConfig().getBoolean("No_Experience", true)) {
            event.setExpToDrop(0);
        }
    }


}