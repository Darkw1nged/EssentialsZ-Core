package me.darkwinged.essentialsz.libaries;

import me.darkwinged.essentialsz.libaries.util.Ping;
import me.darkwinged.essentialsz.libaries.util.TicksPerSecond;
import me.darkwinged.essentialsz.libaries.util.center.DefaultFontInfo;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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

    public String chat(String message, OfflinePlayer player, OfflinePlayer target) {
        String newMessage = message;

        if (newMessage.contains("%n")) {
            newMessage = newMessage.replaceAll("%n", "\n");
        }
        if (newMessage.contains("%tps%")) {
            newMessage = newMessage.replaceAll("%tps%", "" + TicksPerSecond.getTPS());
        }
        if (player != null && newMessage.contains("%player%")) {
            newMessage = newMessage.replaceAll("%player%", player.getName());
        }
        if (target != null && newMessage.contains("%target%")) {
            newMessage = newMessage.replaceAll("%target%", target.getName());
        }

        return ChatColor.translateAlternateColorCodes('&', newMessage);
    }

    public String chat(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
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
        if (TicksPerSecond.getTPS() < 20) {
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

}
