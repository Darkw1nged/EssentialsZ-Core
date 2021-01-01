package me.darkwinged.Essentials.REWORK.Events.Economy;

import me.darkwinged.Essentials.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.darkwinged.Essentials.REWORK.Utils.EconomyManager;

public class AccountSetup implements Listener {

	private Main plugin;
	public AccountSetup(Main plugin) { this.plugin = plugin; }

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if (plugin.getConfig().getBoolean("Economy", true)) {
			double StartingBal = plugin.getConfig().getInt("Starting_Balance");
			if (EconomyManager.hasAccount(event.getPlayer().getName())) { return; }
			EconomyManager.setBalance(event.getPlayer().getName(), StartingBal);
		}

	}

}
