package me.darkwinged.essentialsz.commands.economy;

import lombok.RequiredArgsConstructor;
import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.Permission;
import me.darkwinged.essentialsz.commands.Name;
import me.darkwinged.essentialsz.commands.annotation.Permissions;
import me.darkwinged.essentialsz.inject.Inject;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Name("withdraw")
@Permissions(value = {Permission.WITHDRAW, Permission.GLOBAL})
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class WithdrawCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
            if (plugin.getConfig().getBoolean("Economy.Settings.Bank Notes", true)) {
                if (!plugin.Module_Economy) return true;
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ErrorManager.getErrors(Errors.Console));
                    return true;
                }
                Player player = (Player)sender;
                if (args.length != 1) {
                    sender.sendMessage(ErrorManager.getErrors(Errors.InvalidAmount));
                    return true;
                }
                double amount = Double.parseDouble(args[0]);
                if (!plugin.economyManager.hasAccount(player)) {
                    return true;
                }
                if (plugin.economyManager.hasEnoughMoney(player, amount)) {
                    sender.sendMessage(ErrorManager.getErrors(Errors.NotEnoughMoney));
                    return true;
                }
                // Getting the amount
                try {
                    amount = Double.parseDouble(args[0]);
                } catch (Exception e) {
                    sender.sendMessage(ErrorManager.getErrors(Errors.InvalidAmount));
                    return true;
                }
                if (player.getInventory().firstEmpty() == 0) return true;
                // Removing the amount from the senders balance
                plugin.economyManager.RemoveAccount(player, amount);
                player.getInventory().addItem(createNote("Money Note", amount));
            }
        }
        return false;
    }

    public ItemStack createNote(String Path, double amount) {
        String uniqueID = UUID.randomUUID().toString();
        ItemStack item;
        YamlConfiguration file = plugin.EconomyItems.getConfig();
        if (file.getString(Path + ".item") == null) {
            item = new ItemStack(Material.getMaterial(file.getString(Path + ".material").toUpperCase()));
        } else {
            item = new ItemStack(Material.PAPER);
        }
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(plugin.essentialsZAPI.utils.chat(file.getString(Path + ".name")));

        List<String> old_lore = plugin.essentialsZAPI.utils.getConvertedLore(file, Path);
        List<String> lore = new ArrayList<>();
        for (String str : old_lore) {
            str = str.replaceAll("%amount%", "" + amount);
            str = str.replaceAll("%identification%", uniqueID);
            lore.add(str);
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

}
