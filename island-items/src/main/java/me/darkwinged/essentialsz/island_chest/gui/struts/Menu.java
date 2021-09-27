package me.darkwinged.essentialsz.island_chest.gui.struts;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryHolder;

public interface Menu extends InventoryHolder {

    default void onClick(Player player, int slot, ClickType type) {}

    default void onOpen(Player player) {}

    default void onClose(Player player) {}

}
