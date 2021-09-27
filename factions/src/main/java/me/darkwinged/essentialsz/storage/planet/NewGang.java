package me.darkwinged.essentialsz.storage.planet;

import me.darkwinged.essentialsz.EssentialsZFactions;
import me.darkwinged.essentialsz.libaries.storage.CustomConfig;

import java.util.List;
import java.util.UUID;

public class NewGang {

    private final EssentialsZFactions plugin = EssentialsZFactions.getInstance;
    private final String gangName;
    private final UUID owner;
    private final List<UUID> members;
    private final PlanetTypes planet;

    public NewGang(String gangName, UUID owner, List<UUID> members, PlanetTypes planet) {
        this.gangName = gangName;
        this.owner = owner;
        this.members = members;
        this.planet = planet;

        setupPlanet(planet);
    }

    private void setupPlanet(PlanetTypes type) {

    }

    private void saveData() {
        CustomConfig data = new CustomConfig(plugin, gangName, "Data");
    }

}
