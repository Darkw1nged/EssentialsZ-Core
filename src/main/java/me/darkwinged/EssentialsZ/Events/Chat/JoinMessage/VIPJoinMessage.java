package me.darkwinged.EssentialsZ.Events.Chat.JoinMessage;

import me.darkwinged.EssentialsZ.Libaries.Lang.CustomConfig;
import me.darkwinged.EssentialsZ.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class VIPJoinMessage implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
            Player player = event.getPlayer();
            CustomConfig Data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
            if (!Data.getConfig().contains("lastKnownName")) return;

            if (Data.getConfig().getBoolean("isVanished", true)) return;
            if (Data.getConfig().getBoolean("isVIP", true)) {
                if (plugin.getConfig().getBoolean("Chat.Settings.Join Messages.VIP Message.enabled", true)) {

                    if (plugin.getConfig().getBoolean("Chat.Settings.Join Messages.VIP Message.Center")) {
                        String message = plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("join message"), player, null, player, true);
                        event.setJoinMessage(plugin.essentialsZAPI.utils.CenteredMessage(message));
                    } else {
                        event.setJoinMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("join message"), player, null, player, true));
                    }
                }
            }
        }
    }

}
