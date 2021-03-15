package me.darkwinged.Essentials.Commands.Teleport.Staff;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import me.darkwinged.Essentials.Utils.TeleportUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Top implements CommandExecutor {

    private Main plugin;
    public cmd_Top(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("top")) {
            if (plugin.getConfig().getBoolean("Teleportation", true)) {
                if (plugin.getConfig().getBoolean("cmd_Top", true)) {
                    if (!(sender instanceof Player)) {
                        Utils.Message(sender, Errors.getErrors(Errors.Console));
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.TopTeleport) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        Location TopLocation = TeleportUtils.findSafeLocationTop(player);
                        player.teleport(TopLocation);
                    } else {
                        Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                    }
                }
            }
        }
        return false;
    }
}
