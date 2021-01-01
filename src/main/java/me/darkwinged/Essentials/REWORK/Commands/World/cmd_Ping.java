package me.darkwinged.Essentials.REWORK.Commands.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.MessagesFile;
import me.darkwinged.Essentials.REWORK.Utils.ErrorMessages;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import me.darkwinged.Essentials.REWORK.Utils.PlayersPing;
import me.darkwinged.Essentials.REWORK.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Ping implements CommandExecutor {

    private MessagesFile messagesFile;
    private Main plugin;
    public cmd_Ping(MessagesFile messagesFile, Main plugin) {
        this.messagesFile = messagesFile;
        this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ping")) {
            if (plugin.getConfig().getBoolean("cmd_Ping", true)) {
                if (!(sender instanceof Player)) {
                    if (args.length < 1) {
                        sender.sendMessage(ErrorMessages.NoPlayer);
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    sender.sendMessage(Utils.chat(messagesFile.getConfig().getString("Prefix") + messagesFile.getConfig().getString("Ping Player message")
                            .replaceAll("%player%", target.getName())
                            .replaceAll("%ping%",""+PlayersPing.getPing(target))));
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.Ping) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (args.length < 1) {
                        player.sendMessage(Utils.chat(messagesFile.getConfig().getString("Prefix") + messagesFile.getConfig().getString("Ping message")
                                .replaceAll("%ping%", ""+PlayersPing.getPing(player))));
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if (player.hasPermission(Permissions.PingOther) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        player.sendMessage(Utils.chat(messagesFile.getConfig().getString("Prefix") + messagesFile.getConfig().getString("Ping Player message")
                                .replaceAll("%player%", target.getName())
                                .replaceAll("%ping%",""+PlayersPing.getPing(target))));
                    } else {
                        player.sendMessage(ErrorMessages.NoPermission);
                    }

                } else {
                    player.sendMessage(ErrorMessages.NoPermission);
                }
            }
        }
        return false;
    }
}
