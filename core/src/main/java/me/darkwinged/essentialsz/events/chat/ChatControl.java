package me.darkwinged.essentialsz.events.chat;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import me.darkwinged.essentialsz.libaries.lang.Utils;
import me.darkwinged.essentialsz.libaries.storage.CustomConfig;
import org.bukkit.Bukkit;
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
        Cooldown.remove(event.getPlayer().getName());
    }

    // Muted chat
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (Utils.isChatMuted) {
            if (!player.hasPermission(Permissions.bypass) || !player.hasPermission(Permissions.GlobalOverwrite)) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Chat Muted")));
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
        String message = event.getMessage();
        Player sender = event.getPlayer();

        if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
            if (plugin.getConfig().getBoolean("Chat.Settings.Chat Ping.enabled", true)) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    String Ping = "@" + player.getName();
                    String color = "&" + plugin.getConfig().getString("Chat.Settings.Chat Ping.color");

                    if (message.contains(Ping)) {
                        if (player == sender) return;
                        event.setMessage(event.getMessage().replaceAll(Ping, color+Ping + "&f"));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1.2F, 0.5F);
                    }
                }
            }
        }
    }

    // Quit message
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
            if (plugin.getConfig().getBoolean("Chat.Settings.Events.Leave Message", true)) {
                Player player = event.getPlayer();
                CustomConfig Data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
                if (Data.getConfig().getBoolean("isVanished", true)) {
                    event.setQuitMessage(null);
                    return;
                }
                event.setQuitMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("quit message"), player, player));
                return;
            }
            event.setQuitMessage(null);
        }
    }

}
