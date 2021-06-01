package me.darkwinged.essentialsz.commands.teleport;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.CustomConfig;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class DelHomeCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("delhome")) {
            if (plugin.getConfig().getBoolean("Teleportation.enabled", true)) {
                if (plugin.getConfig().getBoolean("Teleportation.Settings.Homes.enabled", true)) {
                    if (!(sender instanceof Player)) {
                        if (args.length < 2) {
                            sender.sendMessage(ChatColor.RED + "Error! Please use /delhome <player> <home-name>");
                            return true;
                        }
                        Player target = Bukkit.getPlayer(args[0]);
                        String HomeName = args[1];
                        CustomConfig Data = new CustomConfig(plugin, String.valueOf(target.getUniqueId()), "Data");
                        if (!Data.getCustomConfigFile().exists()) return true;
                        if (!Data.getConfig().contains("Homes." + HomeName)) {
                            sender.sendMessage(plugin.essentialsZAPI.utils.chat(ErrorManager.getErrors(Errors.HomeDoesNotExist),
                                    null, null, null, false));
                            return true;
                        }
                        Data.getConfig().set("Homes." + HomeName, null);
                        Data.saveConfig();
                        sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("Home")
                                        .replaceAll("%home%", HomeName).replaceAll("%setting%", "removed"),
                                target, target, null, false));
                        return true;
                    }
                    Player player = (Player) sender;
                    if (player.hasPermission(Permissions.DelHomes) || player.hasPermission(Permissions.HomesOverwrite) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length == 1) {
                            String HomeName = args[0];
                            CustomConfig Data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
                            if (!Data.getCustomConfigFile().exists()) return true;
                            if (!Data.getConfig().contains("Homes." + HomeName)) {
                                sender.sendMessage(plugin.essentialsZAPI.utils.chat(ErrorManager.getErrors(Errors.HomeDoesNotExist),
                                        null, null, null, false));
                                return true;
                            }
                            Data.getConfig().set("Homes." + HomeName, null);
                            Data.saveConfig();
                            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                            plugin.MessagesFile.getConfig().getString("Home")
                                                    .replaceAll("%home%", HomeName).replaceAll("%setting%", "removed"),
                                    player, player, null, false));

                        } else if (args.length == 2) {
                            if (player.hasPermission(Permissions.DelHomesOther) || player.hasPermission(Permissions.HomesOverwrite) || player.hasPermission(Permissions.GlobalOverwrite)) {
                                Player target = Bukkit.getPlayer(args[0]);
                                String HomeName = args[1];
                                CustomConfig Data = new CustomConfig(plugin, String.valueOf(target.getUniqueId()), "Data");
                                if (!Data.getCustomConfigFile().exists()) return true;
                                if (!Data.getConfig().contains("Homes." + HomeName)) {
                                    sender.sendMessage(plugin.essentialsZAPI.utils.chat(ErrorManager.getErrors(Errors.HomeDoesNotExist),
                                            null, null, null, false));
                                    return true;
                                }
                                Data.getConfig().set("Homes." + HomeName, null);
                                Data.saveConfig();
                                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                                plugin.MessagesFile.getConfig().getString("Home")
                                                        .replaceAll("%home%", HomeName).replaceAll("%setting%", "removed"),
                                        target, target, null, false));
                            }
                        }
                    } else
                        player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
                } else {
                    sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));
                }
            }
        }
        return true;
    }
}
