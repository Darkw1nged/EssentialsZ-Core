package me.darkwinged.essentialsz.commands.world;

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

public class FlyCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("fly")) {
            if (plugin.getConfig().getBoolean("Commands.Fly", true)) {
                if (!(sender instanceof Player)) {
                    if (args.length != 1) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.SpecifyPlayer));
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.NoPlayerFound));
                        return true;
                    }
                    if (target.getAllowFlight()) {
                        target.setAllowFlight(false);
                        sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("Fly Other").replaceAll("%setting%", "disabled"),
                                target, target, null, false));
                        Utils.Fly_List.remove(target.getUniqueId());
                        return true;
                    }
                    target.setAllowFlight(true);
                    sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                            plugin.MessagesFile.getConfig().getString("Fly Other").replaceAll("%setting%", "enabled"),
                            target, target, null, false));
                    Utils.Fly_List.add(target.getUniqueId());
                    return true;
                }
                Player player = (Player) sender;
                if (args.length != 1) {
                    if (player.hasPermission(Permissions.Fly) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (player.getAllowFlight()) {
                            player.setAllowFlight(false);
                            sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                    plugin.MessagesFile.getConfig().getString("Fly").replaceAll("%setting%", "disabled"),
                                    null, null, null, false));
                            Utils.Fly_List.remove(player.getUniqueId());
                            return true;
                        }
                        player.setAllowFlight(true);
                        sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("Fly").replaceAll("%setting%", "enabled"),
                                null, null, null, false));
                        Utils.Fly_List.add(player.getUniqueId());
                        return true;
                    } else
                        player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
                    return true;
                }
                if (player.hasPermission(Permissions.FlyOther) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.NoPlayerFound));
                        return true;
                    }
                    if (target.getAllowFlight()) {
                        target.setAllowFlight(false);
                        sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("Fly Other").replaceAll("%setting%", "disabled"),
                                target, target, null, false));
                        Utils.Fly_List.remove(target.getUniqueId());
                        return true;
                    }
                    target.setAllowFlight(true);
                    sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                            plugin.MessagesFile.getConfig().getString("Fly Other").replaceAll("%setting%", "enabled"),
                            target, target, null, false));
                    Utils.Fly_List.add(target.getUniqueId());
                } else
                    player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
            }
        }
        return false;
    }

}
