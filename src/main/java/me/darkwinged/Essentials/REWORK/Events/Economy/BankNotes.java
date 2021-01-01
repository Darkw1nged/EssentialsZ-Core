package me.darkwinged.Essentials.REWORK.Events.Economy;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.ErrorMessages;
import me.darkwinged.Essentials.REWORK.Utils.EconomyManager;
import me.darkwinged.Essentials.REWORK.Utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

public class BankNotes implements Listener {

    private Main plugin;
    public BankNotes(Main plugin) { this.plugin = plugin;}

    @EventHandler
    public void BankNotesInteract(PlayerInteractEvent event) {
        if (plugin.getConfig().getBoolean("Economy", true)) {
            if (plugin.getConfig().getBoolean("Bank_Notes", true)) {
                Player player = event.getPlayer();
                if (player.getItemInHand().getType() == Material.AIR)
                    return;
                if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (player.getItemInHand().getItemMeta().getDisplayName().equals(Utils.chat(plugin.getConfig().getString("BNLayout.name")))) {
                        ArrayList<String> itemlore = new ArrayList<>(player.getItemInHand().getItemMeta().getLore());
                        String newLore = itemlore.get(0).replaceAll(plugin.getConfig().getString("BNLayout.amount"), "");
                        // Player Account
                        if (!EconomyManager.hasAccount(player.getName())) {
                            return;
                        }
                        // Getting the amount
                        double amount;
                        try {
                            amount = Double.parseDouble(newLore);
                        } catch(Exception e) {
                            player.sendMessage(ErrorMessages.InvalidAmount);
                            return;
                        }
                        // Adding the amount to the players balance
                        EconomyManager.setBalance(player.getName(), EconomyManager.getBalance(player.getName()) + amount);
                        player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                    }
                }
            }
        }
    }

}
