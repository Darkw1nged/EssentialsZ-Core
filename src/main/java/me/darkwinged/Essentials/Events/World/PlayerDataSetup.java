package me.darkwinged.Essentials.Events.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.EssentialsZEconomy.EconomyManager;
import me.darkwinged.Essentials.Utils.Lang.CustomConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerDataSetup implements Listener {

    private Main plugin;
    public PlayerDataSetup(Main plugin) { this.plugin = plugin; }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CustomConfig Data = new CustomConfig(plugin, "Data/" + player.getName());
        Data.saveDefaultConfig();
        Data.getConfig().set("Player Name", player.getName());
        Data.getConfig().set("Balance", EconomyManager.getAccount(player));
        Data.getConfig().set("Ip", player.getAddress().getHostString());
        Data.getConfig().createSection("Homes");
        Data.saveConfig();

    }

    @EventHandler
    public void onExit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        CustomConfig Data = new CustomConfig(plugin, "Data/" + player.getName());
        Data.saveConfig();
    }

}
