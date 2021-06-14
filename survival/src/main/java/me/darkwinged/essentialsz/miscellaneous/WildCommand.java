package me.darkwinged.essentialsz.miscellaneous;

import me.darkwinged.essentialsz.EssentialsZSurvival;
import me.darkwinged.essentialsz.miscellaneous.utils.TeleportUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;


public class WildCommand implements CommandExecutor {

    private final EssentialsZSurvival plugin = EssentialsZSurvival.getInstance;

    private HashMap<UUID, Integer> RandomTeleportDelay = new HashMap<>();
    private HashMap<UUID, Integer> Cooldown = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("wild")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(plugin.essentialsZAPI.utils.chat("&cError! You can not do this in console"));
                return true;
            }
            Player player = (Player) sender;
            if (RandomTeleportDelay.containsKey(player.getUniqueId()) || Cooldown.containsKey(player.getUniqueId())) {
                player.sendMessage(plugin.essentialsZAPI.utils.chat("&cErrors! You must wait before doing this again."));
                return true;
            }
            if (player.hasPermission("EssentialsZ.Survival.Bypass") || player.hasPermission("EssentialsZ.Survival.*")) {
                Location randomLocation = TeleportUtils.findSafeLocationRandom(player);
                player.teleport(randomLocation);
                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.Messages.getConfig().getString("Prefix") +
                        plugin.Messages.getConfig().getString("random teleport message")));
                return true;
            } else {
                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.Messages.getConfig().getString("teleporting message")
                        .replaceAll("%delay%", "" + 5)));
                RandomTeleportDelay.put(player.getUniqueId(), 5);
                new BukkitRunnable() {
                    public void run() {
                        if (!RandomTeleportDelay.containsKey(player.getUniqueId())) return;
                        if (RandomTeleportDelay.get(player.getUniqueId()) <= 0) {
                            Location randomLocation = TeleportUtils.findSafeLocationRandom(player);
                            player.teleport(randomLocation);
                            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.Messages.getConfig().getString("Prefix") +
                                    plugin.Messages.getConfig().getString("random teleport message")));

                            RandomTeleportDelay.remove(player.getUniqueId());
                            Cooldown.put(player.getUniqueId(), plugin.getConfig().getInt("RandomTeleport Cooldown"));
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
        }
        return false;
    }
}

