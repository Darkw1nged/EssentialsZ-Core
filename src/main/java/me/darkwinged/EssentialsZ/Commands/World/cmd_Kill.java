package me.darkwinged.EssentialsZ.Commands.World;

import me.darkwinged.EssentialsZ.Main;
import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Libaries.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

public class cmd_Kill  implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("kill")) {
            if (plugin.getConfig().getBoolean("Commands.Kill", true)) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Utils.chat(Errors.getErrors(Errors.Console)));
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

                                player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + plugin.MessagesFile.getConfig().getString("Kill " +
                                        "Entities")));
                            }
                            return true;
                        } else if (args[0].equalsIgnoreCase("@a")) {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.setHealth(0);
                                player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + plugin.MessagesFile.getConfig().getString("Kill " +
                                        "Players")));
                            }
                            return true;
                        }
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target == null) {
                            player.setHealth(0);
                            player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + plugin.MessagesFile.getConfig().getString("Killed " +
                                    "Message").replaceAll("%player%", player.getName())));
                            return true;
                        }
                        target.setHealth(0.0);
                        player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + plugin.MessagesFile.getConfig().getString("Killed " +
                                "Message").replaceAll("%player%", target.getName())));
                    }
                }
            }
        }
        return false;
    }

}
