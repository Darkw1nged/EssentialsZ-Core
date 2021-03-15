package me.darkwinged.Essentials.Events.Economy;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.EssentialsZEconomy.EconomyManager;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class MoneyPouchesEvent implements Listener {

    private Main plugin;
    public MoneyPouchesEvent(Main plugin) {
        this.plugin = plugin;
    }

    HashMap<UUID, Integer> open = new HashMap<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
            if (plugin.getConfig().getBoolean("Economy.Settings.Money Pouches", true)) {
                if (plugin.Module_Economy = false) return;

                Player player = event.getPlayer();
                if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    for (String name : Utils.MoneyPouches.keySet()) {
                        if (name == null || !player.getItemInHand().hasItemMeta()) return;
                        if (!name.equals(player.getItemInHand().getItemMeta().getDisplayName())) return;
                        if (open.containsKey(player.getUniqueId())) return;
                        if (!EconomyManager.hasAccount(player)) return;
                        // Getting the amount
                        int max = Utils.MoneyPouches_max.get(name);
                        int min = Utils.MoneyPouches_min.get(name);
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
                        List<String> magic = new ArrayList<>();
                        for (int i=0; i<String.valueOf(amount).length(); i++) {
                            magic.add("&a&k"+i);
                        }
                        int open_time = String.valueOf(int_amount).length();
                        // Adding the amount to the players balance
                        EconomyManager.AddAccount(player, amount);
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
                }
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        for (String name : Utils.MoneyPouches.keySet()) {
            if (name == null || !player.getItemInHand().hasItemMeta()) return;
            if (!name.equals(player.getItemInHand().getItemMeta().getDisplayName())) return;
            event.setCancelled(true);
        }
    }


}
