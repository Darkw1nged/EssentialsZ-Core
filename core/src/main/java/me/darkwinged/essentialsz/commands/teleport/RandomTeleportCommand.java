package me.darkwinged.essentialsz.commands.teleport;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.TeleportUtils;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class RandomTeleportCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    private final HashMap<UUID, Integer> RandomTeleportDelay = new HashMap<>();
    private final HashMap<UUID, Integer> Cooldown = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("rtp")) {
            if (plugin.getConfig().getBoolean("Teleportation.enabled", true)) {
                if (plugin.getConfig().getBoolean("Teleportation.Settings.Random Teleport.enabled", true)) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.Console));
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.RandomTeleport) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (RandomTeleportDelay.containsKey(player.getUniqueId()) || Cooldown.containsKey(player.getUniqueId())) {
                            player.sendMessage(ErrorManager.getErrors(Errors.Cooldown));
                            return true;
                        }
                        if (player.hasPermission(Permissions.TeleportBypass) || player.hasPermission(Permissions.GlobalOverwrite)) {
                            Location randomLocation = TeleportUtils.findSafeLocationRandom(player);
                            player.teleport(randomLocation);
                            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                    plugin.MessagesFile.getConfig().getString("Random Teleport message"), null, null, null, false));
                            return true;
                        } else {
                            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Teleporting message")
                                    .replaceAll("%delay%", ""+plugin.Delay), null, null, null, false));
                            RandomTeleportDelay.put(player.getUniqueId(), plugin.Delay);
                            new BukkitRunnable() {
                                public void run() {
                                    if (!RandomTeleportDelay.containsKey(player.getUniqueId())) return;
                                    if (RandomTeleportDelay.get(player.getUniqueId()) <= 0) {
                                        Location randomLocation = TeleportUtils.findSafeLocationRandom(player);
                                        player.teleport(randomLocation);
                                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                                plugin.MessagesFile.getConfig().getString("Random Teleport message"),
                                                null, null, null, false));

                                        RandomTeleportDelay.remove(player.getUniqueId());
                                        Cooldown.put(player.getUniqueId(), plugin.getConfig().getInt("RandomTeleport_Cooldown") );
                                        new BukkitRunnable() {
                                            public void run() {
                                                if (Cooldown.containsKey(player.getUniqueId())) {
                                                    Cooldown.put(player.getUniqueId(), Cooldown.get(player.getUniqueId()) - 1);
                                                    return;
                                                }
                                                Cooldown.remove(player.getUniqueId());
                                                cancel();
                                            }
                                        }.runTaskTimer(plugin, 0L, 20L);
                                        cancel();
                                        return;
                                    }
                                    RandomTeleportDelay.put(player.getUniqueId(), RandomTeleportDelay.get(player.getUniqueId()) - 1);
                                }
                            }.runTaskTimer(plugin, 0L, 20L);
                        }
                    } else
                        player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
                } else {
                    sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));
                }
            }
        }
        return false;
    }
}
