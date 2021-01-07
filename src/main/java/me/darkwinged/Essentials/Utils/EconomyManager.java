package me.darkwinged.Essentials.Utils;

import java.util.HashMap;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.entity.Player;

public class EconomyManager  {

	private static Main plugin;
	
	public EconomyManager(Main instance) {
		plugin = instance;
	}
	
	public static HashMap<String, Double> economy = new HashMap<>();
	
	public static void setBalance(String player, double amount) {
		economy.put(player, amount);
	}
	
	public static Double getBalance(String player) {
		return economy.get(player);
	}
	
	public static boolean hasAccount(String player) {
		return economy.containsKey(player);
	}
	
	public static HashMap<String, Double> getAccountMap() {
		return economy;
	}
	
	public static Main getPlugin() {
		return plugin;
	}

	public static void AccountCheck(Player player) {
		if (!EconomyManager.hasAccount(player.getName())) {
			Utils.Message(player, Errors.getErrors(Errors.NoAccount));
		}
	}

	public static void EnoughMoneyCheck(Player player, Double amount) {
		if (amount > EconomyManager.getBalance(player.getName())) {
			Utils.Message(player, Errors.getErrors(Errors.NotEnoughMoney));
		}
	}

}
