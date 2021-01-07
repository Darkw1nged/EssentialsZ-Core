package me.darkwinged.Essentials.Commands.Economy;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.EconomyManager;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class cmd_Autosell implements CommandExecutor {

    private Main plugin;
    public cmd_Autosell(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("autosell")) {
            if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
                if (plugin.getConfig().getBoolean("Economy.Settings.Sell.Autosell", true)) {
                    if (!(sender instanceof Player)) {
                        Utils.Message(sender, Errors.getErrors(Errors.Console));
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.Autosell) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (Utils.Autosell_List.contains(player.getUniqueId())) {
                            player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Autosell Toggle").replaceAll("%setting%", "disabled")));
                            Utils.Autosell_List.remove(player.getUniqueId());
                            return true;
                        }
                        player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Autosell Toggle").replaceAll("%setting%", "enabled")));
                        Utils.Autosell_List.add(player.getUniqueId());

                        new BukkitRunnable() {
                            public void run() {
                                for (Player online : Bukkit.getOnlinePlayers()) {
                                    if (Utils.Autosell_List.contains(online.getUniqueId())) {
                                        double amount = 1;
                                        for (int i = 0; i <= 36; i++) {
                                            ItemStack item = online.getInventory().getItem(i);
                                            if (item == null) continue;
                                            if (plugin.WorthFile.getConfig().isDouble("worth." + item.getType().name())) {
                                                amount += plugin.WorthFile.getConfig().getDouble("worth." + item.getType().name()) * item.getAmount();
                                                item.setAmount(0);
                                            }
                                        }
                                        if (plugin.getConfig().getString("Economy.API").equalsIgnoreCase("Vault")) {
                                            plugin.econ.depositPlayer(player, amount);
                                        } else if (plugin.getConfig().getString("Economy.API").equalsIgnoreCase("EssentialsZ")) {
                                            EconomyManager.setBalance(player.getName(), EconomyManager.getBalance(player.getName()) + amount);
                                        }
                                    }
                                }
                            }
                        }.runTaskTimer(plugin, 0L, 20L);

                    } else {
                        Utils.Message(player, Errors.getErrors(Errors.NoPermission));
                    }
                }
            }
        }
        return false;
    }
}
