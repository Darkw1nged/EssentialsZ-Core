package me.darkwinged.Essentials.Events.Chat;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class JoinAndLeaveMessage implements Listener {

    private Main plugin;
    public JoinAndLeaveMessage(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("Chat", true)) {
            Player player = event.getPlayer();
            if (!player.hasPlayedBefore()) {
                if (plugin.getConfig().getBoolean("Show_First_Time_Join_Message", true)) {
                    plugin.getConfig().set("Server_Player_Total", plugin.getConfig().getInt("Server_Player_Total") + 1);
                    String Message = Utils.chat(plugin.MessagesFile.getConfig().getString("first time join message"))
                            .replaceAll("%player%", player.getDisplayName())
                            .replaceAll("%total_amount%", plugin.getConfig().getInt("Server_Player_Total") + "");
                    event.setJoinMessage(Message);
                }
            }
            if (plugin.getConfig().getBoolean("Show_Join_Message", true)) {
                if (plugin.getConfig().getBoolean("Private_Join_Message", true))
                    return;
                switch (plugin.getConfig().getString("Join_Message_Style")) {
                    case "CHAT": {
                        if (Utils.invisible_list.contains(player.getUniqueId())) {
                            return;
                        }
                        String Message = Utils.chat(plugin.MessagesFile.getConfig().getString("join message"))
                                .replaceAll("%player%", player.getDisplayName());
                        event.setJoinMessage(Message);
                        break;
                    }
                    case "TITLE":
                        if (Utils.invisible_list.contains(player.getUniqueId())) {
                            return;
                        }
                        player.sendTitle(Utils.chat(plugin.MessagesFile.getConfig().getString("title message")), Utils.chat(plugin.MessagesFile.getConfig().getString("subtitle message")));
                        event.setJoinMessage(null);
                        break;
                    case "ACTION_BAR": {
                        if (Utils.invisible_list.contains(player.getUniqueId())) {
                            return;
                        }
                        String Message = Utils.chat(plugin.MessagesFile.getConfig().getString("join message"))
                                .replaceAll("%player%", player.getDisplayName());
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Utils.chat(Message)));
                        event.setJoinMessage(null);
                        break;
                    }
                    case "BOSS_BAR": {
                        if (Utils.invisible_list.contains(player.getUniqueId())) {
                            return;
                        }
                        String Message = Utils.chat(plugin.MessagesFile.getConfig().getString("join message"))
                                .replaceAll("%player%", player.getDisplayName());
                        BossBar bar = Bukkit.createBossBar(Message, BarColor.PURPLE, BarStyle.SOLID);
                        bar.setVisible(true);
                        bar.setProgress(1);
                        HashMap<UUID, Integer> time = new HashMap<>();
                        time.put(player.getUniqueId(), plugin.getConfig().getInt("Bossbar_Length"));
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            bar.addPlayer(online);
                        }
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
                        event.setJoinMessage(null);
                        break;
                    }
                }
                return;
            }
            event.setJoinMessage(null);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (plugin.getConfig().getBoolean("Chat", true)) {
            if (plugin.getConfig().getBoolean("Show_Leave_Message", true)) {
                Player player = event.getPlayer();
                if (Utils.invisible_list.contains(player.getUniqueId())) {
                    event.setQuitMessage(null);
                    return;
                }
                String Message = Utils.chat(plugin.MessagesFile.getConfig().getString("quit message"))
                        .replaceAll("%player%", player.getDisplayName());
                event.setQuitMessage(Message);
                return;
            }
            event.setQuitMessage(null);
        }
    }

}
