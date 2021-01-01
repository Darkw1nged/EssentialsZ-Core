package me.darkwinged.Essentials.REWORK.Commands.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.MessagesFile;
import me.darkwinged.Essentials.REWORK.Utils.ErrorMessages;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import me.darkwinged.Essentials.REWORK.Utils.Utils;
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
    public MessagesFile messagesFile;
    public cmd_ClearLag(Main plugin, MessagesFile messagesFile) {
        this.plugin = plugin;
        this.messagesFile = messagesFile;
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
                        Bukkit.broadcastMessage(Utils.chat(messagesFile.getConfig().getString("Prefix") + messagesFile.getConfig().getString("Clear Lag Message")
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
                Bukkit.broadcastMessage(Utils.chat(messagesFile.getConfig().getString("Prefix") + messagesFile.getConfig().getString("Clear Lag Message")
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
                        Bukkit.broadcastMessage(messagesFile.getConfig().getString("Prefix") + messagesFile.getConfig().getString("Clear Lag Message")
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
                Bukkit.broadcastMessage(Utils.chat(messagesFile.getConfig().getString("Prefix") + messagesFile.getConfig().getString("Clear Lag Message")
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
