package me.darkwinged.Essentials.Commands.Teleport;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class cmd_Homes implements CommandExecutor {

    private Main plugin;
    public cmd_Homes(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("homes")) {
            if (plugin.getConfig().getBoolean("Teleportation", true)) {
                if (plugin.getConfig().getBoolean("Homes", true)) {
                    if (!(sender instanceof Player)) {
                        if (args.length == 1) {
                            Player target = Bukkit.getPlayer(args[0]);
                            FileConfiguration homes = plugin.getHomes();
                            if (homes.get("Homes.Owner's Name " + target.getName()) == "" || homes.get("Homes.Owner's Name " + target.getName()) == null) {
                                Utils.Message(sender, Errors.getErrors(Errors.NoHomes));
                            } else {
                                sender.sendMessage(Utils.chat("&6&lHomes: &f&l" + homes.getConfigurationSection("Homes.Owner's Name " + target.getName()).getKeys(false))
                                        .replace("[", "").replace("]", ""));
                            }
                        } else {
                            Utils.Message(sender, Errors.getErrors(Errors.NoPlayerFound));
                        }
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.Homes) || player.hasPermission(Permissions.HomesOverwrite) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        FileConfiguration homes = plugin.getHomes();
                        if (args.length == 0) {
                            if (homes.get("Homes.Owner's Name " + player.getName()) == "" || homes.get("Homes.Owner's Name " + player.getName()) == null) {
                                Utils.Message(sender, Errors.getErrors(Errors.NoHomes));
                            } else {
                                player.sendMessage(Utils.chat("&6&lHomes: &f&l" + homes.getConfigurationSection("Homes.Owner's Name " + player.getName()).getKeys(false))
                                        .replace("[", "").replace("]", ""));
                            }
                        } else if (args.length == 1) {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (homes.get("Homes.Owner's Name " + target.getName()) == "" || homes.get("Homes.Owner's Name " + target.getName()) == null) {
                                Utils.Message(sender, Errors.getErrors(Errors.NoHomes));
                            } else {
                                player.sendMessage(Utils.chat("&6&lHomes: &f&l" + homes.getConfigurationSection("Homes.Owner's Name " + target.getName()).getKeys(false))
                                        .replace("[", "").replace("]", ""));
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "Error! Usage: /homes OR /homes <player>");
                        }
                    } else {
                        Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                    }
                }
            }
        }
        return true;
    }
}