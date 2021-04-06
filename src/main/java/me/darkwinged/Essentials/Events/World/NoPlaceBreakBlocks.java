package me.darkwinged.Essentials.Events.World;

import me.darkwinged.Essentials.Libaries.Lang.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.darkwinged.Essentials.Main;

public class NoPlaceBreakBlocks implements Listener {

	private final Main plugin = Main.getInstance;
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (plugin.getConfig().getBoolean("Cancel Events.Block Break", true)) {
			if (player.hasPermission(Permissions.bypass) || player.hasPermission(Permissions.GlobalOverwrite))
				return;
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (plugin.getConfig().getBoolean("Cancel Events.Block Place", true)) {
			if (player.hasPermission(Permissions.bypass) || player.hasPermission(Permissions.GlobalOverwrite))
				return;
			event.setCancelled(true);
		}
	}

}
