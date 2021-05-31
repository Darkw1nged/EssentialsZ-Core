package me.darkwinged.essentialsz.events.world;

import me.darkwinged.essentialsz.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class InventoryFull implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (plugin.getConfig().getBoolean("World Events.Inventory Full.enabled", true)) {
            if (player.getInventory().firstEmpty() != -1) {
                return;
            }
            switch (plugin.getConfig().getString("World Events.Inventory Full.alert")) {
                case "ACTIONBAR":
                    plugin.essentialsZAPI.utils.sendActionBar(player,plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("World Events.Inventory Full.message"),
                            player, null, null, false));
                    break;
                case "TITLE":
                    plugin.essentialsZAPI.utils.sendTitle(player, plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("World Events.Inventory Full.Title.header"),
                            player, null, null, false),
                            plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("World Events.Inventory Full.Title.header"),
                                    player, null, null, false));
                    break;
                case "CHAT":
                    if (plugin.getConfig().getBoolean("World Events.Inventory Full.center", true)) {
                        player.sendMessage(plugin.essentialsZAPI.utils.CenteredMessage(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("World Events.Inventory Full.message"),
                                player, null, null, false)));
                    } else {
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("World Events.Inventory Full.message"),
                                player, null, null, false));
                    }
                    break;
            }
        }
    }

}
