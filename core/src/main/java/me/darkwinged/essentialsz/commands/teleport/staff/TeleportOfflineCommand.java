package me.darkwinged.essentialsz.commands.teleport.staff;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import me.darkwinged.essentialsz.libaries.lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class TeleportOfflineCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tpoffline")) {
            if (plugin.getConfig().getBoolean("Teleportation.enabled", true)) {
                if (plugin.getConfig().getBoolean("Teleportation.Settings.Commands.teleport", true)) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.Console));
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.TPOffline) || player.hasPermission(Permissions.GlobalOverwrite)) {

                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                        YamlConfiguration config = Utils.getDataFile((Player) target).getConfig();
                        if (target == sender) {
                            player.sendMessage(ErrorManager.getErrors(Errors.SenderInstaceOfPlayer));
                            return true;
                        }
                        if (!config.contains("lastKnownName") || config.contains("timestamps.lastLogout.location.world")) {
                            player.sendMessage(ErrorManager.getErrors(Errors.DataFileError));
                            return true;
                        }

                        World world = Bukkit.getWorld(config.getString("timestamps.lastLogout.location.world"));
                        double x = config.getDouble("timestamps.lastLogout.location.x");
                        double y = config.getDouble("timestamps.lastLogout.location.y");
                        double z = config.getDouble("timestamps.lastLogout.location.z");
                        float yaw = (float) config.getDouble("timestamps.lastLogout.location.yaw");
                        float pitch = (float) config.getDouble("timestamps.lastLogout.location.pitch");
                        Location loc = new Location(world, x, y, z, yaw, pitch);

                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("TP Offline message"), player, target, null, false));
                        player.teleport(loc);

                    } else
                        player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
                } else
                    sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));
            }
        }
        return false;
    }

}
