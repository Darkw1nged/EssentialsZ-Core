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

public class cmd_Clearchat implements CommandExecutor {

    private MessagesFile messagesFile;
    private Main plugin;
    public cmd_Clearchat(MessagesFile messagesFile, Main plugin) {
        this.messagesFile = messagesFile;
        this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("clearchat")) {
            if (plugin.getConfig().getBoolean("Chat", true)) {
                if (plugin.getConfig().getBoolean("cmd_Clearchat", true)) {
                    if (!(sender instanceof Player)) {
                        for (int i = 0; i < 500; i++)
                            Bukkit.broadcastMessage(" ");
                        Bukkit.broadcastMessage(Utils.chat(messagesFile.getConfig().getString("ClearChat Message").replaceAll("%player%", sender.getName())));
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.ClearChat) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        for (int i = 0; i < 500; i++)
                            Bukkit.broadcastMessage(" ");
                        Bukkit.broadcastMessage(Utils.chat(messagesFile.getConfig().getString("ClearChat Message").replaceAll("%player%", player.getName())));
                    } else {
                        player.sendMessage(ErrorMessages.NoPermission);
                    }
                }
            }
        }
        return false;
    }

}