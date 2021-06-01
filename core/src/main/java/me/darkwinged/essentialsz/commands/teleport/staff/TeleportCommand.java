package me.darkwinged.essentialsz.commands.teleport.staff;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tp")) {
            if (plugin.getConfig().getBoolean("Teleportation.enabled", true)) {
                if (plugin.getConfig().getBoolean("Teleportation.Commands.teleport", true)) {
                    if (!(sender instanceof Player)) {
                        if (args.length != 2) {
                            sender.sendMessage(ChatColor.RED + "Error! Usage: /tp <player> <player>");
                            return true;
                        } else {
                            Player target1 = Bukkit.getPlayer(args[0]);
                            Player target2 = Bukkit.getPlayer(args[1]);
                            if (target1 == null) {
                                sender.sendMessage(ErrorManager.getErrors(Errors.NoPlayerFound));
                                return true;
                            }
                            if (target2 == null) {
                                sender.sendMessage(ErrorManager.getErrors(Errors.NoPlayerFound));
                                return true;
                            }
                            if (target1 == target2) {
                                sender.sendMessage(ErrorManager.getErrors(Errors.SenderInstaceOfPlayer));
                                return true;
                            }
                            sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                    plugin.MessagesFile.getConfig().getString("TP Other message"), target1, target1, null, false ));
                            target1.teleport(target2);
                        }
                        return true;
                    }

                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.TP) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length == 1) {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target == null) {
                                player.sendMessage(ErrorManager.getErrors(Errors.NoPlayerFound));
                                return true;
                            }
                            if (player == target) {
                                player.sendMessage(ErrorManager.getErrors(Errors.SenderInstaceOfPlayer));
                                return true;
                            }
                            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                    plugin.MessagesFile.getConfig().getString("TP message"), target, target, null, false));
                            player.teleport(target);
                        } else if (args.length == 2) {
                            if (args[0].equalsIgnoreCase("world")) {
                                if (Bukkit.getWorld(args[1]) != null) {
                                    World world = Bukkit.getWorld(args[1]);
                                    player.teleport(world.getSpawnLocation());
                                    player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("TP World message")
                                            .replaceAll("%world%", world.getName()), null, null, null, false));
                                } else {
                                    player.sendMessage(ErrorManager.getErrors(Errors.InvalidWorld));
                                }
                                return true;
                            }
                            Player target1 = Bukkit.getPlayer(args[0]);
                            Player target2 = Bukkit.getPlayer(args[1]);
                            if (target1 == null) {
                                player.sendMessage(ErrorManager.getErrors(Errors.NoPlayerFound));
                                return true;
                            }
                            if (target2 == null) {
                                player.sendMessage(ErrorManager.getErrors(Errors.NoPlayerFound));
                                return true;
                            }
                            if (target1 == target2) {
                                player.sendMessage(ErrorManager.getErrors(Errors.SenderInstaceOfPlayer));
                                return true;
                            }
                            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + plugin.MessagesFile.getConfig().getString("TP Other message")
                                    .replaceAll("%target2%", target2.getDisplayName()), target1, target1, null, false));
                            target1.teleport(target2);
                        } else if (args.length == 3) {
                            if (args[0].equalsIgnoreCase("world")) {
                                if (Bukkit.getWorld(args[1]) != null) {
                                    Player target = Bukkit.getPlayer(args[2]);
                                    if (target == null) {
                                        player.sendMessage(ErrorManager.getErrors(Errors.NoPlayerFound));
                                        return true;
                                    }
                                    if (player == target) {
                                        player.sendMessage(ErrorManager.getErrors(Errors.SenderInstaceOfPlayer));
                                        return true;
                                    }
                                    World world = Bukkit.getWorld(args[1]);
                                    target.teleport(world.getSpawnLocation());
                                    player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("TP World Other message")
                                                        .replaceAll("%world%", world.getName()), target, target, null, false));
                                } else {
                                    player.sendMessage(ErrorManager.getErrors(Errors.InvalidWorld));
                                }
                                return true;
                            }
                            if (isInt(args[0])) {
                                int x = Integer.parseInt(args[0]);
                                if (isInt(args[0])) {
                                    int y = Integer.parseInt(args[1]);
                                    if (isInt(args[0])) {
                                        int z = Integer.parseInt(args[2]);
                                        Location loc = new Location(player.getWorld(), x, y, z);
                                        player.teleport(loc);
                                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("TP Cords message")
                                                .replaceAll("%X%", ""+x)
                                                .replaceAll("%Y%", ""+y)
                                                .replaceAll("%Z%", ""+z), null, null, null, false));
                                    }
                                }
                            }
                        }
                    } else
                        player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
                } else {
                    sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));
                }
            }
        }
        return false;
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
