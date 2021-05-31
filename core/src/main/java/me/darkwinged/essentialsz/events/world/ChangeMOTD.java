package me.darkwinged.essentialsz.events.world;

import me.darkwinged.essentialsz.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ChangeMOTD implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void MOTDChange(ServerListPingEvent event) {
        if (plugin.getConfig().getBoolean("Chat.enabled")) {
            if (plugin.getConfig().getBoolean("Chat.Settings.Server MOTD.enabled")) {
                event.setMotd(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("Chat.Settings.Server MOTD.MOTD"),
                        null, null, null, false));
            }
        }
    }

}
