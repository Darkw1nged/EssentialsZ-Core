package me.darkwinged.essentialsz.commands.economy.sell;

import lombok.RequiredArgsConstructor;
import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.Permission;
import me.darkwinged.essentialsz.commands.Name;
import me.darkwinged.essentialsz.commands.annotation.Permissions;
import me.darkwinged.essentialsz.inject.Inject;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@Name("sellwand")
@Permissions(value = { Permission.SELLWAND, Permission.GLOBAL })
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class SellWandCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
            if (plugin.getConfig().getBoolean("Economy.Settings.Sell.Sell", true)) {

                if (!(sender instanceof Player)) {
                    if (args.length != 2) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.SpecifyPlayer));
                        sender.sendMessage(ErrorManager.getErrors(Errors.Sellwand));
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.NoPlayerFound));
                        return true;
                    }
                    int uses = Integer.parseInt(args[1]);
                    ItemStack item = new ItemStack(Material.getMaterial(plugin.EconomyItems.getConfig().getString("Sellwand.material").toUpperCase()));
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(plugin.essentialsZAPI.utils.chat(plugin.EconomyItems.getConfig().getString("Sellwand.name")));

                    List<String> old_lore = plugin.EconomyItems.getConfig().getStringList("Sellwand.lore");
                    List<String> lore = new ArrayList<>();
                    for (String str : old_lore) {
                        if (str.contains("%uses%")) {
                            str = str.replaceAll("%uses%", String.valueOf(uses));
                        }
                        lore.add(plugin.essentialsZAPI.utils.chat(str));
                    }
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                    meta.setLore(lore);
                    item.setItemMeta(meta);

                    target.getInventory().addItem(item);
                    return true;
                }
                if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.NoPlayerFound));
                        return true;
                    }
                    int uses = Integer.parseInt(args[1]);
                    ItemStack item = new ItemStack(Material.getMaterial(plugin.EconomyItems.getConfig().getString("Sellwand.material").toUpperCase()));
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(plugin.essentialsZAPI.utils.chat(plugin.EconomyItems.getConfig().getString("Sellwand.name")));

                    List<String> old_lore = plugin.EconomyItems.getConfig().getStringList("Sellwand.lore");
                    List<String> lore = new ArrayList<>();
                    for (String str : old_lore) {
                        if (str.contains("%uses%")) {
                            str = str.replaceAll("%uses%", String.valueOf(uses));
                        }
                        lore.add(plugin.essentialsZAPI.utils.chat(str));
                    }
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                    meta.setLore(lore);
                    item.setItemMeta(meta);

                    target.getInventory().addItem(item);
                } else if (args.length == 1) {
                    Player player = (Player)sender;
                    int uses = Integer.parseInt(args[0]);
                    ItemStack item = new ItemStack(Material.getMaterial(plugin.EconomyItems.getConfig().getString("Sellwand.material").toUpperCase()));
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(plugin.essentialsZAPI.utils.chat(plugin.EconomyItems.getConfig().getString("Sellwand.name")));

                    List<String> old_lore = plugin.EconomyItems.getConfig().getStringList("Sellwand.lore");
                    List<String> lore = new ArrayList<>();
                    for (String str : old_lore) {
                        if (str.contains("%uses%")) {
                            str = str.replaceAll("%uses%", String.valueOf(uses));
                        }
                        lore.add(plugin.essentialsZAPI.utils.chat(str));
                    }
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                    meta.setLore(lore);
                    item.setItemMeta(meta);

                    player.getInventory().addItem(item);
                }
            }
        }
        return false;
    }

}
