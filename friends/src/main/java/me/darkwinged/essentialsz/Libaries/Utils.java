package me.darkwinged.essentialsz.Libaries;

import me.darkwinged.essentialsz.EssentialsZFriends;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static final EssentialsZFriends plugin = EssentialsZFriends.getInstance;

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

}
