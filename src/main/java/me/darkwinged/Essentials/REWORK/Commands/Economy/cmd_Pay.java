package me.darkwinged.Essentials.REWORK.Commands.Economy;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.MessagesFile;
import me.darkwinged.Essentials.REWORK.Utils.EconomyManager;
import me.darkwinged.Essentials.REWORK.Utils.ErrorMessages;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import me.darkwinged.Essentials.REWORK.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Pay implements CommandExecutor {

    private MessagesFile messagesFile;
    private Main plugin;
    public cmd_Pay(MessagesFile messagesFile, Main plugin) {
        this.messagesFile = messagesFile;
        this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("pay")) {
            if (plugin.getConfig().getBoolean("Economy", true)) {
                if (plugin.getConfig().getBoolean("cmd_Pay", true)) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(ErrorMessages.Console);
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.Pay) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length < 2) {
                            player.sendMessage(ErrorMessages.UsagePay);
                            return true;
                        }
                        Player target = Bukkit.getPlayer(args[0]);
                        double amount = Double.parseDouble(args[1]);
                        if (target == null) {
                            player.sendMessage(ErrorMessages.NoPlayerFound);
                            return true;
                        }
                        if (target == player) {
                            player.sendMessage(ErrorMessages.SenderInstaceOfPlayer);
                            return true;
                        }
                        // Player Account
                        EconomyManager.AccountCheck(player);
                        EconomyManager.EnoughMoneyCheck(player, amount);

                        // Target Account
                        EconomyManager.AccountCheck(target);

                        // Getting the amount
                        try {
                            amount = Double.parseDouble(args[1]);
                        } catch(Exception e) {
                            player.sendMessage(ErrorMessages.InvalidAmount);
                            return true;
                        }
                        // Removing the amount from the senders balance
                        EconomyManager.setBalance(player.getName(), EconomyManager.getBalance(player.getName()) - amount);
                        // Adding the amount to the target balance
                        EconomyManager.setBalance(target.getName(), EconomyManager.getBalance(target.getName()) + amount);
                        player.sendMessage(Utils.chat(messagesFile.getConfig().getString("Payed")
                                .replaceAll("%player%", target.getName())
                                .replaceAll("%amount%", ""+amount)));
                    } else {
                        player.sendMessage(ErrorMessages.NoPermission);
                    }
                }
            }
        }
        return false;
    }

}
