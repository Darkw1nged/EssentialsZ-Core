package me.darkwinged.essentialsz.commands.world;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.Permission;
import me.darkwinged.essentialsz.commands.Name;
import me.darkwinged.essentialsz.commands.annotation.Permissions;
import me.darkwinged.essentialsz.commands.processor.annotation.PlayersOnly;
import me.darkwinged.essentialsz.inject.Inject;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Name("condense")
@PlayersOnly
@Permissions(value = {Permission.CONDENSE, Permission.GLOBAL})
public class CondenseCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    @Inject
    public CondenseCommand() {}

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (plugin.getConfig().getBoolean("Commands.Condense", true)) {
            Player player = (Player)sender;

            for (int x = 0; x <= 7; x++) {
                for (int i = 0; i <= 36; i++) {
                    try {
                        ItemStack item = player.getInventory().getItem(i);
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
                    } catch (Exception ignored) { }
                }
            }


        } else
            sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));
        return false;
    }
}
