package me.darkwinged.essentialsz.libaries.ui;

import me.darkwinged.essentialsz.EssentialsZAPI;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;

public class Bossbar {

    private static final EssentialsZAPI plugin = EssentialsZAPI.getInstance;

    public void sendBossbar(Player player, String title, BarColor color, BarStyle style, int length) {
        if (Bukkit.getVersion().contains("1.7") || Bukkit.getVersion().contains("1.8") || Bukkit.getVersion().contains("1.9") ||
                Bukkit.getVersion().contains("1.10") || Bukkit.getVersion().contains("1.11")) {

            me.darkwinged.essentialsz.api.Ver_1_8_8.UI_Manager.sendBossbar(plugin, player, title, length);

        } else if (Bukkit.getVersion().contains("1.12")) {
            me.darkwinged.essentialsz.api.Ver_1_12_2.UI_Manager.sendBossbar(plugin, player, title, color, style, length);

        } else if (Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15")) {
            me.darkwinged.essentialsz.api.Ver_1_12_2.UI_Manager.sendBossbar(plugin, player, title, color, style, length);

        } else if (Bukkit.getVersion().contains("1.16")) {
            me.darkwinged.essentialsz.api.Ver_1_16_5.UI_Manager.sendBossbar(plugin, player, title, color, style, length);

        } else if (Bukkit.getVersion().contains("1.17")) {
            me.darkwinged.essentialsz.api.Ver_1_17.UI_Manager.sendBossbar(plugin, player, title, color, style, length);
        }
    }

    public void sendAllBossbar(Player player, String title, BarColor color, BarStyle style, int length) {
        if (Bukkit.getVersion().contains("1.7") || Bukkit.getVersion().contains("1.8") || Bukkit.getVersion().contains("1.9") ||
                Bukkit.getVersion().contains("1.10") || Bukkit.getVersion().contains("1.11")) {
            me.darkwinged.essentialsz.api.Ver_1_8_8.UI_Manager.sendAllBossbar(plugin, player, title, length);

        } else if (Bukkit.getVersion().contains("1.12")) {
            me.darkwinged.essentialsz.api.Ver_1_12_2.UI_Manager.sendAllBossbar(plugin, player, title, color, style, length);

        } else if (Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15")) {
            me.darkwinged.essentialsz.api.Ver_1_12_2.UI_Manager.sendAllBossbar(plugin, player, title, color, style, length);

        } else if (Bukkit.getVersion().contains("1.16")) {
            me.darkwinged.essentialsz.api.Ver_1_16_5.UI_Manager.sendAllBossbar(plugin, player, title, color, style, length);

        } else if (Bukkit.getVersion().contains("1.17")) {
            me.darkwinged.essentialsz.api.Ver_1_17.UI_Manager.sendAllBossbar(plugin, player, title, color, style, length);
        }
    }

}
