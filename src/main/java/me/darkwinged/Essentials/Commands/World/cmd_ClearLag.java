package me.darkwinged.Essentials.Commands.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

import java.util.List;

public class cmd_ClearLag implements CommandExecutor {

    private Main plugin;
    public cmd_ClearLag(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("clearlag")) {
            if (!(sender instanceof Player)) {
                if (args.length == 1) {
                    if (Bukkit.getWorld(args[0]) != null) {
                        int total = 0;
                        World world = Bukkit.getWorld(args[0]);
                        List<Entity> entList = world.getEntities();
                        for (Entity current : entList) {
                            if (current instanceof Item) {
                                current.remove();
                            }
                            if (current instanceof Mob) {
                                if (current.getCustomName() != null) return true;
                                current.remove();
                            }
                            if (current instanceof Player) {
                                total -= 1;
                            }
                        }
                        total += 1;
                        Bukkit.broadcastMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + plugin.MessagesFile.getConfig().getString("Clear Lag Message")
                                .replaceAll("%n", "\n")
                                .replaceAll("%entity_amount%", ""+total)
                                .replaceAll("%time%", ""+plugin.getConfig().getInt("Clear_Lag_Delay")/60)));
                        return true;
                    }
                }
                int total = 0;
                for (Player online : Bukkit.getOnlinePlayers()) {
                    World world = online.getWorld();
                    List<Entity> entList = world.getEntities();
                    for (Entity current : entList) {
                        if (current instanceof Item) {
                            current.remove();
                        }
                        if (current instanceof Mob) {
                            if (current.getCustomName() != null) return true;
                            current.remove();
                        }
                        if (current instanceof Player) {
                            total -= 1;
                        }
                    }
                    total += 1;
                }
                Bukkit.broadcastMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + plugin.MessagesFile.getConfig().getString("Clear Lag Message")
                        .replaceAll("%n", "\n")
                        .replaceAll("%entity_amount%", ""+total)
                        .replaceAll("%time%", ""+plugin.getConfig().getInt("Clear_Lag_Delay")/60)));
                return true;
            }
            Player player = (Player)sender;
            if (player.hasPermission(Permissions.ClearLag) || player.hasPermission(Permissions.GlobalOverwrite)) {
                if (args.length == 1) {
                    if (Bukkit.getWorld(args[0]) != null) {
                        int total = 0;
                        World world = Bukkit.getWorld(args[0]);
                        List<Entity> entList = world.getEntities();
                        for (Entity current : entList) {
                            if (current instanceof Item) {
                                current.remove();
                            }
                            if (current instanceof Mob) {
                                if (current.getCustomName() != null) return true;
                                current.remove();
                            }
                            if (current instanceof Player) {
                                total -= 1;
                            }
                        }
                        total += 1;
                        Bukkit.broadcastMessage(plugin.MessagesFile.getConfig().getString("Prefix") + plugin.MessagesFile.getConfig().getString("Clear Lag Message")
                                .replaceAll("%n", "\n")
                                .replaceAll("%entity_amount%", ""+total)
                                .replaceAll("%time%", ""+plugin.getConfig().getInt("Clear_Lag_Delay")/60));
                        return true;
                    }
                }
                int total = 0;
                for (Player online : Bukkit.getOnlinePlayers()) {
                    World world = online.getWorld();
                    List<Entity> entList = world.getEntities();
                    for (Entity current : entList) {
                        if (current instanceof Item) {
                            current.remove();
                        }
                        if (current instanceof Mob) {
                            if (current.getCustomName() != null) return true;
                            current.remove();
                        }
                        if (current instanceof Player) {
                            total -= 1;
                        }
                        total += 1;
                    }
                    total += 1;
                }
                Bukkit.broadcastMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + plugin.MessagesFile.getConfig().getString("Clear Lag Message")
                        .replaceAll("%n", "\n")
                        .replaceAll("%entity_amount%", ""+total)
                        .replaceAll("%time%", ""+plugin.getConfig().getInt("Clear_Lag_Delay")/60)));
            } else {
                player.sendMessage(ErrorMessages.NoPermission);
            }
        }
        return false;
    }
}
