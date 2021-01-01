package me.darkwinged.Essentials.REWORK.Events.Teleport;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Commands.Teleport.Staff.cmd_Back;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.MessagesFile;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import me.darkwinged.Essentials.REWORK.Utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class Back implements Listener {

    private MessagesFile messagesFile;
    private Main plugin;

    public Back(MessagesFile messagesFile, Main plugin) {
        this.messagesFile = messagesFile;
        this.plugin = plugin; }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (plugin.getConfig().getBoolean("cmd_Back", true)) {
            Player player = event.getEntity();
            if (player.hasPermission(Permissions.BackTeleport) || player.hasPermission(Permissions.GlobalOverwrite)) {
                player.sendMessage(Utils.chat(messagesFile.getConfig().getString("Back Death Message")));
                cmd_Back.back_loc.put(player.getUniqueId(), player.getLocation());
            }
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        if (plugin.getConfig().getBoolean("cmd_Back", true)) {
            Player player = event.getPlayer();
            if (player.hasPermission(Permissions.BackTeleport) || player.hasPermission(Permissions.GlobalOverwrite)) {
                cmd_Back.back_loc.put(player.getUniqueId(), player.getLocation());
            }
        }
    }

}
