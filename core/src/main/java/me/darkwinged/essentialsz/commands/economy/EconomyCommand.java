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

@Name("economy")
@Permissions(value = {Permission.ECONOMY, Permission.GLOBAL})
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class EconomyCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
            if (!plugin.Module_Economy) return true;
            Player player = (Player) sender;
            if (args.length < 2) {
                player.sendMessage(ErrorManager.getErrors(Errors.UsageEconomy));
                return true;
            }
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                player.sendMessage(ErrorManager.getErrors(Errors.NoPlayerFound));
            }
            if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("give")) {
                Double amount = getAmount(sender, target, args[2]);
                plugin.economyManager.AddAccount(target, amount);
                NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
                String amount_formatted = nf.format(amount);

                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix")
                        + plugin.MessagesFile.getConfig().getString("Economy Add Message")
                        .replaceAll("%amount%", amount_formatted + ""), target, target, null, false));
                return true;
            } else if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("take")) {
                Double amount = getAmount(sender, target, args[2]);
                plugin.economyManager.RemoveAccount(player, amount);
                NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
                String amount_formatted = nf.format(amount);

                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix")
                        + plugin.MessagesFile.getConfig().getString("Economy Remove Message")
                        .replaceAll("%amount%", amount_formatted + ""), target, target, null, false));
                return true;
            } else if (args[0].equalsIgnoreCase("set")) {
                Double amount = getAmount(sender, target, args[2]);
                plugin.economyManager.setAccount(target, amount);
                NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
                String amount_formatted = nf.format(amount);

                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix")
                        + plugin.MessagesFile.getConfig().getString("Economy Set Message")
                        .replaceAll("%amount%", amount_formatted + ""), target, target, null, false));
                return true;
            } else if (args[0].equalsIgnoreCase("reset")) {
                if (!plugin.economyManager.hasAccount(target)) return true;
                plugin.economyManager.RemoveAccount(target, plugin.economyManager.getAccount(target));
                plugin.economyManager.AddAccount(target, plugin.getConfig().getInt("Economy.Settings.Starting Balance"));
                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix")
                        + plugin.MessagesFile.getConfig().getString("Economy Reset Message"), target, target, null, false));
            } else
                player.sendMessage(ErrorManager.getErrors(Errors.UsageEconomy));
        } else
            sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));
        return true;
    }

    private Double getAmount(CommandSender sender, Player target, String convert) {
        if (!plugin.economyManager.hasAccount(target)) return null;
        double amount;
        try {
            amount = Double.parseDouble(convert);
        } catch (Exception e) {
            sender.sendMessage(ErrorManager.getErrors(Errors.InvalidAmount));
            return null;
        }
        return amount;
    }

}
