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

public class ClearchatCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("clearchat")) {
            if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
                if (plugin.getConfig().getBoolean("Chat.Settings.Commands.Clearchat", true)) {
                    if (!(sender instanceof Player)) {
                        for (int i = 0; i < 500; i++)
                            Bukkit.broadcastMessage(" ");
                        Bukkit.broadcastMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("ClearChat Message").replaceAll("%player%", sender.getName())));
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.ClearChat) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        for (int i = 0; i < 500; i++)
                            Bukkit.broadcastMessage(" ");
                        Bukkit.broadcastMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("ClearChat Message").replaceAll("%player%", player.getName())));
                    } else
                        player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
                }
            }
        }
        return false;
    }

}
