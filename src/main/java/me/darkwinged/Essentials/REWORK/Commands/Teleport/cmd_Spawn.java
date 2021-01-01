package me.darkwinged.Essentials.REWORK.Commands.Teleport;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.MessagesFile;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Telepotation.SpawnFile;
import me.darkwinged.Essentials.REWORK.Utils.ErrorMessages;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import me.darkwinged.Essentials.REWORK.Utils.Utils;
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

    private MessagesFile messagesFile;
    private SpawnFile spawnFile;
    private Main plugin;
    public cmd_Spawn(MessagesFile messagesFile, SpawnFile spawnFile, Main plugin) {
        this.messagesFile = messagesFile;
        this.spawnFile = spawnFile;
        this.plugin = plugin; }

    private HashMap<UUID, Integer> TeleportDelay = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("spawn")) {
            if (plugin.getConfig().getBoolean("Teleportation", true)) {
                if (plugin.getConfig().getBoolean("cmd_Spawn", true)) {
                    if (!(sender instanceof Player)) {
                        if (args.length != 1) {
                            sender.sendMessage(ErrorMessages.NoPlayer);
                            return true;
                        }
                        FileConfiguration spawn = spawnFile.getConfig();
                        Player target = Bukkit.getPlayer(args[0]);
                        if (spawn.getString("Spawn.world") == null) {
                            sender.sendMessage(ErrorMessages.noSpawn);
                            return true;
                        }
                        if (target == null) {
                            sender.sendMessage(ErrorMessages.NoPlayerFound);
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
                    FileConfiguration spawn = spawnFile.getConfig();
                    if (spawn.getString("Spawn.world") == null) {
                        sender.sendMessage(ErrorMessages.noSpawn);
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
                            sender.sendMessage(ErrorMessages.noSpawn);
                            return true;
                        }
                        if (target == null) {
                            player.sendMessage(ErrorMessages.NoPlayerFound);
                            return true;
                        }
                        if (player == target) {
                            player.sendMessage(ErrorMessages.SenderInstaceOfPlayer);
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
                            player.sendMessage(Utils.chat(messagesFile.getConfig().getString("Prefix") +
                                    Utils.chat(messagesFile.getConfig().getString("Spawn Teleport message"))));
                            return true;
                        }
                        // Player does have delay.
                        player.sendMessage(Utils.chat(messagesFile.getConfig().getString("Teleporting message")
                                .replaceAll("%delay%", ""+plugin.Delay)));
                        TeleportDelay.put(player.getUniqueId(), plugin.Delay);

                        // Counting down from delay that is set in config.
                        new BukkitRunnable() {
                            public void run() {
                                if (!TeleportDelay.containsKey(player.getUniqueId())) return;
                                if (TeleportDelay.get(player.getUniqueId()) <= 0) {
                                    // Sending the player to spawn.
                                    FileConfiguration spawn = spawnFile.getConfig();
                                    World world = Bukkit.getWorld(spawn.getString("Spawn.world"));
                                    double x = spawn.getDouble("Spawn.x");
                                    double y = spawn.getDouble("Spawn.y");
                                    double z = spawn.getDouble("Spawn.z");
                                    float yaw = (float) spawn.getDouble("Spawn.yaw");
                                    float pitch = (float) spawn.getDouble("Spawn.pitch");
                                    Location loc = new Location(world, x, y, z, yaw, pitch);
                                    player.teleport(loc);
                                    player.sendMessage(Utils.chat(messagesFile.getConfig().getString("Prefix") +
                                            Utils.chat(messagesFile.getConfig().getString("Spawn Teleport message"))));

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
                        player.sendMessage(ErrorMessages.NoPermission);
                    }
                }
            }
        }
        return false;
    }

}
