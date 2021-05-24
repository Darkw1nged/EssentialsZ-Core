package me.darkwinged.EssentialsZ.Commands.World;

import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class cmd_Anvil implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("anvil")) {
            if (plugin.getConfig().getBoolean("Commands.Anvil", true)) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Errors.getErrors(Errors.Console));
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.Anvil) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    Inventory inv = Bukkit.createInventory(null, InventoryType.ANVIL);
                    player.openInventory(inv);
                } else
                    player.sendMessage(Errors.getErrors(Errors.NoPermission));
            }
        }
        return false;
    }

}
