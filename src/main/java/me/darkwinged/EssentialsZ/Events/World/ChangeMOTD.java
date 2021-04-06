package me.darkwinged.EssentialsZ.Events.World;

import me.darkwinged.EssentialsZ.Main;
import me.darkwinged.EssentialsZ.Libaries.Lang.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ChangeMOTD implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void MOTDChange(ServerListPingEvent event) {
        if (plugin.getConfig().getBoolean("Chat.enabled")) {
            if (plugin.getConfig().getBoolean("Chat.Settings.Server MOTD.enabled")) {
                event.setMotd(Utils.chat(plugin.getConfig().getString("Chat.Settings.Server MOTD.MOTD")
                        .replace("%n", "\n")));
            }
        }
    }

}
