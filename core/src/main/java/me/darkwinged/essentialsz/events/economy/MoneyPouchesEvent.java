package me.darkwinged.essentialsz.events.economy;

import me.darkwinged.essentialsz.libaries.lang.Utils;
import me.darkwinged.essentialsz.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import static org.bukkit.Material.getMaterial;

public class MoneyPouchesEvent implements Listener {

    private final Main plugin = Main.getInstance;

    HashMap<UUID, Integer> open = new HashMap<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
            if (plugin.getConfig().getBoolean("Economy.Settings.Money Pouches.enabled", true)) {
                if (plugin.Module_Economy = false) return;
                Player player = event.getPlayer();
                ItemStack item = player.getInventory().getItemInHand();
                ItemStack pouch = null;
                int max = 0;
                int min = 0;
                if (!plugin.MoneyPouchesFile.getConfig().contains("Tiers.")) return;
                for (String key : plugin.MoneyPouchesFile.getConfig().getConfigurationSection("Tiers.").getKeys(false)) {
                    ItemStack temp = new ItemStack(getMaterial(plugin.MoneyPouchesFile.getConfig().getString("Tiers." + key + ".material")));
                    ItemMeta meta = temp.getItemMeta();
                    meta.setDisplayName(Utils.chat(plugin.MoneyPouchesFile.getConfig().getString("Tiers." + key + ".name")));
                    meta.setLore(plugin.essentialsZAPI.utils.getConvertedLore(plugin.MoneyPouchesFile.getConfig(), "Tiers." + key));
                    temp.setItemMeta(meta);

                    min = plugin.MoneyPouchesFile.getConfig().getInt("Tiers." + key + ".min");
                    max = plugin.MoneyPouchesFile.getConfig().getInt("Tiers." + key + ".max");
                    pouch = temp;
                }
                if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (item.isSimilar(pouch)) {
                        if (!plugin.economyManager.hasAccount(player)) return;
                        Random random = new Random();
                        int int_amount = random.nextInt((max-min) + 1) + min;
                        double amount;
                        try {
                            amount = int_amount;
                            event.setCancelled(true);
                        } catch(Exception e) {
                            event.setCancelled(true);
                            player.updateInventory();
                            return;
                        }

                        plugin.economyManager.AddAccount(player, amount);
                        item.setAmount(player.getItemInHand().getAmount() - 1);
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("Money Pouch Open")
                                        .replaceAll("%amount%", plugin.getConfig().getString("Economy.Settings.Currency Symbol") + amount),
                                null, null, null, false));
                    }

                    /*
                Player player = event.getPlayer();
                if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    for (String name : Utils.MoneyPouches.keySet()) {
                        List<String> magic = new ArrayList<>();
                        for (int i=0; i<String.valueOf(amount).length(); i++) {
                            magic.add("&a&k"+i);
                        }
                        int open_time = String.valueOf(int_amount).length();
                        new BukkitRunnable() {
                            public void run() {
                                if (!open.containsKey(player.getUniqueId())) return;
                                if (open.get(player.getUniqueId()) <= 0) {
                                    open.remove(player.getUniqueId());
                                    String line1 = Utils.chat("&a"+plugin.getConfig().getString("Economy.Settings.Currency_Symbol") + int_amount);
                                    String line2 = Utils.chat("&fOpening Pouch...");
                                    player.sendTitle(line1, line2);
                                    player.sendMessage(Utils.chat("&aYou have oppend a money pouch and gained &2" + plugin.getConfig().getString("Economy.Settings.Currency_Symbol") + int_amount));
                                    player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                                    open.put(player.getUniqueId(), open_time);
                                    magic.clear();
                                    cancel();
                                    return;
                                }
                                // Removing 1 from the count
                                if (open_time > 0) {
                                    magic.get(magic.size() - 1).replace("&k", "");
                                    String string_amount = "";
                                    for (int i=0; i<magic.size(); i++) {
                                        string_amount = string_amount + i;
                                    }

                                    player.sendTitle(Utils.chat(string_amount), Utils.chat("&fOpening Pouch..."));
                                }
                                open.put(player.getUniqueId(), open.get(player.getUniqueId()) - 1);
                            }
                        }.runTaskTimer(plugin, 0L, 8L * open_time);
                    }
                     */
                }
            }
        }
    }


    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        ItemStack pouch = null;
        if (!plugin.MoneyPouchesFile.getConfig().contains("Tiers.")) return;
        for (String key : plugin.MoneyPouchesFile.getConfig().getConfigurationSection("Tiers.").getKeys(false)) {
            ItemStack item = new ItemStack(getMaterial(plugin.MoneyPouchesFile.getConfig().getString("Tiers." + key + ".material")));
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(Utils.chat(plugin.MoneyPouchesFile.getConfig().getString("Tiers." + key + ".name")));
            meta.setLore(plugin.essentialsZAPI.utils.getConvertedLore(plugin.MoneyPouchesFile.getConfig(), "Tiers."+key));
            item.setItemMeta(meta);

            pouch = item;
        }
        if (pouch == null) return;
        if (pouch.isSimilar(player.getInventory().getItemInHand())) {
            event.setCancelled(true);
        }
    }


}
