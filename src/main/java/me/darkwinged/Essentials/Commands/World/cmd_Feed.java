package me.darkwinged.Essentials.Commands.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Feed implements CommandExecutor {

    private final Main plugin;
    public cmd_Feed(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("feed")) {
            if (plugin.getConfig().getBoolean("cmd_Feed", true)) {
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
                    sender.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                            plugin.MessagesFile.getConfig().getString("Feed Other").replaceAll("%player%", target.getName())));
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
                        sender.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("Feed")));
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
                    sender.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                            plugin.MessagesFile.getConfig().getString("Feed Other").replaceAll("%player%", target.getName())));
                } else
                    player.sendMessage(Errors.getErrors(Errors.NoPermission));
            }
        }
        return false;
    }
}
