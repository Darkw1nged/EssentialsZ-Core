package me.darkwinged.essentialsz.libaries.ui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Title {

    public void sendTitle(Player player, String title, String subtitle) {
        if (Bukkit.getVersion().contains("1.7") || Bukkit.getVersion().contains("1.8") || Bukkit.getVersion().contains("1.9") ||
                Bukkit.getVersion().contains("1.10") || Bukkit.getVersion().contains("1.11")) {

            me.darkwinged.essentialsz.api.Ver_1_8_8.UI_Manager.sendTitle(player, title, subtitle);

        } else if (Bukkit.getVersion().contains("1.12")) {
            me.darkwinged.essentialsz.api.Ver_1_12_2.UI_Manager.sendTitle(player, title, subtitle);

        } else if (Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15")) {
            me.darkwinged.essentialsz.api.Ver_1_12_2.UI_Manager.sendTitle(player, title, subtitle);

        } else if (Bukkit.getVersion().contains("1.16")) {
            me.darkwinged.essentialsz.api.Ver_1_16_5.UI_Manager.sendTitle(player, title, subtitle);

        } else if (Bukkit.getVersion().contains("1.17")) {
            me.darkwinged.essentialsz.api.Ver_1_17.UI_Manager.sendTitle(player, title, subtitle);
        }
    }

    public void sendAllTitle(String title, String subtitle) {
        if (Bukkit.getVersion().contains("1.7") || Bukkit.getVersion().contains("1.8") || Bukkit.getVersion().contains("1.9") ||
                Bukkit.getVersion().contains("1.10") || Bukkit.getVersion().contains("1.11")) {

            me.darkwinged.essentialsz.api.Ver_1_8_8.UI_Manager.sendAllTitle(title, subtitle);

        } else if (Bukkit.getVersion().contains("1.12")) {
            me.darkwinged.essentialsz.api.Ver_1_12_2.UI_Manager.sendAllTitle(title, subtitle);

        } else if (Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15")) {
            me.darkwinged.essentialsz.api.Ver_1_12_2.UI_Manager.sendAllTitle(title, subtitle);

        } else if (Bukkit.getVersion().contains("1.16")) {
            me.darkwinged.essentialsz.api.Ver_1_16_5.UI_Manager.sendAllTitle(title, subtitle);

        } else if (Bukkit.getVersion().contains("1.17")) {
            me.darkwinged.essentialsz.api.Ver_1_17.UI_Manager.sendAllTitle(title, subtitle);
        }
    }

    public void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        if (Bukkit.getVersion().contains("1.12")) {
            me.darkwinged.essentialsz.api.Ver_1_12_2.UI_Manager.sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);

        } else if (Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15")) {
            me.darkwinged.essentialsz.api.Ver_1_12_2.UI_Manager.sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);

        } else if (Bukkit.getVersion().contains("1.16")) {
            me.darkwinged.essentialsz.api.Ver_1_16_5.UI_Manager.sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);

        } else if (Bukkit.getVersion().contains("1.17")) {
            me.darkwinged.essentialsz.api.Ver_1_17.UI_Manager.sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);
        }
    }

    public void sendAllTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        if (Bukkit.getVersion().contains("1.12")) {
            me.darkwinged.essentialsz.api.Ver_1_12_2.UI_Manager.sendAllTitle(title, subtitle, fadeIn, stay, fadeOut);

        } else if (Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15")) {
            me.darkwinged.essentialsz.api.Ver_1_12_2.UI_Manager.sendAllTitle(title, subtitle, fadeIn, stay, fadeOut);

        } else if (Bukkit.getVersion().contains("1.16")) {
            me.darkwinged.essentialsz.api.Ver_1_16_5.UI_Manager.sendAllTitle(title, subtitle, fadeIn, stay, fadeOut);

        } else if (Bukkit.getVersion().contains("1.17")) {
            me.darkwinged.essentialsz.api.Ver_1_17.UI_Manager.sendAllTitle(title, subtitle, fadeIn, stay, fadeOut);
        }
    }

}
