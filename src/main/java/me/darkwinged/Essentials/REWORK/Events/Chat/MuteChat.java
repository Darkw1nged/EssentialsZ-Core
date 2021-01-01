package me.darkwinged.Essentials.REWORK.Events.Chat;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.MessagesFile;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import me.darkwinged.Essentials.REWORK.Utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MuteChat implements Listener {

    private MessagesFile messagesFile;
    private Main plugin;
    public MuteChat(MessagesFile messagesFile, Main plugin) {
        this.messagesFile = messagesFile;
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (Utils.isChatMuted) {
            if (!player.hasPermission(Permissions.bypass) || !player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(Utils.chat(messagesFile.getConfig().getString("Chat Muted")));
            }
        }
    }

}
