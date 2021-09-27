package me.darkwinged.essentialsz.island_chest.gui.struts.listeners;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;

public class IChest_Chunk_Listener implements Listener {

    @EventHandler
    public void onDrop(ItemSpawnEvent event) {
        Item item = event.getEntity();
    }

}
