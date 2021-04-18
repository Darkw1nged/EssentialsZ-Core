package me.darkwinged.EssentialsZ.Commands.Chat;

import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Broadcast implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("broadcast")) {
            if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
                if (plugin.getConfig().getBoolean("Chat.Settings.Commands.Broadcast", true)) {
                    if (!(sender instanceof Player)) {
                        if (args.length < 1) {
                            sender.sendMessage(Errors.getErrors(Errors.MessageEmpty));
                            return true;
                        }
                        String msg = plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Broadcast Prefix"),
                                null, null, null, false);
                        for (String s : args) {
                            msg = msg + " " + s;
                        }
                        Bukkit.broadcastMessage(msg);
                        return true;
                    }
                    Player player = (Player) sender;
                    if (player.hasPermission(Permissions.Broadcast) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length < 1) {
                            sender.sendMessage(Errors.getErrors(Errors.MessageEmpty));
                            return true;
                        }
                        String msg = plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Broadcast Prefix"),
                                null, null, null, false);
                        for (String s : args) {
                            msg = msg + " " + s;
                        }
                        Bukkit.broadcastMessage(msg);
                    } else
                        player.sendMessage(Errors.getErrors(Errors.NoPermission));

                }
            }

        }
        return true;
    }

}
