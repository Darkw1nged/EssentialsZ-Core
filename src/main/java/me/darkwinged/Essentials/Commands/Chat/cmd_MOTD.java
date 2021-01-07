package me.darkwinged.Essentials.Commands.Chat;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
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
                        Utils.Message(sender, Errors.getErrors(Errors.MessageEmpty));
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
                        Utils.Message(player, Errors.getErrors(Errors.MessageEmpty));
                        return true;
                    }
                    String msg = "";
                    for (String s : args) {
                        msg = msg + " " + s;
                    }
                    plugin.getConfig().set("MOTD", msg);
                } else {
                    Utils.Message(player, Errors.getErrors(Errors.NoPermission));
                }
            }
        }
        return false;
    }

}
