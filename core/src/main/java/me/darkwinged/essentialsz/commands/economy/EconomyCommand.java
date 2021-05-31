package me.darkwinged.essentialsz.commands.economy;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EconomyCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("economy")) {
            if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
                if (plugin.Module_Economy = false) return true;
                Player player = (Player) sender;
                if (player.hasPermission(Permissions.Economy) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (args.length < 2) {
                        player.sendMessage(Errors.getErrors(Errors.UsageEconomy));
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        player.sendMessage(Errors.getErrors(Errors.NoPlayerFound));
                    }
                    if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("give")) {
                        if (!plugin.economyManager.hasAccount(target)) return true;
                        double amount;
                        try {
                            amount = Double.parseDouble(args[2]);
                        } catch (Exception e) {
                            player.sendMessage(Errors.getErrors(Errors.InvalidAmount));
                            return true;
                        }
                        plugin.economyManager.AddAccount(target, amount);
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix")
                                + plugin.MessagesFile.getConfig().getString("Economy Add Message")
                                .replaceAll("%amount%", amount+""), target, target, null, false));
                        return true;
                    } else if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("take")) {
                        if (!plugin.economyManager.hasAccount(target)) return true;
                        double amount;
                        try {
                            amount = Double.parseDouble(args[2]);
                        } catch (Exception e) {
                            player.sendMessage(Errors.getErrors(Errors.InvalidAmount));
                            return true;
                        }
                        plugin.economyManager.RemoveAccount(player, amount);
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix")
                                + plugin.MessagesFile.getConfig().getString("Economy Remove Message")
                                .replaceAll("%amount%", amount+""), target, target, null, false));
                        return true;
                    } else if (args[0].equalsIgnoreCase("set")) {
                        if (!plugin.economyManager.hasAccount(target)) return true;
                        double amount;
                        try {
                            amount = Double.parseDouble(args[2]);
                        } catch (Exception e) {
                            player.sendMessage(Errors.getErrors(Errors.InvalidAmount));
                            return true;
                        }
                        plugin.economyManager.setAccount(target, amount);
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix")
                                + plugin.MessagesFile.getConfig().getString("Economy Set Message")
                                .replaceAll("%amount%", amount+""), target, target, null, false));
                        return true;
                    } else if (args[0].equalsIgnoreCase("reset")) {
                        if (!plugin.economyManager.hasAccount(target)) return true;
                        plugin.economyManager.RemoveAccount(target, plugin.economyManager.getAccount(target));
                        plugin.economyManager.AddAccount(target, plugin.getConfig().getInt("Economy.Settings.Starting Balance"));
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix")
                                + plugin.MessagesFile.getConfig().getString("Economy Reset Message"), target, target, null, false));
                    } else
                        player.sendMessage(Errors.getErrors(Errors.UsageEconomy));

                }
            }
        }
        return true;
    }

}
