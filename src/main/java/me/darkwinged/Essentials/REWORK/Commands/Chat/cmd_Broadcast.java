package me.darkwinged.Essentials.REWORK.Commands.Chat;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.MessagesFile;
import me.darkwinged.Essentials.REWORK.Utils.ErrorMessages;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import me.darkwinged.Essentials.REWORK.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Broadcast implements CommandExecutor {

    private MessagesFile messagesFile;
    private Main plugin;
    public cmd_Broadcast(MessagesFile messagesFile, Main plugin) {
        this.messagesFile = messagesFile;
        this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("broadcast")) {
            if (plugin.getConfig().getBoolean("cmd_Broadcast", true)) {
                if (!(sender instanceof Player)) {
                    if (args.length < 1) {
                        sender.sendMessage(ErrorMessages.MessageEmpty);
                    } else {
                        String msg = Utils.chat(messagesFile.getConfig().getString("Broadcast Prefix"));
                        for (String s : args) {
                            msg = msg + " " + s;
                        }
                    }
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.Broadcast) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (args.length < 1) {
                        player.sendMessage(ErrorMessages.MessageEmpty);
                        return true;
                    }
                    String msg = Utils.chat(messagesFile.getConfig().getString("Broadcast Prefix"));
                    for (String s : args) {
                        msg = msg + " " + Utils.chat(s);
                    }
                    Bukkit.broadcastMessage(msg);
                } else {
                    player.sendMessage(ErrorMessages.NoPermission);
                }
            }
        }
        return true;
    }

}
