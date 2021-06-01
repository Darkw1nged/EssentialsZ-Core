package me.darkwinged.essentialsz.commands.world;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CondenseCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("condense")) {
            if (plugin.getConfig().getBoolean("Commands.Condense", true)) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ErrorManager.getErrors(Errors.Console));
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.Condense) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    for (ItemStack item : player.getInventory()) {
                        switch (item.getType()) {

                            case COAL:
                                if (item.getAmount() >= 9) {
                                    item.setAmount(item.getAmount() - 9);
                                    player.getInventory().addItem(new ItemStack(Material.COAL_BLOCK));
                                }
                                break;
                            case IRON_INGOT:
                                if (item.getAmount() >= 9) {
                                    item.setAmount(item.getAmount() - 9);
                                    player.getInventory().addItem(new ItemStack(Material.IRON_BLOCK));
                                }
                                break;
                            case GOLD_INGOT:
                                if (item.getAmount() >= 9) {
                                    item.setAmount(item.getAmount() - 9);
                                    player.getInventory().addItem(new ItemStack(Material.GOLD_BLOCK));
                                }
                                break;
                            case DIAMOND:
                                if (item.getAmount() >= 9) {
                                    item.setAmount(item.getAmount() - 9);
                                    player.getInventory().addItem(new ItemStack(Material.DIAMOND_BLOCK));
                                }
                                break;
                            case EMERALD:
                                if (item.getAmount() >= 9) {
                                    item.setAmount(item.getAmount() - 9);
                                    player.getInventory().addItem(new ItemStack(Material.EMERALD_BLOCK));
                                }
                                break;
                            case REDSTONE:
                                if (item.getAmount() >= 9) {
                                    item.setAmount(item.getAmount() - 9);
                                    player.getInventory().addItem(new ItemStack(Material.REDSTONE_BLOCK));
                                }
                                break;
                        }
                        if (item.getItemMeta().getDisplayName().equalsIgnoreCase("Lapis Lazuli")) {
                            if (item.getAmount() >= 9) {
                                item.setAmount(item.getAmount() - 9);
                                player.getInventory().addItem(new ItemStack(Material.LAPIS_BLOCK));
                            }
                        }
                    }
                } else
                    player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
            } else
                sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));
        }
        return false;
    }
}
