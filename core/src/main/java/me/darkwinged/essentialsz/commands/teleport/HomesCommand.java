package me.darkwinged.essentialsz.commands.teleport;

import me.darkwinged.essentialsz.libaries.lang.CustomConfig;
import me.darkwinged.essentialsz.libaries.lang.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import me.darkwinged.essentialsz.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomesCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("homes")) {
            if (plugin.getConfig().getBoolean("Teleportation.enabled", true)) {
                if (plugin.getConfig().getBoolean("Teleportation.Settings.Homes.enabled", true)) {
                    if (!(sender instanceof Player)) {
                        if (args.length != 1) {
                            sender.sendMessage(Errors.getErrors(Errors.SpecifyPlayer));
                            return true;
                        }
                        Player target = Bukkit.getPlayer(args[0]);

                        CustomConfig Data = new CustomConfig(plugin, String.valueOf(target.getUniqueId()), "Data");
                        if (!Data.getCustomConfigFile().exists()) return true;

                        if (Data.getConfig().getConfigurationSection("Homes").getKeys(false).isEmpty()) {
                            sender.sendMessage(Errors.getErrors(Errors.NoHomes));
                        } else {
                            sender.sendMessage(plugin.essentialsZAPI.utils.chat("&6&lHomes: &f&l" + Data.getConfig().getConfigurationSection("Homes").getKeys(false), null, null, null, false)
                                    .replace("[", "").replace("]", ""));
                        }
                        return true;
                    }
                    Player player = (Player) sender;
                    if (player.hasPermission(Permissions.Homes) || player.hasPermission(Permissions.HomesOverwrite) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        CustomConfig Data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
                        if (!Data.getCustomConfigFile().exists()) return true;

                        if (Data.getConfig().getConfigurationSection("Homes").getKeys(false).isEmpty()) {
                            sender.sendMessage(Errors.getErrors(Errors.NoHomes));
                        } else {
                            sender.sendMessage(plugin.essentialsZAPI.utils.chat("&6&lHomes: &f&l" + Data.getConfig().getConfigurationSection("Homes").getKeys(false), null, null, null, false)
                                    .replace("[", "").replace("]", ""));
                        }
                    } else if (player.hasPermission(Permissions.HomesOther) || player.hasPermission(Permissions.HomesOverwrite) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length == 1) {
                            Player target = Bukkit.getPlayer(args[0]);

                            CustomConfig Data = new CustomConfig(plugin, String.valueOf(target.getUniqueId()), "Data");
                            if (!Data.getCustomConfigFile().exists()) return true;

                            if (Data.getConfig().getConfigurationSection("Homes").getKeys(false).isEmpty()) {
                                sender.sendMessage(Errors.getErrors(Errors.NoHomes));
                            } else {
                                sender.sendMessage(plugin.essentialsZAPI.utils.chat("&6&lHomes: &f&l" + Data.getConfig().getConfigurationSection("Homes").getKeys(false), null, null, null, false)
                                        .replace("[", "").replace("]", ""));
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "Error! Usage: /homes OR /homes <player>");
                        }
                    } else {
                        player.sendMessage(Errors.getErrors(Errors.NoPermission));
                    }
                } else {
                    sender.sendMessage(Errors.getErrors(Errors.DisabledCommand));
                }
            }
        }
        return true;
    }
}
