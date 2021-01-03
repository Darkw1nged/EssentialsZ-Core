package me.darkwinged.Essentials.Events.World;

import me.darkwinged.Essentials.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class NoFallDamage implements Listener {

    private Main plugin;
    public NoFallDamage(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void Fall(EntityDamageEvent event) {
        if (plugin.getConfig().getBoolean("No_Fall_Damage", true)) {
            if (event.getEntity() instanceof Player) {
                Player player = (Player)event.getEntity();
                if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                    player.setFallDistance(0F);
                    event.setCancelled(true);
                }
            }
        }
    }

}
