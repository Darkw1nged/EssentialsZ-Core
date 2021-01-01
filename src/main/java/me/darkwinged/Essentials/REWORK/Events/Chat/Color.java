package me.darkwinged.Essentials.REWORK.Events.Chat;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Color implements Listener {

    private Main plugin;
    public Color(Main plugin) { this.plugin = plugin; }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (plugin.getConfig().getBoolean("Chat", true)) {
            if (event.getPlayer().hasPermission(Permissions.ChatColor) || event.getPlayer().hasPermission(Permissions.GlobalOverwrite)) {
                String message = ChatColor.translateAlternateColorCodes('&', event.getMessage());
                event.setMessage(message);
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
