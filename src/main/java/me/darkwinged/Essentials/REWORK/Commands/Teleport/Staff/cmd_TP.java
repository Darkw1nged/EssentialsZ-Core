package me.darkwinged.Essentials.REWORK.Commands.Teleport.Staff;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.MessagesFile;
import me.darkwinged.Essentials.REWORK.Utils.ErrorMessages;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import me.darkwinged.Essentials.REWORK.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_TP implements CommandExecutor {

    private MessagesFile messagesFile;
    private Main plugin;
    public cmd_TP(MessagesFile messagesFile, Main plugin) {
        this.messagesFile = messagesFile;
        this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tp")) {
            if (plugin.getConfig().getBoolean("Teleportation", true)) {
                if (plugin.getConfig().getBoolean("cmd_TP", true)) {
                    if (!(sender instanceof Player)) {
                        if (args.length != 2) {
                            sender.sendMessage(ChatColor.RED + "Error! Usage: /tp <player> <player>");
                            return true;
                        } else {
                            Player target1 = Bukkit.getPlayer(args[0]);
                            Player target2 = Bukkit.getPlayer(args[1]);
                            if (target1 == null) {
                                sender.sendMessage(ErrorMessages.NoPlayerFound);
                                return true;
                            }
                            if (target2 == null) {
                                sender.sendMessage(ErrorMessages.NoPlayerFound);
                                return true;
                            }
                            if (target1 == target2) {
                                sender.sendMessage(ErrorMessages.SenderInstaceOfPlayer);
                                return true;
                            }
                            String Message = Utils.chat(messagesFile.getConfig().getString("TP Other message")
                                    .replaceAll("%target2%", target2.getDisplayName())
                                    .replaceAll("%target%", target1.getDisplayName()));
                            sender.sendMessage(Utils.chat(messagesFile.getConfig().getString("Prefix")) + Message);
                            target1.teleport(target2);
                        }
                        return true;
                    }

                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.TP) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length == 1) {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target == null) {
                                player.sendMessage(ErrorMessages.NoPlayerFound);
                                return true;
                            }
                            if (player == target) {
                                player.sendMessage(ErrorMessages.SenderInstaceOfPlayer);
                                return true;
                            }
                            String Message = Utils.chat(messagesFile.getConfig().getString("TP message"))
                                    .replaceAll("%player%", target.getDisplayName());
                            player.sendMessage(Utils.chat(messagesFile.getConfig().getString("Prefix")) + Message);
                            player.teleport(target);
                        } else if (args.length == 2) {
                            if (args[0].equalsIgnoreCase("world")) {
                                if (Bukkit.getWorld(args[1]) != null) {
                                    World world = Bukkit.getWorld(args[1]);
                                    player.teleport(world.getSpawnLocation());
                                    player.sendMessage(Utils.chat(messagesFile.getConfig().getString("TP World message").replaceAll("%world%", world.getName())));
                                } else {
                                    player.sendMessage(ErrorMessages.InvalidWorld);
                                }
                                return true;
                            }
                            Player target1 = Bukkit.getPlayer(args[0]);
                            Player target2 = Bukkit.getPlayer(args[1]);
                            if (target1 == null) {
                                player.sendMessage(ErrorMessages.NoPlayerFound);
                                return true;
                            }
                            if (target2 == null) {
                                player.sendMessage(ErrorMessages.NoPlayerFound);
                                return true;
                            }
                            if (target1 == target2) {
                                player.sendMessage(ErrorMessages.SenderInstaceOfPlayer);
                                return true;
                            }
                            String Message = Utils.chat(messagesFile.getConfig().getString("TP Other message")
                                            .replaceAll("%target2%", target2.getDisplayName())
                                            .replaceAll("%target%", target1.getDisplayName()));
                            player.sendMessage(Utils.chat(messagesFile.getConfig().getString("Prefix")) + Message);
                            target1.teleport(target2);
                        } else if (args.length == 3) {
                            if (args[0].equalsIgnoreCase("world")) {
                                if (Bukkit.getWorld(args[1]) != null) {
                                    Player target = Bukkit.getPlayer(args[2]);
                                    if (target == null) {
                                        player.sendMessage(ErrorMessages.NoPlayerFound);
                                        return true;
                                    }
                                    if (player == target) {
                                        player.sendMessage(ErrorMessages.SenderInstaceOfPlayer);
                                        return true;
                                    }
                                    World world = Bukkit.getWorld(args[1]);
                                    target.teleport(world.getSpawnLocation());
                                    player.sendMessage(Utils.chat(messagesFile.getConfig().getString("TP World Other message")
                                                        .replaceAll("%world%", world.getName())
                                                        .replaceAll("%player%", target.getName())));
                                } else {
                                    player.sendMessage(ErrorMessages.InvalidWorld);
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
                                        player.sendMessage(Utils.chat(messagesFile.getConfig().getString("TP Cords message")
                                                .replaceAll("%X%", ""+x)
                                                .replaceAll("%Y%", ""+y)
                                                .replaceAll("%Z%", ""+z)));
                                    }
                                }
                            }
                        }
                    } else {
                        player.sendMessage(ErrorMessages.NoPermission);
                        return true;
                    }

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
