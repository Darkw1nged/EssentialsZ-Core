package me.darkwinged.Essentials.Utils.Lang;

import me.darkwinged.Essentials.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Utils implements Listener {

    // REWORK

    private static Main plugin;
    public Utils(Main plugin) { this.plugin = plugin; }

    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    // Lists
    public static boolean isChatMuted = false;
    public static ArrayList<UUID> staff_chat = new ArrayList<>();
    public static HashMap<String, ItemStack> MoneyPouches = new HashMap<>();
    public static HashMap<String, Integer> MoneyPouches_max = new HashMap<>();
    public static HashMap<String, Integer> MoneyPouches_min = new HashMap<>();
    public static ArrayList<String> AutoMessages = new ArrayList<>();


    // OLD CODE

    public static ArrayList<UUID> invisible_list = new ArrayList<>();
    public static ArrayList<UUID> hide_player_list = new ArrayList<>();
    public static HashMap<UUID, UUID> message_list = new HashMap<>();
    public static HashMap<UUID, Integer> player_infractions = new HashMap<>();

    public static FileConfiguration config;
    public static File cfile;
    public static FileConfiguration griefcheck;
    public static File griefcheckfile;
    public static FileConfiguration sidebar;
    public static File sidebarfile;

    // anti grief File =========================================================
    public static void LoadGriefCheckFile() {
        griefcheckfile = new File(plugin.getDataFolder(), "grieflog.yml");
        griefcheck = YamlConfiguration.loadConfiguration(griefcheckfile);
        saveGriefCheck();
    }

    public static void saveGriefCheck() {
        try {
            griefcheck.save(griefcheckfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File getGriefCheckfile() {
        return griefcheckfile;
    }

    public static FileConfiguration getFriefCheck() {
        return griefcheck;
    }

    // SideBar File ============================================================
    public static void LoadSideBarFile() {
        sidebarfile = new File(plugin.getDataFolder(), "Sidebar.yml");
        sidebar = YamlConfiguration.loadConfiguration(sidebarfile);
        setSideBar();
        saveSideBar();
    }

    public static void setSideBar() {
        if (Utils.sidebarfile.exists()) {
            return;
        } else {
            getSideBar().set("Sidebar.name", "&f&lMy Server");
            saveSideBar();
        }

    }

    public static void saveSideBar() {
        try {
            Utils.sidebar.save(Utils.sidebarfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File getSideBarfile() {
        return sidebarfile;
    }

    public static FileConfiguration getSideBar() {
        return sidebar;
    }
    // ====================================================================


}
