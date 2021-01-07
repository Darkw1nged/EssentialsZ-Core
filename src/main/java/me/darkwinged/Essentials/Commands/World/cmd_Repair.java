package me.darkwinged.Essentials.Commands.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class cmd_Repair implements CommandExecutor {

    private Main plugin;
    public cmd_Repair(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("repair")) {
            if (!(sender instanceof Player)) {
                Utils.Message(sender, Errors.getErrors(Errors.Console));
                return true;
            }
            Player player = (Player)sender;
            if (args.length == 1 && args[0].equalsIgnoreCase("all")) {
                if (player.hasPermission(Permissions.RepairAll) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    player.getInventory().getItemInOffHand().setDurability((short)0);
                    ItemStack[] arrayOfItemStack;
                    int localItemStack1 = (arrayOfItemStack = player.getInventory().getArmorContents()).length;
                    for (int ix = 0; ix < localItemStack1; ix++) {
                        ItemStack i = arrayOfItemStack[ix];
                        try {
                            if (i.getDurability() != 0)
                                i.setDurability((short)0);
                        } catch (Exception exception) {}
                    }
                    for (int i = 0; i <= 36; i++) {
                        try {
                            ItemStack w = player.getInventory().getItem(i);
                            if (w.getDurability() != 0)
                                player.getInventory().getItem(i).setDurability((short)0);
                        } catch (Exception ignored) {}
                    }
                } else {
                    Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                }
                return true;
            }
            if (player.hasPermission(Permissions.Repair) || player.hasPermission(Permissions.GlobalOverwrite)) {
                if (player.getItemInHand().getDurability() == 0) return true;
                player.getItemInHand().setDurability((short) 0);
            } else {
                Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
            }
        }
        return false;
    }

}
