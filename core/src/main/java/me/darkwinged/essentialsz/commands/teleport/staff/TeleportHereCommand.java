package me.darkwinged.essentialsz.commands.teleport.staff;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

public class TeleportHereCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tphere")) {
            if (plugin.getConfig().getBoolean("Teleportation.enabled", true)) {
                if (plugin.getConfig().getBoolean("Teleportation.Settings.Commands.teleport", true)) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.Console));
                        return true;
                    }
                    Player player = (Player) sender;
                    if (player.hasPermission(Permissions.TPhere) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length != 1) {
                            player.sendMessage(ErrorManager.getErrors(Errors.TPhereUsage));
                            return true;
                        }
                        if (args[0].equalsIgnoreCase("@a")) {
                            if (player.hasPermission(Permissions.TPhereAll) || player.hasPermission(Permissions.GlobalOverwrite)) {
                                for (Player online : Bukkit.getOnlinePlayers()) {
                                    online.teleport(player);
                                }
                                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                        plugin.MessagesFile.getConfig().getString("TPhere All message"), player, null, null, false));
                            }
                            return true;
                        }
                        if (args[0].equalsIgnoreCase("@e")) {
                            if (player.hasPermission(Permissions.TPhereEntities) || player.hasPermission(Permissions.GlobalOverwrite)) {
                                for (Entity entity : player.getWorld().getEntities()) {
                                    if (entity instanceof Animals || entity instanceof Monster || entity instanceof Player) {
                                        entity.teleport(player);
                                    }
                                }
                                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                        plugin.MessagesFile.getConfig().getString("TPhere All Entities message"), player, null, null, false));
                            }
                            return true;
                        }
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target == null) {
                            player.sendMessage(ErrorManager.getErrors(Errors.NoPlayerFound));
                            return true;
                        }
                        if (target == sender) {
                            player.sendMessage(ErrorManager.getErrors(Errors.SenderInstaceOfPlayer));
                            return true;
                        }
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("TPhere message"), player, target, null, false));
                        target.teleport(player);
                    } else {
                        player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
                    }
                } else {
                    sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));
                }
            }
        }
        return false;
    }


}
