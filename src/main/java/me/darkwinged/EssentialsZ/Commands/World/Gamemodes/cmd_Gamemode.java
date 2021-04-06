package me.darkwinged.EssentialsZ.Commands.World.Gamemodes;

import me.darkwinged.EssentialsZ.Main;
import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Libaries.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Gamemode implements CommandExecutor {

	private final Main plugin;
	public cmd_Gamemode(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gamemode")) {
			if (plugin.getConfig().getBoolean("cmd_Gamemode", true)) {
				if (args.length <= 2) {
					if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s")) {
						if (!(sender instanceof Player)) {
							if (args.length != 2) {
								Utils.Message(sender, Errors.getErrors(Errors.GamemodeUsage));
								return true;
							}
							Player target = Bukkit.getPlayer(args[1]);
							if (target == null) {
								Utils.Message(sender, Errors.getErrors(Errors.NoPlayerFound));
								return true;
							}
							String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
									.replaceAll("%gamemode%", "survival")
									.replaceAll("%player%", target.getName());
							sender.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
							target.setGameMode(GameMode.SURVIVAL);
						}
						Player player = (Player) sender;
						if (args.length != 2) {
							if (player.hasPermission(Permissions.SurvivalMode) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
								String Message = plugin.MessagesFile.getConfig().getString("Gamemode").replaceAll("%gamemode%", "survival");
								player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
								player.setGameMode(GameMode.SURVIVAL);
							} else {
								Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
							}
						} else {
							Player target = Bukkit.getPlayer(args[1]);
							if (player.hasPermission(Permissions.SurvivalModeOther) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
								if (target == null) {
									Utils.Message(sender, Errors.getErrors(Errors.NoPlayerFound));
									return true;
								}
								String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
										.replaceAll("%gamemode%", "survival")
										.replaceAll("%player%", target.getName());
								sender.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
								target.setGameMode(GameMode.SURVIVAL);
							}
						}
					} else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c")) {
						if (!(sender instanceof Player)) {
							if (args.length != 2) {
								Utils.Message(sender, Errors.getErrors(Errors.GamemodeUsage));
								return true;
							}
							Player target = Bukkit.getPlayer(args[1]);
							if (target == null) {
								Utils.Message(sender, Errors.getErrors(Errors.NoPlayerFound));
								return true;
							}
							String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
									.replaceAll("%gamemode%", "creative")
									.replaceAll("%player%", target.getName());
							sender.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
							target.setGameMode(GameMode.CREATIVE);
						}
						Player player = (Player) sender;
						if (args.length != 2) {
							if (player.hasPermission(Permissions.CreativeMode) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
								String Message = plugin.MessagesFile.getConfig().getString("Gamemode").replaceAll("%gamemode%", "creative");
								player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
								player.setGameMode(GameMode.CREATIVE);
							} else {
								Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
							}
						} else {
							Player target = Bukkit.getPlayer(args[1]);
							if (player.hasPermission(Permissions.CreativeModeOther) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
								if (target == null) {
									Utils.Message(sender, Errors.getErrors(Errors.NoPlayerFound));
									return true;
								}
								String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
										.replaceAll("%gamemode%", "creative")
										.replaceAll("%player%", target.getName());
								sender.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
								target.setGameMode(GameMode.CREATIVE);
							}
						}
					} else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a")) {
						if (!(sender instanceof Player)) {
							if (args.length != 2) {
								Utils.Message(sender, Errors.getErrors(Errors.GamemodeUsage));
								return true;
							}
							Player target = Bukkit.getPlayer(args[1]);
							if (target == null) {
								Utils.Message(sender, Errors.getErrors(Errors.NoPlayerFound));
								return true;
							}
							String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
									.replaceAll("%gamemode%", "adventure")
									.replaceAll("%player%", target.getName());
							sender.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
							target.setGameMode(GameMode.ADVENTURE);
						}
						Player player = (Player) sender;
						if (args.length != 2) {
							if (player.hasPermission(Permissions.AdventureMode) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
								String Message = plugin.MessagesFile.getConfig().getString("Gamemode").replaceAll("%gamemode%", "adventure");
								player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
								player.setGameMode(GameMode.ADVENTURE);
							} else {
								Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
							}
						} else {
							Player target = Bukkit.getPlayer(args[1]);
							if (player.hasPermission(Permissions.AdventureModeOther) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
								if (target == null) {
									Utils.Message(sender, Errors.getErrors(Errors.NoPlayerFound));
									return true;
								}
								String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
										.replaceAll("%gamemode%", "adventure")
										.replaceAll("%player%", target.getName());
								sender.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
								target.setGameMode(GameMode.ADVENTURE);
							}
						}
					} else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("sp")) {
						if (!(sender instanceof Player)) {
							if (args.length != 2) {
								Utils.Message(sender, Errors.getErrors(Errors.GamemodeUsage));
								return true;
							}
							Player target = Bukkit.getPlayer(args[1]);
							if (target == null) {
								Utils.Message(sender, Errors.getErrors(Errors.NoPlayerFound));
								return true;
							}
							String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
									.replaceAll("%gamemode%", "spectator")
									.replaceAll("%player%", target.getName());
							sender.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
							target.setGameMode(GameMode.SPECTATOR);
						}
						Player player = (Player) sender;
						if (args.length != 2) {
							if (player.hasPermission(Permissions.SpectatorMode) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
								String Message = plugin.MessagesFile.getConfig().getString("Gamemode").replaceAll("%gamemode%", "spectator");
								player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
								player.setGameMode(GameMode.SPECTATOR);
							} else {
								Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
							}
						} else {
							Player target = Bukkit.getPlayer(args[1]);
							if (player.hasPermission(Permissions.SpectatorModeOther) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
								if (target == null) {
									Utils.Message(sender, Errors.getErrors(Errors.NoPlayerFound));
									return true;
								}
								String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
										.replaceAll("%gamemode%", "spectator")
										.replaceAll("%player%", target.getName());
								sender.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
								target.setGameMode(GameMode.SPECTATOR);
							}
						}
					} else
						Utils.Message(sender, Errors.getErrors(Errors.GamemodeUsage));
				}
			}
        }
        return true;
    }
}
