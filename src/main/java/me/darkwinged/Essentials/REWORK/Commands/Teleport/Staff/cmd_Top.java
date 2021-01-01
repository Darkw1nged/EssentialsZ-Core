package me.darkwinged.Essentials.REWORK.Commands.Teleport.Staff;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.ErrorMessages;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import me.darkwinged.Essentials.REWORK.Utils.TeleportUtils;
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
                        sender.sendMessage(ErrorMessages.Console);
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.TopTeleport) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        Location TopLocation = TeleportUtils.findSafeLocationTop(player);
                        player.teleport(TopLocation);
                    } else {
                        player.sendMessage(ErrorMessages.NoPermission);
                    }
                }
            }
        }
        return false;
    }
}
