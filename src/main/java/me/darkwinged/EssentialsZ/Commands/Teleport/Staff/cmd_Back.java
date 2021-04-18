package me.darkwinged.EssentialsZ.Commands.Teleport.Staff;

import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class cmd_Back implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public static HashMap<UUID, Location> back_loc = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("back")) {
            if (plugin.getConfig().getBoolean("Teleportation.enabled", true)) {
                if (plugin.getConfig().getBoolean("Teleportation.Commands.back", true)) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(Errors.getErrors(Errors.Console));
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.BackTeleport) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (!back_loc.containsKey(player.getUniqueId())) {
                            player.sendMessage(Errors.getErrors(Errors.NoPreviousLocation));
                            return true;
                        }
                        player.teleport(back_loc.get(player.getUniqueId()));
                        back_loc.remove(player.getUniqueId());
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("Back Message"), null, null, null, false));
                    } else
                        player.sendMessage(Errors.getErrors(Errors.NoPermission));
                } else {
                    sender.sendMessage(Errors.getErrors(Errors.DisabledCommand));
                }
            }
        }
        return false;
    }

}
