package me.darkwinged.essentialsz;

import me.darkwinged.essentialsz.libraries.center.DefaultFontInfo;
import me.darkwinged.essentialsz.libraries.Ping;
import me.darkwinged.essentialsz.libraries.TicksPerSecond;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static org.bukkit.Bukkit.getServer;

public class Utils {

    public String chat(String message, OfflinePlayer player, OfflinePlayer target, Player pingPlayer, boolean DisplayName) {
        String newMessage = message;

        if (newMessage.contains("%n")) {
            newMessage = newMessage.replaceAll("%n", "\n");
        }
        if (newMessage.contains("%tps%")) {
            newMessage = newMessage.replaceAll("%tps%", "" + TicksPerSecond.getTPS());
        }
        if (player != null && newMessage.contains("%player%")) {
            if (DisplayName) {
                newMessage = newMessage.replaceAll("%player%", Bukkit.getPlayer(player.getName()).getDisplayName());
            } else {
                newMessage = newMessage.replaceAll("%player%", player.getName());
            }
        }
        if (target != null && newMessage.contains("%target%")) {
            newMessage = newMessage.replaceAll("%target%", target.getName());
        }
        if (pingPlayer != null && newMessage.contains("%ping%")) {
            newMessage = newMessage.replaceAll("%ping%", "" + Ping.getPing(pingPlayer));
        }

        return ChatColor.translateAlternateColorCodes('&', newMessage);
    }

    public List<String> getConvertedLore(FileConfiguration config, String path) {
        if (config == null) return null;
        List<String> oldList = config.getStringList(path + ".lore");
        List<String> newList = new ArrayList<>();
        for (String a : oldList)
            newList.add(ChatColor.translateAlternateColorCodes('&', a));
        return newList;
    }

    public String getServerTPS() {
        String tps;
        if (TicksPerSecond.getTPS() <= 20) {
            tps = String.format("%.0f", TicksPerSecond.getTPS());
        } else {
            tps = "20";
        }
        return tps;
    }

    public void sendPlayerCommand(Player player, String command) {
        player.performCommand(command);
    }

    public void sendConsoleCommand(String command) {
        getServer().dispatchCommand(getServer().getConsoleSender(), command);
    }

    public int getPlayerPing(Player player) {
        return Ping.getPing(player);
    }

    public static HashSet<Material> bad_blocks = new HashSet<>();

    static {
        bad_blocks.add(Material.LAVA);
        bad_blocks.add(Material.FIRE);
        bad_blocks.add(Material.CACTUS);
    }

    public boolean isLocationSafe(Location location) {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        Block block = location.getWorld().getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);
        return !(bad_blocks.contains(below.getType())) || (block.getType().isSolid()) || (above.getType().isSolid());
    }

    public void sendBossbar(Player player, String title, BarColor color, BarStyle style, int length) {
        BossBar bar = Bukkit.createBossBar(title, color, style);
        bar.setVisible(true);
        bar.addPlayer(player);
        bar.setProgress(1);
        HashMap<UUID, Integer> time = new HashMap<>();
        time.put(player.getUniqueId(), length);
        new BukkitRunnable() {
            public void run() {
                if (!time.containsKey(player.getUniqueId())) return;
                if (time.get(player.getUniqueId()) <= 0) {
                    time.remove(player.getUniqueId());
                    bar.removeAll();
                    return;
                }
                time.put(player.getUniqueId(), time.get(player.getUniqueId()) - 1);
            }
        }.runTaskTimer(EssentialsZAPI.getInstance, 0L, 20L);
    }

    public void sendAllBossbar(Player join, String title, BarColor color, BarStyle style, int length) {
        BossBar bar = Bukkit.createBossBar(title, color, style);
        bar.setVisible(true);
        for (Player online : Bukkit.getOnlinePlayers()) {
            bar.addPlayer(online);
        }
        bar.setProgress(1);
        HashMap<UUID, Integer> time = new HashMap<>();
        time.put(join.getUniqueId(), length);
        new BukkitRunnable() {
            public void run() {
                if (!time.containsKey(join.getUniqueId())) return;
                if (time.get(join.getUniqueId()) <= 0) {
                    time.remove(join.getUniqueId());
                    bar.removeAll();
                    return;
                }
                time.put(join.getUniqueId(), time.get(join.getUniqueId()) - 1);
            }
        }.runTaskTimer(EssentialsZAPI.getInstance, 0L, 20L);
    }

    public void sendActionBar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
    }

    public void sendAllActionBar(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
        }
    }

    public void sendTitle(Player player, String header, String footer) {
        player.sendTitle(ChatColor.translateAlternateColorCodes('&', header), ChatColor.translateAlternateColorCodes('&', footer));
    }

    public void sendAllTitle(String header, String footer) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendTitle(ChatColor.translateAlternateColorCodes('&', header), ChatColor.translateAlternateColorCodes('&', footer));
        }
    }

    public void sendTitle(Player player, String header, String footer, int fadeIn, int Stay, int fadeOut) {
        player.sendTitle(ChatColor.translateAlternateColorCodes('&', header), ChatColor.translateAlternateColorCodes('&', footer),
                fadeIn, Stay, fadeOut);
    }

    public void sendAllTitle(String header, String footer, int fadeIn, int Stay, int fadeOut) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendTitle(ChatColor.translateAlternateColorCodes('&', header), ChatColor.translateAlternateColorCodes('&', footer),
                    fadeIn, Stay, fadeOut);
        }
    }

    public String PlayerCords(Player player) {
        return "X: " + player.getLocation().getX() + ", Y: " + player.getLocation().getY() + ", Z: " + player.getLocation().getZ();
    }

    public ItemStack playerHead(Player player) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwningPlayer(player);
        item.setItemMeta(meta);
        return item;
    }

    private final static int CENTER_PX = 154;
    public String CenteredMessage(String message) {
        String[] lines = ChatColor.translateAlternateColorCodes('&', message).split("\n", 40);
        StringBuilder returnMessage = new StringBuilder();

        for (String line : lines) {
            int messagePxSize = 0;
            boolean previousCode = false;
            boolean isBold = false;

            for (char c : line.toCharArray()) {
                if (c == '§') {
                    previousCode = true;
                } else if (previousCode) {
                    previousCode = false;
                    isBold = c == 'l';
                } else {
                    DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                    messagePxSize = isBold ? messagePxSize + dFI.getBoldLength() : messagePxSize + dFI.getLength();
                    messagePxSize++;
                }
            }
            int toCompensate = CENTER_PX - messagePxSize / 2;
            int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
            int compensated = 0;
            StringBuilder sb = new StringBuilder();
            while(compensated < toCompensate){
                sb.append(" ");
                compensated += spaceLength;
            }
            returnMessage.append(sb.toString()).append(line).append("\n");
        }

        return returnMessage.toString();
    }

    public void SpawnParticles(Location location, Particle particle, Integer amount) {
        if (location.getWorld() == null) return;
        location.getWorld().spawnParticle(particle, location, amount);
    }

    public void SpawnEntity(Location location, EntityType entityType) {
        if (location.getWorld() == null) return;
        location.getWorld().spawnEntity(location, entityType);
    }

}
