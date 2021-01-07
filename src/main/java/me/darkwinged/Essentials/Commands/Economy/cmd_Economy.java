package me.darkwinged.Essentials.Commands.Economy;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.EconomyManager;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Economy implements CommandExecutor {

    private Main plugin;
    public cmd_Economy(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("economy")) {
            if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
                if (plugin.getConfig().getString("Economy.API").equalsIgnoreCase("Vault")) {
                } else if (plugin.getConfig().getString("Economy.API").equalsIgnoreCase("EssentialsZ")) {
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.Economy) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length != 3) {
                            Utils.Message(player, Errors.getErrors(Errors.UsageEconomy));
                            return true;
                        }
                        if (args[0].equalsIgnoreCase("add")) {
                            if (!EconomyManager.hasAccount(args[1])) {
                                return true;
                            }
                            double amount;
                            try {
                                amount = Double.parseDouble(args[2]);
                            } catch(Exception e) {
                                Utils.Message(player, Errors.getErrors(Errors.InvalidAmount));
                                return true;
                            }
                            EconomyManager.setBalance(args[1], EconomyManager.getBalance(args[1]) + amount);
                            return true;
                        } else if (args[0].equalsIgnoreCase("remove")) {
                            if (!EconomyManager.hasAccount(args[1])) {
                                return true;
                            }
                            double amount;
                            try {
                                amount = Double.parseDouble(args[2]);
                            } catch(Exception e) {
                                Utils.Message(player, Errors.getErrors(Errors.InvalidAmount));
                                return true;
                            }
                            EconomyManager.setBalance(args[1], EconomyManager.getBalance(args[1]) - amount);
                            return true;
                        } else if (args[0].equalsIgnoreCase("set")) {
                            if (!EconomyManager.hasAccount(args[1])) {
                                return true;
                            }
                            double amount;
                            try {
                                amount = Double.parseDouble(args[2]);
                            } catch(Exception e) {
                                Utils.Message(player, Errors.getErrors(Errors.InvalidAmount));
                                return true;
                            }
                            EconomyManager.setBalance(args[1], amount);
                            return true;
                        } else {
                            Utils.Message(player, Errors.getErrors(Errors.UsageEconomy));
                            return true;
                        }
                    }
                }
            }
        }
        return true;
    }

}
