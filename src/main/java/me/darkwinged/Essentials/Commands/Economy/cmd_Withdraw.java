package me.darkwinged.Essentials.Commands.Economy;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.EconomyManager;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class cmd_Withdraw implements CommandExecutor {

    private Main plugin;
    public cmd_Withdraw(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("withdraw")) {
            if (plugin.getConfig().getBoolean("Economy", true)) {
                if (plugin.getConfig().getBoolean("Bank_Notes", true)) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(ErrorMessages.Console);
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.Withdraw) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length != 1) {
                            player.sendMessage(ErrorMessages.InvalidAmount);
                            return true;
                        }
                        double amount = Double.parseDouble(args[0]);
                        // Player Account
                        if (!EconomyManager.hasAccount(player.getName())) {
                            return true;
                        }
                        if (amount > EconomyManager.getBalance(player.getName())) {
                            player.sendMessage(ErrorMessages.NotEnoughMoney);
                            return true;
                        }
                        // Getting the amount
                        try {
                            amount = Double.parseDouble(args[0]);
                        } catch(Exception e) {
                            player.sendMessage(ErrorMessages.InvalidAmount);
                            return true;
                        }
                        // Removing the amount from the senders balance
                        EconomyManager.setBalance(player.getName(), EconomyManager.getBalance(player.getName()) - amount);

                        // Creating the withdraw item.
                        ItemStack item = null;
                        if (plugin.getConfig().getItemStack("BNLayout.item") == null) {
                            item = new ItemStack(Material.PAPER);
                        } else {
                            item = new ItemStack(Material.getMaterial(plugin.getConfig().getString("BNLayout.item")));
                        }
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(Utils.chat(plugin.getConfig().getString("BNLayout.name")));
                        ArrayList<String> lore = new ArrayList<>();
                        lore.add(Utils.chat(plugin.getConfig().getString("BNLayout.amount")) + amount);
                        meta.setLore(lore);
                        item.setItemMeta(meta);

                        player.getInventory().addItem(item);

                    } else {
                        player.sendMessage(ErrorMessages.NoPermission);
                    }
                }

            }
        }
        return false;
    }


}
