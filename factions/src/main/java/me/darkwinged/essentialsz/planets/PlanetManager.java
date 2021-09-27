package me.darkwinged.essentialsz.planets;

import me.darkwinged.essentialsz.storage.users.Permissions;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class PlanetManager {

    public static boolean isUserSafe(Player player) {
        World world = player.getWorld();
        String name = world.getName();
        if (player.hasPermission(Permissions.ADMIN_BYPASS.getPermissionNode())) return false;
        return name.contains("marketplace") || name.contains("warzone") || name.contains("seabed");
    }

}
