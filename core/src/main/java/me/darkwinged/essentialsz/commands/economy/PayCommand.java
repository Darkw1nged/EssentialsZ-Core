package me.darkwinged.essentialsz.commands.economy;

import lombok.RequiredArgsConstructor;
import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.Permission;
import me.darkwinged.essentialsz.commands.Name;
import me.darkwinged.essentialsz.commands.annotation.Permissions;
import me.darkwinged.essentialsz.inject.Inject;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.NumberFormat;
import java.util.Locale;

@Name("pay")
@Permissions(value = {Permission.PAY, Permission.GLOBAL})
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PayCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
            if (plugin.getConfig().getBoolean("Economy.Settings.Commands.Pay", true)) {
                if (plugin.Module_Economy = false) return true;
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ErrorManager.getErrors(Errors.Console));
                    return true;
                }
                Player player = (Player) sender;
                if (args.length < 2) {
                    player.sendMessage(ErrorManager.getErrors(Errors.UsagePay));
                    return true;
                }
                Player target = Bukkit.getPlayer(args[0]);
                double amount = Double.parseDouble(args[1]);
                if (target == null) {
                    player.sendMessage(ErrorManager.getErrors(Errors.NoPlayerFound));
                    return true;
                }
                if (target == player) {
                    player.sendMessage(ErrorManager.getErrors(Errors.SenderInstaceOfPlayer));
                    return true;
                }
                // Player Account
                plugin.economyManager.hasAccount(player);
                plugin.economyManager.hasEnoughMoney(player, amount);

                // Target Account
                plugin.economyManager.hasAccount(player);

                // Getting the amount
                try {
                    amount = Double.parseDouble(args[1]);
                } catch (Exception e) {
                    player.sendMessage(ErrorManager.getErrors(Errors.InvalidAmount));
                    return true;
                }
                // Removing the amount from the senders balance
                plugin.economyManager.RemoveAccount(player, amount);
                // Adding the amount to the target balance
                plugin.economyManager.AddAccount(target, amount);

                NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
                String amount_formatted = nf.format(amount);

                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Payed")
                        .replaceAll("%amount%", "" + amount_formatted), target, target));
                target.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Money Received")
                        .replaceAll("%amount%", "" + amount_formatted), player, player));
            }
        }
        return false;
    }

}
