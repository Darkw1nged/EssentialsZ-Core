package me.darkwinged.essentialsz.commands.teleport.staff;

import me.darkwinged.essentialsz.libaries.lang.CustomConfig;
import me.darkwinged.essentialsz.libaries.lang.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import me.darkwinged.essentialsz.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelWarpCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("delwarp")) {
            if (plugin.getConfig().getBoolean("Teleportation", true)) {
                if (plugin.getConfig().getBoolean("Teleportation.Settings.Warps.enabled", true)) {
                    if (!(sender instanceof Player)) {
                        if (args.length < 1) {
                            sender.sendMessage(Errors.getErrors(Errors.NoWarpNameProvided));
                        }
                        String WarpName = args[0];
                        CustomConfig WarpFile = new CustomConfig(plugin, WarpName, "Warps");
                        if (!WarpFile.getCustomConfigFile().exists()) {
                            sender.sendMessage(Errors.getErrors(Errors.WarpDoesNotExist));
                            return true;
                        }
                        WarpFile.getCustomConfigFile().delete();
                        sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("Remove Warp").replaceAll("%warp%", WarpName), null,
                                null, null, false));
                        return true;
                    }
                    Player player = (Player) sender;
                    if (player.hasPermission(Permissions.WarpsOverwrite) || player.hasPermission(Permissions.DelWarps) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length < 1) {
                            sender.sendMessage(Errors.getErrors(Errors.NoWarpNameProvided));
                        }
                        String WarpName = args[0];
                        CustomConfig WarpFile = new CustomConfig(plugin, WarpName, "Warps");
                        if (!WarpFile.getCustomConfigFile().exists()) {
                            sender.sendMessage(Errors.getErrors(Errors.WarpDoesNotExist));
                            return true;
                        }
                        WarpFile.getCustomConfigFile().delete();
                        sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                        plugin.MessagesFile.getConfig().getString("Remove Warp").replaceAll("%warp%", WarpName), null,
                                null, null, false));
                    }
                } else {
                    sender.sendMessage(Errors.getErrors(Errors.DisabledCommand));
                }
            }
        }
        return true;
    }

}
