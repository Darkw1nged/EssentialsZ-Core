package me.darkwinged.Essentials.REWORK.Events.Chat;


import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class AntiSpam implements Listener {

    private Main plugin;
    public AntiSpam(Main plugin) { this.plugin = plugin; }

    HashMap<String, Long> Cooldown = new HashMap<>();

    @EventHandler
    public void checkChatSpam(AsyncPlayerChatEvent event) {
        if (plugin.getConfig().getBoolean("Anti_Spam", true)) {
            Player player = event.getPlayer();
            if (player.hasPermission(Permissions.bypass) || player.hasPermission(Permissions.GlobalOverwrite))
                return;
            long time = System.currentTimeMillis();
            long lastUse = 0;
            if (Cooldown.containsKey(player.getName())) {
                lastUse = Cooldown.get(player.getName());
            }
            if (lastUse + 1000L > time) {
                event.setCancelled(true);
                Bukkit.getScheduler().runTask(plugin, new Runnable() {
                    public void run() {
                        player.kickPlayer("Kicked for spamming");
                    }
                });
            }
            Cooldown.remove(player.getName());
            Cooldown.put(player.getName(), time);
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Cooldown.remove(event.getPlayer());
    }

}
