package me.darkwinged.Essentials.REWORK.Commands.Economy;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.MessagesFile;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Economy.MoneyPouchFile;
import me.darkwinged.Essentials.REWORK.Utils.ErrorMessages;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import me.darkwinged.Essentials.REWORK.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.Material.getMaterial;

public class cmd_MoneyPouches implements CommandExecutor {

    private MessagesFile messagesFile;
    private MoneyPouchFile moneyPouchFile;
    private Main plugin;
    public cmd_MoneyPouches(MessagesFile messagesFile, MoneyPouchFile moneyPouchFile, Main plugin) {
        this.messagesFile = messagesFile;
        this.plugin = plugin;
        this.moneyPouchFile = moneyPouchFile;
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
                        if (!moneyPouchFile.getConfig().contains("Tiers.")) return true;
                        for (String key : moneyPouchFile.getConfig().getConfigurationSection("Tiers.").getKeys(false)) {
                            ItemStack item = new ItemStack(getMaterial(moneyPouchFile.getConfig().getString("Tiers." + key + ".material")));
                            ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName(Utils.chat(moneyPouchFile.getConfig().getString("Tiers." + key + ".name")));
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
                            if (!moneyPouchFile.getConfig().contains("Tiers.")) return true;
                            for (String key : moneyPouchFile.getConfig().getConfigurationSection("Tiers.").getKeys(false)) {
                                ItemStack item = new ItemStack(getMaterial(moneyPouchFile.getConfig().getString("Tiers." + key + ".material")));
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(Utils.chat(moneyPouchFile.getConfig().getString("Tiers." + key + ".name")));
                                meta.setLore(plugin.getConvertedLore("Tiers." + key));
                                item.setItemMeta(meta);

                                target.getInventory().addItem(item);
                            }
                        }
                        if (args.length < 1) {
                            player.sendMessage(ErrorMessages.InvalidPouch);
                            return true;
                        }
                        if (!moneyPouchFile.getConfig().contains("Tiers.")) return true;
                        for (String key : moneyPouchFile.getConfig().getConfigurationSection("Tiers.").getKeys(false)) {
                            ItemStack item = new ItemStack(getMaterial(moneyPouchFile.getConfig().getString("Tiers." + key + ".material")));
                            ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName(Utils.chat(moneyPouchFile.getConfig().getString("Tiers." + key + ".name")));
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
