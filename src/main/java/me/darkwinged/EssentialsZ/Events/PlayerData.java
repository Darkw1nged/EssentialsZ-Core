package me.darkwinged.EssentialsZ.Events;

import me.darkwinged.EssentialsZ.Libaries.Lang.CustomConfig;
import me.darkwinged.EssentialsZ.Libaries.Lang.Utils;
import me.darkwinged.EssentialsZ.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerData implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CustomConfig Data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");

        Date login = new Date();
        SimpleDateFormat time_format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        if (Data.getCustomConfigFile().exists()) {

            Data.getConfig().set("lastKnownName", player.getName());
            Data.getConfig().set("timestamps.lastLogin", time_format.format(login));
            Data.getConfig().set("Prefix", plugin.getChat().getPlayerPrefix(player));
            Data.getConfig().set("Suffix", plugin.getChat().getPlayerSuffix(player));

            Data.saveConfig();

            plugin.economyManager.BankAccounts.put(player.getUniqueId(), Data.getConfig().getDouble("money"));
            Utils.PT_Days.put(player.getUniqueId(), Data.getConfig().getInt("Playtime.Days"));
            Utils.PT_Hours.put(player.getUniqueId(), Data.getConfig().getInt("Playtime.Hours"));
            Utils.PT_Minutes.put(player.getUniqueId(), Data.getConfig().getInt("Playtime.Minutes"));
            Utils.PT_Seconds.put(player.getUniqueId(), Data.getConfig().getInt("Playtime.Seconds"));
            return;
        }
        // Setup data
        Utils.PT_Days.put(player.getUniqueId(), 0);
        Utils.PT_Hours.put(player.getUniqueId(), 0);
        Utils.PT_Minutes.put(player.getUniqueId(), 0);
        Utils.PT_Seconds.put(player.getUniqueId(), 0);

        Data.getConfig().set("lastKnownName", player.getName());
        Data.getConfig().set("Prefix", plugin.getChat().getPlayerPrefix(player));
        Data.getConfig().set("Suffix", plugin.getChat().getPlayerSuffix(player));

        // Time Stamps
        Data.getConfig().set("timestamps.lastLogin", time_format.format(login));
        Data.getConfig().set("timestamps.lastLogout", "N/A");

        Data.getConfig().set("money", plugin.economyManager.getAccount(player));
        Data.getConfig().set("ipAddress", player.getAddress().getHostString());

        // Playtime
        Data.getConfig().set("Playtime.Days", Utils.PT_Days.get(player.getUniqueId()));
        Data.getConfig().set("Playtime.Hours", Utils.PT_Hours.get(player.getUniqueId()));
        Data.getConfig().set("Playtime.Minutes", Utils.PT_Minutes.get(player.getUniqueId()));
        Data.getConfig().set("Playtime.Seconds", Utils.PT_Seconds.get(player.getUniqueId()));

        Data.getConfig().createSection("Homes");
        Data.saveConfig();

        plugin.economyManager.BankAccounts.put(player.getUniqueId(), Data.getConfig().getDouble("money"));
    }

    @EventHandler
    public void onExit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        CustomConfig Data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
        if (!Data.getConfig().contains("lastKnownName")) return;

        Data.getConfig().set("money", plugin.economyManager.getAccount(player));
        Data.getConfig().set("Prefix", plugin.getChat().getPlayerPrefix(player));
        Data.getConfig().set("Suffix", plugin.getChat().getPlayerSuffix(player));

        Data.getConfig().set("Playtime.Days", Utils.PT_Days.get(player.getUniqueId()));
        Data.getConfig().set("Playtime.Hours", Utils.PT_Hours.get(player.getUniqueId()));
        Data.getConfig().set("Playtime.Minutes", Utils.PT_Minutes.get(player.getUniqueId()));
        Data.getConfig().set("Playtime.Seconds", Utils.PT_Seconds.get(player.getUniqueId()));

        String world = player.getWorld().getName();
        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();
        float yaw = player.getLocation().getYaw();
        float pitch = player.getLocation().getPitch();

        Date logout = new Date();
        SimpleDateFormat time_format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Data.getConfig().set("timestamps.lastLogout.time", time_format.format(logout));
        Data.getConfig().set("timestamps.lastLogout.location.world", world);
        Data.getConfig().set("timestamps.lastLogout.location.x", x);
        Data.getConfig().set("timestamps.lastLogout.location.y", y);
        Data.getConfig().set("timestamps.lastLogout.location.z", z);
        Data.getConfig().set("timestamps.lastLogout.location.yaw", yaw);
        Data.getConfig().set("timestamps.lastLogout.location.pitch", pitch);

        Data.saveConfig();
        plugin.economyManager.BankAccounts.remove(player.getUniqueId());
        Utils.PT_Days.remove(player.getUniqueId());
        Utils.PT_Hours.remove(player.getUniqueId());
        Utils.PT_Minutes.remove(player.getUniqueId());
        Utils.PT_Seconds.remove(player.getUniqueId());
    }

}
