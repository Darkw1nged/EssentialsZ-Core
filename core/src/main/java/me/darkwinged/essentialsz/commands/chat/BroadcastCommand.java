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

public class BroadcastCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("broadcast")) {
            if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
                if (plugin.getConfig().getBoolean("Chat.Settings.Commands.Broadcast", true)) {
                    if (!(sender instanceof Player)) {
                        if (ContainsMessage(sender, args)) return true;
                        return true;
                    }
                    Player player = (Player) sender;
                    if (player.hasPermission(Permissions.Broadcast) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (ContainsMessage(sender, args)) return true;
                    } else
                        player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));

                }
            }

        }
        return true;
    }

    private boolean ContainsMessage(CommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(ErrorManager.getErrors(Errors.MessageEmpty));
            return true;
        }
        String msg = plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Broadcast Prefix"),
                null, null, null, false);
        for (String s : args) {
            msg = msg + " " + s;
        }
        Bukkit.broadcastMessage(msg);
        return false;
    }

}
