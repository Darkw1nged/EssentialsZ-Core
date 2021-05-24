package me.darkwinged.EssentialsZ.Libaries;

import me.darkwinged.EssentialsZ.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;

public class TeleportUtils {

    private final static Main plugin = Main.getInstance;

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
        while (!plugin.essentialsZAPI.utils.isLocationSafe(randomLocation)) {
            randomLocation = RandomTeleport(player);
        }
        return randomLocation;
    }

    public static Location findSafeLocationTop(Player player) {
        Location TopLocation = HighestBlockTeleport(player);
        while (!plugin.essentialsZAPI.utils.isLocationSafe(TopLocation)) {
            TopLocation = HighestBlockTeleport(player);
        }
        return TopLocation;
    }

}