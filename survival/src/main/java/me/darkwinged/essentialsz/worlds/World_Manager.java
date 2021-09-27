package me.darkwinged.essentialsz.worlds;

import me.darkwinged.essentialsz.EssentialsZSurvival;
import me.darkwinged.essentialsz.worlds.utils.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class World_Manager {

    private final EssentialsZSurvival plugin;
    private final Map<UUID, World> Private_Worlds = new HashMap<>();

    public World_Manager(EssentialsZSurvival plugin) {
        this.plugin = plugin;
        registerEvents();
    }

    public void registerEvents() {

    }



}
