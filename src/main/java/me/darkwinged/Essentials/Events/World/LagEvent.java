package me.darkwinged.Essentials.Events.World;

import me.darkwinged.Essentials.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class LagEvent implements Listener {

    private Main plugin;
    public LagEvent(Main plugin) { this.plugin = plugin; }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        if (plugin.Cancel_TNT = true) {
            event.setCancelled(true);
        }
    }

}
