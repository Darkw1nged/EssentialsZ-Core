package me.darkwinged.essentialsz.events.economy;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Errors;
import me.darkwinged.essentialsz.libaries.lang.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

public class BankNotes implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void BankNotesInteract(PlayerInteractEvent event) {
        if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
            if (plugin.getConfig().getBoolean("Economy.Settings.Bank Notes", true)) {
                if (plugin.Module_Economy = false) return;
                Player player = event.getPlayer();
                if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    String BankNote_Name = Utils.chat(plugin.getConfig().getString("Economy.Settings.Bank Notes.Item.name"));
                    if (player.getInventory().getItemInHand().getType() == Material.AIR)
                        return;
                    if (!player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(BankNote_Name)) return;
                    if (player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(BankNote_Name)) {
                        ArrayList<String> itemlore = new ArrayList<>(player.getInventory().getItemInHand().getItemMeta().getLore());
                        String newLore = itemlore.get(0).replaceAll(plugin.getConfig().getString("Economy.Settings.Bank Notes.Item.amount"), "");
                        // Player Account
                        if (!plugin.economyManager.hasAccount(player)) return;
                        // Getting the amount
                        double amount;
                        try {
                            amount = Double.parseDouble(newLore);
                        } catch (Exception e) {
                            player.sendMessage(Errors.getErrors(Errors.InvalidAmount));
                            return;
                        }
                        // Adding the amount to the players balance
                        plugin.economyManager.AddAccount(player, amount);
                        player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
                    }
                }
            }
        }
    }

}
