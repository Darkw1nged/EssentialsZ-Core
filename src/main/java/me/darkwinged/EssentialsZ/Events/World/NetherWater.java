package me.darkwinged.EssentialsZ.Events.World;

import me.darkwinged.EssentialsZ.Main;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

public class NetherWater implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void onWaterPlace(PlayerBucketEmptyEvent event) {
        if (plugin.getConfig().getBoolean("World Events.Nether Water", true)) {
            Block block = event.getBlockClicked();
            if (block.getWorld().getEnvironment().equals(World.Environment.NETHER) && event.getBucket().equals(Material.WATER_BUCKET)) {
                block.setType(Material.WATER);
            }
        }
    }

}
