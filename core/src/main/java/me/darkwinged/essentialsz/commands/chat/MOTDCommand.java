package me.darkwinged.essentialsz.commands.chat;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MOTDCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("motd")) {
            if (plugin.getConfig().getBoolean("Change MOTD", true)) {
                if (!(sender instanceof Player)) {
                    if (!(args.length >= 1)) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.MessageEmpty));
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
                        player.sendMessage(ErrorManager.getErrors(Errors.MessageEmpty));
                        return true;
                    }
                    String msg = "";
                    for (String s : args) {
                        msg = msg + " " + s;
                    }
                    plugin.getConfig().set("MOTD", msg);
                } else
                    player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
            }
        }
        return false;
    }

}
