package me.darkwinged.Essentials.Commands.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class cmd_Invsee implements CommandExecutor, Listener {

    private Main plugin;
    public cmd_Invsee(Main plugin) {
        this.plugin = plugin;
    }

    private Inventory inv;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("invsee")) {
            if (plugin.getConfig().getBoolean("cmd_Invsee", true)) {
                if (!(sender instanceof Player)) {
                    Utils.Message(sender, Errors.getErrors(Errors.Console));
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.Invsee) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (args.length != 1) {
                        Utils.Message(sender, Errors.getErrors(Errors.NoPlayerFound));
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        Utils.Message(sender, Errors.getErrors(Errors.NoPlayerFound));
                        return true;
                    }
                    if (target == player) {
                        Utils.Message(sender, Errors.getErrors(Errors.SenderInstaceOfPlayer));
                        return true;
                    }
                    CreateInventory(target);
                    player.openInventory(inv);
                } else {
                    Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                }
            }
        }
        return false;
    }

    private void CreateInventory(Player player) {
        inv = Bukkit.createInventory(player, 54, Utils.chat("&7" + player.getName() + "'s Inventory"));
        // Hotbar
        inv.setItem(0, player.getInventory().getItem(0));
        inv.setItem(1, player.getInventory().getItem(1));
        inv.setItem(2, player.getInventory().getItem(2));
        inv.setItem(3, player.getInventory().getItem(3));
        inv.setItem(4, player.getInventory().getItem(4));
        inv.setItem(5, player.getInventory().getItem(5));
        inv.setItem(6, player.getInventory().getItem(6));
        inv.setItem(7, player.getInventory().getItem(7));
        inv.setItem(8, player.getInventory().getItem(8));

        // Inventory Line 1
        inv.setItem(9, player.getInventory().getItem(9));
        inv.setItem(10, player.getInventory().getItem(10));
        inv.setItem(11, player.getInventory().getItem(11));
        inv.setItem(12, player.getInventory().getItem(12));
        inv.setItem(13, player.getInventory().getItem(13));
        inv.setItem(14, player.getInventory().getItem(14));
        inv.setItem(15, player.getInventory().getItem(15));
        inv.setItem(16, player.getInventory().getItem(16));
        inv.setItem(17, player.getInventory().getItem(17));

        // Inventory Line 2
        inv.setItem(18, player.getInventory().getItem(18));
        inv.setItem(19, player.getInventory().getItem(19));
        inv.setItem(20, player.getInventory().getItem(20));
        inv.setItem(21, player.getInventory().getItem(21));
        inv.setItem(22, player.getInventory().getItem(22));
        inv.setItem(23, player.getInventory().getItem(23));
        inv.setItem(24, player.getInventory().getItem(24));
        inv.setItem(25, player.getInventory().getItem(25));
        inv.setItem(26, player.getInventory().getItem(26));

        // Inventory Line 3
        inv.setItem(27, player.getInventory().getItem(27));
        inv.setItem(28, player.getInventory().getItem(28));
        inv.setItem(29, player.getInventory().getItem(29));
        inv.setItem(30, player.getInventory().getItem(30));
        inv.setItem(31, player.getInventory().getItem(31));
        inv.setItem(32, player.getInventory().getItem(32));
        inv.setItem(33, player.getInventory().getItem(33));
        inv.setItem(34, player.getInventory().getItem(34));
        inv.setItem(35, player.getInventory().getItem(35));

        // Gray Line
        inv.setItem(36, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inv.setItem(37, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inv.setItem(38, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inv.setItem(39, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inv.setItem(40, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inv.setItem(41, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inv.setItem(42, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inv.setItem(43, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        inv.setItem(44, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));

        // Armor + Item In hand
        inv.setItem(45, player.getInventory().getHelmet());
        inv.setItem(46, player.getInventory().getChestplate());
        inv.setItem(47, player.getInventory().getLeggings());
        inv.setItem(48, player.getInventory().getBoots());
        inv.setItem(49, player.getInventory().getItemInMainHand());
        inv.setItem(50, player.getInventory().getItemInOffHand());
    }

    @EventHandler
    public void click(InventoryClickEvent event) {
        if (event.getInventory().equals(inv)) {
            event.setCancelled(true);
        }
    }

}
