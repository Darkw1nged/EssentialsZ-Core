package me.darkwinged.Essentials.Events.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class OnePlayerSleep implements Listener {

    private Main plugin;
    public OnePlayerSleep(Main plugin) { this.plugin = plugin;}

    @EventHandler
    public void onSleep(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
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
