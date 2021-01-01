package me.darkwinged.Essentials.Events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.Utils;

public class ServerSelector implements Listener {
	
	private Main plugin;
	public ServerSelector(Main plugin) { this.plugin = plugin; }
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) { 
		Player player = event.getPlayer();
		if (plugin.getConfig().getBoolean("Events.Server-Selector", true)) {
			ItemStack item = new ItemStack(Material.getMaterial(plugin.getConfig().getString("ServerSelector.ServerSelector-Item.item")));
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(Utils.chat(plugin.getConfig().getString(Utils.chat("ServerSelector.ServerSelector-Item.name"))));
            meta.setLore(plugin.getConfig().getStringList(Utils.chat("ServerSelector.ServerSelector-Item.lore")));
            item.setItemMeta(meta);
            player.getInventory().setItem(plugin.getConfig().getInt("ServerSelector.ServerSelector-Item.slot"), item);
		}
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		Action action = event.getAction();
		Player player = event.getPlayer();
		Inventory select = Bukkit.createInventory(null, 
				plugin.getConfig().getInt("ServerSelector.ServerSelector-GUI.size"), plugin.getConfig().getString("ServerSelector.ServerSelector-GUI.name"));
		if (plugin.getConfig().getBoolean("Events.Server-Selector", true)) {
			if (player.getItemInHand().getType().equals(Material.COMPASS)) {
				if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
					ItemStack item = new ItemStack(Material.ACACIA_BOAT);
					select.setItem(4, item);
					player.openInventory(select);
				}
			}
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player player = (Player)event.getWhoClicked();
		ItemStack item = player.getItemOnCursor();
		if (plugin.getConfig().getBoolean("Events.Server-Selector", true)) {
			/*if (event.getInventory().getName().equals(plugin.getConfig().getString("ServerSelector.ServerSelector-GUI.name"))) {
				event.setCancelled(true);
			}
			if (event.getInventory().getTitle().equals(plugin.getConfig().getString("ServerSelector.ServerSelector-GUI.name"))) {
				if (event.getCurrentItem().equals(Material.ACACIA_BOAT)) {
					ByteArrayOutputStream b = new ByteArrayOutputStream();
				    DataOutputStream out = new DataOutputStream(b);
				    try {
				    	out.writeUTF("Connect");
					    out.writeUTF("Hub");
					} catch (IOException e) {
					    e.printStackTrace();
					} 
					player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray()); 
				}
			}*/
		}
		
	}
	
}
