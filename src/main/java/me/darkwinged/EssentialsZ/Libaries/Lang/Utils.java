package me.darkwinged.EssentialsZ.Libaries.Lang;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import java.util.*;

public class Utils implements Listener {

    // TEMP
    public static String chat(String string) { return string; }
    public static void Message(CommandSender sender, String string) {}

    // Lists
    public static boolean isChatMuted = false;
    public static List<UUID> staff_chat = new ArrayList<>();
    public static List<String> AutoMessages = new ArrayList<>();
    public static Map<UUID, CustomConfig> PlayerData = new HashMap<>();

    // If players have something toggled
    public static List<UUID> invisible_list = new ArrayList<>();
    public static List<UUID> Autosell_List = new ArrayList<>();
    public static List<UUID> GodMode_List = new ArrayList<>();
    public static List<UUID> Fly_List = new ArrayList<>();
    public static List<UUID> Coords_List = new ArrayList<>();
    public static Map<UUID, Integer> PT_Days = new HashMap<>();
    public static Map<UUID, Integer> PT_Hours = new HashMap<>();
    public static Map<UUID, Integer> PT_Minutes = new HashMap<>();
    public static Map<UUID, Integer> PT_Seconds = new HashMap<>();


}
