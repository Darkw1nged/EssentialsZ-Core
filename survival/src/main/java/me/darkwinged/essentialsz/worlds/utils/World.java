package me.darkwinged.essentialsz.worlds.utils;

import org.bukkit.Difficulty;
import org.bukkit.WeatherType;

import java.util.UUID;

public class World {

    private final String name;
    private final Integer maxPlayers;
    private final String permission;
    private final UUID owner;
    private final Boolean isHardcore;
    private final WeatherType weatherType;
    private final Difficulty difficulty;

    public World(String name, Integer maxPlayers, String permission, UUID owner, Boolean isHardcore, WeatherType weatherType, Difficulty difficulty) {
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.permission = permission;
        this.owner = owner;
        this.isHardcore = isHardcore;
        this.weatherType = weatherType;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public String getPermission() {
        return permission;
    }

    public UUID getOwner() {
        return owner;
    }

    public Boolean getIsHardcore() {
        return isHardcore;
    }

    public WeatherType getWeatherType() {
        return weatherType;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
