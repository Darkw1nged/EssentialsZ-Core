package me.darkwinged.Essentials.Commands.Chat;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Sudo implements CommandExecutor {

    private Main plugin;
    public cmd_Sudo(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("sudo")) {
            if (plugin.getConfig().getBoolean("Chat", true)) {
                if (plugin.getConfig().getBoolean("cmd_Sudo", true)) {
                    if (!(sender instanceof Player)) {
                        if (!(args.length >= 2)) {
                            Utils.Message(sender, Errors.getErrors(Errors.MessageEmpty));
                            return true;
                        }
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target == null) {
                            Utils.Message(sender, Errors.getErrors(Errors.SpecifyPlayer));
                            return true;
                        }
                        String msg = "";
                        for (String s : args) {
                            msg = msg + " " + s;
                        }
                        target.chat(msg.replaceAll(" "+args[0]+" ", ""));
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.Sudo) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (!(args.length >= 2)) {
                            Utils.Message(player, Errors.getErrors(Errors.MessageEmpty));
                            return true;
                        }
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target == null) {
                            Utils.Message(sender, Errors.getErrors(Errors.SpecifyPlayer));
                            return true;
                        }
                        String msg = "";
                        for (String s : args) {
                            msg = msg + " " + s;
                        }
                        target.chat(msg.replaceAll(" "+args[0]+" ", ""));
                    } else {
                        Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                    }
                }
            }
        }
        return false;
    }

}
