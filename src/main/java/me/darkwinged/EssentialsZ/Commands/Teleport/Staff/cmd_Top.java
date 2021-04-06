package me.darkwinged.EssentialsZ.Commands.Teleport.Staff;

import me.darkwinged.EssentialsZ.Main;
import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Libaries.Lang.Utils;
import me.darkwinged.EssentialsZ.Libaries.TeleportUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Top implements CommandExecutor {

    private final Main plugin;
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
