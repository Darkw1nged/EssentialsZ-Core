package me.darkwinged.Essentials.Events.Chat;

import me.darkwinged.Essentials.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFilterEvent implements Listener {

    private Main plugin;
    public ChatFilterEvent(Main plugin) {
        this.plugin = plugin; }

    @EventHandler
    public void antiswear(AsyncPlayerChatEvent event) {
        if (plugin.getConfig().getBoolean("Chat", true)) {
            if (plugin.getConfig().getBoolean("Chat_Filter", true)) {
                String censor = "#$@&%*!";
                String msg = event.getMessage();
                for (String blacklist : plugin.ChatFilterFile.getConfig().getStringList("Blacklist")) {
                    msg = msg.toLowerCase().replace(blacklist.toLowerCase(), censor);
                    event.setMessage(msg);
                }
            }
        }
    }

}
