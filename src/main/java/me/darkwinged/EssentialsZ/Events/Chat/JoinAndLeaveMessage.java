package me.darkwinged.EssentialsZ.Events.Chat;

import me.darkwinged.EssentialsZ.Libaries.Lang.Utils;
import me.darkwinged.EssentialsZ.Main;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinAndLeaveMessage implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
            Player player = event.getPlayer();

            if (!player.hasPlayedBefore()) {
                if (plugin.getConfig().getBoolean("Chat.Settings.Join Messages.First Time join", true)) {

                    plugin.getConfig().set("Server_Player_Total", plugin.getConfig().getInt("Server_Player_Total") + 1);
                    plugin.saveConfig();

                    event.setJoinMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("first time join message")
                            .replaceAll("%total_amount%", ""+plugin.getConfig().getInt("Server_Player_Total")), player, null, null, false));
                }
            }

            if (plugin.getConfig().getBoolean("Chat.Settings.Join Messages.Show join Message", true)) {
                if (plugin.getConfig().getBoolean("Chat.Settings.Join Messages.motd.enabled", true)) {

                    for (String s : plugin.MessagesFile.getConfig().getStringList("motd")) {
                        if (plugin.getConfig().getBoolean("Chat.Settings.Join Messages.motd.center", true)) {
                            s = plugin.essentialsZAPI.utils.chat(s, player, null, player, false);
                            player.sendMessage(plugin.essentialsZAPI.utils.CenteredMessage(s));
                        } else {
                            player.sendMessage(plugin.essentialsZAPI.utils.chat(s, player, null, player, false));
                        }
                    }

                }

                if (Utils.invisible_list.contains(player.getUniqueId())) {
                    event.setJoinMessage(null);
                    return;
                }
                if (plugin.getConfig().getBoolean("Chat.Settings.Join Messages.VIP Message.enabled", true)) {
                    if (plugin.getConfig().getBoolean("Chat.Settings.Join Messages.VIP Message.Center")) {
                        String message = plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("join message"), player, null, player, true);
                        event.setJoinMessage(plugin.essentialsZAPI.utils.CenteredMessage(message));
                    } else {
                        event.setJoinMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("join message"), player, null, player, true));
                    }
                }
                if (plugin.getConfig().getBoolean("Chat.Settings.Join Messages.VIP Message.enabled", true) &&
                        plugin.getConfig().getBoolean("Chat.Settings.Join Messages.VIP Message.No Join Message", true)) {
                    return;
                }

                switch (plugin.getConfig().getString("Chat.Settings.Join Messages.Style")) {
                    case "CHAT":
                        if (plugin.getConfig().getBoolean("Chat.Settings.Join Messages.Join Message Center")) {
                            String message = plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("join message"), player, null, player, false);
                            event.setJoinMessage(plugin.essentialsZAPI.utils.CenteredMessage(message));
                        } else {
                            event.setJoinMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("join message"), player, null, player, false));
                        }
                        break;

                    case "TITLE":
                        plugin.essentialsZAPI.utils.sendAllTitle(
                                plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("title message"), player, null, player, false),
                                plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("subtitle message"), player, null, player, false));
                        event.setJoinMessage(null);
                        break;

                    case "ACTION_BAR":
                        plugin.essentialsZAPI.utils.sendAllActionBar(
                                plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("join message"), player, null, player, false));
                        event.setJoinMessage(null);
                        break;

                    case "BOSS_BAR":
                        BarColor color = (BarColor) plugin.getConfig().get("Chat.Settings.Join Messages.Bossbar.color");
                        BarStyle style = (BarStyle) plugin.getConfig().get("Chat.Settings.Join Messages.Bossbar.style");

                        plugin.essentialsZAPI.utils.sendAllBossbar(player,
                                plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("join message"), player, null, player, false),
                                color, style, plugin.getConfig().getInt("Chat.Settings.Join Messages.Bossbar.length"));
                        event.setJoinMessage(null);
                        break;
                }
            }
        }
    }

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
