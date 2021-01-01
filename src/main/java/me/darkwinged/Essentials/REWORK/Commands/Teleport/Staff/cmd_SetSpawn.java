package me.darkwinged.Essentials.REWORK.Commands.Teleport.Staff;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.MessagesFile;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Telepotation.SpawnFile;
import me.darkwinged.Essentials.REWORK.Utils.ErrorMessages;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import me.darkwinged.Essentials.REWORK.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_SetSpawn implements CommandExecutor {

    private MessagesFile messagesFile;
    private SpawnFile spawnFile;
    private Main plugin;
    public cmd_SetSpawn(MessagesFile messagesFile, SpawnFile spawnFile, Main plugin) {
        this.messagesFile = messagesFile;
        this.spawnFile = spawnFile;
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
                            spawnFile.getConfig().set("Spawn.world", world);
                            spawnFile.getConfig().set("Spawn.x", x);
                            spawnFile.getConfig().set("Spawn.y", y);
                            spawnFile.getConfig().set("Spawn.z", z);
                            spawnFile.getConfig().set("Spawn.pitch", pitch);
                            spawnFile.getConfig().set("Spawn.yaw", yaw);
                            spawnFile.saveConfig();
                            player.sendMessage(Utils.chat(messagesFile.getConfig().getString("Prefix") +
                                    Utils.chat(messagesFile.getConfig().getString("Set Spawn message"))));
                        } else {
                            player.sendMessage(ErrorMessages.NoPermission);
                        }
                    } else {
                        sender.sendMessage(ErrorMessages.Console);
                    }
                }
            }

        }
        return true;
    }

}
