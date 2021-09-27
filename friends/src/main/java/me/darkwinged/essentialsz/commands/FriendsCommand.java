package me.darkwinged.essentialsz.commands;

import me.darkwinged.essentialsz.EssentialsZFriends;
import me.darkwinged.essentialsz.libaries.managers.Friend_Manager;
import me.darkwinged.essentialsz.libaries.messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.messages.Errors;
import me.darkwinged.essentialsz.libaries.storage.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FriendsCommand implements CommandExecutor {

    private final EssentialsZFriends plugin = EssentialsZFriends.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("friend")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ErrorManager.getErrors(Errors.Console));
                return true;
            }
            Player player = (Player)sender;
            CustomConfig PlayerData = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
            if (player.hasPermission("EssentialsZ.Friend")) {
                if (args.length < 1) {
                    if (!PlayerData.getConfig().contains("Current Friends") || PlayerData.getConfig().getInt("Total Friends") == 0) {
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.Messages.getConfig().getString("Prefix") +
                                plugin.Messages.getConfig().getString("NoFriends")));
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
                        player.sendMessage(ErrorManager.getErrors(Errors.SpecifyPlayer));
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        player.sendMessage(ErrorManager.getErrors(Errors.NoPlayerFound));
                        return true;
                    }
                    if (Friend_Manager.isFriends(player, target)) return true;
                    Friend_Manager.sendRequest(player, target);

                } else if (choice.equalsIgnoreCase("remove")) {
                    if (args.length != 2) {
                        player.sendMessage(ErrorManager.getErrors(Errors.SpecifyPlayer));
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        player.sendMessage(ErrorManager.getErrors(Errors.NoPlayerFound));
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
                    if (Friend_Manager.Request.containsKey(player.getUniqueId()) && Friend_Manager.Requests.containsKey(player.getUniqueId())) {
                        Player target = Bukkit.getPlayer(Friend_Manager.Requests.get(player.getUniqueId()));

                        CustomConfig TargetData = new CustomConfig(plugin, String.valueOf(target.getUniqueId()), "Data");
                        PlayerData.getConfig().set("Current Friends." + target.getUniqueId().toString() + ".Name", target.getName());
                        TargetData.getConfig().set("Current Friends." + player.getUniqueId().toString() + ".Name", player.getName());

                        PlayerData.getConfig().set("Total Friends", PlayerData.getConfig().getInt("Total Friends") + 1);
                        TargetData.getConfig().set("Total Friends", TargetData.getConfig().getInt("Total Friends") + 1);

                        PlayerData.saveConfig();
                        TargetData.saveConfig();

                        Friend_Manager.Request.remove(player.getUniqueId());
                        Friend_Manager.Requests.remove(player.getUniqueId());

                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("Friend Message"),
                                null, target, null, false));

                    } else
                        player.sendMessage(plugin.essentialsZAPI.utils.chat("&cError! No incoming friend requests."));

                } else if (choice.equalsIgnoreCase("deny")) {
                    if (Friend_Manager.Request.containsKey(player.getUniqueId()) && Friend_Manager.Requests.containsKey(player.getUniqueId())) {
                        Player target = Bukkit.getPlayer(Friend_Manager.Requests.get(player.getUniqueId()));

                        Friend_Manager.Request.remove(player.getUniqueId());
                        Friend_Manager.Requests.remove(player.getUniqueId());

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
