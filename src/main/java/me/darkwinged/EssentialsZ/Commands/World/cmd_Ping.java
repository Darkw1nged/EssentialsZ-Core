package me.darkwinged.EssentialsZ.Commands.World;

import me.darkwinged.EssentialsZ.Main;
import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Libaries.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Ping implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ping")) {
            if (plugin.getConfig().getBoolean("Commands.Ping", true)) {
                if (!(sender instanceof Player)) {
                    if (args.length < 1) {
                        Utils.Message(sender, Errors.getErrors(Errors.NoPlayerFound));
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    sender.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + plugin.MessagesFile.getConfig().getString("Ping Player message")
                            .replaceAll("%player%", target.getName())
                            .replaceAll("%ping%",""+plugin.essentialsZAPI.utils.getPlayerPing(target))));
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.Ping) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (args.length < 1) {
                        player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + plugin.MessagesFile.getConfig().getString("Ping message")
                                .replaceAll("%ping%", ""+plugin.essentialsZAPI.utils.getPlayerPing(player))));
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if (player.hasPermission(Permissions.PingOther) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + plugin.MessagesFile.getConfig().getString("Ping Player message")
                                .replaceAll("%player%", target.getName())
                                .replaceAll("%ping%",""+plugin.essentialsZAPI.utils.getPlayerPing(target))));
                    } else {
                        Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                    }

                } else {
                    Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                }
            }
        }
        return false;
    }
}
