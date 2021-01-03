package me.darkwinged.Essentials.Utils;

import me.darkwinged.Essentials.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Random;

public class TeleportUtils {

    private static Main plugin;
    public TeleportUtils(Main plugin) { this.plugin = plugin; }

    public static HashSet<Material> bad_blocks = new HashSet<>();
    static {
        bad_blocks.add(Material.LAVA);
        bad_blocks.add(Material.FIRE);
        bad_blocks.add(Material.CACTUS);
    }

    public static Location RandomTeleport(Player player) {
        Random random = new Random();
        int x = 0;
        int z = 0;
        int y = 0;

        if (plugin.getConfig().getBoolean("World_Border")) {
            x = random.nextInt(plugin.getConfig().getInt("Border"));
            z = random.nextInt(plugin.getConfig().getInt("Border"));
            y = 150;
        } else if (!plugin.getConfig().getBoolean("World_Border")){
            x = random.nextInt(25000);
            z = random.nextInt(25000);
            y = 150;
        }

        Location randomLocation = new Location(player.getWorld(), x, y, z);
        y = randomLocation.getWorld().getHighestBlockYAt(randomLocation) + 1;
        randomLocation.setY(y);

        return randomLocation;
    }

    public static Location HighestBlockTeleport(Player player) {
        int y = 0;
        Location TopLocation = new Location(player.getWorld(), player.getLocation().getX(), y, player.getLocation().getZ());
        y = TopLocation.getWorld().getHighestBlockYAt(TopLocation) + 1;
        TopLocation.setY(y);

        return TopLocation;
    }

    public static Location findSafeLocationRandom(Player player) {
        Location randomLocation = RandomTeleport(player);
        while (!isLocationSafe(randomLocation)) {
            randomLocation = RandomTeleport(player);
        }
        return randomLocation;
    }

    public static Location findSafeLocationTop(Player player) {
        Location TopLocation = HighestBlockTeleport(player);
        while (!isLocationSafe(TopLocation)) {
            TopLocation = HighestBlockTeleport(player);
        }
        return TopLocation;
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