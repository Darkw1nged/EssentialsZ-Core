package me.darkwinged.Essentials.Events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.Utils;

public class AntiGrief implements Listener {

	private Main plugin;
	public AntiGrief(Main plugin) { this.plugin = plugin; }
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Location loc = event.getBlock().getLocation();
		if (plugin.getConfig().getBoolean("Events.Anti-grief", true)) {
			if (event.getBlock().getType().equals(Material.TNT)) {
				for (Player staff : Bukkit.getOnlinePlayers()) {
	                if (staff.hasPermission("Essentials.antigrief.see") || staff.hasPermission("Essentials.*")) {
	                	
	                	Double x = player.getLocation().getX();
	                	String locx = String.format("%.1f", x);
	               	
	                	Double y = player.getLocation().getY();
	                	String locy = String.format("%.1f", y);
	                	
	                	Double z = player.getLocation().getZ();
	                	String locz = String.format("%.1f", z);
	                	
	                	staff.sendMessage(Utils.chat("&4&l[!] &6&l" + player.getName() + "&f&l has placed &a&l" + event.getBlock().getType() +
	                			" &f&lat &a&lWorld: " + player.getWorld().getName() + " X: " + locx + " Y: " 
	                			+ locy + " Z: " + locz));                	
	                }
	        	}
				Utils.griefcheck.set("Grief.Player", player.getName() + loc);
			}
			
		}
	}
	
}
