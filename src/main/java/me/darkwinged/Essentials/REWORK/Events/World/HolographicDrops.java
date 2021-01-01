package me.darkwinged.Essentials.REWORK.Events.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.Utils;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class HolographicDrops implements Listener {

    private Main plugin;
    public HolographicDrops(Main plugin) { this.plugin = plugin; }

    int stack_amount = 0;

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (plugin.getConfig().getBoolean("Item_Holograms", true)) {
            Item item = event.getItemDrop();
            if (plugin.getConfig().getBoolean("Item_Holograms_Amount", true)) {
                int amount = stack_amount + item.getItemStack().getAmount();
                item.setCustomName(Utils.chat(plugin.getConfig().getString("Item_Holograms_Name")
                        .replaceAll("%item%", item.getName())
                        .replaceAll("%amount%", ""+amount)));
                item.setCustomNameVisible(true);
                return;
            }
            item.setCustomName(Utils.chat(plugin.getConfig().getString("Item_Holograms_Name")
                    .replaceAll("%item%", item.getName())));
        }
    }

    @EventHandler
    public void pickup(EntityPickupItemEvent event) {
        if (plugin.getConfig().getBoolean("Item_Holograms", true)) {
            stack_amount = 0;
        }
    }

    @EventHandler
    public void droppedItem(ItemMergeEvent event) {
        if (plugin.getConfig().getBoolean("Item_Holograms", true)) {
            stack_amount = event.getEntity().getItemStack().getAmount();
        }
    }

}
