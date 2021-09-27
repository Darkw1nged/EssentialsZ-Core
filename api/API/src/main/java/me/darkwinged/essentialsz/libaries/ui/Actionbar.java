package me.darkwinged.essentialsz.libaries.ui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Actionbar {

    public void sendActionbar(Player player, String message) {
        if (Bukkit.getVersion().contains("1.12")) {
            me.darkwinged.essentialsz.api.Ver_1_12_2.UI_Manager.sendActionBar(player, message);

        } else if (Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15")) {
            me.darkwinged.essentialsz.api.Ver_1_12_2.UI_Manager.sendActionBar(player, message);

        } else if (Bukkit.getVersion().contains("1.16")) {
            me.darkwinged.essentialsz.api.Ver_1_16_5.UI_Manager.sendActionBar(player, message);

        } else if (Bukkit.getVersion().contains("1.17")) {
            me.darkwinged.essentialsz.api.Ver_1_17.UI_Manager.sendActionBar(player, message);
        }
    }

    public void sendAllActionbar(String message) {
        if (Bukkit.getVersion().contains("1.12")) {
            me.darkwinged.essentialsz.api.Ver_1_12_2.UI_Manager.sendAllActionBar(message);

        } else if (Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15")) {
            me.darkwinged.essentialsz.api.Ver_1_12_2.UI_Manager.sendAllActionBar(message);

        } else if (Bukkit.getVersion().contains("1.16")) {
            me.darkwinged.essentialsz.api.Ver_1_16_5.UI_Manager.sendAllActionBar(message);

        } else if (Bukkit.getVersion().contains("1.17")) {
            me.darkwinged.essentialsz.api.Ver_1_17.UI_Manager.sendAllActionBar(message);
        }
    }

}
