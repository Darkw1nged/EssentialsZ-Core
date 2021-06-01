package me.darkwinged.essentialsz.commands.world;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import java.util.List;

public class ClearlagCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("clearlag")) {
            if (plugin.getConfig().getBoolean("Commands.Clear Lag", true)) {
                if (!(sender instanceof Player)) {
                    int total = 0;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        World world = Bukkit.getWorld(online.getWorld().getName());
                        if (world == null) return true;
                        List<Entity> entList = world.getEntities();
                        for (Entity current : entList) {
                            if (current instanceof Item) {
                                current.remove();
                                total += 1;
                            }
                            if (current instanceof Monster) {
                                if (current.getCustomName() != null) return true;
                                current.remove();
                            }
                            if (current instanceof Animals) {
                                if (current.getCustomName() != null) return true;
                                current.remove();
                            }
                        }
                    }
                    Bukkit.broadcastMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                            plugin.MessagesFile.getConfig().getString("Clear Lag Message")
                                    .replaceAll("%n", "\n")
                                    .replaceAll("%entity_amount%", ""+total)
                                    .replaceAll("%time%", ""+plugin.ServerDataFile.getConfig().getInt("Clear Lag Delay")/60), null, null, null, false));
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.Clearlag) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    int total = 0;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        World world = Bukkit.getWorld(online.getWorld().getName());
                        if (world == null) return true;
                        List<Entity> entList = world.getEntities();
                        for (Entity current : entList) {
                            if (current instanceof Item) {
                                current.remove();
                                total += 1;
                            }
                            if (current instanceof Monster) {
                                if (current.getCustomName() != null) return true;
                                current.remove();
                            }
                            if (current instanceof Animals) {
                                if (current.getCustomName() != null) return true;
                                current.remove();
                            }
                        }
                    }
                    Bukkit.broadcastMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + plugin.MessagesFile.getConfig().getString("Clear Lag " +
                            "Message")
                            .replaceAll("%n", "\n")
                            .replaceAll("%entity_amount%", ""+total)
                            .replaceAll("%time%", ""+plugin.ServerDataFile.getConfig().getInt("Clear Lag Delay")/60), null, null, null, false));
                } else {
                    sender.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
                }
            } else
                sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));
        }
        return false;
    }
}