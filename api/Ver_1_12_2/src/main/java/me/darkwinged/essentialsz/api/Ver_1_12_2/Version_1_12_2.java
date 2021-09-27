package me.darkwinged.essentialsz.api.Ver_1_12_2;

import org.bukkit.plugin.java.JavaPlugin;

public class Version_1_12_2 extends JavaPlugin {

    public Items items;
    public UI_Manager ui_manager;

    public void onEnable() {

        items = new Items();
        ui_manager = new UI_Manager();
    }
}
