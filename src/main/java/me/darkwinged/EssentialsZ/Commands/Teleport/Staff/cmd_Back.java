package me.darkwinged.EssentialsZ.Commands.Teleport.Staff;

import me.darkwinged.EssentialsZ.Main;
import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Libaries.Lang.Utils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class cmd_Back implements CommandExecutor {

    private final Main plugin;
    public cmd_Back(Main plugin) { this.plugin = plugin; }

    public static HashMap<UUID, Location> back_loc = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("back")) {
            if (plugin.getConfig().getBoolean("cmd_Back", true)) {
                if (!(sender instanceof Player)) {
                    Utils.Message(sender, Errors.getErrors(Errors.Console));
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.BackTeleport) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (!back_loc.containsKey(player.getUniqueId())) {
                        Utils.Message(sender, Errors.getErrors(Errors.NoPreviousLocation));
                        return true;
                    }
                    player.teleport(back_loc.get(player.getUniqueId()));
                    back_loc.remove(player.getUniqueId());
                    player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + plugin.MessagesFile.getConfig().getString("Back Message")));
                } else {
                    Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                }
            }
        }
        return false;
    }

}
