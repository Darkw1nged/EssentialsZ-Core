package me.darkwinged.Essentials.Commands.Teleport;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class cmd_DelHome implements CommandExecutor {

    private Main plugin;
    public cmd_DelHome(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("delhome")) {
            if (plugin.getConfig().getBoolean("Teleportation", true)) {
                if (plugin.getConfig().getBoolean("Homes", true)) {
                    if (!(sender instanceof Player)) {
                        if (args.length < 2) {
                            sender.sendMessage(ChatColor.RED + "Error! Please use /delhome <player> <home-name>");
                            return true;
                        }
                        Player target = Bukkit.getPlayer(args[0]);
                        if (!plugin.getHomes().contains("Homes.Owner's Name " + target.getName() + "." + args[1])) {
                            sender.sendMessage(ErrorMessages.HomeDoesNotExist);
                        } else {
                            plugin.getHomes().set("Homes.Owner's Name " + target.getName() + "." + args[1], null);
                            plugin.saveHomes();
                            sender.sendMessage(plugin.MessagesFile.getConfig().getString("Remove Home").replaceAll("%home%", args[1]));
                        }
                        return true;
                    }
                    Player player = (Player) sender;
                    if (player.hasPermission(Permissions.DelHomes) || player.hasPermission(Permissions.HomesOverwrite) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length == 1) {
                            if (plugin.getHomes().contains("Homes.Owner's Name " + player.getName() + "." + args[0])) {
                                String Message = Utils.chat(plugin.MessagesFile.getConfig().getString("Remove Home").replaceAll("%home%", args[0]));

                                plugin.getHomes().set("Homes.Owner's Name " + player.getName() + "." + args[0], null);
                                plugin.saveHomes();
                                player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
                            } else if (!plugin.getHomes().contains("Homes.Owner's Name " + player.getName() + "." + args[0])) {
                                player.sendMessage(ErrorMessages.HomeDoesNotExist);
                            }
                        } else if (args.length == 2) {
                            if (player.hasPermission(Permissions.DelHomesOther) || player.hasPermission(Permissions.HomesOverwrite) || player.hasPermission(Permissions.GlobalOverwrite)) {
                                Player target = Bukkit.getPlayer(args[0]);
                                if (!plugin.getHomes().contains("Homes.Owner's Name " + target.getName() + "." + args[1])) {
                                    sender.sendMessage(ErrorMessages.HomeDoesNotExist);
                                } else {
                                    plugin.getHomes().set("Homes.Owner's Name " + target.getName() + "." + args[1], null);
                                    plugin.saveHomes();
                                    sender.sendMessage(plugin.MessagesFile.getConfig().getString("Remove Home").replaceAll("%home%", args[1]));
                                }
                            }
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