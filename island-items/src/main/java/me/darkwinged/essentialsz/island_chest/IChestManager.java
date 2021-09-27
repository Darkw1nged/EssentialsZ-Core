package me.darkwinged.essentialsz.island_chest;

import me.darkwinged.essentialsz.EssentialsZIslandItems;
import me.darkwinged.essentialsz.island_chest.gui.IChest_Menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class IChestManager {

    private static final EssentialsZIslandItems plugin = EssentialsZIslandItems.getInstance;
    public static final Map<UUID, List<IChest_Token>> iChestTokens = new HashMap<>();
    public static Map<UUID, IChest_Menu> iChestActive = new HashMap<>();

    public enum Permissions {
        ICHEST_OVERWRITE("SBItems.IChest.*"),
        ICHEST_CREATE("SBItems.IChest.Create");

        private final String permissionNode;

        Permissions(String permissionNode)
        {
            this.permissionNode = permissionNode;
        }

        public String getPermissionNode()
        {
            return permissionNode;
        }
    }


}
