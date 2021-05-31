package me.darkwinged.essentialsz.commands.economy;

import me.darkwinged.essentialsz.libaries.lang.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import me.darkwinged.essentialsz.libaries.lang.Utils;
import me.darkwinged.essentialsz.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.Material.getMaterial;

public class MoneyPouchesCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("pouches")) {
            if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
                if (plugin.getConfig().getBoolean("Economy.Settings.Money Pouches.enabled", true)) {
                    if (plugin.Module_Economy = false) return true;
                    if (!(sender instanceof Player)) {
                        if (args.length != 2) {
                            sender.sendMessage(Errors.getErrors(Errors.SpecifyPlayer));
                            sender.sendMessage(Errors.getErrors(Errors.NoPouch));
                            return true;
                        }
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target == null) {
                            sender.sendMessage(Errors.getErrors(Errors.SpecifyPlayer));
                            return true;
                        }
                        if (!plugin.MoneyPouchesFile.getConfig().contains("Tiers.")) return true;
                        for (String key : plugin.MoneyPouchesFile.getConfig().getConfigurationSection("Tiers.").getKeys(false)) {
                            if (key.equalsIgnoreCase(args[1])) {
                                ItemStack item = new ItemStack(getMaterial(plugin.MoneyPouchesFile.getConfig().getString("Tiers." + key + ".material")));
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(Utils.chat(plugin.MoneyPouchesFile.getConfig().getString("Tiers." + key + ".name")));
                                meta.setLore(plugin.essentialsZAPI.utils.getConvertedLore(plugin.MoneyPouchesFile.getConfig(), "Tiers." + key));
                                item.setItemMeta(meta);

                                target.getInventory().addItem(item);
                            }
                        }
                        sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("Money Pouch Given"), target, target, null, false));
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.MoneyPouch) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length == 2) {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target == null) {
                                player.sendMessage(Errors.getErrors(Errors.SpecifyPlayer));
                                return true;
                            }
                            if (!plugin.MoneyPouchesFile.getConfig().contains("Tiers.")) return true;
                            for (String key : plugin.MoneyPouchesFile.getConfig().getConfigurationSection("Tiers.").getKeys(false)) {
                                if (key.equalsIgnoreCase(args[1])) {
                                    ItemStack item = new ItemStack(getMaterial(plugin.MoneyPouchesFile.getConfig().getString("Tiers." + key + ".material")));
                                    ItemMeta meta = item.getItemMeta();
                                    meta.setDisplayName(Utils.chat(plugin.MoneyPouchesFile.getConfig().getString("Tiers." + key + ".name")));
                                    meta.setLore(plugin.essentialsZAPI.utils.getConvertedLore(plugin.MoneyPouchesFile.getConfig(), "Tiers." + key));
                                    item.setItemMeta(meta);

                                    target.getInventory().addItem(item);
                                }
                            }
                            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                    plugin.MessagesFile.getConfig().getString("Money Pouch Given"), target, target, null, false));
                        }
                        if (args.length < 1) {
                            player.sendMessage(Errors.getErrors(Errors.InvalidPouch));
                            return true;
                        }
                        if (!plugin.MoneyPouchesFile.getConfig().contains("Tiers.")) return true;
                        for (String key : plugin.MoneyPouchesFile.getConfig().getConfigurationSection("Tiers.").getKeys(false)) {
                            if (key.equalsIgnoreCase(args[0])) {
                                ItemStack item = new ItemStack(getMaterial(plugin.MoneyPouchesFile.getConfig().getString("Tiers." + key + ".material")));
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(Utils.chat(plugin.MoneyPouchesFile.getConfig().getString("Tiers." + key + ".name")));
                                meta.setLore(plugin.essentialsZAPI.utils.getConvertedLore(plugin.MoneyPouchesFile.getConfig(), "Tiers." + key));
                                item.setItemMeta(meta);

                                player.getInventory().addItem(item);
                            }
                        }
                        sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("Money Pouch Given"), player, player, null, false));
                    } else {
                        player.sendMessage(Errors.getErrors(Errors.NoPermission));
                    }
                }
            }
        }
        return false;
    }

}
