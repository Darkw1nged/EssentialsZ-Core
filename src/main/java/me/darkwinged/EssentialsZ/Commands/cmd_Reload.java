package me.darkwinged.EssentialsZ.Commands;

import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmd_Reload implements CommandExecutor {

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
