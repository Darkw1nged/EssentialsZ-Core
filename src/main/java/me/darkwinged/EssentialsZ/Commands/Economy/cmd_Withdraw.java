package me.darkwinged.EssentialsZ.Commands.Economy;

import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class cmd_Withdraw implements CommandExecutor {

    private final Main plugin = Main.getInstance;
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("withdraw")) {
            if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
                if (plugin.getConfig().getBoolean("Economy.Settings.Bank Notes", true)) {
                    if (plugin.Module_Economy = false) return true;
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(Errors.getErrors(Errors.Console));
                        return true;
                    }
                    Player player = (Player) sender;
                    if (player.hasPermission(Permissions.Withdraw) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length != 1) {
                            sender.sendMessage(Errors.getErrors(Errors.InvalidAmount));
                            return true;
                        }
                        double amount = Double.parseDouble(args[0]);
                        // Player Account
                        if (!plugin.economyManager.hasAccount(player)) {
                            return true;
                        }
                        if (plugin.economyManager.hasEnoughMoney(player, amount)) {
                            sender.sendMessage(Errors.getErrors(Errors.NotEnoughMoney));
                            return true;
                        }
                        // Getting the amount
                        try {
                            amount = Double.parseDouble(args[0]);
                        } catch (Exception e) {
                            sender.sendMessage(Errors.getErrors(Errors.InvalidAmount));
                            return true;
                        }
                        // Removing the amount from the senders balance
                        plugin.economyManager.RemoveAccount(player, amount);

                        // Creating the withdraw item.
                        ItemStack item = null;
                        if (plugin.getConfig().getItemStack("BNLayout.Settings.item") == null) {
                            item = new ItemStack(Material.PAPER);
                        } else {
                            item = new ItemStack(Material.getMaterial(plugin.getConfig().getString("Economy.Settings.Bank Notes.Item.material")));
                        }
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("Economy.Settings.Bank Notes.Item.name"),
                                null, null, null, false));
                        List<String> lore = new ArrayList<>();
                        lore.add(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("Economy.Settings.Bank Notes.Item.amount"),
                                null, null, null, false) + amount);
                        meta.setLore(lore);
                        item.setItemMeta(meta);

                        player.getInventory().addItem(item);

                    } else {
                        sender.sendMessage(Errors.getErrors(Errors.NoPermission));
                    }
                }
            }
        }
        return false;
    }


}
