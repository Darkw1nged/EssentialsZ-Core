package me.darkwinged.Essentials.Commands.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class cmd_Craft implements CommandExecutor {

    private Main plugin;
    public cmd_Craft(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("craft")) {
            if (plugin.getConfig().getBoolean("cmd_Craft", true)) {
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
