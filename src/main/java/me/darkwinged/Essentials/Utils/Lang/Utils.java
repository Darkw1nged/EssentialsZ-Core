package me.darkwinged.Essentials.Utils.Lang;

import me.darkwinged.Essentials.Main;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.*;

public class Utils implements Listener {

    // REWORK

    private static Main plugin;
    public Utils(Main plugin) { this.plugin = plugin; }

    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
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
    public static List<UUID> Autosell_List = new ArrayList<>();


    // OLD CODE

    public static ArrayList<UUID> invisible_list = new ArrayList<>();
    public static ArrayList<UUID> hide_player_list = new ArrayList<>();
    public static HashMap<UUID, UUID> message_list = new HashMap<>();
    public static HashMap<UUID, Integer> player_infractions = new HashMap<>();


}
