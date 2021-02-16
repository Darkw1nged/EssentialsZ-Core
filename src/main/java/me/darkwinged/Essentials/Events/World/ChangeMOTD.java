package me.darkwinged.Essentials.Events.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ChangeMOTD implements Listener {

    private Main plugin;
    public ChangeMOTD(Main plugin) { this.plugin = plugin; }

    @EventHandler
    public void MOTDChange(ServerListPingEvent event) {
        if (plugin.getConfig().getBoolean("Chat.Settings.MOTD.enabled", true)) {
            event.setMotd(Utils.chat(plugin.getConfig().getString("Chat.Settings.MOTD.MOTD")
                    .replace("%n", "\n")));
        }
    }

}
