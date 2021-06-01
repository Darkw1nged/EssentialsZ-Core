package me.darkwinged.essentialsz.commands.chat;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import me.darkwinged.essentialsz.libaries.lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteChatCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("mutechat")) {
            if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
                if (plugin.getConfig().getBoolean("Chat.Settings.Commands.Commands.Mutechat", true)) {
                    if (!(sender instanceof Player)) {
                        isChatMuted(sender);
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.MuteChat) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        isChatMuted(sender);
                    } else
                        player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
                }
            }
        }
        return false;
    }

    private void isChatMuted(CommandSender sender) {
        if (!Utils.isChatMuted) {
            Utils.isChatMuted = true;
            String Message = Utils.chat(plugin.MessagesFile.getConfig().getString("Broadcast Chat Muted")
                    .replaceAll("%player%", sender.getName()).replaceAll("%setting%", "muted"));
            Bukkit.broadcastMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix")) + Message);
        } else {
            Utils.isChatMuted = false;
            String Message = Utils.chat(plugin.MessagesFile.getConfig().getString("Broadcast Chat Muted")
                    .replaceAll("%player%", sender.getName()).replaceAll("%setting%", "unmuted"));
            Bukkit.broadcastMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix")) + Message);
        }
    }

}
