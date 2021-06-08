package me.darkwinged.essentialsz.commands.teleport;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import me.darkwinged.essentialsz.libaries.util.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class WarpCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;
    private final HashMap<UUID, Integer> TeleportDelay = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("warp")) {
            if (plugin.getConfig().getBoolean("Teleportation.enabled", true)) {
                if (plugin.getConfig().getBoolean("Teleportation.Settings.Warps.enabled", true)) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.Console));
                        return true;
                    }
                    Player player = (Player) sender;
                    if (args.length == 1) {
                        String WarpName = args[0];
                        if (player.hasPermission(Permissions.Warp + args[0]) || player.hasPermission(Permissions.WarpsOverwrite) || player.hasPermission(Permissions.GlobalOverwrite)) {
                            CustomConfig WarpFile = new CustomConfig(plugin, WarpName, "Warps");
                            if (!WarpFile.getCustomConfigFile().exists()) {
                                sender.sendMessage(ErrorManager.getErrors(Errors.WarpDoesNotExist));
                                return true;
                            }
                            if (player.hasPermission(Permissions.TeleportBypass) || player.hasPermission(Permissions.GlobalOverwrite)) {
                                World world = Bukkit.getWorld(WarpFile.getConfig().getString("world"));
                                double x = WarpFile.getConfig().getDouble("x");
                                double y = WarpFile.getConfig().getDouble("y");
                                double z = WarpFile.getConfig().getDouble("z");
                                float pitch = (float)  WarpFile.getConfig().getDouble("pitch");
                                float yaw = (float)  WarpFile.getConfig().getDouble("yaw");
                                Location loc = new Location(world, x, y, z, yaw, pitch);
                                player.teleport(loc);

                                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                        plugin.MessagesFile.getConfig().getString("Warp Message").replaceAll("%warp%", WarpName),
                                        player, player, null, false));
                                return true;
                            }
                            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                            plugin.MessagesFile.getConfig().getString("Teleporting message").replaceAll("%delay%", ""+plugin.Delay),
                                    player, player, null, false));
                            TeleportDelay.put(player.getUniqueId(), plugin.Delay);
                            new BukkitRunnable() {
                                public void run() {
                                    if (!TeleportDelay.containsKey(player.getUniqueId())) return;
                                    if (TeleportDelay.get(player.getUniqueId()) <= 0) {
                                        // Sending the player to spawn.
                                        World world = Bukkit.getWorld(WarpFile.getConfig().getString("world"));
                                        double x = WarpFile.getConfig().getDouble("x");
                                        double y = WarpFile.getConfig().getDouble("y");
                                        double z = WarpFile.getConfig().getDouble("z");
                                        float pitch = (float)  WarpFile.getConfig().getDouble("pitch");
                                        float yaw = (float)  WarpFile.getConfig().getDouble("yaw");
                                        Location loc = new Location(world, x, y, z, yaw, pitch);
                                        player.teleport(loc);

                                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                                        plugin.MessagesFile.getConfig().getString("Warp Message").replaceAll("%warp%", WarpName),
                                                player, player, null, false));

                                        // Removing the player from the delay and resetting it
                                        TeleportDelay.remove(player.getUniqueId());
                                        cancel();
                                        return;
                                    }
                                    // Removing 1 from the count
                                    TeleportDelay.put(player.getUniqueId(), TeleportDelay.get(player.getUniqueId()) - 1);
                                }
                            }.runTaskTimer(plugin, 0L, 20L);

                        } else
                            player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
                    } else {
                        if (args.length != 2) {
                            player.sendMessage(ErrorManager.getErrors(Errors.NoWarpNameProvided));
                            player.sendMessage(ErrorManager.getErrors(Errors.NoPlayerFound));
                            return true;
                        }
                        Player target = Bukkit.getPlayer(args[1]);
                        String WarpName = args[0];
                        if (player.hasPermission(Permissions.Warp + WarpName + ".Other") || player.hasPermission(Permissions.WarpsOverwrite) || player.hasPermission(Permissions.GlobalOverwrite)) {

                            CustomConfig WarpFile = new CustomConfig(plugin, WarpName, "Warps");
                            if (!WarpFile.getCustomConfigFile().exists()) {
                                sender.sendMessage(ErrorManager.getErrors(Errors.WarpDoesNotExist));
                                return true;
                            }

                            World world = Bukkit.getWorld(WarpFile.getConfig().getString("world"));
                            double x = WarpFile.getConfig().getDouble("x");
                            double y = WarpFile.getConfig().getDouble("y");
                            double z = WarpFile.getConfig().getDouble("z");
                            float pitch = (float)  WarpFile.getConfig().getDouble("pitch");
                            float yaw = (float)  WarpFile.getConfig().getDouble("yaw");
                            Location loc = new Location(world, x, y, z, yaw, pitch);
                            target.teleport(loc);

                            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                            plugin.MessagesFile.getConfig().getString("Warp Player Message").replaceAll("%warp%", WarpName),
                                    player, target, null, false));
                        } else
                            player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
                    }
                } else {
                    sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));
                }
            }

        }
        return true;
    }

}
