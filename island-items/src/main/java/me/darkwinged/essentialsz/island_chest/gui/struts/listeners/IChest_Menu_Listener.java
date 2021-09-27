package me.darkwinged.essentialsz.island_chest.gui.struts.listeners;

import me.darkwinged.essentialsz.EssentialsZIslandItems;
import me.darkwinged.essentialsz.island_chest.gui.struts.Menu_Basic_Items;
import me.darkwinged.essentialsz.island_chest.gui.struts.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class IChest_Menu_Listener implements Listener {

    private final EssentialsZIslandItems plugin = EssentialsZIslandItems.getInstance;

    @EventHandler
    private void onClick(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (!(holder instanceof Menu)) return;

        ((Menu)holder).onClick((Player)event.getWhoClicked(), event.getSlot(), event.getClick());
        ItemStack item = event.getCurrentItem();
        Player player = (Player)event.getWhoClicked();
        if (item == null) return;
        if (item.isSimilar(Menu_Basic_Items.getBackground()) || item.isSimilar(Menu_Basic_Items.getDespawn(player)) ||
                item.isSimilar(Menu_Basic_Items.getRefresh()) || item.isSimilar(Menu_Basic_Items.getSell(0))) event.setCancelled(true);
    }

    @EventHandler
    private void onOpen(InventoryOpenEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof Menu)
            ((Menu) holder).onOpen((Player)event.getPlayer());
    }

    @EventHandler
    private void onClose(InventoryCloseEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof Menu)
            ((Menu) holder).onClose((Player)event.getPlayer());
    }

}
