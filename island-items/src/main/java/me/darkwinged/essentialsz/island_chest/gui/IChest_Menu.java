package me.darkwinged.essentialsz.island_chest.gui;

import me.darkwinged.essentialsz.EssentialsZIslandItems;
import me.darkwinged.essentialsz.island_chest.IChestManager;
import me.darkwinged.essentialsz.island_chest.gui.struts.Menu;
import me.darkwinged.essentialsz.island_chest.gui.struts.Menu_Basic_Items;
import me.darkwinged.essentialsz.libaries.storage.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class IChest_Menu implements Menu {

    private final EssentialsZIslandItems plugin = EssentialsZIslandItems.getInstance;
    private final Player player;
    private final Inventory inventory;
    String name;

    ItemStack background = Menu_Basic_Items.getBackground();

    public IChest_Menu(Player player) {
        this.name = plugin.essentialsZAPI.utils.chat("iChest");
        this.player = player;
        this.inventory = Bukkit.createInventory(this, 45, this.name);

        inventory.setItem(0, background);                               inventory.setItem(1, background);                      inventory.setItem(2, background);
        inventory.setItem(3, background);                               inventory.setItem(4, background);                      inventory.setItem(5, background);
        inventory.setItem(6, background);                               inventory.setItem(7, background);                      inventory.setItem(8, background);

        inventory.setItem(9, background);                               inventory.setItem(10, background);
        inventory.setItem(16, background);                              inventory.setItem(17, background);

        inventory.setItem(18, background);                              inventory.setItem(19, background);
        inventory.setItem(25, background);                              inventory.setItem(26, background);

        inventory.setItem(27, background);                              inventory.setItem(28, background);                      inventory.setItem(29, background);
        inventory.setItem(30, background);                              inventory.setItem(31, background);                      inventory.setItem(32, background);
        inventory.setItem(33, background);                              inventory.setItem(34, background);                      inventory.setItem(35, background);

        inventory.setItem(36, Menu_Basic_Items.getDespawn(player));     inventory.setItem(37, background);                      inventory.setItem(38, background);
        inventory.setItem(39, background);                              inventory.setItem(40, Menu_Basic_Items.getRefresh());   inventory.setItem(41, background);
        inventory.setItem(42, background);                              inventory.setItem(43, background);                      inventory.setItem(44, Menu_Basic_Items.getSell(0));
    }

    public void onClick(Player player, int slot, ClickType type) {
        UUID uuid = player.getUniqueId();
        switch (slot) {
            case 36:
                for (int i = 0; i< IChestManager.iChestTokens.get(uuid).size(); i++) {
                    if (this == IChestManager.iChestActive.get(uuid)) {
                        IChestManager.iChestTokens.get(uuid).get(i).getLocation().getBlock().setType(Material.AIR);
                        IChestManager.iChestTokens.get(uuid).remove(i);

                        CustomConfig data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
                        data.getConfig().set("iChest.tokens", data.getConfig().getInt("iChest.tokens") + 1);
                        data.saveConfig();
                        break;
                    }
                }
                player.closeInventory();
                break;
            case 40:
                player.closeInventory();
                player.openInventory(this.getInventory());
            case 44:

                break;
        }
    }

    public void onOpen(Player player) {
        UUID uuid = player.getUniqueId();

    }

    public void onClose(Player player) {
        IChestManager.iChestActive.remove(player.getUniqueId());
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}
