package me.darkwinged.Essentials.Libaries;

import me.darkwinged.Essentials.Main;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.ServicePriority;

public class VaultHook {

    private final Main plugin = Main.getInstance;
    private final Economy provider = plugin.economy_essentialsZ;

    public void hook() {
        Bukkit.getServicesManager().register(Economy.class, provider, plugin, ServicePriority.Highest);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "VaultAPI hooked into " + ChatColor.AQUA + plugin.getName());
    }

    public void unhook() {
        Bukkit.getServicesManager().unregisterAll(plugin);
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "VaultAPI unhooked from " + ChatColor.AQUA + plugin.getName());
    }

}
