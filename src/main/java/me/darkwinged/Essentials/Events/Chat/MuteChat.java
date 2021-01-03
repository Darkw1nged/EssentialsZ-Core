package me.darkwinged.Essentials.Events.Chat;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MuteChat implements Listener {

    private Main plugin;
    public MuteChat(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (Utils.isChatMuted) {
            if (!player.hasPermission(Permissions.bypass) || !player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Chat Muted")));
            }
        }
    }

}
