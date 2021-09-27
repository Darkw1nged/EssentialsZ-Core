package me.darkwinged.essentialsz.libaries.lang;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.storage.CustomConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.*;

public class Utils implements Listener {

    private static final Main plugin = Main.getInstance;

    // TEMP
    public static String chat(String string) { return plugin.essentialsZAPI.utils.chat(string, null, null, null, false); }

    public static CustomConfig getDataFile(Player player) {
        return new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
    }

    // Lists
    public static boolean isChatMuted = false;
    public static List<UUID> staff_chat = new ArrayList<>();

    // If players have something toggled
    public static List<UUID> Autosell_List = new ArrayList<>();
    public static List<UUID> GodMode_List = new ArrayList<>();
    public static List<UUID> Fly_List = new ArrayList<>();
    public static List<UUID> Coords_List = new ArrayList<>();
    public static Map<UUID, Integer> PT_Days = new HashMap<>();
    public static Map<UUID, Integer> PT_Hours = new HashMap<>();
    public static Map<UUID, Integer> PT_Minutes = new HashMap<>();
    public static Map<UUID, Integer> PT_Seconds = new HashMap<>();


}
