package me.darkwinged.Essentials.Events.Teleport;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Commands.Teleport.Staff.cmd_Back;
import me.darkwinged.Essentials.Libaries.Lang.Permissions;
import me.darkwinged.Essentials.Libaries.Lang.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class Back implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (plugin.getConfig().getBoolean("Teleportation.Settings.Commands.back", true)) {
            Player player = event.getEntity();
            if (player.hasPermission(Permissions.BackTeleport) || player.hasPermission(Permissions.GlobalOverwrite)) {
                player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Back Death Message")));
                cmd_Back.back_loc.put(player.getUniqueId(), player.getLocation());
            }
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        if (plugin.getConfig().getBoolean("Teleportation.Settings.Commands.back", true)) {
            Player player = event.getPlayer();
            if (player.hasPermission(Permissions.BackTeleport) || player.hasPermission(Permissions.GlobalOverwrite)) {
                cmd_Back.back_loc.put(player.getUniqueId(), player.getLocation());
            }
        }
    }

}
