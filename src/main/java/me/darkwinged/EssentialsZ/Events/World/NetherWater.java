package me.darkwinged.EssentialsZ.Events.World;

import me.darkwinged.EssentialsZ.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

public class NetherWater implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void onWaterPlace(PlayerBucketEmptyEvent event) {
        Player player = event.getPlayer();
        if (plugin.getConfig().getBoolean("World Events.Nether Water", true)) {
            Block block = event.getBlockClicked();
            if (block.getWorld().getEnvironment().equals(World.Environment.NETHER) && event.getBucket().equals(Material.WATER_BUCKET)) {
                int x = block.getX();
                int z = block.getZ();
                int y = block.getY();

                Location loc_top = new Location(block.getWorld(), x, y + 1, z);
                Location loc_bottom = new Location(block.getWorld(), x, y - 1, z);
                Location loc_north = new Location(block.getWorld(), x, y, z - 1);
                Location loc_south = new Location(block.getWorld(), x, y, z + 1);
                Location loc_east = new Location(block.getWorld(), x + 1, y, z);
                Location loc_west = new Location(block.getWorld(), x - 1, y, z);

                switch (event.getBlockFace()) {
                    case UP:
                        if (loc_top.getBlock().getType().equals(Material.AIR) && !loc_top.getBlock().getType().equals(Material.WATER)) {
                            loc_top.getBlock().setType(Material.WATER);
                        }
                        break;
                    case DOWN:
                        if (loc_bottom.getBlock().getType().equals(Material.AIR) && !loc_bottom.getBlock().getType().equals(Material.WATER)) {
                            loc_bottom.getBlock().setType(Material.WATER);
                        }
                        break;
                    case NORTH:
                        if (loc_north.getBlock().getType().equals(Material.AIR) && !loc_north.getBlock().getType().equals(Material.WATER)) {
                            loc_north.getBlock().setType(Material.WATER);
                        }
                        break;
                    case EAST:
                        if (loc_east.getBlock().getType().equals(Material.AIR) && !loc_east.getBlock().getType().equals(Material.WATER)) {
                            loc_east.getBlock().setType(Material.WATER);
                        }
                        break;
                    case SOUTH:
                        if (loc_south.getBlock().getType().equals(Material.AIR) && !loc_south.getBlock().getType().equals(Material.WATER)) {
                            loc_south.getBlock().setType(Material.WATER);
                        }
                        break;
                    case WEST:
                        if (loc_west.getBlock().getType().equals(Material.AIR) && !loc_west.getBlock().getType().equals(Material.WATER)) {
                            loc_west.getBlock().setType(Material.WATER);
                        }
                        break;
                }
            }
        }
    }

}
