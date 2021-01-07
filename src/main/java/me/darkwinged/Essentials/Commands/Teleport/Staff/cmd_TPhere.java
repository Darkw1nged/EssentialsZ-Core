package me.darkwinged.Essentials.Commands.Teleport.Staff;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_TPhere implements CommandExecutor {

    private Main plugin;
    public cmd_TPhere(Main plugin) {
        this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tphere")) {
            if (plugin.getConfig().getBoolean("Teleportation", true)) {
                if (plugin.getConfig().getBoolean("cmd_TPhere", true)) {
                    if (!(sender instanceof Player)) {
                        Utils.Message(sender, Errors.getErrors(Errors.Console));
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.TPhere) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length != 1) {
                            Utils.Message(sender, Errors.getErrors(Errors.TPhereUsage));
                        } else {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target == null) {
                                Utils.Message(sender, Errors.getErrors(Errors.NoPlayerFound));
                                return true;
                            }
                            if (target == sender) {
                                Utils.Message(sender, Errors.getErrors(Errors.SenderInstaceOfPlayer));
                                return true;
                            }
                            String Message = Utils.chat(plugin.MessagesFile.getConfig().getString("TPhere message")
                                    .replaceAll("%player%", target.getDisplayName()));
                            player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix")) + Message);
                            target.teleport(player);
                        }
                        return true;
                    } else {
                        Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                    }
                }
            }
        }
        return false;
    }


}
