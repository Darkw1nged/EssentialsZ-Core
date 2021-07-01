package me.darkwinged.essentialsz.items.boosters;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.Boosters;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ExperienceBooster {

    private final Main plugin = Main.getInstance;
    public static ItemStack MoneyBoosterPH;

    private ItemStack booster(Player player) {
        ItemStack item = new ItemStack(Material.getMaterial(plugin.EconomyItems.getConfig().getString("PlaceHolders.booster.material").toUpperCase()));
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(plugin.essentialsZAPI.utils.chat(plugin.EconomyItems.getConfig().getString("PlaceHolders.booster.name")));
        List<String> lore = new ArrayList<>();
        for (String s : plugin.EconomyItems.getConfig().getStringList("PlaceHolders.booster.lore")) {
            s = s.replaceAll("%Boosters%", String.valueOf(new Boosters().getMoneyBoosters(player)));
            lore.add(plugin.essentialsZAPI.utils.chat(s));
        }
        meta.setLore(lore);

        item.setItemMeta(meta);
        return MoneyBoosterPH = item;
    }

}
