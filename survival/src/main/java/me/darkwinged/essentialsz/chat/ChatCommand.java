package me.darkwinged.essentialsz.chat;

import me.darkwinged.essentialsz.EssentialsZSurvival;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatCommand implements CommandExecutor {

    private final EssentialsZSurvival plugin = EssentialsZSurvival.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("chat")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.Messages.getConfig().getString("Errors.Error Prefix") +
                        plugin.Messages.getConfig().getString("Errors.Console")));
                return true;
            }
            Player player = (Player)sender;
            if (args.length != 2) {
                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.Messages.getConfig().getString("Errors.Error Prefix") +
                        plugin.Messages.getConfig().getString("Errors.Specify Chat")));
                return true;
            }
            if (args[0].equalsIgnoreCase("global")) {
                Chat_Manager.chat_GLOBAL.add(player.getUniqueId());
                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.Messages.getConfig().getString("prefix") +
                        plugin.Messages.getConfig().getString("chat").replaceAll("%channel%", "global")));

            } else if (args[0].equalsIgnoreCase("local") || args[0].equalsIgnoreCase("world")) {
                Chat_Manager.chat_WORLD.put(player.getUniqueId(), player.getWorld());
                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.Messages.getConfig().getString("prefix") +
                        plugin.Messages.getConfig().getString("chat").replaceAll("%channel%", "local")));
            }
        }
        return false;
    }
}
