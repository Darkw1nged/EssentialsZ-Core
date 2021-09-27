package me.darkwinged.essentialsz.data;

import me.darkwinged.essentialsz.EssentialsZIslandItems;
import me.darkwinged.essentialsz.libaries.storage.CustomConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Player_Data implements Listener {

    private final EssentialsZIslandItems plugin = EssentialsZIslandItems.getInstance;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CustomConfig Data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");

        if (Data.getCustomConfigFile().exists()) {
            Data.saveConfig();
            return;
        }
        // Setup data
        Data.getConfig().set("iChest.tokens", 1);
        int tokens = Data.getConfig().getInt("iChest.tokens");
        for (int i=0; i<tokens; i++) {
            if (tokens <= 0) return;
            Data.getConfig().createSection("iChest." + i + ".items");
        }

        Data.saveConfig();
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onExit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        CustomConfig Data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
        Data.saveConfig();
    }


}
