package me.darkwinged.Essentials.Commands.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Enderchest implements CommandExecutor {

    private Main plugin;
    public cmd_Enderchest(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("enderchest")) {
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
                Utils.Message(sender, Errors.getErrors(Errors.NoPermission));;
            }
        }
        return false;
    }
}
