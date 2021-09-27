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
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Name("autosell")
@PlayersOnly
@Permissions(value = { Permission.AUTOSELL, Permission.GLOBAL })
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class AutosellCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;
    private List<UUID> autosell_list = new ArrayList<>();

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
            if (plugin.getConfig().getBoolean("Economy.Settings.Sell.Autosell", true)) {
                if (!plugin.Module_Economy) return true;
                Player player = (Player)sender;
                UUID uuid = player.getUniqueId();
                if (autosell_list.contains(uuid)) {
                    autosell_list.remove(uuid);
                    player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                            plugin.MessagesFile.getConfig().getString("Autosell Toggle").replaceAll("%setting%", "disabled")));
                } else {
                    autosell_list.add(uuid);
                    player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                            plugin.MessagesFile.getConfig().getString("Autosell Toggle").replaceAll("%setting%", "enabled")));
                }
                Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
                    if (!autosell_list.contains(uuid)) return;
                    Double amount = getSellableItems(player);
                    if (amount <= 0) return;
                    // message
                    player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                            plugin.MessagesFile.getConfig().getString("Sold").replaceAll("%amount%", ""+amount)));
                    plugin.economyManager.AddAccount(player, amount);

                }, 0L, 20L);
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
