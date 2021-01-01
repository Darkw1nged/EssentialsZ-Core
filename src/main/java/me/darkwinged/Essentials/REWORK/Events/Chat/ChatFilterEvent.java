package me.darkwinged.Essentials.REWORK.Events.Chat;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.ChatFilterFile;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFilterEvent implements Listener {

    private ChatFilterFile chatFilterFile;
    private Main plugin;
    public ChatFilterEvent(ChatFilterFile chatFilterFile, Main plugin) {
        this.chatFilterFile = chatFilterFile;
        this.plugin = plugin; }

    @EventHandler
    public void antiswear(AsyncPlayerChatEvent event) {
        if (plugin.getConfig().getBoolean("Chat", true)) {
            if (plugin.getConfig().getBoolean("Chat_Filter", true)) {
                String censor = "#$@&%*!";
                String msg = event.getMessage();
                for (String blacklist : chatFilterFile.getConfig().getStringList("Blacklist")) {
                    msg = msg.toLowerCase().replace(blacklist.toLowerCase(), censor);
                    event.setMessage(msg);
                }
            }
        }
    }

}
