package me.darkwinged.essentialsz;

import me.darkwinged.EssentialsZ2FA.MetricsLite;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class Main extends JavaPlugin implements Listener {

    public EssentialsZAPI essentialsZAPI = (EssentialsZAPI) Bukkit.getServer().getPluginManager().getPlugin("EssentialsZAPI");
    List<UUID> NotLoggedIn = new ArrayList<>();
    List<UUID> NewLogin = new ArrayList<>();

    public void onEnable() {
        getServer().getConsoleSender().sendMessage(essentialsZAPI.utils.chat("&aEssentialsZ 2FA has been enabled."));
        MetricsLite metricsLite = new MetricsLite(this, 9811);
        loadConfig();
        getServer().getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {
        getServer().getConsoleSender().sendMessage(essentialsZAPI.utils.chat("&cEssentialsZ 2FA has been disabled."));
        saveConfig();
    }

    private FileConfiguration config;
    private File cfile;
    public void loadConfig() {
        config = getConfig();
        config.options().copyDefaults(true);
        saveDefaultConfig();
        cfile = new File(getDataFolder(), "config.yml");
    }

    public Optional<Block> getNextBlockUnderPlayer(Player player) {
        Location loc = player.getLocation();
        Block block;
        while(loc.getY() >= 0) {
            loc.subtract(0, 0.5, 0);
            block = loc.getBlock();
            if(block.getType() != Material.AIR) {
                return Optional.of(block);
            }
        }
        return Optional.empty();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("EssentialsZ.2FA.Staff")) {
            reloadConfig();
            if (!player.hasPermission("EssentialsZ.2FA.Required")) return;

            Location loc = player.getLocation();
            Location blockUnderPlayer = new Location(player.getWorld(), loc.getX(), loc.getY()-1, loc.getZ());

            if (blockUnderPlayer.getBlock().getType().equals(Material.AIR)) {
                Location oldLoc = getNextBlockUnderPlayer(player).get().getLocation();
                Location newLoc = new Location(oldLoc.getWorld(), oldLoc.getX(), oldLoc.getY() + 1, oldLoc.getZ());

                player.teleport(newLoc);
            }
            if (!getConfig().contains("Data." + player.getUniqueId())) {
                NewLogin.add(player.getUniqueId());
                player.sendMessage(essentialsZAPI.utils.chat("&aSince you are staff. You will need to create a password."));
                return;
            }
            player.sendMessage(essentialsZAPI.utils.chat("&aSince you are staff. Please enter your password."));
            NotLoggedIn.add(player.getUniqueId());
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (NotLoggedIn.contains(player.getUniqueId())) event.setCancelled(true);
        if (NewLogin.contains(player.getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (NewLogin.contains(player.getUniqueId())) {
            String[] message = event.getMessage().split(" "); //splits via space
            String pass = message[0];
            getConfig().set("Data." + player.getUniqueId() + ".username", player.getName());
            getConfig().set("Data." + player.getUniqueId() + ".password", pass);
            if (player.hasPermission("EssentialsZ.2FA.Required")) {
                getConfig().set("Data." + player.getUniqueId() + ".required", true);
            } else {
                getConfig().set("Data." + player.getUniqueId() + ".required", false);
            }
            NewLogin.remove(player.getUniqueId());
            player.sendMessage(essentialsZAPI.utils.chat("&aSuccess! Your new password is " + pass));
            event.setCancelled(true);
            saveConfig();
            return;
        }

        if (NotLoggedIn.contains(player.getUniqueId())) {
            if (event.getMessage().equals(getConfig().getString("Data." + player.getUniqueId() + ".password"))) {
                NotLoggedIn.remove(player.getUniqueId());
                player.sendMessage(essentialsZAPI.utils.chat("&aSuccess! You can now play!"));
            } else {
                player.sendMessage(essentialsZAPI.utils.chat("&cTry again. If you are having trouble message an administrator."));
            }
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (NotLoggedIn.contains(player.getUniqueId())) event.setCancelled(true);
        if (NewLogin.contains(player.getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (NotLoggedIn.contains(player.getUniqueId())) event.setCancelled(true);
        if (NewLogin.contains(player.getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (NotLoggedIn.contains(player.getUniqueId())) event.setCancelled(true);
        if (NewLogin.contains(player.getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (NotLoggedIn.contains(player.getUniqueId())) event.setCancelled(true);
        if (NewLogin.contains(player.getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        Player player = (Player)event.getPlayer();
        if (NotLoggedIn.contains(player.getUniqueId())) event.setCancelled(true);
        if (NewLogin.contains(player.getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if (NotLoggedIn.contains(player.getUniqueId())) event.setCancelled(true);
        if (NewLogin.contains(player.getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (NotLoggedIn.contains(player.getUniqueId())) event.setCancelled(true);
        if (NewLogin.contains(player.getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        if (NotLoggedIn.contains(player.getUniqueId())) event.setCancelled(true);
        if (NewLogin.contains(player.getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        NewLogin.remove(player.getUniqueId());
        NotLoggedIn.remove(player.getUniqueId());
    }

}
