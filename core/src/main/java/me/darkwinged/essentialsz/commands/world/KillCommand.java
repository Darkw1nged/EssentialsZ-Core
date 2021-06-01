package me.darkwinged.essentialsz.commands.world;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import me.darkwinged.essentialsz.libaries.lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

public class KillCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("kill")) {
            if (plugin.getConfig().getBoolean("Commands.Kill", true)) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Utils.chat(ErrorManager.getErrors(Errors.Console)));
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.Kill) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("@e")) {
                            for (Entity entity : player.getWorld().getEntities()) {
                                if (entity instanceof Monster) {
                                    ((Monster) entity).setHealth(0);
                                } else if (entity instanceof Animals) {
                                    ((Animals) entity).setHealth(0);
                                } else if (entity instanceof Player) {
                                    ((Player) entity).setHealth(0);
                                } else return true;

                            }
                            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                    plugin.MessagesFile.getConfig().getString("Kill Entities"), null, null, null, false));
                            return true;
                        } else if (args[0].equalsIgnoreCase("@a")) {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.setHealth(0);
                                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                        plugin.MessagesFile.getConfig().getString("Kill Players"), online, online, null, false));
                            }
                            return true;
                        }
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target == null) {
                            player.setHealth(0);
                            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                    plugin.MessagesFile.getConfig().getString("Killed Message"), player, player, null, false));
                            return true;
                        }
                        target.setHealth(0.0);
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("Killed Message"), target, target, null, false));
                    }
                } else
                    player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
            }
        }
        return false;
    }

}
