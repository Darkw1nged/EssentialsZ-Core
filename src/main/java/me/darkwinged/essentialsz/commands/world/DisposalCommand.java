package me.darkwinged.essentialsz.commands.world;

import me.darkwinged.essentialsz.libaries.lang.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import me.darkwinged.essentialsz.libaries.lang.Utils;
import me.darkwinged.essentialsz.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class DisposalCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("disposal")) {
            if (plugin.getConfig().getBoolean("Commands.Disposal", true)) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Errors.getErrors(Errors.Console));
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.Disposal) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    Inventory inv = Bukkit.createInventory(null, 27, Utils.chat("&7Disposal"));
                    player.openInventory(inv);
                } else 
                    player.sendMessage(Errors.getErrors(Errors.NoPermission));
            }

        }
        return false;
    }

}
