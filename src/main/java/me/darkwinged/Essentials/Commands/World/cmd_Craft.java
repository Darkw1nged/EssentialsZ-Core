package me.darkwinged.Essentials.Commands.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Libaries.Lang.Errors;
import me.darkwinged.Essentials.Libaries.Lang.Permissions;
import me.darkwinged.Essentials.Libaries.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class cmd_Craft implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("craft")) {
            if (plugin.getConfig().getBoolean("Commands.Craft", true)) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Utils.chat(Errors.getErrors(Errors.Console)));
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.Craft) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    Inventory inv = Bukkit.createInventory(null, InventoryType.WORKBENCH);
                    player.openInventory(inv);
                }
            }
        }
        return false;
    }

}
