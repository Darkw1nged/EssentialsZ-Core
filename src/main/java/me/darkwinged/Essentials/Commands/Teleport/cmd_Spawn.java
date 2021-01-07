package me.darkwinged.Essentials.Commands.Teleport;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class cmd_Spawn implements CommandExecutor {

    private Main plugin;
    public cmd_Spawn(Main plugin) {
        this.plugin = plugin; }

    private HashMap<UUID, Integer> TeleportDelay = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("spawn")) {
            if (plugin.getConfig().getBoolean("Teleportation", true)) {
                if (plugin.getConfig().getBoolean("cmd_Spawn", true)) {
                    if (!(sender instanceof Player)) {
                        if (args.length != 1) {
                            Utils.Message(sender, Errors.getErrors(Errors.NoPlayerFound));
                            return true;
                        }
                        FileConfiguration spawn = plugin.SpawnFile.getConfig();
                        Player target = Bukkit.getPlayer(args[0]);
                        if (spawn.getString("Spawn.world") == null) {
                            Utils.Message(sender, Errors.getErrors(Errors.noSpawn));
                            return true;
                        }
                        if (target == null) {
                            Utils.Message(sender, Errors.getErrors(Errors.NoPlayerFound));
                            return true;
                        }
                        World world = Bukkit.getWorld(spawn.getString("Spawn.world"));
                        double x = spawn.getDouble("Spawn.x");
                        double y = spawn.getDouble("Spawn.y");
                        double z = spawn.getDouble("Spawn.z");
                        float yaw = (float) spawn.getDouble("Spawn.yaw");
                        float pitch = (float) spawn.getDouble("Spawn.pitch");
                        Location loc = new Location(world, x, y, z, yaw, pitch);
                        target.teleport(loc);
                        return true;
                    }
                    Player player = (Player)sender;
                    FileConfiguration spawn = plugin.SpawnFile.getConfig();
                    if (spawn.getString("Spawn.world") == null) {
                        Utils.Message(sender, Errors.getErrors(Errors.noSpawn));
                        return true;
                    }
                    // Checking if the player is already teleporting to spawn.
                    if (TeleportDelay.containsKey(player.getUniqueId())) {
                        return true;
                    }
                    // Sending another player to spawn.
                    if (args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (spawn.getString("Spawn.world") == null) {
                            Utils.Message(sender, Errors.getErrors(Errors.noSpawn));
                            return true;
                        }
                        if (target == null) {
                            Utils.Message(sender, Errors.getErrors(Errors.NoPlayerFound));
                            return true;
                        }
                        if (player == target) {
                            Utils.Message(sender, Errors.getErrors(Errors.SenderInstaceOfPlayer));
                            return true;
                        }
                        World world = Bukkit.getWorld(spawn.getString("Spawn.world"));
                        double x = spawn.getDouble("Spawn.x");
                        double y = spawn.getDouble("Spawn.y");
                        double z = spawn.getDouble("Spawn.z");
                        float yaw = (float) spawn.getDouble("Spawn.yaw");
                        float pitch = (float) spawn.getDouble("Spawn.pitch");
                        Location loc = new Location(world, x, y, z, yaw, pitch);
                        target.teleport(loc);
                        return true;
                    }
                    // Sending the player to spawn.
                    if (player.hasPermission(Permissions.Spawn) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        // Player does not have delay.
                        if (player.hasPermission(Permissions.TeleportBypass) || player.hasPermission(Permissions.GlobalOverwrite)) {
                            World world = Bukkit.getWorld(spawn.getString("Spawn.world"));
                            double x = spawn.getDouble("Spawn.x");
                            double y = spawn.getDouble("Spawn.y");
                            double z = spawn.getDouble("Spawn.z");
                            float yaw = (float) spawn.getDouble("Spawn.yaw");
                            float pitch = (float) spawn.getDouble("Spawn.pitch");
                            Location loc = new Location(world, x, y, z, yaw, pitch);
                            player.teleport(loc);
                            player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                    Utils.chat(plugin.MessagesFile.getConfig().getString("Spawn Teleport message"))));
                            return true;
                        }
                        // Player does have delay.
                        player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Teleporting message")
                                .replaceAll("%delay%", ""+plugin.Delay)));
                        TeleportDelay.put(player.getUniqueId(), plugin.Delay);

                        // Counting down from delay that is set in config.
                        new BukkitRunnable() {
                            public void run() {
                                if (!TeleportDelay.containsKey(player.getUniqueId())) return;
                                if (TeleportDelay.get(player.getUniqueId()) <= 0) {
                                    // Sending the player to spawn.
                                    FileConfiguration spawn = plugin.SpawnFile.getConfig();
                                    World world = Bukkit.getWorld(spawn.getString("Spawn.world"));
                                    double x = spawn.getDouble("Spawn.x");
                                    double y = spawn.getDouble("Spawn.y");
                                    double z = spawn.getDouble("Spawn.z");
                                    float yaw = (float) spawn.getDouble("Spawn.yaw");
                                    float pitch = (float) spawn.getDouble("Spawn.pitch");
                                    Location loc = new Location(world, x, y, z, yaw, pitch);
                                    player.teleport(loc);
                                    player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                            Utils.chat(plugin.MessagesFile.getConfig().getString("Spawn Teleport message"))));

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
                        Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                    }
                }
            }
        }
        return false;
    }

}
