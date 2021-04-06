package me.darkwinged.Essentials.Commands.Chat;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Libaries.Lang.Errors;
import me.darkwinged.Essentials.Libaries.Lang.Permissions;
import me.darkwinged.Essentials.Libaries.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Mutechat implements CommandExecutor {

    private final Main plugin;
    public cmd_Mutechat(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("mutechat")) {
            if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
                if (plugin.getConfig().getBoolean("Chat.Settings.Commands.Commands.Mutechat", true)) {
                    if (!(sender instanceof Player)) {
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
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.MuteChat) || player.hasPermission(Permissions.GlobalOverwrite)) {
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
                    } else {
                        Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                    }
                }
            }
        }
        return false;
    }

}
