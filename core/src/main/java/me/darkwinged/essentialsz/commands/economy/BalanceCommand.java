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

@Name("balance")
@Permissions(value = { Permission.BALANCE, Permission.GLOBAL })
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class BalanceCommand implements CommandExecutor {

	private final Main plugin = Main.getInstance;
	
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
			if (plugin.getConfig().getBoolean("Economy.Settings.Commands.Balance", true)) {
				if (!plugin.Module_Economy) return true;
				if (!(sender instanceof Player)) {
					if (args.length != 1) {
						sender.sendMessage(ErrorManager.getErrors(Errors.SpecifyPlayer));
						return true;
					}
					Player target = Bukkit.getPlayer(args[0]);
					if (target == null) {
						sender.sendMessage(ErrorManager.getErrors(Errors.NoPlayerFound));
						return true;
					}
					return getBalance(sender, target);
				}
				Player player = (Player)sender;
				if (args.length == 1) {
					if (player.hasPermission(Permission.BALANCE_OTHER.getPermissionNode()) || player.hasPermission(Permission.GLOBAL.getPermissionNode())) {
						Player target = Bukkit.getPlayer(args[0]);
						if (target == null) {
							player.sendMessage(ErrorManager.getErrors(Errors.NoPlayerFound));
							return true;
						}
						return getBalance(sender, target);
					}
				}
				NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
				String amount_formatted = nf.format(plugin.economyManager.getAccount(player));

				sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("Economy.Settings.Balance.Prefix") +
						plugin.getConfig().getString("Economy.Settings.Currency Symbol") + amount_formatted));
			} else
				sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));
		}
		return true;
	}

	private boolean getBalance(CommandSender sender, Player target) {
		NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
		String amount_formatted = nf.format(plugin.economyManager.getAccount(target));

		sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("Economy.Settings.Balance.Other") +
				plugin.getConfig().getString("Economy.Settings.Currency Symbol") + amount_formatted, target, target));
		return true;
	}

}
