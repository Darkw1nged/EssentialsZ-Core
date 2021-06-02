package me.darkwinged.essentialsz.Events;

import me.darkwinged.essentialsz.EssentialsZFriends;
import me.darkwinged.essentialsz.Libaries.CustomConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerData implements Listener {

    private final EssentialsZFriends plugin = EssentialsZFriends.getInstance;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CustomConfig Data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");

        if (Data.getCustomConfigFile().exists()) {
            Data.getConfig().set("lastKnownName", player.getName());
            Data.saveConfig();
            return;
        }
        // Setup data
        Data.getConfig().set("lastKnownName", player.getName());
        Data.getConfig().set("Chat Mode", "GLOBAL");
        Data.getConfig().set("Total Friends", 0);
        Data.getConfig().createSection("Friends");
        Data.saveConfig();
    }

}
