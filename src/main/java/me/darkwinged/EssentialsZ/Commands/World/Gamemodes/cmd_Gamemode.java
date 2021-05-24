package me.darkwinged.EssentialsZ.Commands.World.Gamemodes;

import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Gamemode implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gamemode")) {
            if (plugin.getConfig().getBoolean("cmd_Gamemode", true)) {
                if (args.length <= 2) {
                    if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s")) {
                        if (!(sender instanceof Player)) {
                            if (args.length != 1) {
                                sender.sendMessage(Errors.getErrors(Errors.GamemodeUsage));
                                return true;
                            }
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target == null) {
                                sender.sendMessage(Errors.getErrors(Errors.NoPlayerFound));
                                return true;
                            }
                            String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
                                    .replaceAll("%gamemode%", "survival");
                            sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message, target, target, null, false));
                            target.setGameMode(GameMode.SURVIVAL);
                            return true;
                        }
                        Player player = (Player) sender;
                        if (args.length != 1) {
                            if (player.hasPermission(Permissions.SurvivalMode) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
                                String Message = plugin.MessagesFile.getConfig().getString("Gamemode").replaceAll("%gamemode%", "survival");
                                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message, null, null, null, false));
                                player.setGameMode(GameMode.SURVIVAL);
                            } else {
                                player.sendMessage(Errors.getErrors(Errors.NoPermission));
                            }
                        } else {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (player.hasPermission(Permissions.SurvivalModeOther) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
                                if (target == null) {
                                    player.sendMessage(Errors.getErrors(Errors.NoPlayerFound));
                                    return true;
                                }
                                String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
                                        .replaceAll("%gamemode%", "survival");
                                sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message,
                                        target, target, null, false));
                                target.setGameMode(GameMode.SURVIVAL);
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c")) {
                        if (!(sender instanceof Player)) {
                            if (args.length != 1) {
                                sender.sendMessage(Errors.getErrors(Errors.GamemodeUsage));
                                return true;
                            }
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target == null) {
                                sender.sendMessage(Errors.getErrors(Errors.NoPlayerFound));
                                return true;
                            }
                            String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
                                    .replaceAll("%gamemode%", "creative");
                            sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message,
                                    target, target, null, false));
                            target.setGameMode(GameMode.CREATIVE);
                            return true;
                        }
                        Player player = (Player) sender;
                        if (args.length != 1) {
                            if (player.hasPermission(Permissions.CreativeMode) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
                                String Message = plugin.MessagesFile.getConfig().getString("Gamemode").replaceAll("%gamemode%", "creative");
                                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message,
                                        null, null, null, false));
                                player.setGameMode(GameMode.CREATIVE);
                            } else {
                                player.sendMessage(Errors.getErrors(Errors.NoPermission));
                            }
                        } else {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (player.hasPermission(Permissions.CreativeModeOther) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
                                if (target == null) {
                                    player.sendMessage(Errors.getErrors(Errors.NoPlayerFound));
                                    return true;
                                }
                                String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
                                        .replaceAll("%gamemode%", "creative");
                                sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message,
                                        target, target, null, false));
                                target.setGameMode(GameMode.CREATIVE);
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a")) {
                        if (!(sender instanceof Player)) {
                            if (args.length != 1) {
                                sender.sendMessage(Errors.getErrors(Errors.GamemodeUsage));
                                return true;
                            }
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target == null) {
                                sender.sendMessage(Errors.getErrors(Errors.NoPlayerFound));
                                return true;
                            }
                            String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
                                    .replaceAll("%gamemode%", "adventure");
                            sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message,
                                    target, target, null, false));
                            target.setGameMode(GameMode.ADVENTURE);
                            return true;
                        }
                        Player player = (Player) sender;
                        if (args.length != 1) {
                            if (player.hasPermission(Permissions.AdventureMode) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
                                String Message = plugin.MessagesFile.getConfig().getString("Gamemode").replaceAll("%gamemode%", "adventure");
                                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message,
                                        null, null, null, false));
                                player.setGameMode(GameMode.ADVENTURE);
                            } else {
                                player.sendMessage(Errors.getErrors(Errors.NoPermission));
                            }
                        } else {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (player.hasPermission(Permissions.AdventureModeOther) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
                                if (target == null) {
                                    player.sendMessage(Errors.getErrors(Errors.NoPlayerFound));
                                    return true;
                                }
                                String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
                                        .replaceAll("%gamemode%", "adventure");
                                sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message,
                                        target, target, null, false));
                                target.setGameMode(GameMode.ADVENTURE);
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("sp")) {
                        if (!(sender instanceof Player)) {
                            if (args.length != 1) {
                                sender.sendMessage(Errors.getErrors(Errors.GamemodeUsage));
                                return true;
                            }
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target == null) {
                                sender.sendMessage(Errors.getErrors(Errors.NoPlayerFound));
                                return true;
                            }
                            String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
                                    .replaceAll("%gamemode%", "spectator");
                            sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message,
                                    target, target, null, false));
                            target.setGameMode(GameMode.SPECTATOR);
                            return true;
                        }
                        Player player = (Player) sender;
                        if (args.length != 1) {
                            if (player.hasPermission(Permissions.SpectatorMode) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
                                String Message = plugin.MessagesFile.getConfig().getString("Gamemode").replaceAll("%gamemode%", "spectator");
                                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message,
                                        null, null, null, false));
                                player.setGameMode(GameMode.SPECTATOR);
                            } else {
                                player.sendMessage(Errors.getErrors(Errors.NoPermission));
                            }
                        } else {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (player.hasPermission(Permissions.SpectatorModeOther) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
                                if (target == null) {
                                    sender.sendMessage(Errors.getErrors(Errors.NoPlayerFound));
                                    return true;
                                }
                                String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
                                        .replaceAll("%gamemode%", "spectator");
                                sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message,
                                        target, target, null, false));
                                target.setGameMode(GameMode.SPECTATOR);
                            }
                        }
                    } else
                        sender.sendMessage(Errors.getErrors(Errors.GamemodeUsage));
                }
            }
        }
        return true;
    }
}
