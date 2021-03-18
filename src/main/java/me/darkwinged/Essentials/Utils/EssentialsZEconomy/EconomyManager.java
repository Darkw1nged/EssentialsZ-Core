package me.darkwinged.Essentials.Utils.EssentialsZEconomy;

import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EconomyManager {

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
}
