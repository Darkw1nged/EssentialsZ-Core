package me.darkwinged.Essentials.REWORK.Commands.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.ErrorMessages;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
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
                sender.sendMessage(ErrorMessages.Console);
                return true;
            }
            Player player = (Player)sender;
            if (args.length == 1) {
                if (player.hasPermission(Permissions.EnderchestOther) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    Player target = Bukkit.getPlayer(args[0]);
                    player.openInventory(target.getEnderChest());
                } else {
                    player.sendMessage(ErrorMessages.NoPermission);
                }
                return true;
            }
            if (player.hasPermission(Permissions.Enderchest) || player.hasPermission(Permissions.GlobalOverwrite)) {
                player.openInventory(player.getEnderChest());
            } else {
                player.sendMessage(ErrorMessages.NoPermission);
            }
        }
        return false;
    }
}
