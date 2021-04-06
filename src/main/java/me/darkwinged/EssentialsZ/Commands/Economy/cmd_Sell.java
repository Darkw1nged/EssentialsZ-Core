package me.darkwinged.EssentialsZ.Commands.Economy;

import me.darkwinged.EssentialsZ.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmd_Sell implements CommandExecutor {

    private final Main plugin;
    public cmd_Sell(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("sell")) {
            /*
            if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
                if (plugin.getConfig().getBoolean("Economy.Settings.Sell.Sell command", true)) {
                    if (plugin.Module_Economy = false) return true;
                    if (!(sender instanceof Player)) {
                        Errors.getErrors(Errors.Console);
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.Sell) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length != 1) {
                            Utils.Message(sender, Errors.getErrors(Errors.SellUsage));
                            return true;
                        }
                        if (args[0].equalsIgnoreCase("hand")) {
                            if (player.getItemInHand().getType().equals(Material.AIR)) return true;
                            double amount = 1;
                            for (int i = 0; i <= 36; i++) {
                                ItemStack item = player.getInventory().getItem(i);
                                if (item == null) continue;
                                if (item == player.getItemInHand()) {
                                    if (plugin.WorthFile.getConfig().isDouble("worth." + item.getType().name())) {
                                        amount += plugin.WorthFile.getConfig().getDouble("worth." + item.getType().name()) * item.getAmount();
                                        item.setAmount(0);
                                    }
                                }
                            }
                            EconomyManager.AddAccount(player, amount);
                            sender.sendMessage(Utils.chat(plugin.getConfig().getString("Economy.Settings.Currency Symbol") + amount));
                        } else if (args[0].equalsIgnoreCase("all")) {
                            double amount = 1;
                            for (int i = 0; i <= 36; i++) {
                                ItemStack item = player.getInventory().getItem(i);
                                if (item == null) continue;
                                if (plugin.WorthFile.getConfig().isDouble("worth." + item.getType().name())) {
                                    amount += plugin.WorthFile.getConfig().getDouble("worth." + item.getType().name()) * item.getAmount();
                                    item.setAmount(0);
                                }
                            }
                            EconomyManager.AddAccount(player, amount);
                            sender.sendMessage(Utils.chat(plugin.getConfig().getString("Economy.Settings.Currency Symbol") + amount));
                        }
                    } else {
                        Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                    }
                }
            }

             */
        }
        return false;
    }
}