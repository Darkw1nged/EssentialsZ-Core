package me.darkwinged.Essentials.Events.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.CustomConfig;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerDataSetup implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CustomConfig Data = new CustomConfig(plugin, "Data/" + player.getUniqueId());

        if (Utils.PlayerData.isEmpty()) {
            Utils.PlayerData.put(player.getUniqueId(), Data);
            Utils.PlayerData.get(player.getUniqueId()).saveConfig();
        }

        if (Utils.PlayerData.containsKey(player.getUniqueId())) return;
        Utils.PlayerData.put(player.getUniqueId(), Data);

        Utils.PlayerData.get(player.getUniqueId()).getConfig().set("Player Name", player.getName());
        Utils.PlayerData.get(player.getUniqueId()).getConfig().set("Balance", plugin.economyManager.getAccount(player));
        Utils.PlayerData.get(player.getUniqueId()).getConfig().set("Ip", player.getAddress().getHostString());
        Utils.PlayerData.get(player.getUniqueId()).getConfig().set("Group", "default");
        Utils.PlayerData.get(player.getUniqueId()).getConfig().set("Prefix", "");
        Utils.PlayerData.get(player.getUniqueId()).getConfig().set("Suffix", "");
        Utils.PlayerData.get(player.getUniqueId()).getConfig().createSection("Homes");
        Utils.PlayerData.get(player.getUniqueId()).saveConfig();
    }

    @EventHandler
    public void onExit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        CustomConfig Data = new CustomConfig(plugin, "Data/" + player.getUniqueId());
        Data.saveConfig();
    }

}
