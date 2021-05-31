package me.darkwinged.essentialsz.commands.world;

import me.darkwinged.essentialsz.libaries.lang.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import me.darkwinged.essentialsz.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderchestCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("enderchest")) {
            if (plugin.getConfig().getBoolean("Commands.Enderchest", true)) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Errors.getErrors(Errors.Console));
                    return true;
                }
                Player player = (Player)sender;
                if (args.length == 1) {
                    if (player.hasPermission(Permissions.EnderchestOther) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        Player target = Bukkit.getPlayer(args[0]);
                        player.openInventory(target.getEnderChest());
                    } else 
                        player.sendMessage(Errors.getErrors(Errors.NoPermission));
                    return true;
                }
                if (player.hasPermission(Permissions.Enderchest) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    player.openInventory(player.getEnderChest());
                } else 
                    player.sendMessage(Errors.getErrors(Errors.NoPermission));
            }
        }
        return false;
    }
}
