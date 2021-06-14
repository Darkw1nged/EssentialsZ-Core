package me.darkwinged.essentialsz.miscellaneous;

import me.darkwinged.essentialsz.EssentialsZSurvival;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class OnePlayerSleep implements Listener {

    private final EssentialsZSurvival plugin = EssentialsZSurvival.getInstance;

    @EventHandler
    public void onSleep(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (world.getTime() >= 12541) {
            world.setTime(0L);
            world.setThundering(false);
            world.setStorm(false);
            event.setCancelled(true);
            Bukkit.broadcastMessage(plugin.essentialsZAPI.utils.chat(plugin.Messages.getConfig().getString("one player sleep")));
        } else {
            event.setCancelled(true);
            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.Messages.getConfig().getString("already day")));
        }
    }

}
