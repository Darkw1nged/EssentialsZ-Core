package me.darkwinged.Essentials.Commands.Teleport.Staff;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_SetSpawn implements CommandExecutor {

    private Main plugin;
    public cmd_SetSpawn(Main plugin) {
        this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("setspawn")) {
            if (plugin.getConfig().getBoolean("Teleportation", true)) {
                if (plugin.getConfig().getBoolean("cmd_SetSpawn", true)) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        if (player.hasPermission(Permissions.SetSpawn) || player.hasPermission(Permissions.GlobalOverwrite)) {
                            String world = player.getWorld().getName();
                            double x = player.getLocation().getX();
                            double y = player.getLocation().getY();
                            double z = player.getLocation().getZ();
                            float pitch = player.getLocation().getPitch();
                            float yaw = player.getLocation().getYaw();
                            plugin.SpawnFile.getConfig().set("Spawn.world", world);
                            plugin.SpawnFile.getConfig().set("Spawn.x", x);
                            plugin.SpawnFile.getConfig().set("Spawn.y", y);
                            plugin.SpawnFile.getConfig().set("Spawn.z", z);
                            plugin.SpawnFile.getConfig().set("Spawn.pitch", pitch);
                            plugin.SpawnFile.getConfig().set("Spawn.yaw", yaw);
                            plugin.SpawnFile.saveConfig();
                            player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                    Utils.chat(plugin.MessagesFile.getConfig().getString("Set Spawn message"))));
                        } else {
                            Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                        }
                    } else {
                        Utils.Message(sender, Errors.getErrors(Errors.Console));
                    }
                }
            }

        }
        return true;
    }

}
