package me.darkwinged.essentialsz.miscellaneous.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Random;

public class TeleportUtils {

    public static HashSet<Material> bad_blocks = new HashSet<>();
    static {
        bad_blocks.add(Material.LAVA);
        bad_blocks.add(Material.FIRE);
        bad_blocks.add(Material.CACTUS);
    }

    public static Location RandomTeleport(Player player) {
        Random random = new Random();
        int x = random.nextInt(25000);
        int z = random.nextInt(25000);
        int y = 150;

        Location randomLocation = new Location(player.getWorld(), x, y, z);
        y = randomLocation.getWorld().getHighestBlockYAt(randomLocation) + 1;
        randomLocation.setY(y);

        return randomLocation;
    }

    public static Location findSafeLocationRandom(Player player) {
        Location randomLocation = RandomTeleport(player);
        while (!isLocationSafe(randomLocation)) {
            randomLocation = RandomTeleport(player);
        }
        return randomLocation;
    }

    public static boolean isLocationSafe(Location location) {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        Block block = location.getWorld().getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);
        return !(bad_blocks.contains(below.getType())) || (block.getType().isSolid()) || (above.getType().isSolid());
    }

}