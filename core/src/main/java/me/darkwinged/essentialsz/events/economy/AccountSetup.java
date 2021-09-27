package me.darkwinged.essentialsz.events.economy;

import me.darkwinged.essentialsz.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class AccountSetup implements Listener {

	private final Main plugin = Main.getInstance;

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
			if (!plugin.Module_Economy) return;

			if (plugin.economyManager.hasAccount(event.getPlayer())) return;
			plugin.economyManager.createAccount(event.getPlayer(), plugin.getConfig().getDouble("Economy.Settings.Starting Balance"));


		}
	}

}
