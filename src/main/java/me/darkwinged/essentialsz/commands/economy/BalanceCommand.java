package me.darkwinged.essentialsz.commands.economy;

import me.darkwinged.essentialsz.libaries.lang.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import me.darkwinged.essentialsz.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand implements CommandExecutor {

	private final Main plugin = Main.getInstance;
	
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (cmd.getName().equalsIgnoreCase("balance")) {
			if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
				if (plugin.getConfig().getBoolean("Economy.Settings.Commands.Balance", true)) {
					if (plugin.Module_Economy = false) return true;
					if (!(sender instanceof Player)) {
						if (args.length != 1) {
							sender.sendMessage(Errors.getErrors(Errors.SpecifyPlayer));
							return true;
						}
						Player target = Bukkit.getPlayer(args[0]);
						if (target == null) {
							sender.sendMessage(Errors.getErrors(Errors.NoPlayerFound));
							return true;
						}
						sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("Economy.Settings.Balance.Other") +
								plugin.getConfig().getString("Economy.Settings.Currency Symbol") + plugin.economyManager.getAccount(target),
								target, target, null, false));
						return true;
					}
					Player player = (Player)sender;
					if (args.length == 1) {
						if (player.hasPermission(Permissions.BalanceOther) || player.hasPermission(Permissions.GlobalOverwrite)) {
							Player target = Bukkit.getPlayer(args[0]);
							if (target == null) {
								player.sendMessage(Errors.getErrors(Errors.NoPlayerFound));
								return true;
							}
							sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("Economy.Settings.Balance.Other") +
									plugin.getConfig().getString("Economy.Settings.Currency Symbol") + plugin.economyManager.getAccount(target),
									target, target, null, false));
							return true;
						}
					}
					if (player.hasPermission(Permissions.Balance) || player.hasPermission(Permissions.GlobalOverwrite)) {
						sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("Economy.Settings.Balance.Prefix") +
								plugin.getConfig().getString("Economy.Settings.Currency Symbol") + plugin.economyManager.getAccount(player),
								null, null, null, false));
					}
				}
			}
		}
		return true;
	}
	
}
