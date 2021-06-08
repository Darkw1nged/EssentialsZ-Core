package me.darkwinged.essentialsz.events.chat.joinmessage;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.util.CustomConfig;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DefaultJoinMessage implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
            Player player = event.getPlayer();
            CustomConfig Data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
            event.setJoinMessage(null);
            if (!Data.getConfig().contains("lastKnownName")) return;
            if (Data.getConfig().getBoolean("isVanished", true)) return;

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
                    BarColor color = BarColor.valueOf(plugin.getConfig().getString("Chat.Settings.Join Messages.Bossbar.color"));
                    BarStyle style = BarStyle.valueOf(plugin.getConfig().getString("Chat.Settings.Join Messages.Bossbar.style"));

                    plugin.essentialsZAPI.utils.sendAllBossbar(player,
                            plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("join message"), player, null, player, false),
                            color, style, plugin.getConfig().getInt("Chat.Settings.Join Messages.Bossbar.length"));
                    event.setJoinMessage(null);
                    break;
            }
        }
    }

}
