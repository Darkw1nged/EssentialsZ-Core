package me.darkwinged.essentialsz.Events;

import me.darkwinged.essentialsz.EssentialsZFriends;
import me.darkwinged.essentialsz.Libaries.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class NotifyFriend implements Listener {

    private final EssentialsZFriends plugin = EssentialsZFriends.getInstance;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        for (Player friends : Utils.getFriends(player.getPlayer())) {
            friends.sendMessage(plugin.essentialsZAPI.utils.chat("&a[+] " + player.getDisplayName()));
        }
    }

    @EventHandler
    public void onJoin(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        for (Player friends : Utils.getFriends(player.getPlayer())) {
            friends.sendMessage(plugin.essentialsZAPI.utils.chat("&c[-] " + player.getDisplayName()));
        }
    }

}
