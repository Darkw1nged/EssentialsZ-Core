package me.darkwinged.Essentials.Commands.Economy;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.EssentialsZEconomy.EconomyManager;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Balance implements CommandExecutor {

	private Main plugin;
	public cmd_Balance(Main plugin) {
		this.plugin = plugin;
	}
	
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
						sender.sendMessage(Utils.chat(plugin.getConfig().getString("Economy.Settings.Currency Symbol") + EconomyManager.getAccount(target)));
						return true;
					}
					Player player = (Player)sender;
					if (args.length == 1) {
						if (player.hasPermission(Permissions.BalanceOther) || player.hasPermission(Permissions.GlobalOverwrite)) {
							Player target = Bukkit.getPlayer(args[0]);
							sender.sendMessage(Utils.chat(plugin.getConfig().getString("Economy.Settings.Currency Symbol") + EconomyManager.getAccount(target)));
							return true;
						}
					}
					if (player.hasPermission(Permissions.Balance) || player.hasPermission(Permissions.GlobalOverwrite)) {
						sender.sendMessage(Utils.chat(plugin.getConfig().getString("Economy.Settings.Currency Symbol") + EconomyManager.getAccount(player)));
					}
				}
			}
		}
		return true;
	}
	
}
