package me.darkwinged.Essentials.Events.Economy;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.EconomyManager;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Utils;
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
        if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
            if (plugin.getConfig().getBoolean("Economy.Settings.Bank Notes", true)) {
                if (plugin.getConfig().getString("Economy.API").equalsIgnoreCase("Vault")) {
                } else if (plugin.getConfig().getString("Economy.API").equalsIgnoreCase("EssentialsZ")) {
                    Player player = event.getPlayer();
                    if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        String BankNote_Name = Utils.chat(plugin.getConfig().getString("Economy.Settings.Bank Notes.Item.name"));
                        if (player.getItemInHand().getType() == Material.AIR)
                            return;
                        if (!player.getItemInHand().getItemMeta().getDisplayName().equals(BankNote_Name)) return;
                        if (player.getItemInHand().getItemMeta().getDisplayName().equals(BankNote_Name)) {
                            ArrayList<String> itemlore = new ArrayList<>(player.getItemInHand().getItemMeta().getLore());
                            String newLore = itemlore.get(0).replaceAll(plugin.getConfig().getString("Economy.Settings.Bank Notes.Item.amount"), "");
                            // Player Account
                            if (!EconomyManager.hasAccount(player.getName())) {
                                return;
                            }
                            // Getting the amount
                            double amount;
                            try {
                                amount = Double.parseDouble(newLore);
                            } catch(Exception e) {
                                Utils.Message(player, Errors.getErrors(Errors.InvalidAmount));
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

}
