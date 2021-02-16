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

public class cmd_Home implements CommandExecutor {

    private Main plugin;
    public cmd_Home(Main plugin) { this.plugin = plugin; }
    private HashMap<UUID, Integer> TeleportDelay = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (plugin.getConfig().getBoolean("Teleportation", true)) {
            if (plugin.getConfig().getBoolean("Homes", true)) {
                if (!(sender instanceof Player)) {
                    Utils.Message(sender, Errors.getErrors(Errors.Console));
                    return true;
                }
                Player player = (Player) sender;
                if (TeleportDelay.containsKey(player.getUniqueId())) {
                    return true;
                }
                if (args.length == 1) {
                    if (player.hasPermission(Permissions.Home) || player.hasPermission(Permissions.HomesOverwrite) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (player.hasPermission(Permissions.TeleportBypass) || player.hasPermission(Permissions.GlobalOverwrite)) {
                            String Message = Utils.chat(plugin.MessagesFile.getConfig().getString("Home Message")
                                    .replaceAll("%home%", args[0]));
                            FileConfiguration home = plugin.getHomes();
                            World world = Bukkit.getWorld(home.getString("Homes.Owner's Name " + player.getName() + "." + args[0] + ".world"));
                            double x = home.getDouble("Homes.Owner's Name " + player.getName() + "." + args[0] + ".x");
                            double y = home.getDouble("Homes.Owner's Name " + player.getName() + "." + args[0] + ".y");
                            double z = home.getDouble("Homes.Owner's Name " + player.getName() + "." + args[0] + ".z");
                            float yaw = (float) home.getDouble("Homes.Owner's Name " + player.getName() + "." + args[0] + ".yaw");
                            float pitch = (float) home.getDouble("Homes.Owner's Name " + player.getName() + "." + args[0] + ".pitch");
                            Location loc = new Location(world, x, y, z, yaw, pitch);
                            player.teleport(loc);
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
                                    FileConfiguration home = plugin.getHomes();
                                    World world = Bukkit.getWorld(home.getString("Homes.Owner's Name " + player.getName() + "." + args[0] + ".world"));
                                    double x = home.getDouble("Homes.Owner's Name " + player.getName() + "." + args[0] + ".x");
                                    double y = home.getDouble("Homes.Owner's Name " + player.getName() + "." + args[0] + ".y");
                                    double z = home.getDouble("Homes.Owner's Name " + player.getName() + "." + args[0] + ".z");
                                    float yaw = (float) home.getDouble("Homes.Owner's Name " + player.getName() + "." + args[0] + ".yaw");
                                    float pitch = (float) home.getDouble("Homes.Owner's Name " + player.getName() + "." + args[0] + ".pitch");
                                    Location loc = new Location(world, x, y, z, yaw, pitch);
                                    player.teleport(loc);
                                    String Message = Utils.chat(plugin.MessagesFile.getConfig().getString("Home Message")
                                            .replaceAll("%home%", args[0]));
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
                        Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
