package me.darkwinged.EssentialsZ.Commands;

import me.darkwinged.EssentialsZ.Main;
import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Libaries.Lang.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static org.bukkit.Bukkit.getServer;

public class cmd_Reload implements CommandExecutor {

    private final Main plugin;
    public cmd_Reload(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("essentials")) {
            if (sender.hasPermission(Permissions.Reload) || sender.hasPermission(Permissions.GlobalOverwrite)) {
                disable();
                enable();
                sender.sendMessage(Utils.chat("&eConfiguration files have has been reloaded."));
            } else {
                Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
            }
        }
        return false;
    }

    private void disable() {
        // Console Stop Message
        getServer().getConsoleSender().sendMessage(Utils.chat("&cEssentialsZ plugin has been disabled!"));
    }

    public void enable() {
        // Loading Files
        plugin.reloadConfig();
        plugin.MessagesFile.reloadConfig();
        plugin.ChatFilterFile.reloadConfig();
        plugin.BlockedCommandsFile.reloadConfig();
        plugin.MoneyPouchesFile.reloadConfig();
        plugin.WorthFile.reloadConfig();
        plugin.CouponsFile.reloadConfig();

        // Console Start Message
        getServer().getConsoleSender().sendMessage(Utils.chat("&aEssentialsZ plugin has been enabled!"));
    }
}
