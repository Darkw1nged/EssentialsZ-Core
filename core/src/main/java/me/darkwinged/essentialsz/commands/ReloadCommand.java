package me.darkwinged.essentialsz.commands;

import me.darkwinged.essentialsz.libaries.lang.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import me.darkwinged.essentialsz.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("essentials")) {
            if (sender.hasPermission(Permissions.Reload) || sender.hasPermission(Permissions.GlobalOverwrite)) {
                enable();
                sender.sendMessage(plugin.essentialsZAPI.utils.chat("&eConfiguration files have has been reloaded.", null, null, null, false));
            } else {
                sender.sendMessage(Errors.getErrors(Errors.NoPermission));
            }
        }
        return false;
    }

    public void enable() {
        plugin.reloadConfig();
        plugin.MessagesFile.reloadConfig();
        plugin.ChatFilterFile.reloadConfig();
        plugin.BlockedCommandsFile.reloadConfig();
        plugin.MoneyPouchesFile.reloadConfig();
        plugin.WorthFile.reloadConfig();
        plugin.CouponsFile.reloadConfig();
    }
}
