package me.darkwinged.Essentials.Commands.Teleport;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_SetHome  implements CommandExecutor {

    private Main plugin;
    public cmd_SetHome(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("sethome")) {
            if (plugin.getConfig().getBoolean("Teleportation", true)) {
                if (plugin.getConfig().getBoolean("Homes", true)) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(ErrorMessages.Console);
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.SetHomes) || player.hasPermission(Permissions.HomesOverwrite) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length < 1) {
                            player.sendMessage(ErrorMessages.NoHomeNameProvided);
                            return true;
                        }
                        if (args.length == 1 && plugin.getHomes().contains("Homes.Owner's Name " + player.getName() + "." + args[0])) {
                            player.sendMessage(ErrorMessages.HomeAlreadyExist);
                            return true;
                        }
                        String Message = Utils.chat(plugin.MessagesFile.getConfig().getString("Create Home").replaceAll("%home%", args[0]));

                        String world = player.getWorld().getName();
                        double x = player.getLocation().getX();
                        double y = player.getLocation().getY();
                        double z = player.getLocation().getZ();
                        float pitch = player.getLocation().getPitch();
                        float yaw = player.getLocation().getYaw();
                        plugin.getHomes().set("Homes.Owner's Name " + player.getName() + "." + args[0] + ".world", world);
                        plugin.getHomes().set("Homes.Owner's Name " + player.getName() + "." + args[0] + ".x", x);
                        plugin.getHomes().set("Homes.Owner's Name " + player.getName() + "." + args[0] + ".y", y);
                        plugin.getHomes().set("Homes.Owner's Name " + player.getName() + "." + args[0] + ".z", z);
                        plugin.getHomes().set("Homes.Owner's Name " + player.getName() + "." + args[0] + ".pitch", pitch);
                        plugin.getHomes().set("Homes.Owner's Name " + player.getName() + "." + args[0] + ".yaw", yaw);
                        plugin.saveHomes();
                        player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
                    } else {
                        player.sendMessage(ErrorMessages.NoPermission);
                    }
                }
            }
        }
        return true;
    }
}