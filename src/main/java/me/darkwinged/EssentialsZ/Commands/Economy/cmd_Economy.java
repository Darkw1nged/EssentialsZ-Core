package me.darkwinged.EssentialsZ.Commands.Economy;

import me.darkwinged.EssentialsZ.Main;
import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Libaries.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Economy implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("economy")) {
            if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
                if (plugin.Module_Economy = false) return true;
                Player player = (Player) sender;
                if (player.hasPermission(Permissions.Economy) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (args.length < 2) {
                        Utils.Message(player, Errors.getErrors(Errors.UsageEconomy));
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        player.sendMessage(Utils.chat(Errors.getErrors(Errors.NoPlayerFound)));
                    }
                    if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("give")) {
                        if (!plugin.economyManager.hasAccount(target)) return true;
                        double amount;
                        try {
                            amount = Double.parseDouble(args[2]);
                        } catch (Exception e) {
                            Utils.Message(player, Errors.getErrors(Errors.InvalidAmount));
                            return true;
                        }
                        plugin.economyManager.AddAccount(target, amount);
                        player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix")
                                + plugin.MessagesFile.getConfig().getString("Economy Add Message")
                                .replaceAll("%player%", target.getName()).replaceAll("%amount%", amount+"")));
                        return true;
                    } else if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("take")) {
                        if (!plugin.economyManager.hasAccount(target)) return true;
                        double amount;
                        try {
                            amount = Double.parseDouble(args[2]);
                        } catch (Exception e) {
                            Utils.Message(player, Errors.getErrors(Errors.InvalidAmount));
                            return true;
                        }
                        plugin.economyManager.RemoveAccount(player, amount);
                        player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix")
                                + plugin.MessagesFile.getConfig().getString("Economy Remove Message")
                                .replaceAll("%player%", target.getName()).replaceAll("%amount%", amount+"")));
                        return true;
                    } else if (args[0].equalsIgnoreCase("set")) {
                        if (!plugin.economyManager.hasAccount(target)) return true;
                        double amount;
                        try {
                            amount = Double.parseDouble(args[2]);
                        } catch (Exception e) {
                            Utils.Message(player, Errors.getErrors(Errors.InvalidAmount));
                            return true;
                        }
                        plugin.economyManager.setAccount(target, amount);
                        player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix")
                                + plugin.MessagesFile.getConfig().getString("Economy Set Message")
                                .replaceAll("%player%", target.getName()).replaceAll("%amount%", amount+"")));
                        return true;
                    } else if (args[0].equalsIgnoreCase("reset")) {
                        if (!plugin.economyManager.hasAccount(target)) return true;
                        plugin.economyManager.RemoveAccount(target, plugin.economyManager.getAccount(target));
                        plugin.economyManager.AddAccount(target, plugin.getConfig().getInt("Economy.Settings.Starting Balance"));
                        player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix")
                                + plugin.MessagesFile.getConfig().getString("Economy Reset Message"))
                                .replaceAll("%player%", target.getName()));
                    } else {
                        Utils.Message(player, Errors.getErrors(Errors.UsageEconomy));
                        return true;
                    }
                }
            }
        }
        return true;
    }

}
