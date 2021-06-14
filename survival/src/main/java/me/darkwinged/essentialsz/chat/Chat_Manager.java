package me.darkwinged.essentialsz.chat;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class Chat_Manager {

    public static List<UUID> chat_GLOBAL = new ArrayList<>();
    public static Map<UUID, World> chat_WORLD = new HashMap<>();

    public Chat_Manager(Plugin plugin) {

        plugin.getServer().getPluginManager().registerEvents(new ChatTypeEvent(), plugin);
        plugin.getServer().getPluginCommand("chat").setExecutor(new ChatCommand());

    }

    public void onChat(Player player, String message) {
        if (chat_WORLD.containsKey(player.getUniqueId())) {
            for (Player target : chat_WORLD.get(player.getUniqueId()).getPlayers()) {
                if (target != null) {
                    target.sendMessage(message);
                }
            }
            return;
        }
        for (UUID uuid : chat_GLOBAL) {
            Player target = Bukkit.getPlayer(uuid);
            if (target != null) {
                target.sendMessage(message);
            }
        }
    }

}
