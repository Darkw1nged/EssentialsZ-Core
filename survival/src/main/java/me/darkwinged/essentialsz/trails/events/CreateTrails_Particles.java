package me.darkwinged.essentialsz.trails.events;

import me.darkwinged.essentialsz.EssentialsZSurvival;
import me.darkwinged.essentialsz.trails.Trails_Manager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.material.MaterialData;

import java.util.UUID;

public class CreateTrails_Particles implements Listener {

    private final EssentialsZSurvival plugin = EssentialsZSurvival.getInstance;

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Location loc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 1, player.getLocation().getZ());
        if (!Trails_Manager.PlayerTrail_Particles.containsKey(uuid)) return;

        switch (Trails_Manager.PlayerTrail_Particles.get(uuid)) {
            case LAVA:
                player.getWorld().spawnParticle(Particle.DRIP_LAVA, loc, 2);
                break;
            case WATER:
                player.getWorld().spawnParticle(Particle.DRIP_WATER, loc, 2);
                break;
            case SNOW:
                player.getWorld().spawnParticle(Particle.SNOWBALL, loc, 2);
                break;
            case SLIME:
                player.getWorld().spawnParticle(Particle.SLIME, loc, 2);
                break;
            case PORTAL:
                player.getWorld().spawnParticle(Particle.PORTAL, loc, 2);
                break;
            case DAYLIGHT:
                player.getWorld().spawnParticle(Particle.BLOCK_CRACK, loc, 2, new MaterialData(Material.DAYLIGHT_DETECTOR));
                break;
            case ENCHANTING:
                player.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, loc, 2);
                break;
            case RAINBOW_WOOL:
                player.getWorld().spawnParticle(Particle.BLOCK_CRACK, loc, 2, new MaterialData(Material.BLUE_WOOL));
                player.getWorld().spawnParticle(Particle.BLOCK_CRACK, loc, 2, new MaterialData(Material.CYAN_WOOL));
                player.getWorld().spawnParticle(Particle.BLOCK_CRACK, loc, 2, new MaterialData(Material.GREEN_WOOL));
                player.getWorld().spawnParticle(Particle.BLOCK_CRACK, loc, 2, new MaterialData(Material.LIME_WOOL));
                player.getWorld().spawnParticle(Particle.BLOCK_CRACK, loc, 2, new MaterialData(Material.MAGENTA_WOOL));
                player.getWorld().spawnParticle(Particle.BLOCK_CRACK, loc, 2, new MaterialData(Material.PINK_WOOL));
                player.getWorld().spawnParticle(Particle.BLOCK_CRACK, loc, 2, new MaterialData(Material.ORANGE_WOOL));
                player.getWorld().spawnParticle(Particle.BLOCK_CRACK, loc, 2, new MaterialData(Material.RED_WOOL));
                break;
            case ANGRY_VILLAGER:
                player.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, loc, 2);
                break;
        }
    }
}
