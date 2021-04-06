package me.darkwinged.EssentialsZ.Commands.Chat;

import me.darkwinged.EssentialsZ.Main;
import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Libaries.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Broadcast implements CommandExecutor {

    private final Main plugin;
    public cmd_Broadcast(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("broadcast")) {
            if (plugin.getConfig().getBoolean("cmd_Broadcast", true)) {
                if (!(sender instanceof Player)) {
                    if (args.length < 1) {
                        Utils.Message(sender, Errors.getErrors(Errors.MessageEmpty));
                    } else {
                        String msg = Utils.chat(plugin.MessagesFile.getConfig().getString("Broadcast Prefix"));
                        for (String s : args) {
                            msg = msg + " " + s;
                        }
                    }
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.Broadcast) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (args.length < 1) {
                        Utils.Message(sender, Errors.getErrors(Errors.MessageEmpty));
                        return true;
                    }
                    String msg = Utils.chat(plugin.MessagesFile.getConfig().getString("Broadcast Prefix"));
                    for (String s : args) {
                        msg = msg + " " + Utils.chat(s);
                    }
                    Bukkit.broadcastMessage(msg);
                } else {
                    Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                }
            }
        }
        return true;
    }

}
