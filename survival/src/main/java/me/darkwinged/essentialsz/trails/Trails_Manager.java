package me.darkwinged.essentialsz.trails;

import me.darkwinged.essentialsz.EssentialsZSurvival;
import me.darkwinged.essentialsz.trails.events.CreateTrails_Blocks;
import me.darkwinged.essentialsz.trails.events.CreateTrails_Particles;
import me.darkwinged.essentialsz.trails.utils.Blocks;
import me.darkwinged.essentialsz.trails.utils.Particles;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Trails_Manager {

    private final EssentialsZSurvival plugin;
    public static Map<UUID, Blocks> PlayerTrail_Blocks = new HashMap<>();
    public static Map<UUID, Particles> PlayerTrail_Particles = new HashMap<>();

    public Trails_Manager(EssentialsZSurvival plugin) {
        this.plugin = plugin;
        registerEvents();
    }

    public void registerEvents() {
        plugin.getServer().getPluginManager().registerEvents(new CreateTrails_Blocks(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new CreateTrails_Particles(), plugin);
    }

}
