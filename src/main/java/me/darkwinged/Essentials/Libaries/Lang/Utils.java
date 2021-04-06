package me.darkwinged.Essentials.Libaries.Lang;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Utils implements Listener {

    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s)
                .replaceAll("%n", "\n");
    }

    public static void Message(CommandSender sender, String string) {
        try {
            sender.sendMessage(Utils.chat(string));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // Lists
    public static boolean isChatMuted = false;
    public static List<UUID> staff_chat = new ArrayList<>();
    public static Map<String, ItemStack> MoneyPouches = new HashMap<>();
    public static Map<String, Integer> MoneyPouches_max = new HashMap<>();
    public static Map<String, Integer> MoneyPouches_min = new HashMap<>();
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