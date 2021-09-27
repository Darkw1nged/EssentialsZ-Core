package me.darkwinged.essentialsz.events.economy;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Sellwand implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            Block block = event.getClickedBlock();
            if (block == null) return;
            if (!block.getType().equals(Material.CHEST) || !block.getType().equals(Material.TRAPPED_CHEST)) return;

            if (validItem(player)) {
                Chest chest = (Chest) block.getState();
                double amount = 0;
                if (chest.getInventory().getSize() == 54) {
                    for (int i = 0; i <= 54; i++) {
                        try {
                            ItemStack sellable = chest.getInventory().getItem(i);
                            if (plugin.WorthFile.getConfig().contains("worth." + sellable.getType().name().toUpperCase())) {
                                amount += plugin.WorthFile.getConfig().getDouble("worth." + sellable.getType().name().toUpperCase()) * sellable.getAmount();
                            }
                        } catch (Exception ignored) {
                        }
                    }
                } else if (chest.getInventory().getSize() == 27) {
                    for (int i = 0; i <= 27; i++) {
                        try {
                            ItemStack sellable = chest.getInventory().getItem(i);
                            if (plugin.WorthFile.getConfig().contains("worth." + sellable.getType().name().toUpperCase())) {
                                amount += plugin.WorthFile.getConfig().getDouble("worth." + sellable.getType().name().toUpperCase()) * sellable.getAmount();
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }
                if (amount <= 0) {
                    player.sendMessage(ErrorManager.getErrors(Errors.NoSellableItems));
                    return;
                }
                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                        plugin.MessagesFile.getConfig().getString("Sold").replaceAll("%amount%", String.valueOf(amount))));
                plugin.economyManager.AddAccount(player, amount);
            }


            ItemStack item = player.getInventory().getItemInHand();
            ItemStack sellwand = new ItemStack(Material.PAPER);
            if (item.isSimilar(sellwand)) {
                List<String> lore;
                int use_line = 0;
                for (int i = 0; i < item.getItemMeta().getLore().size(); i++) {
                    lore = new ArrayList<>();
                    lore.add(item.getItemMeta().getLore().get(i));
                    if (item.getItemMeta().getLore().get(i).contains("Uses: ")) {
                        use_line = i;
                    }
                }
                String line = item.getItemMeta().getLore().get(use_line).replaceAll("&fUses: ", "");
                int uses = Integer.parseInt(line);
                if (uses <= 1) {
                    item.setAmount(0);
                    player.updateInventory();
                } else {
                    uses = uses - 1;
                }
                item.getItemMeta().getLore().set(use_line, "Uses: " + uses);
            }
        }
    }

    public boolean validItem(Player player) {
        ItemStack checkItem = player.getInventory().getItemInHand();
        ItemStack item = new ItemStack(Material.getMaterial(plugin.EconomyItems.getConfig().getString("Sellwand.material").toUpperCase()));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(plugin.essentialsZAPI.utils.chat(plugin.EconomyItems.getConfig().getString("Sellwand.name")));

        List<String> old_lore = plugin.EconomyItems.getConfig().getStringList("Sellwand.lore");
        List<String> lore = new ArrayList<>();
        for (String str : old_lore) {
            if (str.contains("%uses%")) {
                str = str.replaceAll("%uses%", String.valueOf(0));
            }
            lore.add(plugin.essentialsZAPI.utils.chat(str));
        }
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);
        item.setItemMeta(meta);

        return checkItem.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName()) && checkItem.getItemMeta().getItemFlags() == item.getItemMeta().getItemFlags();
    }

    @EventHandler
    public void temp(PlayerJoinEvent event) {
        ItemStack item = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(plugin.essentialsZAPI.utils.chat("&2Sell Wand"));
        item.setItemMeta(meta);
        event.getPlayer().getInventory().addItem(item);
    }


}
