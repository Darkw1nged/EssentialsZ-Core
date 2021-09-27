package me.darkwinged.essentialsz.commands.economy.sell;

import lombok.RequiredArgsConstructor;
import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.Permission;
import me.darkwinged.essentialsz.commands.Name;
import me.darkwinged.essentialsz.commands.annotation.Permissions;
import me.darkwinged.essentialsz.inject.Inject;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

@Name("chestsell")
@Permissions({ Permission.CHESTSELL, Permission.GLOBAL})
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ChestsellCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
            if (plugin.getConfig().getBoolean("Economy.Settings.Sell.ChestSell", true)) {
                if (!plugin.Module_Economy) return true;
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ErrorManager.getErrors(Errors.Console));
                    return true;
                }
                Player player = (Player)sender;
                Set<Material> material_set = null;
                Block block = player.getTargetBlock(material_set, 5);
                if (block.getType().equals(Material.CHEST) || block.getType().equals(Material.TRAPPED_CHEST)) {
                    Chest chest = (Chest)block.getState();
                    double amount = 0;
                    if (chest.getInventory().getSize() == 54) {
                        for (int i = 0; i <= 54; i++) {
                            amount = getAmount(chest, amount, i);
                        }
                    } else if (chest.getInventory().getSize() == 27) {
                        for (int i = 0; i <= 27; i++) {
                            amount = getAmount(chest, amount, i);
                        }
                    }
                    if (amount <= 0) {
                        player.sendMessage(ErrorManager.getErrors(Errors.NoSellableItems));
                        return true;
                    }
                    player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                            plugin.MessagesFile.getConfig().getString("Sold").replaceAll("%amount%", ""+amount)));
                    plugin.economyManager.AddAccount(player, amount);
                } else {
                    player.sendMessage(ErrorManager.getErrors(Errors.NoChest));
                }
            } else
                sender.sendMessage(ErrorManager.getErrors(Errors.Console));
        }
        return false;
    }

    private double getAmount(Chest chest, double amount, int i) {
        try {
            ItemStack item = chest.getInventory().getItem(i);
            if (plugin.WorthFile.getConfig().contains("worth." + item.getType().name().toUpperCase())) {
                amount += plugin.WorthFile.getConfig().getDouble("worth." + item.getType().name().toUpperCase()) * item.getAmount();
            }
            item.setAmount(0);
        } catch (Exception ignored) { }
        return amount;
    }

}
