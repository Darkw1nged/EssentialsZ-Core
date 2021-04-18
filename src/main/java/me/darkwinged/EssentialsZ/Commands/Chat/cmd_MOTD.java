package me.darkwinged.EssentialsZ.Commands.Chat;

import me.darkwinged.EssentialsZ.Main;
import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Libaries.Lang.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_MOTD implements CommandExecutor {

    private final Main plugin = Main.getInstance;

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
