package me.darkwinged.Essentials.Commands.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Vanish implements CommandExecutor {

    private Main plugin;
    public cmd_Vanish(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("vanish")) {
            if (plugin.getConfig().getBoolean("cmd_Vanish", true)) {
                if (!(sender instanceof Player)) {
                    Utils.Message(sender, Errors.getErrors(Errors.Console));
                    return true;
                }
                Player player = (Player) sender;
                if (player.hasPermission(Permissions.Vanish) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (Utils.invisible_list.contains(player.getUniqueId())) {
                        for (Player people : Bukkit.getOnlinePlayers()) {
                            people.showPlayer(player);
                        }
                        Utils.invisible_list.remove(player.getUniqueId());
                        player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                Utils.chat(plugin.MessagesFile.getConfig().getString("Vanish").replaceAll("%setting%", "disabled"))));

                    } else if (!Utils.invisible_list.contains(player.getUniqueId())) {
                        for (Player people : Bukkit.getOnlinePlayers()) {
                            if (people.hasPermission(Permissions.SeeVanish) || people.hasPermission(Permissions.GlobalOverwrite)) {
                                people.showPlayer(player);
                            } else {
                                people.hidePlayer(player);
                            }
                        }
                        Utils.invisible_list.add(player.getUniqueId());
                        player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                Utils.chat(plugin.MessagesFile.getConfig().getString("Vanish").replaceAll("%setting%", "enabled"))));
                    }
                } else {
                    Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                }
            }
        }
        return false;
    }

}
