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

public class cmd_Pay implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("pay")) {
            if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
                if (plugin.getConfig().getBoolean("Economy.Settings.Commands.Pay", true)) {
                    if (plugin.Module_Economy = false) return true;
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(Errors.getErrors(Errors.Console));
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.Pay) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length < 2) {
                            player.sendMessage(Errors.getErrors(Errors.UsagePay));
                            return true;
                        }
                        Player target = Bukkit.getPlayer(args[0]);
                        double amount = Double.parseDouble(args[1]);
                        if (target == null) {
                            player.sendMessage(Errors.getErrors(Errors.NoPlayerFound));
                            return true;
                        }
                        if (target == player) {
                            player.sendMessage(Errors.getErrors(Errors.SenderInstaceOfPlayer));
                            return true;
                        }
                        // Player Account
                        plugin.economyManager.hasAccount(player);
                        plugin.economyManager.hasEnoughMoney(player, amount);

                        // Target Account
                        plugin.economyManager.hasAccount(player);

                        // Getting the amount
                        try {
                            amount = Double.parseDouble(args[1]);
                        } catch(Exception e) {
                            player.sendMessage(Errors.getErrors(Errors.InvalidAmount));
                            return true;
                        }
                        // Removing the amount from the senders balance
                        plugin.economyManager.RemoveAccount(player, amount);
                        // Adding the amount to the target balance
                        plugin.economyManager.AddAccount(target, amount);
                        player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Payed")
                                .replaceAll("%player%", target.getName())
                                .replaceAll("%amount%", ""+amount)));
                    } else
                        player.sendMessage(Errors.getErrors(Errors.NoPermission));
                }
            }
        }
        return false;
    }

}
