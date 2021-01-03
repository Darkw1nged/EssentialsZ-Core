package me.darkwinged.Essentials.Commands.Teleport;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
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

public class cmd_Warp implements CommandExecutor {

    private Main plugin;
    public cmd_Warp(Main plugin) {
        this.plugin = plugin; }
    private HashMap<UUID, Integer> TeleportDelay = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("warp")) {
            if (plugin.getConfig().getBoolean("Teleportation", true)) {
                if (plugin.getConfig().getBoolean("cmd_Warp", true)) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(ErrorMessages.Console);
                        return true;
                    }
                    Player player = (Player) sender;
                    if (args.length == 1) {
                        if (!plugin.getWarps().contains("Warps." + args[0])) {
                            player.sendMessage(ErrorMessages.WarpDoesNotExist);
                            return true;
                        }
                        if (player.hasPermission(Permissions.WarpsOverwrite) || player.hasPermission(Permissions.Warp + args[0]) || player.hasPermission(Permissions.GlobalOverwrite)) {
                            if (player.hasPermission(Permissions.TeleportBypass) || player.hasPermission(Permissions.GlobalOverwrite)) {
                                FileConfiguration warp = plugin.getWarps();
                                World world = Bukkit.getWorld(warp.getString("Warps." + args[0] + ".world"));
                                double x = warp.getDouble("Warps." + args[0] + ".x");
                                double y = warp.getDouble("Warps." + args[0] + ".y");
                                double z = warp.getDouble("Warps." + args[0] + ".z");
                                float yaw = (float) warp.getDouble("Warps." + args[0] + ".yaw");
                                float pitch = (float) warp.getDouble("Warps." + args[0] + ".pitch");
                                Location loc = new Location(world, x, y, z, yaw, pitch);
                                player.teleport(loc);

                                String Message = Utils.chat(plugin.MessagesFile.getConfig().getString("Warp Message")
                                        .replaceAll("%warp%", args[0]));
                                player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
                                return true;
                            }
                            player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Teleporting message")
                                    .replaceAll("%delay%", ""+plugin.Delay)));
                            TeleportDelay.put(player.getUniqueId(), plugin.Delay);

                            // Counting down from delay that is set in config.
                            new BukkitRunnable() {
                                public void run() {
                                    if (!TeleportDelay.containsKey(player.getUniqueId())) return;
                                    if (TeleportDelay.get(player.getUniqueId()) <= 0) {
                                        // Sending the player to spawn.
                                        FileConfiguration warp = plugin.getWarps();
                                        World world = Bukkit.getWorld(warp.getString("Warps." + args[0] + ".world"));
                                        double x = warp.getDouble("Warps." + args[0] + ".x");
                                        double y = warp.getDouble("Warps." + args[0] + ".y");
                                        double z = warp.getDouble("Warps." + args[0] + ".z");
                                        float yaw = (float) warp.getDouble("Warps." + args[0] + ".yaw");
                                        float pitch = (float) warp.getDouble("Warps." + args[0] + ".pitch");
                                        Location loc = new Location(world, x, y, z, yaw, pitch);
                                        player.teleport(loc);

                                        String Message = Utils.chat(plugin.MessagesFile.getConfig().getString("Warp Message")
                                                .replaceAll("%warp%", args[0]));
                                        player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));

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
                    } else {
                        if (args.length != 2) {
                            player.sendMessage(ErrorMessages.NoWarpNameProvided);
                            player.sendMessage(ErrorMessages.NoPlayer);
                            return true;
                        }
                        Player target = Bukkit.getPlayer(args[1]);
                        if (player.hasPermission(Permissions.WarpsOverwrite) || player.hasPermission(Permissions.Warp + args[0] + ".Other") || player.hasPermission(Permissions.GlobalOverwrite)) {
                            FileConfiguration warp = plugin.getWarps();
                            World world = Bukkit.getWorld(warp.getString("Warps." + args[0] + ".world"));
                            double x = warp.getDouble("Warps." + args[0] + ".x");
                            double y = warp.getDouble("Warps." + args[0] + ".y");
                            double z = warp.getDouble("Warps." + args[0] + ".z");
                            float yaw = (float) warp.getDouble("Warps." + args[0] + ".yaw");
                            float pitch = (float) warp.getDouble("Warps." + args[0] + ".pitch");
                            Location loc = new Location(world, x, y, z, yaw, pitch);
                            target.teleport(loc);

                            String Message = Utils.chat(plugin.MessagesFile.getConfig().getString("Warp Player Message")
                                    .replaceAll("%warp%", args[0])
                                    .replaceAll("%player%", args[1]));
                            player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
                        } else {
                            player.sendMessage(ErrorMessages.NoPermission);
                        }
                    }
                }
            }

        }
        return true;
    }

}
