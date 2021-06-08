package me.darkwinged.essentialsz.commands;

import me.darkwinged.essentialsz.EssentialsZSpawn;
import me.darkwinged.essentialsz.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class SpawnCommand implements CommandExecutor {

    private final EssentialsZSpawn plugin = EssentialsZSpawn.getInstance;

    private final HashMap<UUID, Integer> TeleportDelay = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("spawn")) {
            if (!(sender instanceof Player)) {
                if (args.length != 1) {
                    sender.sendMessage(Utils.chat("&cError! That player could not be found."));
                    return true;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if (!plugin.SpawnLocation.getConfig().contains("world")) {
                    sender.sendMessage(Utils.chat("&cError! Spawn has not been set."));
                    return true;
                }
                if (target == null) {
                    sender.sendMessage(Utils.chat("&cError! That player could not be found."));
                    return true;
                }
                plugin.spawnAPI.TeleportToSpawn(target);
                return true;
            }
            Player player = (Player) sender;
            if (!plugin.SpawnLocation.getConfig().contains("world")) {
                sender.sendMessage(Utils.chat("&cError! Spawn has not been set."));
                return true;
            }
            // Checking if the player is already teleporting to spawn.
            if (TeleportDelay.containsKey(player.getUniqueId())) {
                return true;
            }
            // Sending another player to spawn.
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (!plugin.SpawnLocation.getConfig().contains("world")) {
                    sender.sendMessage(Utils.chat("&cError! Spawn has not been set."));
                    return true;
                }
                if (target == null) {
                    sender.sendMessage(Utils.chat("&cError! That player could not be found."));
                    return true;
                }
                plugin.spawnAPI.TeleportToSpawn(target);
                return true;
            }
            // Sending the player to spawn.
            if (player.hasPermission("EssentialsZ.Spawn")) {
                // Player does not have delay.
                if (player.hasPermission("Essentials.TeleportDelay.Bypass")) {
                    plugin.spawnAPI.TeleportToSpawn(player);
                    player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                            plugin.MessagesFile.getConfig().getString("Spawn Teleport Message")));
                    return true;
                }
                // Player does have delay.
                player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Teleporting Message"))
                        .replaceAll("%delay%", "" + plugin.ConfigFile.getConfig().getInt("Teleportation Delay")));
                TeleportDelay.put(player.getUniqueId(), plugin.ConfigFile.getConfig().getInt("Teleportation Delay"));

                // Counting down from delay that is set in config.
                new BukkitRunnable() {
                    public void run() {
                        if (!TeleportDelay.containsKey(player.getUniqueId())) return;
                        if (TeleportDelay.get(player.getUniqueId()) <= 0) {
                            // Sending the player to spawn.
                            plugin.spawnAPI.TeleportToSpawn(player);

                            // Removing the player from the delay and resetting it
                            TeleportDelay.remove(player.getUniqueId());
                            cancel();
                            return;
                        }
                        // Removing 1 from the count
                        TeleportDelay.put(player.getUniqueId(), TeleportDelay.get(player.getUniqueId()) - 1);
                    }
                }.runTaskTimer(plugin, 0L, 20L);
            } else {
                sender.sendMessage(Utils.chat("&cError! You do not have permission to use this command!"));
            }
        }
        return false;
    }


}
