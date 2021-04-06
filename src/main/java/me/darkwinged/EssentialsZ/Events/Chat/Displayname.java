package me.darkwinged.EssentialsZ.Events.Chat;

import me.darkwinged.EssentialsZ.Main;
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
                event.setFormat(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("Chat.Settings.Custom Display Name.Format")
                        .replaceAll("%prefix%", plugin.getChat().getPlayerPrefix(player))
                        .replaceAll("%suffix%", plugin.getChat().getPlayerSuffix(player))
                        .replaceAll("%message%", event.getMessage()), player, null, null, false));
            }
        }
    }

}
