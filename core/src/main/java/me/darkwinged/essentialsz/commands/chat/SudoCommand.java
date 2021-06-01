package me.darkwinged.essentialsz.commands.chat;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SudoCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("sudo")) {
            if (plugin.getConfig().getBoolean("Chat", true)) {
                if (plugin.getConfig().getBoolean("cmd_Sudo", true)) {
                    if (!(sender instanceof Player)) {
                        if (ContainsMessage(sender, args)) return true;
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.Sudo) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (ContainsMessage(player, args)) return true;
                    } else
                        player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
                }
            }
        }
        return false;
    }

    private boolean ContainsMessage(CommandSender sender, String[] args) {
        if (!(args.length >= 2)) {
            sender.sendMessage(ErrorManager.getErrors(Errors.MessageEmpty));
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ErrorManager.getErrors(Errors.SpecifyPlayer));
            return true;
        }
        String msg = "";
        for (String s : args) {
            msg = msg + " " + s;
        }
        target.chat(msg.replaceAll(" "+args[0]+" ", ""));
        return false;
    }

}
