package me.darkwinged.EssentialsZ.Commands.Economy;

import me.darkwinged.EssentialsZ.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmd_Autosell implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("autosell")) {

            /*


            if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
                if (plugin.getConfig().getBoolean("Economy.Settings.Sell.Autosell", true)) {
                    if (plugin.Module_Economy = false) return true;
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(Utils.chat(Errors.getErrors(Errors.Console)));
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
                                            } else {
                                                return;
                                            }
                                        }
                                        EconomyManager.AddAccount(player, amount);
                                        sender.sendMessage(Utils.chat(plugin.getConfig().getString("Economy.Settings.Currency Symbol") + amount));
                                    }
                                }
                            }
                        }.runTaskTimer(plugin, 0L, 40L);

                    } else {
                        sender.sendMessage(Utils.chat(Errors.getErrors(Errors.NoPermission)));
                    }
                }
            }

             */
        }
        return false;
    }
}
