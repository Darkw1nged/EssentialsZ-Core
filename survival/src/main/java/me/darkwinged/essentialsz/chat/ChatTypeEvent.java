package me.darkwinged.essentialsz.chat;

import me.darkwinged.essentialsz.EssentialsZSurvival;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatTypeEvent implements Listener {

    private final EssentialsZSurvival plugin = EssentialsZSurvival.getInstance;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        plugin.chatManager.onChat(event.getPlayer(), plugin.essentialsZAPI.utils.chat(event.getMessage()));
    }

}
