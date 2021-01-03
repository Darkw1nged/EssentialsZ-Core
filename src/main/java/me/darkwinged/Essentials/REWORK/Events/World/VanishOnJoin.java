package me.darkwinged.Essentials.REWORK.Events.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import me.darkwinged.Essentials.REWORK.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class VanishOnJoin implements Listener {

    private Main plugin;
    public VanishOnJoin(Main plugin) { this.plugin = plugin; }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("cmd_Vanish", true)) {
            Player player = event.getPlayer();
            if (plugin.getConfig().getBoolean("Silent_Join", true)) {
                if (player.hasPermission(Permissions.SilentJoin) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    Utils.invisible_list.add(player.getUniqueId());
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.hidePlayer(player);
                    }
                    event.setJoinMessage(null);
                }
            }
        }
    }

}
