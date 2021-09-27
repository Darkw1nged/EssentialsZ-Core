package me.darkwinged.essentialsz.api.Ver_1_8_8;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Items {

    public static ItemStack getPlayerHead(Player player) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(player.getName());
        meta.setDisplayName(player.getName());
        skull.setItemMeta(meta);
        return skull;
    }

    public static boolean isSign(Block block) {
        switch (block.getType()) {
            case SIGN:
            case SIGN_POST:
            case WALL_SIGN:
                return true;
        }
        return false;
    }


}
