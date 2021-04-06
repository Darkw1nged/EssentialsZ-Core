package me.darkwinged.Essentials.Commands.Teleport;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Libaries.Lang.Errors;
import me.darkwinged.Essentials.Libaries.Lang.Permissions;
import me.darkwinged.Essentials.Libaries.TeleportUtils;
import me.darkwinged.Essentials.Libaries.Lang.Utils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class cmd_RandomTeleport implements CommandExecutor {

    private final Main plugin;
    public cmd_RandomTeleport(Main plugin) { this.plugin = plugin; }

    private final HashMap<UUID, Integer> RandomTeleportDelay = new HashMap<>();
    private final HashMap<UUID, Integer> Cooldown = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("rtp")) {
            if (plugin.getConfig().getBoolean("Teleportation", true)) {
                if (plugin.getConfig().getBoolean("cmd_RandomTeleport", true)) {
                    if (!(sender instanceof Player)) {
                        Utils.Message(sender, Errors.getErrors(Errors.Console));
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.RandomTeleport) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (RandomTeleportDelay.containsKey(player.getUniqueId()) || Cooldown.containsKey(player.getUniqueId())) {
                            Utils.Message(sender, Errors.getErrors(Errors.Cooldown));
                            return true;
                        }
                        if (player.hasPermission(Permissions.TeleportBypass) || player.hasPermission(Permissions.GlobalOverwrite)) {
                            Location randomLocation = TeleportUtils.findSafeLocationRandom(player);
                            player.teleport(randomLocation);
                            player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                    plugin.MessagesFile.getConfig().getString("Random Teleport message")));
                            return true;
                        } else {
                            player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Teleporting message")
                                    .replaceAll("%delay%", ""+plugin.Delay)));
                            RandomTeleportDelay.put(player.getUniqueId(), plugin.Delay);
                            new BukkitRunnable() {
                                public void run() {
                                    if (!RandomTeleportDelay.containsKey(player.getUniqueId())) return;
                                    if (RandomTeleportDelay.get(player.getUniqueId()) <= 0) {
                                        Location randomLocation = TeleportUtils.findSafeLocationRandom(player);
                                        player.teleport(randomLocation);
                                        player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                                plugin.MessagesFile.getConfig().getString("Random Teleport message")));

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
                    } else {
                        Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                    }
                }
            }
        }
        return false;
    }
}
