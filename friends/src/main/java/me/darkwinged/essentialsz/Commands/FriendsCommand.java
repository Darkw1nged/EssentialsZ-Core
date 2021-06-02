package me.darkwinged.essentialsz.Commands;

import me.darkwinged.essentialsz.EssentialsZFriends;
import me.darkwinged.essentialsz.Libaries.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FriendsCommand implements CommandExecutor {

    private final EssentialsZFriends plugin = EssentialsZFriends.getInstance;

    public static Map<UUID, Integer> Request = new HashMap<>();
    public static Map<UUID, UUID> Request_Player = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("friend")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(plugin.essentialsZAPI.utils.chat("&cError! You can not use this command."));
                return true;
            }
            Player player = (Player)sender;
            CustomConfig PlayerData = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
            if (player.hasPermission("EssentialsZ.Friend")) {
                if (args.length < 1) {
                    if (!PlayerData.getConfig().contains("Current Friends") || PlayerData.getConfig().getInt("Total Friends") == 0) {
                        player.sendMessage(plugin.essentialsZAPI.utils.chat("&cError! You currently dont have any friends."));
                        return true;
                    }
                    player.sendMessage(plugin.essentialsZAPI.utils.chat("&eYou have &b" + PlayerData.getConfig().getInt("Total Friends") + " &eamount of friends."));
                    for (String name : PlayerData.getConfig().getConfigurationSection("Current Friends").getKeys(false)) {
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(PlayerData.getConfig().getString("Current Friends." + name + ".Name")));
                    }
                    return true;
                }
                String choice = args[0];

                if (choice.equalsIgnoreCase("add")) {
                    if (args.length != 2) {
                        player.sendMessage(plugin.essentialsZAPI.utils.chat("&cError! Please specify a player."));
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        player.sendMessage(plugin.essentialsZAPI.utils.chat("&cError! Could not find player."));
                        return true;
                    }
                    if (PlayerData.getConfig().contains("Current Friends." + target.getUniqueId())) {
                        player.sendMessage(plugin.essentialsZAPI.utils.chat("&cError! You are already friends with this person."));
                        return true;
                    }
                    if (player == target) {
                        player.sendMessage(plugin.essentialsZAPI.utils.chat("&cError! You can not friend yourself."));
                        return true;
                    }
                    Request_Player.put(target.getUniqueId(), player.getUniqueId());
                    Request.put(target.getUniqueId(), plugin.getConfig().getInt("Request Time"));

                    player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("Request Message"),
                            null, target, null, false));

                    for (String message : plugin.getConfig().getStringList("Incoming Request Message")) {
                        target.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("Prefix") +
                                message.replaceAll("%time%", ""+plugin.getConfig().getInt("Request Time")),
                                player, null, null, false));
                    }

                    Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                        public void run() {
                            if (Request.containsKey(target.getUniqueId())) {
                                if (Request.get(target.getUniqueId()) > 0) {
                                    Request.put(target.getUniqueId(), Request.get(target.getUniqueId()) - 1);
                                }
                            }
                        }
                    }, 0L, 20L);

                } else if (choice.equalsIgnoreCase("remove")) {
                    if (args.length != 2) {
                        player.sendMessage(plugin.essentialsZAPI.utils.chat("&cError! Please specify a player."));
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        player.sendMessage(plugin.essentialsZAPI.utils.chat("&cError! Could not find player."));
                        return true;
                    }
                    if (!PlayerData.getConfig().contains("Current Friends." + target.getUniqueId())) {
                        player.sendMessage(plugin.essentialsZAPI.utils.chat("&cError! You are not friends with this person."));
                        return true;
                    }

                    CustomConfig TargetData = new CustomConfig(plugin, String.valueOf(target.getUniqueId()), "Data");
                    PlayerData.getConfig().set("Current Friends." + target.getUniqueId().toString(), null);
                    TargetData.getConfig().set("Current Friends." + player.getUniqueId().toString(), null);

                    PlayerData.getConfig().set("Total Friends", PlayerData.getConfig().getInt("Total Friends") - 1);
                    TargetData.getConfig().set("Total Friends", TargetData.getConfig().getInt("Total Friends") - 1);

                    PlayerData.saveConfig();
                    TargetData.saveConfig();

                    player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("Friend Remove Message"),
                            null, target, null, false));

                } else if (choice.equalsIgnoreCase("accept")) {
                    if (Request.containsKey(player.getUniqueId()) && Request_Player.containsKey(player.getUniqueId())) {
                        Player target = Bukkit.getPlayer(Request_Player.get(player.getUniqueId()));

                        CustomConfig TargetData = new CustomConfig(plugin, String.valueOf(target.getUniqueId()), "Data");
                        PlayerData.getConfig().set("Current Friends." + target.getUniqueId().toString() + ".Name", target.getName());
                        TargetData.getConfig().set("Current Friends." + player.getUniqueId().toString() + ".Name", player.getName());

                        PlayerData.getConfig().set("Total Friends", PlayerData.getConfig().getInt("Total Friends") + 1);
                        TargetData.getConfig().set("Total Friends", TargetData.getConfig().getInt("Total Friends") + 1);

                        PlayerData.saveConfig();
                        TargetData.saveConfig();

                        Request.remove(player.getUniqueId());
                        Request_Player.remove(player.getUniqueId());

                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("Friend Message"),
                                null, target, null, false));

                    } else
                        player.sendMessage(plugin.essentialsZAPI.utils.chat("&cError! No incoming friend requests."));

                } else if (choice.equalsIgnoreCase("deny")) {
                    if (Request.containsKey(player.getUniqueId()) && Request_Player.containsKey(player.getUniqueId())) {
                        Player target = Bukkit.getPlayer(Request_Player.get(player.getUniqueId()));

                        Request.remove(player.getUniqueId());
                        Request_Player.remove(player.getUniqueId());

                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("Deny Message"),
                                null, target, null, false));

                    } else
                        player.sendMessage(plugin.essentialsZAPI.utils.chat("&cError! No incoming friend requests."));

                }
            }
        }
        return false;
    }


}
