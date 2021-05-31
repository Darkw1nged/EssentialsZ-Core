package me.darkwinged.essentialsz.customelements;

import me.darkwinged.essentialsz.EssentialsZAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class CustomInventory {

    private final EssentialsZAPI main = EssentialsZAPI.getInstance;

    private final Map<String, Inventory> CustomInventory = new HashMap<>();

    public boolean isThereNotAInventory() {
        return CustomInventory.isEmpty();
    }

    public boolean doesInventoryExist(String invName) {
        return CustomInventory.containsKey(invName);
    }

    public void createInventory(String CustomName, InventoryHolder invHolder, int invSize, String invName) {
        Inventory inv = Bukkit.createInventory(invHolder, invSize, invName);
        CustomInventory.put(CustomName, inv);
        saveInventories();
    }

    public void removeInventory(String invName) {
        CustomInventory.remove(invName);
        saveInventories();
    }

    public void openInventory(Player player, String invName) {
        Inventory inv = CustomInventory.get(invName);
        player.openInventory(inv);
    }

    public void getInventorySize(String invName) {
        CustomInventory.get(invName).getSize();
    }

    public void getInventoryType(String invName) {
        CustomInventory.get(invName).getType();
    }

    public Inventory getInventory(String invName) { return CustomInventory.get(invName); }

    public void addInventoryItem(String invName, ItemStack item) {
        CustomInventory.get(invName).addItem(item);
    }

    public void setInventoryItem(String invName, ItemStack item, int slot) {
        CustomInventory.get(invName).setItem(slot, item);
    }

    public ItemStack getInventorySlot(String invName, int slot) {
        return CustomInventory.get(invName).getItem(slot);
    }

    public List<ItemStack> getInventoryContents(String invName) {
        return new ArrayList<>(Arrays.asList(CustomInventory.get(invName).getContents()));
    }

    public void saveInventories() {
        if (CustomInventory.isEmpty()) return;
        for (Map.Entry<String, Inventory> entry : CustomInventory.entrySet()) {
            main.InventoriesFile.getConfig().set("Inventories." + entry.getKey(), entry.getValue());
        }
    }

    public void loadInventories() {
        if (!main.InventoriesFile.getConfig().contains("Inventories.") || main.InventoriesFile.getConfig().getString("Inventories") == null) return;
        for (String key : main.InventoriesFile.getConfig().getConfigurationSection("Inventories.").getKeys(false)) {
            CustomInventory.put(key, (Inventory) main.InventoriesFile.getConfig().get("Inventories." + key));
        }
    }

}
