package me.darkwinged.EssentialsZ.CustomElements;

import me.darkwinged.EssentialsZ.EssentialsZAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomItems {

    private final EssentialsZAPI main = EssentialsZAPI.getInstance;

    private final Map<String, ItemStack> CustomItem = new HashMap<>();

    public boolean isThereNotACustomItem() {
        return CustomItem.isEmpty();
    }

    public boolean doesItemExist(String itemName) {
        return CustomItem.containsKey(itemName);
    }

    public void createItem(String CustomName, Material itemMaterial, int itemAmount, String itemName, List<String> itemLore) {
        ItemStack item = new ItemStack(itemMaterial, itemAmount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemName));

        List<String> lore = new ArrayList<>();
        for (String s : itemLore) {
            lore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        meta.setLore(lore);

        item.setItemMeta(meta);

        CustomItem.put(CustomName, item);
        saveItems();
    }

    public void removeItem(String itemName) { CustomItem.remove(itemName); saveItems(); }

    public ItemStack getItem(String itemName) {
        return CustomItem.get(itemName);
    }

    public void addItemEnchantment(String itemName, Enchantment enchantment, int level) {
        ItemStack item = CustomItem.get(itemName);
        item.addUnsafeEnchantment(enchantment, level);
        CustomItem.put(itemName, item);
    }

    public void setItemType(String itemName, Material material) {
        ItemStack item = CustomItem.get(itemName);
        item.setType(material);
        CustomItem.put(itemName, item);
    }

    public void setItemAmount(String itemName, int amount) {
        ItemStack item = CustomItem.get(itemName);
        item.setAmount(amount);
        CustomItem.put(itemName, item);
    }

    public void setItemLore(String itemName, List<String> itemLore) {
        ItemStack item = CustomItem.get(itemName);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<>();
        for (String s : itemLore) {
            lore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        meta.setLore(lore);

        item.setItemMeta(meta);
        CustomItem.put(itemName, item);
    }

    public void saveItems() {
        if (CustomItem.isEmpty()) return;
        for (Map.Entry<String, ItemStack> entry : CustomItem.entrySet()) {
            main.ItemsFile.getConfig().set("Items." + entry.getKey(), entry.getValue());
        }
    }

    public void loadItems() {
        if (!main.ItemsFile.getConfig().contains("Items.") || main.ItemsFile.getConfig().getString("Items") == null) return;
        for (String key : main.ItemsFile.getConfig().getConfigurationSection("Items.").getKeys(false)) {
            CustomItem.put(key, main.ItemsFile.getConfig().getItemStack("Items." + key));
        }
    }

}
