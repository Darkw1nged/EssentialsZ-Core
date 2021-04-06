package me.darkwinged.Essentials.Events.Economy;

import me.darkwinged.Essentials.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class AccountSetup implements Listener {

	private final Main plugin = Main.getInstance;

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
			if (plugin.Module_Economy = false) return;

			if (plugin.economyManager.hasAccount(event.getPlayer())) return;
			plugin.economyManager.createAccount(event.getPlayer(), plugin.getConfig().getDouble("Economy.Settings.Starting Balance"));


		}
	}

}
