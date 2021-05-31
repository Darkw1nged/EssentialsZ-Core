package me.darkwinged.essentialsz.events.chat.joinmessage;

import me.darkwinged.essentialsz.libaries.lang.CustomConfig;
import me.darkwinged.essentialsz.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OtherJoinMessage implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
            Player player = event.getPlayer();

            if (plugin.getConfig().getBoolean("Chat.Settings.Join Messages.First Time join", true)) {
                CustomConfig Data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
                if (!Data.getConfig().contains("lastKnownName")) return;
                if (Data.getConfig().getBoolean("hasPlayedBefore", true)) return;

                Data.getConfig().set("hasPlayedBefore", true);
                Data.saveConfig();
                plugin.ServerDataFile.getConfig().set("Server Player Total", plugin.getConfig().getInt("Server Player Total") + 1);
                plugin.ServerDataFile.saveConfig();

                event.setJoinMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("first time join message")
                        .replaceAll("%total_amount%", ""+plugin.ServerDataFile.getConfig().getInt("Server Player Total")),
                        player, null, null, false));
                return;
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
            }
        }
    }

}
