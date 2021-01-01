package me.darkwinged.Essentials.REWORK.Commands.Teleport.Staff;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.MessagesFile;
import me.darkwinged.Essentials.REWORK.Utils.ErrorMessages;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import me.darkwinged.Essentials.REWORK.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_SetWarp implements CommandExecutor {

    private MessagesFile messagesFile;
    private Main plugin;
    public cmd_SetWarp(MessagesFile messagesFile, Main plugin) {
        this.messagesFile = messagesFile;
        this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("setwarp")) {
            if (plugin.getConfig().getBoolean("Teleportation", true)) {
                if (plugin.getConfig().getBoolean("cmd_SetWarp", true)) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(ErrorMessages.Console);
                        return true;
                    }
                    Player player = (Player) sender;
                    if (player.hasPermission(Permissions.WarpsOverwrite) || player.hasPermission(Permissions.SetWarps) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length < 1) {
                            player.sendMessage(ErrorMessages.NoWarpNameProvided);
                        }
                        String ProvidedWarp = args[0];
                        if (args.length == 1 && plugin.getWarps().contains("Warps." + ProvidedWarp)) {
                            player.sendMessage(ErrorMessages.WarpAlreadyExist);
                        } else {
                            String Message = Utils.chat(messagesFile.getConfig().getString("Create Warp").replaceAll("%warp%", ProvidedWarp));

                            String world = player.getWorld().getName();
                            double x = player.getLocation().getX();
                            double y = player.getLocation().getY();
                            double z = player.getLocation().getZ();
                            float pitch = player.getLocation().getPitch();
                            float yaw = player.getLocation().getYaw();
                            plugin.getWarps().set("Warps." + args[0] + ".world", world);
                            plugin.getWarps().set("Warps." + args[0] + ".x", x);
                            plugin.getWarps().set("Warps." + args[0] + ".y", y);
                            plugin.getWarps().set("Warps." + args[0] + ".z", z);
                            plugin.getWarps().set("Warps." + args[0] + ".pitch", pitch);
                            plugin.getWarps().set("Warps." + args[0] + ".yaw", yaw);
                            plugin.saveWarps();
                            player.sendMessage(Utils.chat(messagesFile.getConfig().getString("Prefix") + Message));
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
