package me.darkwinged.essentialsz.events.chat;

import me.darkwinged.essentialsz.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Displayname implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
            if (plugin.getConfig().getBoolean("Chat.Settings.Custom Display Name.enabled", true)) {
                String format;
                if (plugin.getChat() != null) {
                    format = plugin.getConfig().getString("Chat.Settings.Custom Display Name.Format").replaceAll("%", "%%")
                            .replaceAll("%%prefix%%", plugin.getChat().getPlayerPrefix(player))
                            .replaceAll("%%player%%", player.getName())
                            .replaceAll("%%suffix%%", plugin.getChat().getPlayerSuffix(player))
                            .replaceAll("%%message%%", event.getMessage());
                } else {
                    format = plugin.getConfig().getString("Chat.Settings.Custom Display Name.Format").replaceAll("%", "%%")
                            .replaceAll("%%prefix%%", "")
                            .replaceAll("%%player%%", player.getName())
                            .replaceAll("%%suffix%%", "")
                            .replaceAll("%%message%%", event.getMessage());
                }
                event.setFormat(plugin.essentialsZAPI.utils.chat(format));
            }
        }
    }

}
