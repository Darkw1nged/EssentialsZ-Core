package me.darkwinged.Essentials.Commands.Teleport.Staff;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_DelWarp implements CommandExecutor {

    private Main plugin;
    public cmd_DelWarp(Main plugin) {
        this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("delwarp")) {
            if (plugin.getConfig().getBoolean("Teleportation", true)) {
                if (plugin.getConfig().getBoolean("cmd_DelWarp", true)) {
                    if (!(sender instanceof Player)) {
                        if (args.length < 1) {
                            sender.sendMessage(ErrorMessages.NoWarpNameProvided);
                        }
                        String ProvidedWarp = args[0];
                        if (plugin.getWarps().contains("Warps." + ProvidedWarp)) {
                            String Message = Utils.chat(plugin.MessagesFile.getConfig().getString("Remove Warp").replaceAll("%warp%", ProvidedWarp));

                            plugin.getWarps().set("Warps." + ProvidedWarp, null);
                            plugin.saveWarps();
                            sender.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
                        } else if (!plugin.getWarps().contains("Warps." + ProvidedWarp)) {
                            sender.sendMessage(ErrorMessages.WarpDoesNotExist);
                        }
                        return true;
                    }
                    Player player = (Player) sender;
                    if (player.hasPermission(Permissions.WarpsOverwrite) || player.hasPermission(Permissions.DelWarps) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length < 1) {
                            player.sendMessage(ErrorMessages.NoWarpNameProvided);
                        }
                        String ProvidedWarp = args[0];
                        if (plugin.getWarps().contains("Warps." + ProvidedWarp)) {
                            String Message = Utils.chat(plugin.MessagesFile.getConfig().getString("Remove Warp").replaceAll("%warp%", ProvidedWarp));

                            plugin.getWarps().set("Warps." + ProvidedWarp, null);
                            plugin.saveWarps();
                            player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
                        } else if (!plugin.getWarps().contains("Warps." + ProvidedWarp)) {
                            player.sendMessage(ErrorMessages.WarpDoesNotExist);
                        }
                    }
                }
            }
        }
        return true;
    }

}
