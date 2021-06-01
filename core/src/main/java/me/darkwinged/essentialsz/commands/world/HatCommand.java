package me.darkwinged.essentialsz.commands.world;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HatCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("hat")) {
            if (plugin.getConfig().getBoolean("Commands.Hat", true)) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ErrorManager.getErrors(Errors.Console));
                    return true;
                }
                Player player = (Player) sender;
                if (player.hasPermission(Permissions.Hat) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    ItemStack inHand = player.getItemInHand();
                    if (player.getInventory().getHelmet() != null) {
                        player.getInventory().addItem(player.getInventory().getHelmet());
                    }
                    player.getInventory().setHelmet(inHand);
                    player.getInventory().setItemInHand(null);
                } else {
                   player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
                }
            } else {
                sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));
            }
        }
        return true;
    }

}
