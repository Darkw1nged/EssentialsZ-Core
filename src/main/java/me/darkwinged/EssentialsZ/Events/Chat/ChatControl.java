package me.darkwinged.EssentialsZ.Events.Chat;

import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Libaries.Lang.Utils;
import me.darkwinged.EssentialsZ.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class ChatControl implements Listener {

    private final Main plugin = Main.getInstance;

    // Anti Spam
    HashMap<String, Long> Cooldown = new HashMap<>();

    @EventHandler
    public void checkChatSpam(AsyncPlayerChatEvent event) {
        if (plugin.getConfig().getBoolean("Chat.Settings.Events.Anti Spam", true)) {
            Player player = event.getPlayer();
            if (player.hasPermission(Permissions.bypass) || player.hasPermission(Permissions.GlobalOverwrite))
                return;
            long time = System.currentTimeMillis();
            long lastUse = 0;
            if (Cooldown.containsKey(player.getName())) {
                lastUse = Cooldown.get(player.getName());
            }
            if (lastUse + 1000L > time) {
                event.setCancelled(true);
                Bukkit.getScheduler().runTask(plugin, new Runnable() {
                    public void run() {
                        player.kickPlayer("Kicked for spamming");
                    }
                });
            }
            Cooldown.remove(player.getName());
            Cooldown.put(player.getName(), time);
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Cooldown.remove(event.getPlayer());
    }

    // Muted chat
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (Utils.isChatMuted) {
            if (!player.hasPermission(Permissions.bypass) || !player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Chat Muted")));
            }
        }
    }

    // Blocked Commands
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
            if (plugin.getConfig().getBoolean("Chat.Settings.Events.Blocked Commands", true)) {
                Player player = event.getPlayer();
                for (String msg : event.getMessage().split(" ")) {
                    if (plugin.BlockedCommandsFile.getConfig().get("Commands").toString().contains(msg)) {
                        if (player.hasPermission(Permissions.bypass) || player.hasPermission(Permissions.GlobalOverwrite))
                            return;
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    // Chat Filter
    @EventHandler
    public void antiswear(AsyncPlayerChatEvent event) {
        if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
            if (plugin.getConfig().getBoolean("Chat.Settings.Events.Filter", true)) {
                String censor = "#$@&%*!";
                String msg = event.getMessage();
                for (String blacklist : plugin.ChatFilterFile.getConfig().getStringList("Blacklist")) {
                    msg = msg.toLowerCase().replace(blacklist.toLowerCase(), censor);
                    event.setMessage(msg);
                }
            }
        }
    }

    // Chat ping
    @EventHandler
    public void Ping(AsyncPlayerChatEvent event) {
        if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
            if (plugin.getConfig().getBoolean("Chat.Settings.Chat Ping.enabled", true)) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (event.getMessage().contains("@"+player.getName())) {
                        if (player == event.getPlayer())
                            return;
                        String color = "&" + plugin.getConfig().getString("Chat.Settings.Chat Ping.color");
                        event.getMessage().replaceAll(player.getName(), ChatColor.translateAlternateColorCodes('&', color+player.getName()));
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.2F, 0.5F);
                    }
                }
            }
        }
    }

    // Quit message
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
            if (plugin.getConfig().getBoolean("Chat.Settings.Show Leave Message", true)) {
                Player player = event.getPlayer();
                if (Utils.invisible_list.contains(player.getUniqueId())) {
                    event.setQuitMessage(null);
                    return;
                }
                event.setQuitMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("quit message"), player, null, player, false));
                return;
            }
            event.setQuitMessage(null);
        }
    }

}
