package me.darkwinged.Essentials.Commands.Economy;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.Material.getMaterial;

public class cmd_MoneyPouches implements CommandExecutor {

    private Main plugin;
    public cmd_MoneyPouches(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("pouches")) {
            if (plugin.getConfig().getBoolean("Economy", true)) {
                if (plugin.getConfig().getBoolean("Money_Pouches", true)) {
                    if (!(sender instanceof Player)) {
                        if (args.length != 2) {
                            sender.sendMessage(ErrorMessages.NoPlayer);
                            sender.sendMessage(ErrorMessages.NoPouch);
                            return true;
                        }
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target == null) {
                            sender.sendMessage(ErrorMessages.NoPlayerFound);
                            return true;
                        }
                        if (!plugin.MoneyPouchesFile.getConfig().contains("Tiers.")) return true;
                        for (String key : plugin.MoneyPouchesFile.getConfig().getConfigurationSection("Tiers.").getKeys(false)) {
                            ItemStack item = new ItemStack(getMaterial(plugin.MoneyPouchesFile.getConfig().getString("Tiers." + key + ".material")));
                            ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName(Utils.chat(plugin.MoneyPouchesFile.getConfig().getString("Tiers." + key + ".name")));
                            meta.setLore(plugin.getConvertedLore("Tiers." + key));
                            item.setItemMeta(meta);

                            target.getInventory().addItem(item);
                        }
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.MoneyPouch) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length == 2) {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target == null) {
                                sender.sendMessage(ErrorMessages.NoPlayerFound);
                                return true;
                            }
                            if (!plugin.MoneyPouchesFile.getConfig().contains("Tiers.")) return true;
                            for (String key : plugin.MoneyPouchesFile.getConfig().getConfigurationSection("Tiers.").getKeys(false)) {
                                ItemStack item = new ItemStack(getMaterial(plugin.MoneyPouchesFile.getConfig().getString("Tiers." + key + ".material")));
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(Utils.chat(plugin.MoneyPouchesFile.getConfig().getString("Tiers." + key + ".name")));
                                meta.setLore(plugin.getConvertedLore("Tiers." + key));
                                item.setItemMeta(meta);

                                target.getInventory().addItem(item);
                            }
                        }
                        if (args.length < 1) {
                            player.sendMessage(ErrorMessages.InvalidPouch);
                            return true;
                        }
                        if (!plugin.MoneyPouchesFile.getConfig().contains("Tiers.")) return true;
                        for (String key : plugin.MoneyPouchesFile.getConfig().getConfigurationSection("Tiers.").getKeys(false)) {
                            ItemStack item = new ItemStack(getMaterial(plugin.MoneyPouchesFile.getConfig().getString("Tiers." + key + ".material")));
                            ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName(Utils.chat(plugin.MoneyPouchesFile.getConfig().getString("Tiers." + key + ".name")));
                            meta.setLore(plugin.getConvertedLore("Tiers." + key));
                            item.setItemMeta(meta);

                            player.getInventory().addItem(item);
                        }
                    } else {
                        player.sendMessage(ErrorMessages.NoPermission);
                    }
                }
            }
        }
        return false;
    }

}
