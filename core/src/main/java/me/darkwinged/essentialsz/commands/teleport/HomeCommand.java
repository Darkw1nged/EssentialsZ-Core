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

public class HomeCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;
    private final HashMap<UUID, Integer> TeleportDelay = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("home")) {
            if (plugin.getConfig().getBoolean("Teleportation.enabled", true)) {
                if (plugin.getConfig().getBoolean("Teleportation.Settings.Homes.enabled", true)) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.Console));
                        return true;
                    }
                    Player player = (Player) sender;
                    if (TeleportDelay.containsKey(player.getUniqueId())) {
                        return true;
                    }
                    if (args.length == 1) {
                        if (player.hasPermission(Permissions.Home) || player.hasPermission(Permissions.HomesOverwrite) || player.hasPermission(Permissions.GlobalOverwrite)) {
                            if (player.hasPermission(Permissions.TeleportBypass) || player.hasPermission(Permissions.GlobalOverwrite)) {
                                String HomeName = args[0];
                                CustomConfig Data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
                                if (!Data.getCustomConfigFile().exists()) return true;
                                if (!Data.getConfig().contains("Homes." + HomeName)) {
                                    sender.sendMessage(plugin.essentialsZAPI.utils.chat(ErrorManager.getErrors(Errors.HomeDoesNotExist),
                                            null, null, null, false));
                                    return true;
                                }
                                World world = Bukkit.getWorld(Data.getConfig().getString("Homes." + HomeName + ".world"));
                                double x = Data.getConfig().getDouble("Homes." + HomeName + ".x");
                                double y = Data.getConfig().getDouble("Homes." + HomeName + ".y");
                                double z = Data.getConfig().getDouble("Homes." + HomeName + ".z");
                                float yaw = (float) Data.getConfig().getDouble("Homes." + HomeName + ".yaw");
                                float pitch = (float) Data.getConfig().getDouble("Homes." + HomeName + ".pitch");

                                Location loc = new Location(world, x, y, z, yaw, pitch);
                                player.teleport(loc);

                                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                        plugin.MessagesFile.getConfig().getString("Home Message").replaceAll("%home%", HomeName),
                                        null, null, null, false));

                                return true;
                            }
                            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Teleporting message")
                                    .replaceAll("%delay%", ""+plugin.Delay), null, null, null, false));
                            TeleportDelay.put(player.getUniqueId(), plugin.Delay);

                            // Counting down from delay that is set in config.
                            new BukkitRunnable() {
                                public void run() {
                                    if (!TeleportDelay.containsKey(player.getUniqueId())) return;
                                    if (TeleportDelay.get(player.getUniqueId()) <= 0) {
                                        // Sending the player to spawn.
                                        String HomeName = args[0];
                                        CustomConfig Data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
                                        if (!Data.getCustomConfigFile().exists()) return;
                                        if (!Data.getConfig().contains("Homes." + HomeName)) {
                                            sender.sendMessage(plugin.essentialsZAPI.utils.chat(ErrorManager.getErrors(Errors.HomeDoesNotExist),
                                                    null, null, null, false));
                                            return;
                                        }
                                        World world = Bukkit.getWorld(Data.getConfig().getString("Homes." + HomeName + ".world"));
                                        double x = Data.getConfig().getDouble("Homes." + HomeName + ".x");
                                        double y = Data.getConfig().getDouble("Homes." + HomeName + ".y");
                                        double z = Data.getConfig().getDouble("Homes." + HomeName + ".z");
                                        float yaw = (float) Data.getConfig().getDouble("Homes." + HomeName + ".yaw");
                                        float pitch = (float) Data.getConfig().getDouble("Homes." + HomeName + ".pitch");

                                        Location loc = new Location(world, x, y, z, yaw, pitch);
                                        player.teleport(loc);

                                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                                        plugin.MessagesFile.getConfig().getString("Home Message").replaceAll("%home%", HomeName),
                                                null, null, null, false));

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
                    }
                } else {
                    sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));
                }
            }
        }

        return false;
    }
}
