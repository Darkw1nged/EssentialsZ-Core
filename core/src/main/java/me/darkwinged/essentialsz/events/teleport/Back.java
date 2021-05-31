package me.darkwinged.essentialsz.events.teleport;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.commands.teleport.staff.BackCommand;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import me.darkwinged.essentialsz.libaries.lang.Utils;
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
                BackCommand.back_loc.put(player.getUniqueId(), player.getLocation());
            }
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        if (plugin.getConfig().getBoolean("Teleportation.Settings.Commands.back", true)) {
            Player player = event.getPlayer();
            if (player.hasPermission(Permissions.BackTeleport) || player.hasPermission(Permissions.GlobalOverwrite)) {
                BackCommand.back_loc.put(player.getUniqueId(), player.getLocation());
            }
        }
    }

}
