package me.darkwinged.Essentials.REWORK.Events.Chat;

import me.darkwinged.Essentials.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatPing implements Listener {

    private Main plugin;
    public ChatPing(Main plugin) { this.plugin = plugin; }

    @EventHandler
    public void Ping(AsyncPlayerChatEvent event) {
        if (plugin.getConfig().getBoolean("Chat", true)) {
            if (plugin.getConfig().getBoolean("Chat_Ping", true)) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (event.getMessage().contains(player.getName())) {
                        if (player == event.getPlayer())
                            return;
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3.0F, 0.5F);
                    }
                }
            }
        }
    }

}
