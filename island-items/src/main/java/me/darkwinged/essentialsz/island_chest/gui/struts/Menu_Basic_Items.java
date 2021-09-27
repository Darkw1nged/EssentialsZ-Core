package me.darkwinged.essentialsz.island_chest.gui.struts;

import me.darkwinged.essentialsz.EssentialsZIslandItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Menu_Basic_Items {

    private static final EssentialsZIslandItems plugin = EssentialsZIslandItems.getInstance;

    public static ItemStack getBackground() {
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName( " " );
        meta.getItemFlags().add(ItemFlag.HIDE_ATTRIBUTES);
        meta.getItemFlags().add(ItemFlag.HIDE_ENCHANTS);
        meta.getItemFlags().add(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getDespawn(Player owner) {
        ItemStack item = new ItemStack(Material.BARRIER, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(plugin.essentialsZAPI.utils.chat( "&4&l[!] &c&lDESPAWN iCHEST &7(Click)" ));

        List<String> lore = new ArrayList<>();
        lore.add( " " );
        lore.add(plugin.essentialsZAPI.utils.chat( " &4* &cThis iChest is owned by &7" + owner.getName() ));
        lore.add( " " );
        lore.add(plugin.essentialsZAPI.utils.chat( "&7Once clicked, your &ciChest will despawn&7," ));
        lore.add(plugin.essentialsZAPI.utils.chat( "&7and an iChest Token will be &aadded to your balance&7." ));
        lore.add( " " );
        lore.add(plugin.essentialsZAPI.utils.chat( "&c&oWARNING: &7&OYou will lose all items inside if you remove."));

        meta.setLore(lore);
        meta.getItemFlags().add(ItemFlag.HIDE_ATTRIBUTES);
        meta.getItemFlags().add(ItemFlag.HIDE_ENCHANTS);
        meta.getItemFlags().add(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getRefresh() {
        ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(plugin.essentialsZAPI.utils.chat( "&6&l[!] &e&lREFRESH ITEMS &7(Click)" ));

        List<String> lore = new ArrayList<>();
        lore.add(plugin.essentialsZAPI.utils.chat( "&7Once clicked, the &eitems inside will refresh&7." ));

        meta.setLore(lore);
        meta.getItemFlags().add(ItemFlag.HIDE_ATTRIBUTES);
        meta.getItemFlags().add(ItemFlag.HIDE_ENCHANTS);
        meta.getItemFlags().add(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getSell(int value) {
        ItemStack item = new ItemStack(Material.SIGN, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(plugin.essentialsZAPI.utils.chat( "&2&l[!] &a&lSELL ALL &7(Click)" ));

        List<String> lore = new ArrayList<>();
        lore.add( " " );
        lore.add(plugin.essentialsZAPI.utils.chat( "&7Once clicked, your &2items will disappear&7," ));
        lore.add(plugin.essentialsZAPI.utils.chat( "&7and you will be given &athe money earned&7!" ));
        lore.add( " " );
        lore.add(plugin.essentialsZAPI.utils.chat( "&2&l* &a&lCURRENT VALUE: &f$" + value));

        meta.setLore(lore);
        meta.getItemFlags().add(ItemFlag.HIDE_ATTRIBUTES);
        meta.getItemFlags().add(ItemFlag.HIDE_ENCHANTS);
        meta.getItemFlags().add(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack addItem(Material material, int amount) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(plugin.essentialsZAPI.utils.chat( "&e&l[!] &7" + material.getData().getName() ));

        List<String> lore = new ArrayList<>();
        lore.add(plugin.essentialsZAPI.utils.chat( "&6&l* &e&lQuantity: &fx" + amount ));
        lore.add( " " );
        lore.add(plugin.essentialsZAPI.utils.chat( "&7&o(( Left Click for &a&oStack &7&o))" ));
        lore.add(plugin.essentialsZAPI.utils.chat( "&7&o(( Right Click for &b&oAll &7&o))" ));

        meta.setLore(lore);
        meta.getItemFlags().add(ItemFlag.HIDE_ATTRIBUTES);
        meta.getItemFlags().add(ItemFlag.HIDE_ENCHANTS);
        meta.getItemFlags().add(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        return item;
    }

}
