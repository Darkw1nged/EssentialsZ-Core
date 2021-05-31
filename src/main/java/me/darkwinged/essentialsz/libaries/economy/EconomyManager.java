package me.darkwinged.essentialsz.libaries.economy;

import me.darkwinged.essentialsz.libaries.lang.CustomConfig;
import me.darkwinged.essentialsz.Main;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EconomyManager {

	private final Main plugin = Main.getInstance;

	public Map<UUID, Double> BankAccounts = new HashMap<>();
	public boolean hasAccount(OfflinePlayer player) { return BankAccounts.containsKey(player.getUniqueId()); }
	public void createAccount(OfflinePlayer player, double balance) {
		BankAccounts.put(player.getUniqueId(), balance);
	}
	public void setAccount(OfflinePlayer player, double amount) {
		BankAccounts.put(player.getUniqueId(), amount);
	}
	public void AddAccount(OfflinePlayer player, double amount) {
		BankAccounts.put(player.getUniqueId(), BankAccounts.get(player.getUniqueId()) + amount);
	}
	public void RemoveAccount(OfflinePlayer player, double amount) {
		BankAccounts.put(player.getUniqueId(), BankAccounts.get(player.getUniqueId()) - amount);
	}
	public Double getAccount(OfflinePlayer player) {
		return BankAccounts.get(player.getUniqueId());
	}
	public boolean hasEnoughMoney(OfflinePlayer player, double amount) {
		return BankAccounts.get(player.getUniqueId()) <= amount;
	}
	public Map<UUID, Double> getAccountMap() {
		return BankAccounts;
	}

	public void saveBalance(Player player) {
		CustomConfig Data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
		if (!Data.getConfig().contains("lastKnownName")) return;
		Data.getConfig().set("money", getAccount(player));
	}
	public void loadBalance(Player player) {
		CustomConfig Data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
		BankAccounts.put(player.getUniqueId(), Data.getConfig().getDouble("money"));
	}

}
