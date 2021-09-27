package me.darkwinged.essentialsz.commands.economy.sell;

import lombok.RequiredArgsConstructor;
import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.Permission;
import me.darkwinged.essentialsz.commands.Name;
import me.darkwinged.essentialsz.commands.annotation.Permissions;
import me.darkwinged.essentialsz.commands.processor.annotation.PlayersOnly;
import me.darkwinged.essentialsz.inject.Inject;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Name("sell")
@PlayersOnly
@Permissions(value = { Permission.SELL, Permission.GLOBAL })
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class SellCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
            if (plugin.getConfig().getBoolean("Economy.Settings.Sell.Sell", true)) {
                if (!plugin.Module_Economy) return true;
                Player player = (Player)sender;
                Double amount = getSellableItems(player);
                if (amount <= 0) {
                    player.sendMessage(ErrorManager.getErrors(Errors.NoSellableItems));
                    return true;
                }
                // message
                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                        plugin.MessagesFile.getConfig().getString("Sold").replaceAll("%amount%", ""+amount)));
                plugin.economyManager.AddAccount(player, amount);
            } else
                sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));
        }
        return false;
    }

    public Double getSellableItems(Player player) {
        double amount = 0;
        for (ItemStack item : player.getInventory()) {
            if (item != null) {
                if (plugin.WorthFile.getConfig().contains("worth." + item.getType().name().toUpperCase())) {
                    amount += plugin.WorthFile.getConfig().getDouble("worth." + item.getType().name().toUpperCase()) * item.getAmount();
                    item.setAmount(0);
                }
            }
        }
        return amount;
    }
}
