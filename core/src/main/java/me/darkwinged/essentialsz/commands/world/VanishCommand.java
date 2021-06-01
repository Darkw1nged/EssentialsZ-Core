package me.darkwinged.essentialsz.commands.world;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.CustomConfig;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("vanish")) {
            if (plugin.getConfig().getBoolean("Commands.Vanish", true)) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ErrorManager.getErrors(Errors.Console));
                    return true;
                }
                Player player = (Player) sender;
                CustomConfig Data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
                if (player.hasPermission(Permissions.Vanish) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (Data.getConfig().getBoolean("isVanished", true)) {
                        for (Player people : Bukkit.getOnlinePlayers()) {
                            people.showPlayer(player);
                        }
                        Data.getConfig().set("isVanished", false);
                        Data.saveConfig();
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                        plugin.MessagesFile.getConfig().getString("Vanish").replaceAll("%setting%", "disabled"),
                                player, player, null, false));
                    } else {
                        for (Player people : Bukkit.getOnlinePlayers()) {
                            if (people.hasPermission(Permissions.SeeVanish) || people.hasPermission(Permissions.GlobalOverwrite)) {
                                people.showPlayer(player);
                            } else {
                                people.hidePlayer(player);
                            }
                        }
                        Data.getConfig().set("isVanished", true);
                        Data.saveConfig();
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                        plugin.MessagesFile.getConfig().getString("Vanish").replaceAll("%setting%", "enabled"),
                                player, player, null, false));
                    }
                } else
                    player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
            }
        }
        return false;
    }

}
