package me.darkwinged.Essentials.Events.World;

import me.darkwinged.Essentials.Libaries.Lang.Utils;
import me.darkwinged.Essentials.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class Playtime implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (plugin.getConfig().getBoolean("Playtime.enabled", true)) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                public void run() {
                    if (Utils.PT_Seconds.containsKey(uuid) && plugin.getConfig().getBoolean("Playtime.Seconds", true)) {
                        if (Utils.PT_Seconds.get(uuid) <= 60) {
                            Utils.PT_Seconds.put(uuid, Utils.PT_Seconds.get(uuid) + 1);

                        } else if (Utils.PT_Minutes.containsKey(uuid) && plugin.getConfig().getBoolean("Playtime.Minutes", true)) {
                            if (Utils.PT_Minutes.get(uuid) <= 60) {
                                Utils.PT_Minutes.put(uuid, Utils.PT_Minutes.get(uuid) + 1);
                                Utils.PT_Seconds.put(uuid, 0);

                            } else if (Utils.PT_Hours.containsKey(uuid) && plugin.getConfig().getBoolean("Playtime.Hours", true)) {
                                if (Utils.PT_Hours.get(uuid) <= 24) {
                                    Utils.PT_Hours.put(uuid, Utils.PT_Hours.get(uuid) + 1);
                                    Utils.PT_Minutes.put(uuid, 0);

                                } else if (Utils.PT_Days.containsKey(uuid) && plugin.getConfig().getBoolean("Playtime.Days", true)) {
                                    Utils.PT_Days.put(uuid, Utils.PT_Days.get(uuid) + 1);
                                    Utils.PT_Hours.put(uuid, 0);
                                }
                            }
                        }
                    }
                }
            }, 0L, 20L);
        }


    }

}
