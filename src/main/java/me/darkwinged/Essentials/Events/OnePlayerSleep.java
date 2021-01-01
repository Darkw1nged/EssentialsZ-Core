package me.darkwinged.Essentials.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

import me.darkwinged.Essentials.Main;

public class OnePlayerSleep implements Listener {
	
	private Main plugin;
	public OnePlayerSleep(Main plugin) { this.plugin = plugin; }
	
	@EventHandler
	public void onPlayerViolationCommand(final PlayerBedEnterEvent event) {
		final Player player = event.getPlayer();
		if (this.plugin.getConfig().getBoolean("Events.One-Player-Sleep", true))
			Bukkit.getServer().getScheduler().runTaskLater(plugin, new Runnable() {
				public void run() {
					if (!player.isSleeping())
						return; 
					if (event.getPlayer().getWorld().getTime() >= 12541L) {
						player.getWorld().setTime(0L);
						player.getWorld().setStorm(false);
						player.getWorld().setThundering(false);
					} 
				}
			},  100L); 
	  }

}
