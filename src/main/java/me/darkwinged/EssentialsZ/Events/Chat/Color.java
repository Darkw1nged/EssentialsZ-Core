package me.darkwinged.EssentialsZ.Events.Chat;

import me.darkwinged.EssentialsZ.Main;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Color implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (plugin.getConfig().getBoolean("Chat", true)) {
            if (player.hasPermission(Permissions.ChatColor_black) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&0", "§0");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_dark_blue) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&1", "§1");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_dark_green) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&2", "§2");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_dark_aqua) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&3", "§3");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_dark_red) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&4", "§4");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_dark_purple) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&5", "§5");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_gold) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&6", "§6");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_gray) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&7", "§7");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_dark_gray) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&8", "§8");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_blue) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&9", "§9");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_green) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&a", "§a");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_aqua) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&b", "§b");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_red) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&c", "§c");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_light_purple) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&d", "§d");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_yellow) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&e", "§e");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_white) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&f", "§f");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_magic) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&k", "§k");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_bold) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&l", "§l");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_strikethrough) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&m", "§m");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_underline) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&n", "§n");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_italic) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&o", "§o");
                event.setMessage(Message);
            }
            if (player.hasPermission(Permissions.ChatColor_reset) || player.hasPermission(Permissions.ChatColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String Message = event.getMessage().replaceAll("&r", "§r");
                event.setMessage(Message);
            }

        }
    }

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        if (plugin.getConfig().getBoolean("Chat", true)) {
            Sign s = (Sign) event.getBlock().getState();
            if (event.getPlayer().hasPermission(Permissions.ChatColor) || event.getPlayer().hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, ChatColor.translateAlternateColorCodes('&', event.getLine(0)));
                event.setLine(1, ChatColor.translateAlternateColorCodes('&', event.getLine(1)));
                event.setLine(2, ChatColor.translateAlternateColorCodes('&', event.getLine(2)));
                event.setLine(3, ChatColor.translateAlternateColorCodes('&', event.getLine(3)));
                s.update(true);
            }
        }
    }
}
