package me.darkwinged.essentialsz.Events;

import me.darkwinged.essentialsz.EssentialsZFriends;
import me.darkwinged.essentialsz.Libaries.CustomConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {

    private final EssentialsZFriends plugin = EssentialsZFriends.getInstance;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        CustomConfig Data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");

        switch (Data.getConfig().get("Chat Mode").toString()) {
            case "GLOBAL":
                break;
            case "PARTY":
            case "OFF":
                event.setCancelled(true);
                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.Messages.getConfig().getString("Prefix") + plugin.Messages.getConfig().getString("Chat Disabled")));
                break;
        }

        if (Data.getConfig().get("Chat Mode").equals("GLOBAL")) {
            return;
        }

    }

}
