package me.darkwinged.Essentials.Commands.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class cmd_Repair implements CommandExecutor {

    private Main plugin;
    public cmd_Repair(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("repair")) {
            if (!(sender instanceof Player)) {
                Utils.Message(sender, Errors.getErrors(Errors.Console));
                return true;
            }
            Player player = (Player)sender;
            if (args.length == 1) {
                switch (args[0]) {
                    case "all":
                        if (player.hasPermission(Permissions.RepairAll) || player.hasPermission(Permissions.GlobalOverwrite)) {
                            player.getInventory().getItemInOffHand().setDurability((short) 0);
                            ItemStack[] arrayOfItemStack;
                            int localItemStack1 = (arrayOfItemStack = player.getInventory().getArmorContents()).length;
                            for (int ix = 0; ix < localItemStack1; ix++) {
                                ItemStack i = arrayOfItemStack[ix];
                                try {
                                    if (i.getDurability() != 0)
                                        i.setDurability((short) 0);
                                } catch (Exception ignored) {
                                }
                            }
                            for (int i = 0; i <= 36; i++) {
                                try {
                                    ItemStack w = player.getInventory().getItem(i);
                                    if (w.getDurability() != 0)
                                        player.getInventory().getItem(i).setDurability((short) 0);
                                } catch (Exception ignored) { }
                            }
                            player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + plugin.MessagesFile.getConfig().getString("Repair Inventory")));
                        } else {
                            Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                        }
                        break;
                    case "chest":
                       if (player.hasPermission(Permissions.RepairChest)) {
                           Block block = player.getTargetBlock(null, 5);
                           if (block.getType().equals(Material.CHEST) || block.getType().equals(Material.TRAPPED_CHEST)) {
                               Chest chest = (Chest)block.getState();
                               if (chest.getInventory().getSize() == 54) {
                                   for (int i = 0; i <= 54; i++) {
                                       try {
                                           ItemStack item = chest.getInventory().getItem(i);
                                           if (item.getDurability() != 0) {
                                                item.setDurability((short)0);
                                           }
                                       } catch (Exception ignored) { }
                                   }
                               } else if (chest.getInventory().getSize() == 27) {
                                   for (int i = 0; i <= 27; i++) {
                                       try {
                                           ItemStack item = chest.getInventory().getItem(i);
                                           if (item.getDurability() != 0) {
                                               item.setDurability((short)0);
                                           }
                                       } catch (Exception ignored) { }
                                   }
                               }
                               player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + plugin.MessagesFile.getConfig().getString("Repair Chest")));
                           } else {
                               player.sendMessage(Utils.chat("&cError! Chest was not found."));
                           }
                       } else {
                           Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                       }
                       break;
                }
            }
            if (player.hasPermission(Permissions.Repair) || player.hasPermission(Permissions.GlobalOverwrite)) {
                if (player.getItemInHand().getDurability() == 0) return true;
                player.getItemInHand().setDurability((short) 0);
                player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + plugin.MessagesFile.getConfig().getString("Repair")));
            } else {
                Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
            }
        }
        return false;
    }

}
