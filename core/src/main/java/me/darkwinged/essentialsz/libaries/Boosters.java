package me.darkwinged.essentialsz.libaries;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Boosters {

    private final Map<UUID, Integer> money;

    public Boosters() {
        money = new HashMap<>();
    }

    public void addMoneyBoosters(Player player, Integer amount) {
        money.put(player.getUniqueId(), getMoneyBoosters(player) + amount);
    }

    public void removeMoneyBoosters(Player player, Integer amount) {
        if (getMoneyBoosters(player) < amount) {
            money.put(player.getUniqueId(), 0);
            return;
        }
        money.put(player.getUniqueId(), getMoneyBoosters(player) - amount);
    }

    public void removePlayer(Player player) {
        money.remove(player.getUniqueId());
    }

    public Integer getMoneyBoosters(Player player) {
        return money.get(player.getUniqueId());
    }

}
