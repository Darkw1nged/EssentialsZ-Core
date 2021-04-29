package me.darkwinged.EssentialsZ.Events.Chat;

import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Main;
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
        if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
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
        if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
            Sign sign = (Sign) event.getBlock().getState();
            Player player = event.getPlayer();
            String Line_1 = event.getLine(0);
            String Line_2 = event.getLine(1);
            String Line_3 = event.getLine(2);
            String Line_4 = event.getLine(3);

            if (player.hasPermission(Permissions.SignColor_black) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, ChatColor.translateAlternateColorCodes('&', Line_1));
                event.setLine(1, event.getLine(1).replaceAll("&0", "§0"));
                event.setLine(2, event.getLine(2).replaceAll("&0", "§0"));
                event.setLine(3, event.getLine(3).replaceAll("&0", "§0"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_dark_blue) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&2", "§1"));
                event.setLine(1, Line_2.replaceAll("&2", "§1"));
                event.setLine(2, Line_3.replaceAll("&2", "§1"));
                event.setLine(3, Line_4.replaceAll("&2", "§1"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_dark_green) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&2", "§2"));
                event.setLine(1, Line_2.replaceAll("&2", "§2"));
                event.setLine(2, Line_3.replaceAll("&2", "§2"));
                event.setLine(3, Line_4.replaceAll("&2", "§2"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_dark_aqua) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&3", "§3"));
                event.setLine(1, Line_2.replaceAll("&3", "§3"));
                event.setLine(2, Line_3.replaceAll("&3", "§3"));
                event.setLine(3, Line_4.replaceAll("&3", "§3"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_dark_red) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&4", "§4"));
                event.setLine(1, Line_2.replaceAll("&4", "§4"));
                event.setLine(2, Line_3.replaceAll("&4", "§4"));
                event.setLine(3, Line_4.replaceAll("&4", "§4"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_dark_purple) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&5", "§5"));
                event.setLine(1, Line_2.replaceAll("&5", "§5"));
                event.setLine(2, Line_3.replaceAll("&5", "§5"));
                event.setLine(3, Line_4.replaceAll("&5", "§5"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_gold) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&6", "§6"));
                event.setLine(1, Line_2.replaceAll("&6", "§6"));
                event.setLine(2, Line_3.replaceAll("&6", "§6"));
                event.setLine(3, Line_4.replaceAll("&6", "§6"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_gray) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&7", "§7"));
                event.setLine(1, Line_2.replaceAll("&7", "§7"));
                event.setLine(2, Line_3.replaceAll("&7", "§7"));
                event.setLine(3, Line_4.replaceAll("&7", "§7"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_dark_gray) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&8", "§8"));
                event.setLine(1, Line_2.replaceAll("&8", "§8"));
                event.setLine(2, Line_3.replaceAll("&8", "§8"));
                event.setLine(3, Line_4.replaceAll("&8", "§8"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_blue) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&9", "§9"));
                event.setLine(1, Line_2.replaceAll("&9", "§9"));
                event.setLine(2, Line_3.replaceAll("&9", "§9"));
                event.setLine(3, Line_4.replaceAll("&9", "§9"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_green) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&a", "§a"));
                event.setLine(1, Line_2.replaceAll("&a", "§a"));
                event.setLine(2, Line_3.replaceAll("&a", "§a"));
                event.setLine(3, Line_4.replaceAll("&a", "§a"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_aqua) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&b", "§b"));
                event.setLine(1, Line_2.replaceAll("&b", "§b"));
                event.setLine(2, Line_3.replaceAll("&b", "§b"));
                event.setLine(3, Line_4.replaceAll("&b", "§b"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_red) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&c", "§c"));
                event.setLine(1, Line_2.replaceAll("&c", "§c"));
                event.setLine(2, Line_3.replaceAll("&c", "§c"));
                event.setLine(3, Line_4.replaceAll("&c", "§c"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_light_purple) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&d", "§d"));
                event.setLine(1, Line_2.replaceAll("&d", "§d"));
                event.setLine(2, Line_3.replaceAll("&d", "§d"));
                event.setLine(3, Line_4.replaceAll("&d", "§d"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_yellow) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&e", "§e"));
                event.setLine(1, Line_2.replaceAll("&e", "§e"));
                event.setLine(2, Line_3.replaceAll("&e", "§e"));
                event.setLine(3, Line_4.replaceAll("&e", "§e"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_white) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&f", "§f"));
                event.setLine(1, Line_2.replaceAll("&f", "§f"));
                event.setLine(2, Line_3.replaceAll("&f", "§f"));
                event.setLine(3, Line_4.replaceAll("&f", "§f"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_magic) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&k", "§k"));
                event.setLine(1, Line_2.replaceAll("&k", "§k"));
                event.setLine(2, Line_3.replaceAll("&k", "§k"));
                event.setLine(3, Line_4.replaceAll("&k", "§k"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_bold) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&l", "§l"));
                event.setLine(1, Line_2.replaceAll("&l", "§l"));
                event.setLine(2, Line_3.replaceAll("&l", "§l"));
                event.setLine(3, Line_4.replaceAll("&l", "§l"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_strikethrough) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&m", "§m"));
                event.setLine(1, Line_2.replaceAll("&m", "§m"));
                event.setLine(2, Line_3.replaceAll("&m", "§m"));
                event.setLine(3, Line_4.replaceAll("&m", "§m"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_underline) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&n", "§n"));
                event.setLine(1, Line_2.replaceAll("&n", "§n"));
                event.setLine(2, Line_3.replaceAll("&n", "§n"));
                event.setLine(3, Line_4.replaceAll("&n", "§n"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_italic) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&o", "§o"));
                event.setLine(1, Line_2.replaceAll("&o", "§o"));
                event.setLine(2, Line_3.replaceAll("&o", "§o"));
                event.setLine(3, Line_4.replaceAll("&o", "§o"));
                sign.update(true);
            }
            if (player.hasPermission(Permissions.SignColor_reset) || player.hasPermission(Permissions.SignColor) || player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setLine(0, Line_1.replaceAll("&r", "§r"));
                event.setLine(1, Line_2.replaceAll("&r", "§r"));
                event.setLine(2, Line_3.replaceAll("&r", "§r"));
                event.setLine(3, Line_4.replaceAll("&r", "§r"));
                sign.update(true);
            }
        }
    }
}
