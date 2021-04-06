package me.darkwinged.Essentials.Events.World;

import me.darkwinged.Essentials.Libaries.Lang.Utils;
import me.darkwinged.Essentials.Main;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ActionBarCoords implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void update(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("World Events.Player Coords.enabled", true)) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                public void run() {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        double Coord_X = player.getLocation().getX();
                        double Coord_Y = player.getLocation().getY();
                        double Coord_Z = player.getLocation().getZ();

                        String X = String.format("%.1f", Coord_X);
                        String Y = String.format("%.1f", Coord_Y);
                        String Z = String.format("%.1f", Coord_Z);

                        if (Utils.Coords_List.contains(player.getUniqueId())) {

                            switch (plugin.getConfig().getString("World Events.Player Coords.style")) {

                                case "ACTIONBAR":
                                    plugin.essentialsZAPI.utils.sendActionBar(player, plugin.getConfig().getString("World Events.Player Coords.message")
                                            .replaceAll("%X", X).replaceAll("%Y", Y).replaceAll("%Z", Z));
                                    break;
                                case "BOSS_BAR":
                                    BarColor color = (BarColor) plugin.getConfig().get("World Events.Player Coords.Bossbar.color");
                                    BarStyle style = (BarStyle) plugin.getConfig().get("World Events.Player Coords.Bossbar.style");

                                    plugin.essentialsZAPI.utils.sendAllBossbar(player,
                                            plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("World Events.Player Coords.message")
                                                    .replaceAll("%X", X).replaceAll("%Y", Y).replaceAll("%Z", Z), player, null, player, false),
                                            color, style, plugin.getConfig().getInt("World Events.Player Coords.Bossbar.length"));
                            }
                        }
                    }
                }
            }, 0L, 20 * plugin.getConfig().getLong("World Events.Player Coords.update"));
        }
    }

}
