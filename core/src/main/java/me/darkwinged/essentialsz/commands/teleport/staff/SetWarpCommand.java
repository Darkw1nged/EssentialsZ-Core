package me.darkwinged.essentialsz.commands.teleport.staff;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import me.darkwinged.essentialsz.libaries.util.CustomConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("setwarp")) {
            if (plugin.getConfig().getBoolean("Teleportation.enabled", true)) {
                if (plugin.getConfig().getBoolean("Teleportation.Settings.Warps.enabled", true)) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.Console));
                        return true;
                    }
                    Player player = (Player) sender;
                    if (player.hasPermission(Permissions.WarpsOverwrite) || player.hasPermission(Permissions.SetWarps) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length < 1) {
                            player.sendMessage(ErrorManager.getErrors(Errors.NoWarpNameProvided));
                            return true;
                        }
                        String ProvidedWarp = args[0];

                        CustomConfig newWarp = new CustomConfig(plugin, ProvidedWarp, "Warps");
                        String world = player.getWorld().getName();
                        double x = player.getLocation().getX();
                        double y = player.getLocation().getY();
                        double z = player.getLocation().getZ();
                        float pitch = player.getLocation().getPitch();
                        float yaw = player.getLocation().getYaw();

                        newWarp.getConfig().set("world", world);
                        newWarp.getConfig().set("x", x);
                        newWarp.getConfig().set("y", y);
                        newWarp.getConfig().set("z", z);
                        newWarp.getConfig().set("pitch", pitch);
                        newWarp.getConfig().set("yaw", yaw);
                        newWarp.getConfig().set("name", ProvidedWarp);
                        newWarp.saveConfig();

                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("Create Warp").replaceAll("%warp%", ProvidedWarp),
                                null, null, null, false));
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
