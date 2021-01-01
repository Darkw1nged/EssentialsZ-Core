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

public class cmd_Mutechat implements CommandExecutor {

    private MessagesFile messagesFile;
    private Main plugin;
    public cmd_Mutechat(MessagesFile messagesFile, Main plugin) {
        this.messagesFile = messagesFile;
        this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("mutechat")) {
            if (plugin.getConfig().getBoolean("cmd_mutechat", true)) {
                if (!(sender instanceof Player)) {
                    if (!Utils.isChatMuted) {
                        Utils.isChatMuted = true;
                        String Message = Utils.chat(messagesFile.getConfig().getString("Broadcast Chat Muted").replaceAll("%player%", sender.getName()));
                        Bukkit.broadcastMessage(Utils.chat(messagesFile.getConfig().getString("Prefix")) + Message);
                    } else {
                        Utils.isChatMuted = false;
                        String Message = Utils.chat(messagesFile.getConfig().getString("Broadcast Chat Unmuted").replaceAll("%player%", sender.getName()));
                        Bukkit.broadcastMessage(Utils.chat(messagesFile.getConfig().getString("Prefix")) + Message);
                    }
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.MuteChat) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (!Utils.isChatMuted) {
                        Utils.isChatMuted = true;
                        String Message = Utils.chat(messagesFile.getConfig().getString("Broadcast Chat Muted").replaceAll("%player%", player.getName()));
                        Bukkit.broadcastMessage(Utils.chat(messagesFile.getConfig().getString("Prefix")) + Message);
                    } else {
                        Utils.isChatMuted = false;
                        String Message = Utils.chat(messagesFile.getConfig().getString("Broadcast Chat Unmuted").replaceAll("%player%", player.getName()));
                        Bukkit.broadcastMessage(Utils.chat(messagesFile.getConfig().getString("Prefix")) + Message);
                    }
                } else {
                    player.sendMessage(ErrorMessages.NoPermission);
                }
            }
        }
        return false;
    }

}
