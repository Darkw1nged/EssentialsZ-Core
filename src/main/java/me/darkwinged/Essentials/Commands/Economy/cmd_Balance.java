package me.darkwinged.Essentials.Commands.Economy;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.darkwinged.Essentials.Utils.EconomyManager;
import me.darkwinged.Essentials.Utils.Lang.Utils;

public class cmd_Balance implements CommandExecutor {

	private Main plugin;
	public cmd_Balance(Main plugin) { this.plugin = plugin; }
	
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (cmd.getName().equalsIgnoreCase("balance")) {
			if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
				if (plugin.getConfig().getBoolean("Economy.Settings.Commands.Balance", true)) {
					if (plugin.getConfig().getString("Economy.API").equalsIgnoreCase("Vault")) {
					} else if (plugin.getConfig().getString("Economy.API").equalsIgnoreCase("EssentialsZ")) {
						if (!(sender instanceof Player)) {
							if (args.length != 1) {
								Utils.Message(sender, Errors.getErrors(Errors.SpecifyPlayer));
							}
							Player target = Bukkit.getPlayer(args[0]);
							sender.sendMessage(Utils.chat(plugin.getConfig().getString("Economy.Settings.Balance_Prefix") + plugin.getConfig().getString("Economy.Settings.Currency_Symbol")
									+ EconomyManager.getBalance(target.getName())));
							return true;
						}
						Player player = (Player)sender;
						if (player.hasPermission(Permissions.Balance) || player.hasPermission(Permissions.GlobalOverwrite)) {
							if (args.length != 1) {
								player.sendMessage(Utils.chat(plugin.getConfig().getString("Economy.Settings.Balance_Prefix") + plugin.getConfig().getString("Economy.Settings.Currency_Symbol")
										+ EconomyManager.getBalance(player.getName())));
								return true;
							}
							if (player.hasPermission(Permissions.BalanceOther) || player.hasPermission(Permissions.GlobalOverwrite)) {
								Player target = Bukkit.getPlayer(args[0]);
								player.sendMessage(Utils.chat(plugin.getConfig().getString("Economy.Settings.Balance_Prefix_Other").replaceAll("%player%", target.getName()) + plugin.getConfig().getString(
										"Economy.Settings.Currency_Symbol")
										+ EconomyManager.getBalance(target.getName())));
							} else {
								Utils.Message(player, Errors.getErrors(Errors.NoPermission));
							}
						} else {
							Utils.Message(player, Errors.getErrors(Errors.NoPermission));
						}
					}
				}
			}
		}
		return true;
	}
	
}
