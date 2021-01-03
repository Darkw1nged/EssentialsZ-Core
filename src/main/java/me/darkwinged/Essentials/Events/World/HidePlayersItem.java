package me.darkwinged.Essentials.Events.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class HidePlayersItem implements Listener {

    private Main plugin;
    public HidePlayersItem(Main plugin) {
        this.plugin = plugin;
    }

    public List<String> getConvertedLore(String path) {
        List<String> oldList = plugin.getConfig().getStringList(path + ".lore");
        List<String> newList = new ArrayList<>();
        for (String a : oldList)
            newList.add(ChatColor.translateAlternateColorCodes('&', a));
        return newList;
    }

    public ItemStack HidePlayersItem() {
        ItemStack item = new ItemStack(Material.getMaterial(plugin.getConfig().getString("Hide Players.item_Shown.item")));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.chat(plugin.getConfig().getString("Hide Players.item_Shown.name")));
        meta.setLore(getConvertedLore("Hide Players.item_Shown"));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack ShowPlayersItem() {
        ItemStack item = new ItemStack(Material.getMaterial(plugin.getConfig().getString("Hide Players.item_Hidden.item")));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.chat(plugin.getConfig().getString("Hide Players.item_Hidden.name")));
        meta.setLore(getConvertedLore("Hide Players.item_Hidden"));
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (plugin.getConfig().getBoolean("Hide Players.enable", true)) {
            int slot = plugin.getConfig().getInt("Hide Players.slot");
            if (Utils.hide_player_list.contains(player.getUniqueId())) {
                player.getInventory().setItem(slot, ShowPlayersItem());
            } else {
                player.getInventory().setItem(slot, HidePlayersItem());
            }
        } else {
            return;
        }
    }

    @EventHandler
    public void HideAllPlayers(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        int slot = plugin.getConfig().getInt("Hide Players.slot");
        if (plugin.getConfig().getBoolean("Hide Players.enable", true)) {
            if (player.getItemInHand().equals(HidePlayersItem())) {
                if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
                    player.getInventory().setItem(slot, ShowPlayersItem());
                    Utils.hide_player_list.add(player.getUniqueId());
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        player.hidePlayer(online);
                    }
                }
            } else if (player.getItemInHand().equals(ShowPlayersItem())) {
                if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (Utils.hide_player_list.contains(player.getUniqueId())) {
                        player.getInventory().setItem(slot, HidePlayersItem());
                        Utils.hide_player_list.remove(player.getUniqueId());
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            player.showPlayer(online);
                        }
                    }

                }
            }
        }
    }


}
