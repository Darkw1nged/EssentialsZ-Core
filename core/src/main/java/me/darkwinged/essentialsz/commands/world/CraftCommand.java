package me.darkwinged.essentialsz.commands.world;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.Permission;
import me.darkwinged.essentialsz.commands.Name;
import me.darkwinged.essentialsz.commands.annotation.Permissions;
import me.darkwinged.essentialsz.commands.processor.annotation.PlayersOnly;
import me.darkwinged.essentialsz.inject.Inject;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

@Name("craft")
@PlayersOnly
@Permissions(value = {Permission.CRAFT, Permission.GLOBAL})
public class CraftCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    @Inject
    public CraftCommand() {}

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (plugin.getConfig().getBoolean("Commands.Craft", true)) {
            Player player = (Player)sender;
            Inventory inv = Bukkit.createInventory(null, InventoryType.WORKBENCH);
            player.openInventory(inv);
        }
        return false;
    }

}
