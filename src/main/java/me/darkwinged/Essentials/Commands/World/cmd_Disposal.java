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
import org.bukkit.inventory.Inventory;

public class cmd_Disposal implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("disposal")) {
            if (plugin.getConfig().getBoolean("Commands.Disposal", true)) {
                if (!(sender instanceof Player)) {
                    Utils.Message(sender, Errors.getErrors(Errors.Console));
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.Disposal) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    Inventory inv = Bukkit.createInventory(null, 27, Utils.chat("&7Disposal"));
                    player.openInventory(inv);
                }
            }

        }
        return false;
    }

}
