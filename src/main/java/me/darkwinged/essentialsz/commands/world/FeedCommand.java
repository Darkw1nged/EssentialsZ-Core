package me.darkwinged.essentialsz.commands.world;

import me.darkwinged.essentialsz.libaries.lang.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import me.darkwinged.essentialsz.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("feed")) {
            if (plugin.getConfig().getBoolean("Commands.Feed", true)) {
                if (!(sender instanceof Player)) {
                    if (args.length != 1) {
                        sender.sendMessage(Errors.getErrors(Errors.SpecifyPlayer));
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        sender.sendMessage(Errors.getErrors(Errors.NoPlayerFound));
                        return true;
                    }
                    if (target.getFoodLevel() == 20) {
                        sender.sendMessage(Errors.getErrors(Errors.FullFood));
                        return true;
                    }
                    target.setFoodLevel(20);
                    sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                            plugin.MessagesFile.getConfig().getString("Feed Other"), target, target, null, false));
                    return true;
                }
                Player player = (Player)sender;
                if (args.length != 1) {
                    if (player.hasPermission(Permissions.Feed) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (player.getFoodLevel() == 20) {
                            sender.sendMessage(Errors.getErrors(Errors.FullFood));
                            return true;
                        }
                        player.setFoodLevel(20);
                        sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("Feed"), player, player, null, false));
                    } else
                        player.sendMessage(Errors.getErrors(Errors.NoPermission));
                    return true;
                }
                if (player.hasPermission(Permissions.FeedOther) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        sender.sendMessage(Errors.getErrors(Errors.NoPlayerFound));
                        return true;
                    }
                    if (target.getFoodLevel() == 20) {
                        sender.sendMessage(Errors.getErrors(Errors.FullFood));
                        return true;
                    }
                    target.setFoodLevel(20);
                    sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                            plugin.MessagesFile.getConfig().getString("Feed Other"), target, target, null, false));
                } else
                    player.sendMessage(Errors.getErrors(Errors.NoPermission));
            }
        }
        return false;
    }
}
