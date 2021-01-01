package me.darkwinged.Essentials.REWORK.Commands.Chat;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.ErrorMessages;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_MOTD implements CommandExecutor {

    private Main plugin;
    public cmd_MOTD(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("motd")) {
            if (plugin.getConfig().getBoolean("Change MOTD", true)) {
                if (!(sender instanceof Player)) {
                    if (!(args.length >= 1)) {
                        sender.sendMessage(ErrorMessages.MessageEmpty);
                        return true;
                    }
                    String msg = "";
                    for (String s : args) {
                        msg = msg + " " + s;
                    }
                    plugin.getConfig().set("Change MOTD", msg);
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.ChangeMOTD) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (!(args.length >= 1)) {
                        sender.sendMessage(ErrorMessages.MessageEmpty);
                        return true;
                    }
                    String msg = "";
                    for (String s : args) {
                        msg = msg + " " + s;
                    }
                    plugin.getConfig().set("MOTD", msg);
                } else {
                    player.sendMessage(ErrorMessages.NoPermission);
                }
            }
        }
        return false;
    }

}
