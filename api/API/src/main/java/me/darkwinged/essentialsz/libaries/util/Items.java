package me.darkwinged.essentialsz.libaries.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Items {

    public ItemStack playerHead(Player player) {
        if (Bukkit.getVersion().contains("1.7") || Bukkit.getVersion().contains("1.8") || Bukkit.getVersion().contains("1.9") ||
                Bukkit.getVersion().contains("1.10") || Bukkit.getVersion().contains("1.11")) {
            return me.darkwinged.essentialsz.api.Ver_1_8_8.Items.getPlayerHead(player);
        } else if (Bukkit.getVersion().contains("1.12")) {
            return me.darkwinged.essentialsz.api.Ver_1_12_2.Items.getPlayerHead(player);
        } else if (Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15")) {
            return me.darkwinged.essentialsz.api.Ver_1_12_2.Items.getPlayerHead(player);
        } else if (Bukkit.getVersion().contains("1.16")) {
            return me.darkwinged.essentialsz.api.Ver_1_16_5.Items.getPlayerHead(player);
        } else if (Bukkit.getVersion().contains("1.17")) {
            return me.darkwinged.essentialsz.api.Ver_1_17.Items.getPlayerHead(player);
        }
        return new ItemStack(Material.AIR);
    }

    public boolean isSign(Block block) {
        if (Bukkit.getVersion().contains("1.7") || Bukkit.getVersion().contains("1.8") || Bukkit.getVersion().contains("1.9") ||
                Bukkit.getVersion().contains("1.10") || Bukkit.getVersion().contains("1.11")) {
            return me.darkwinged.essentialsz.api.Ver_1_8_8.Items.isSign(block);
        } else if (Bukkit.getVersion().contains("1.12")) {
            return me.darkwinged.essentialsz.api.Ver_1_12_2.Items.isSign(block);
        } else if (Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15")) {
            return me.darkwinged.essentialsz.api.Ver_1_12_2.Items.isSign(block);
        } else if (Bukkit.getVersion().contains("1.16")) {
            return me.darkwinged.essentialsz.api.Ver_1_16_5.Items.isSign(block);
        } else if (Bukkit.getVersion().contains("1.17")) {
            return me.darkwinged.essentialsz.api.Ver_1_17.Items.isSign(block);
        }
        return false;
    }

}
