package me.darkwinged.Essentials.Commands.World;

import me.darkwinged.Essentials.Libaries.Lang.Errors;
import me.darkwinged.Essentials.Libaries.Lang.Permissions;
import me.darkwinged.Essentials.Libaries.Lang.Utils;
import me.darkwinged.Essentials.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Enderchest implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("enderchest")) {
            if (plugin.getConfig().getBoolean("Commands.Enderchest", true)) {
                if (!(sender instanceof Player)) {
                    Utils.Message(sender, Errors.getErrors(Errors.Console));
                    return true;
                }
                Player player = (Player)sender;
                if (args.length == 1) {
                    if (player.hasPermission(Permissions.EnderchestOther) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        Player target = Bukkit.getPlayer(args[0]);
                        player.openInventory(target.getEnderChest());
                    } else {
                        Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                    }
                    return true;
                }
                if (player.hasPermission(Permissions.Enderchest) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    player.openInventory(player.getEnderChest());
                } else {
                    Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                }
            }
        }
        return false;
    }
}
