package me.darkwinged.essentialsz.events.economy;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

import static java.lang.Math.round;

public class PlayerHeads implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void PlayerHeadSell(PlayerInteractEvent event) {
        if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
            if (plugin.getConfig().getBoolean("Economy.Settings.Money Heads.enabled", true)) {
                if (!plugin.Module_Economy) return;

                Player player = event.getPlayer();
                if (player.getItemInHand().getType().equals(plugin.essentialsZAPI.items.playerHead(player).getType())) {
                    if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        String TargetName = player.getItemInHand().getItemMeta().getDisplayName();
                        Player target = Bukkit.getPlayer(TargetName);
                        // Player Account
                        if (!plugin.economyManager.hasAccount(player)) {
                            return;
                        }
                        // Target Account
                        if (!plugin.economyManager.hasAccount(target)) {
                            return;
                        }

                        // Getting the amount
                        double amount;
                        try {
                            amount = round((plugin.economyManager.getAccount(target) / 100) * plugin.getConfig().getInt("Economy.Settings.Money Heads.SellWand Amount"));
                        } catch (Exception e) {
                            player.sendMessage(ErrorManager.getErrors(Errors.InvalidAmount));
                            return;
                        }
                        // Removing the amount from the senders balance
                        plugin.economyManager.AddAccount(player, amount);
                        // Adding the amount to the target balance
                        plugin.economyManager.RemoveAccount(target, amount);
                        player.setItemInHand(new ItemStack(Material.AIR));
                    }
                }
            }
        }
    }

    @EventHandler
    public void PlayerHeadChance(PlayerDeathEvent event) {
        if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
            if (plugin.getConfig().getBoolean("Economy.Settings.Money Heads.enabled", true)) {
                if (!plugin.Module_Economy) return;
                Player player = event.getEntity();
                Random rand = new Random();
                int n = rand.nextInt(100) + 1;
                if (n <= plugin.getConfig().getInt("Economy.Settings.Money Heads.Drop Chance")) {
                    ItemStack skull = plugin.essentialsZAPI.items.playerHead(player);
                    event.getDrops().add(skull);
                }
            }
        }
    }

    @EventHandler
    public void PlacePlayerHead(BlockPlaceEvent event) {
        if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
            if (plugin.getConfig().getBoolean("Economy.Settings.Money Heads.enabled", true)) {
                if (!plugin.Module_Economy) return;
                Player player = event.getPlayer();
                if (event.getBlock().getType().equals(plugin.essentialsZAPI.items.playerHead(player).getType())) {
                    player.sendMessage(ErrorManager.getErrors(Errors.NoPlacePlayerHead));
                    event.setCancelled(true);
                }
            }
        }
    }

}
