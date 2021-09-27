package me.darkwinged.essentialsz.libaries.managers;

import me.darkwinged.essentialsz.EssentialsZFriends;
import me.darkwinged.essentialsz.libaries.storage.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

public class Friend_Manager {

    private static final EssentialsZFriends plugin = EssentialsZFriends.getInstance;
    private static YamlConfiguration MSGConfig = plugin.Messages.getConfig();
    public static Map<UUID, Integer> Request = new HashMap<>();
    public static Map<UUID, UUID> Requests = new HashMap<>();

    public static List<Player> getFriends(Player user) {
        List<Player> friends = new ArrayList<>();

        CustomConfig Data = new CustomConfig(plugin, String.valueOf(user.getUniqueId()), "Data");
        for (String name : Data.getConfig().getStringList("Friends")) {
            if (Bukkit.getPlayer(name).isOnline()) {
                friends.add(Bukkit.getPlayer(name));
            }
        }

        return friends;
    }
    public static void sendRequest(Player player, Player target) {
        Requests.put(target.getUniqueId(), player.getUniqueId());
        Request.put(target.getUniqueId(), plugin.getConfig().getInt("Request Time"));

        player.sendMessage(plugin.essentialsZAPI.utils.chat(MSGConfig.getString("Prefix") +
                MSGConfig.getString("Request"), player, target));

        for (String message : MSGConfig.getStringList("IncomingRequest")) {
            target.sendMessage(plugin.essentialsZAPI.utils.chat(MSGConfig.getString("Prefix") +
                            message.replaceAll("%time%", ""+plugin.getConfig().getInt("Request Time")), player, null));
        }

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            public void run() {
                if (Request.containsKey(target.getUniqueId())) {
                    if (Request.get(target.getUniqueId()) > 0) {
                        Request.put(target.getUniqueId(), Request.get(target.getUniqueId()) - 1);
                    }
                }
            }
        }, 0L, 20L);
    }
    public static boolean isFriends(Player player, Player target) {
        CustomConfig PlayerData = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
        if (PlayerData.getConfig().contains("Current Friends." + target.getUniqueId())) {
            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.Messages.getConfig().getString("Errors.Prefix") +
                    plugin.Messages.getConfig().getString("Errors.AlreadyFriends")));
            return true;
        }
        if (player == target) {
            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.Messages.getConfig().getString("Errors.Prefix") +
                    plugin.Messages.getConfig().getString("Errors.FriendYourself")));
            return true;
        }
        return false;
    }

    public static void addFriend(Player player, Player target) {

    }

}
