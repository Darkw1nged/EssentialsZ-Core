package me.darkwinged.essentialsz.events.signs;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SignChestSell implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void SignCreate(SignChangeEvent event) {
        if (plugin.getConfig().getBoolean("Economy.Settings.Sell.Chestsell Sign", true)) {
            Player player = event.getPlayer();
            if (player.hasPermission(Permissions.GamemodeSign) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String line0 = event.getLine(0);
                if (line0 != null && line0.equalsIgnoreCase("[chestsell]")) {
                    org.bukkit.material.Sign sign = (org.bukkit.material.Sign) event.getBlock().getState().getData();
                    Block attached = event.getBlock().getRelative(sign.getAttachedFace());
                    if (attached.getType() == Material.CHEST || attached.getType() == Material.TRAPPED_CHEST) {
                        event.setLine(0, plugin.essentialsZAPI.utils.chat("&e[!] &6Chestsell"));
                        event.setLine(1, plugin.essentialsZAPI.utils.chat(""));
                        event.setLine(2, plugin.essentialsZAPI.utils.chat("&8(Right Click or"));
                        event.setLine(3, plugin.essentialsZAPI.utils.chat("&8Punch to Sell.)"));
                    } else {
                        event.setLine(0, plugin.essentialsZAPI.utils.chat("&cInvalid Chestsell!"));
                    }
                }
            }
        }

    }

    @EventHandler
    public void SignInteract(PlayerInteractEvent event) {
        if (plugin.getConfig().getBoolean("Economy.Settings.Sell.Chestsell Sign", true)) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Player player = event.getPlayer();
                Block block = event.getClickedBlock();
                if (block == null) return;
                BlockState blockState = block.getState();
                if (!(blockState instanceof org.bukkit.block.Sign)) return;

                if (plugin.essentialsZAPI.items.isSign(block)) {
                    Sign sign = (Sign)block.getState();
                    String line0 = sign.getLine(0);
                    if (line0.contains(plugin.essentialsZAPI.utils.chat("&e[!] &6Chestsell"))) {
                        org.bukkit.material.Sign chestSell = (org.bukkit.material.Sign) event.getClickedBlock().getState().getData();
                        Block attached = event.getClickedBlock().getRelative(chestSell.getAttachedFace());
                        if (attached.getType().equals(Material.CHEST) || attached.getType().equals(Material.TRAPPED_CHEST)) {
                            Chest chest = (Chest)attached.getState();
                            double amount = 0;
                            if (chest.getInventory().getSize() == 54) {
                                for (int i = 0; i <= 54; i++) {
                                    amount = getAmount(chest, amount, i);
                                }
                            } else if (chest.getInventory().getSize() == 27) {
                                for (int i = 0; i <= 27; i++) {
                                    amount = getAmount(chest, amount, i);
                                }
                            }
                            if (amount <= 0) {
                                player.sendMessage(ErrorManager.getErrors(Errors.NoSellableItems));
                                return;
                            }
                            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                    plugin.MessagesFile.getConfig().getString("Sold").replaceAll("%amount%", ""+amount)));
                            plugin.economyManager.AddAccount(player, amount);
                        }
                    }
                }
            }
        }

    }

    private double getAmount(Chest chest, double amount, int i) {
        try {
            ItemStack item = chest.getInventory().getItem(i);
            if (plugin.WorthFile.getConfig().contains("worth." + item.getType().name().toUpperCase())) {
                amount += plugin.WorthFile.getConfig().getDouble("worth." + item.getType().name().toUpperCase()) * item.getAmount();
            }
            item.setAmount(0);
        } catch (Exception ignored) { }
        return amount;
    }
}
