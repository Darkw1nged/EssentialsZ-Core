package me.darkwinged.EssentialsZ.Commands.World;

import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Invsee implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("invsee")) {
            if (plugin.getConfig().getBoolean("Commands.Invsee", true)) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(plugin.essentialsZAPI.utils.chat(Errors.getErrors(Errors.Console), null, null, null, false));
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.Invsee) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (args.length != 1) {
                        sender.sendMessage(plugin.essentialsZAPI.utils.chat(Errors.getErrors(Errors.NoPlayerFound), null, null, null, false));
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        sender.sendMessage(plugin.essentialsZAPI.utils.chat(Errors.getErrors(Errors.NoPlayerFound), null, null, null, false));
                        return true;
                    }
                    player.openInventory(target.getInventory());
                } else {
                    sender.sendMessage(plugin.essentialsZAPI.utils.chat(Errors.getErrors(Errors.NoPermission), null, null, null, false));
                }
            }
        }
        return false;
    }

}
