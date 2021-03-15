package me.darkwinged.Essentials.Commands.Chat;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Message implements CommandExecutor {

    private Main plugin;
    public cmd_Message(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("message")) {
            if (!(sender instanceof Player)) {
                if (args.length < 2) {
                    sender.sendMessage(Errors.getErrors(Errors.SpecifyPlayer));
                    sender.sendMessage(Errors.getErrors(Errors.MessageEmpty));
                    return true;
                }
                Player target = Bukkit.getPlayer(args[0]);
                String msg = Utils.chat(plugin.getConfig().getString("Chat.Settings.Message.Format").replaceAll("%player%", sender.getName()));
                for (String s : args) {
                    msg = msg + " " + s;
                }
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.hasPermission(Permissions.StaffChat) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (!Utils.staff_chat.contains(player))
                            player.sendMessage(msg);
                    }
                }

            }

        }

        return false;
    }
}
