package me.darkwinged.Essentials.Utils.EssentialsZEconomy;

import me.darkwinged.Essentials.Main;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EconomyManager {

	private static Main plugin;

	public EconomyManager(Main instance) {
		plugin = instance;
	}

	public static Map<UUID, Double> economy = new HashMap<>();

	public static boolean hasAccount(OfflinePlayer player) {
		return economy.containsKey(player.getUniqueId());
	}

	public static void createAccount(OfflinePlayer player, double balance) {
		economy.put(player.getUniqueId(), balance);
	}

	public static void setAccount(OfflinePlayer player, double amount) {
		economy.put(player.getUniqueId(), amount);
	}

	public static void AddAccount(OfflinePlayer player, double amount) {
		economy.put(player.getUniqueId(), economy.get(player.getUniqueId()) + amount);
	}

	public static void RemoveAccount(OfflinePlayer player, double amount) {
		economy.put(player.getUniqueId(), economy.get(player.getUniqueId()) - amount);
	}

	public static Double getAccount(OfflinePlayer player) {
		return economy.get(player.getUniqueId());
	}

	public static boolean hasEnoughMoney(OfflinePlayer player, double amount) {
		return economy.get(player.getUniqueId()) <= amount;
	}

	public static Map<UUID, Double> getAccountMap() {
		return economy;
	}

	public static Main getPlugin() {
		return plugin;
	}
}
