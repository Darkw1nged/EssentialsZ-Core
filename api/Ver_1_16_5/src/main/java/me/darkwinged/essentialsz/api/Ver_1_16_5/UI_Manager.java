package me.darkwinged.essentialsz.api.Ver_1_16_5;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class UI_Manager {

    public static void sendBossbar(Plugin plugin, Player player, String title, BarColor color, BarStyle style, int length) {
        BossBar bar = Bukkit.createBossBar(title, color, style);
        bar.setVisible(true);
        bar.addPlayer(player);
        bar.setProgress(1);
        HashMap<UUID, Integer> time = new HashMap<>();
        time.put(player.getUniqueId(), length);
        new BukkitRunnable() {
            public void run() {
                if (!time.containsKey(player.getUniqueId())) return;
                if (time.get(player.getUniqueId()) <= 0) {
                    time.remove(player.getUniqueId());
                    bar.removeAll();
                    return;
                }
                time.put(player.getUniqueId(), time.get(player.getUniqueId()) - 1);
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    public static void sendAllBossbar(Plugin plugin, Player join, String title, BarColor color, BarStyle style, int length) {
        BossBar bar = Bukkit.createBossBar(title, color, style);
        bar.setVisible(true);
        for (Player online : Bukkit.getOnlinePlayers()) {
            bar.addPlayer(online);
        }
        bar.setProgress(1);
        HashMap<UUID, Integer> time = new HashMap<>();
        time.put(join.getUniqueId(), length);
        new BukkitRunnable() {
            public void run() {
                if (!time.containsKey(join.getUniqueId())) return;
                if (time.get(join.getUniqueId()) <= 0) {
                    time.remove(join.getUniqueId());
                    bar.removeAll();
                    return;
                }
                time.put(join.getUniqueId(), time.get(join.getUniqueId()) - 1);
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    public static void sendActionBar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
    }

    public static void sendAllActionBar(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
        }
    }

    public static void sendTitle(Player player, String header, String footer) {
        player.sendTitle(ChatColor.translateAlternateColorCodes('&', header), ChatColor.translateAlternateColorCodes('&', footer));
    }

    public static void sendAllTitle(String header, String footer) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendTitle(ChatColor.translateAlternateColorCodes('&', header), ChatColor.translateAlternateColorCodes('&', footer));
        }
    }

    public static void sendTitle(Player player, String header, String footer, int fadeIn, int Stay, int fadeOut) {
        player.sendTitle(ChatColor.translateAlternateColorCodes('&', header), ChatColor.translateAlternateColorCodes('&', footer),
                fadeIn, Stay, fadeOut);
    }

    public static void sendAllTitle(String header, String footer, int fadeIn, int Stay, int fadeOut) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendTitle(ChatColor.translateAlternateColorCodes('&', header), ChatColor.translateAlternateColorCodes('&', footer),
                    fadeIn, Stay, fadeOut);
        }
    }

}
