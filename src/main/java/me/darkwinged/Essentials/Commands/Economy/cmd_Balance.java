package me.darkwinged.Essentials.Commands.Economy;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
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
			if (plugin.getConfig().getBoolean("Economy", true)) {
				if (plugin.getConfig().getBoolean("cmd_Balance", true)) {
					if (!(sender instanceof Player)) {
						if (args.length != 1) {
							sender.sendMessage(ErrorMessages.NoPlayer);
						}
						Player target = Bukkit.getPlayer(args[0]);
						sender.sendMessage(Utils.chat(plugin.getConfig().getString("Balance_Prefix") + plugin.getConfig().getString("Currency_Symbol")
								+ EconomyManager.getBalance(target.getName())));
						return true;
					}
					Player player = (Player)sender;
					if (player.hasPermission(Permissions.Balance) || player.hasPermission(Permissions.GlobalOverwrite)) {
						if (args.length != 1) {
							player.sendMessage(Utils.chat(plugin.getConfig().getString("Balance_Prefix") + plugin.getConfig().getString("Currency_Symbol")
									+ EconomyManager.getBalance(player.getName())));
							return true;
						}
						if (player.hasPermission(Permissions.BalanceOther) || player.hasPermission(Permissions.GlobalOverwrite)) {
							Player target = Bukkit.getPlayer(args[0]);
							player.sendMessage(Utils.chat(plugin.getConfig().getString("Balance_Prefix_Other").replaceAll("%player%", target.getName()) + plugin.getConfig().getString(
									"Currency_Symbol")
									+ EconomyManager.getBalance(target.getName())));
						} else {
							player.sendMessage(ErrorMessages.NoPermission + " But here is your balance instead.");
							player.sendMessage(Utils.chat(plugin.getConfig().getString("Balance_Prefix") + plugin.getConfig().getString("Currency_Symbol")
									+ EconomyManager.getBalance(player.getName())));
						}
					} else {
						player.sendMessage(ErrorMessages.NoPermission);
					}
				}
			}
		}
		return true;
	}
	
}
