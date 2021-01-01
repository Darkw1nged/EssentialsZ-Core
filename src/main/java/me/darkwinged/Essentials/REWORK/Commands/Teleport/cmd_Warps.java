package me.darkwinged.Essentials.REWORK.Commands.Teleport;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.ErrorMessages;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import me.darkwinged.Essentials.REWORK.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class cmd_Warps implements CommandExecutor {

    private Main plugin;
    public cmd_Warps(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("warps")) {
            if (plugin.getConfig().getBoolean("Teleportation", true)) {
                if (plugin.getConfig().getBoolean("cmd_Warps", true)) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(ErrorMessages.Console);
                        return true;
                    }
                    Player player = (Player) sender;
                    if (player.hasPermission(Permissions.WarpsOverwrite) || player.hasPermission(Permissions.Warps) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        FileConfiguration warp = plugin.getWarps();
                        if (warp.get("Warps") == "" || warp.get("Warps") == null) {
                            player.sendMessage(ErrorMessages.NoWarpsFound);
                        }
                        player.sendMessage(Utils.chat("&6Warps: &f" + warp.getConfigurationSection("Warps").getKeys(false))
                                .replace("[", "")
                                .replace("]", ""));
                    } else {
                        player.sendMessage(ErrorMessages.NoPermission);
                    }
                }
            }

        }
        return true;
    }

}
