package me.darkwinged.essentialsz.events.chat;

import me.darkwinged.essentialsz.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ChatGames implements Listener {

    private final Main plugin = Main.getInstance;
    private boolean Active = false;
    private String Answer;
    private String Active_name;
    private Integer time;

    public void ChatGames_Questions() {
        double chance = Math.floor(Math.random() * 100) + 1;

        if (Active) return;

        if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
            if (plugin.getConfig().getBoolean("Chat.Settings.Chat Games", true)) {
                for (String key : plugin.ChatGamesFile.getConfig().getConfigurationSection("Questions").getKeys(false)) {
                    String message = plugin.ChatGamesFile.getConfig().getString("Questions." + key + ".question").replaceAll("%n", "\n");
                    if (plugin.ChatGamesFile.getConfig().getDouble("Questions." + key + ".chance") >= chance) {
                        Bukkit.broadcastMessage(plugin.essentialsZAPI.utils.chat(message));
                        Answer = plugin.ChatGamesFile.getConfig().getString("Questions." + key + ".answer");

                        System.out.println(Answer);

                        Active = true;
                        Active_name = key;
                        time = plugin.ChatGamesFile.getConfig().getInt("Settings.Time limit");
                        new BukkitRunnable() {
                            public void run() {

                                if (time == 0) {
                                    Active = false;
                                    cancel();
                                    return;
                                }
                                time--;

                            }
                        }.runTaskTimer(plugin, 20L, 20L);
                        break;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        if (Active) {
            if (message.equalsIgnoreCase(Answer)) {
                for (String reward : plugin.ChatGamesFile.getConfig().getStringList("Questions." + Active_name + ".rewards")) {
                    if (reward.contains("[MSG]")) {
                        player.sendMessage(reward.replace("[MSG] ", ""));
                    } else if (reward.contains("[MONEY]")) {
                        double amount = Double.parseDouble(reward.replace("[MONEY] ", ""));
                        plugin.economyManager.AddAccount(player, amount);
                    } else if (reward.contains("[XP]")) {
                        float amount = Float.parseFloat(reward.replace("[XP] ", ""));
                        player.setExp(amount);
                    } else if (reward.contains("[CMD]")) {
                        String command = plugin.essentialsZAPI.utils.chat(reward.replace("[CMD] ", ""), player, player);
                        plugin.essentialsZAPI.utils.sendConsoleCommand(command);
                    } else if (reward.contains("[ITEM]")) {
                        ItemStack item = new ItemStack(Material.getMaterial(reward.replace("[ITEM] ", "").toUpperCase()));
                        player.getInventory().addItem(item);
                    }
                }
            }
        }
    }
}
