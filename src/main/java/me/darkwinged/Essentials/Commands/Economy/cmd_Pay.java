package me.darkwinged.Essentials.Commands.Economy;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.EssentialsZEconomy.EconomyManager;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Pay implements CommandExecutor {

    private Main plugin;
    public cmd_Pay(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("pay")) {
            if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
                if (plugin.getConfig().getBoolean("Economy.Settings.Commands.Pay", true)) {
                    if (plugin.Module_Economy = false) return true;
                    if (!(sender instanceof Player)) {
                        Utils.Message(sender, Errors.getErrors(Errors.Console));;
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.Pay) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length < 2) {
                            Utils.Message(player, Errors.getErrors(Errors.UsagePay));
                            return true;
                        }
                        Player target = Bukkit.getPlayer(args[0]);
                        double amount = Double.parseDouble(args[1]);
                        if (target == null) {
                            Utils.Message(player, Errors.getErrors(Errors.NoPlayerFound));
                            return true;
                        }
                        if (target == player) {
                            Utils.Message(player, Errors.getErrors(Errors.SenderInstaceOfPlayer));
                            return true;
                        }
                        // Player Account
                        EconomyManager.hasAccount(player);
                        EconomyManager.hasEnoughMoney(player, amount);

                        // Target Account
                        EconomyManager.hasAccount(player);

                        // Getting the amount
                        try {
                            amount = Double.parseDouble(args[1]);
                        } catch(Exception e) {
                            Utils.Message(player, Errors.getErrors(Errors.InvalidAmount));
                            return true;
                        }
                        // Removing the amount from the senders balance
                        EconomyManager.RemoveAccount(player, amount);
                        // Adding the amount to the target balance
                        EconomyManager.AddAccount(target, amount);
                        player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Payed")
                                .replaceAll("%player%", target.getName())
                                .replaceAll("%amount%", ""+amount)));
                    } else {
                        Utils.Message(player, Errors.getErrors(Errors.NoPermission));
                    }
                }
            }
        }
        return false;
    }

}
