package me.darkwinged.EssentialsZ.Commands.World;

import me.darkwinged.EssentialsZ.Libaries.Lang.CustomConfig;
import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Vanish implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("vanish")) {
            if (plugin.getConfig().getBoolean("Commands.Vanish", true)) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Errors.getErrors(Errors.Console));
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
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                        plugin.MessagesFile.getConfig().getString("Vanish").replaceAll("%setting%", "enabled"),
                                player, player, null, false));
                    }
                } else
                    player.sendMessage(Errors.getErrors(Errors.NoPermission));
            }
        }
        return false;
    }

}
