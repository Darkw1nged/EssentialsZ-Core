package me.darkwinged.essentialsz.events;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.managers.Boosters;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import me.darkwinged.essentialsz.libaries.lang.Utils;
import me.darkwinged.essentialsz.libaries.storage.CustomConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PlayerData implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CustomConfig Data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");

        Date login = new Date();
        SimpleDateFormat time_format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        if (Data.getCustomConfigFile().exists()) {

            Data.getConfig().set("lastKnownName", player.getName());
            Data.getConfig().set("timestamps.lastLogin", time_format.format(login));
            if (plugin.getChat() != null) {
                Data.getConfig().set("Prefix", plugin.getChat().getPlayerPrefix(player));
                Data.getConfig().set("Suffix", plugin.getChat().getPlayerSuffix(player));
            }
            Data.getConfig().set("isVanished", false);
            if (player.hasPermission(Permissions.VIPJoin) || player.hasPermission(Permissions.GlobalOverwrite)) {
                Data.getConfig().set("isVIP", true);
            } else {
                Data.getConfig().set("isVIP", false);
            }
            Data.saveConfig();

            plugin.economyManager.loadBalance(player);
            Utils.PT_Days.put(player.getUniqueId(), Data.getConfig().getInt("Playtime.Days"));
            Utils.PT_Hours.put(player.getUniqueId(), Data.getConfig().getInt("Playtime.Hours"));
            Utils.PT_Minutes.put(player.getUniqueId(), Data.getConfig().getInt("Playtime.Minutes"));
            Utils.PT_Seconds.put(player.getUniqueId(), Data.getConfig().getInt("Playtime.Seconds"));
            new Boosters().addMoneyBoosters(player, Data.getConfig().getInt("Boosters.money"));
            return;
        }
        // Setup data
        Utils.PT_Days.put(player.getUniqueId(), 0);
        Utils.PT_Hours.put(player.getUniqueId(), 0);
        Utils.PT_Minutes.put(player.getUniqueId(), 0);
        Utils.PT_Seconds.put(player.getUniqueId(), 0);
        new Boosters().addMoneyBoosters(player, 0);

        Data.getConfig().set("lastKnownName", player.getName());
        if (plugin.getChat() != null) {
            Data.getConfig().set("Prefix", plugin.getChat().getPlayerPrefix(player));
            Data.getConfig().set("Suffix", plugin.getChat().getPlayerSuffix(player));
        }
        NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
        String amount_formatted = nf.format(plugin.economyManager.getAccount(player));
        Data.getConfig().set("money", amount_formatted);

        // Time Stamps
        Data.getConfig().set("timestamps.lastLogin", time_format.format(login));
        Data.getConfig().set("timestamps.lastLogout", "N/A");
        Data.getConfig().set("ipAddress", player.getAddress().getHostString());
        Data.getConfig().set("isVanished", false);
        Data.getConfig().set("isJailed", false);
        Data.getConfig().set("hasPlayedBefore", false);
        if (player.hasPermission(Permissions.VIPJoin) || player.hasPermission(Permissions.GlobalOverwrite)) {
            Data.getConfig().set("isVIP", true);
        } else {
            Data.getConfig().set("isVIP", false);
        }

        setTimePlayed(player, Data);

        Data.getConfig().set("Boosters.money", 0);

        Data.getConfig().createSection("Homes");
        Data.saveConfig();

        plugin.economyManager.BankAccounts.put(player.getUniqueId(), Double.valueOf(Data.getConfig().getString("money").replaceAll(",", "")));
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onExit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        CustomConfig Data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
        if (!Data.getConfig().contains("lastKnownName")) return;
        NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
        String amount_formatted = nf.format(plugin.economyManager.getAccount(player));

        Data.getConfig().set("money", amount_formatted);
        if (plugin.getChat() != null) {
            Data.getConfig().set("Prefix", plugin.getChat().getPlayerPrefix(player));
            Data.getConfig().set("Suffix", plugin.getChat().getPlayerSuffix(player));
        }

        setTimePlayed(player, Data);
        Data.getConfig().set("Boosters.money", new Boosters().getMoneyBoosters(player));

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
        plugin.economyManager.saveBalance(player);
        Utils.PT_Days.remove(player.getUniqueId());
        Utils.PT_Hours.remove(player.getUniqueId());
        Utils.PT_Minutes.remove(player.getUniqueId());
        Utils.PT_Seconds.remove(player.getUniqueId());
        new Boosters().removePlayer(player);
    }

    private void setTimePlayed(Player player, CustomConfig data) {
        data.getConfig().set("Playtime.Days", Utils.PT_Days.get(player.getUniqueId()));
        data.getConfig().set("Playtime.Hours", Utils.PT_Hours.get(player.getUniqueId()));
        data.getConfig().set("Playtime.Minutes", Utils.PT_Minutes.get(player.getUniqueId()));
        data.getConfig().set("Playtime.Seconds", Utils.PT_Seconds.get(player.getUniqueId()));
    }

}
