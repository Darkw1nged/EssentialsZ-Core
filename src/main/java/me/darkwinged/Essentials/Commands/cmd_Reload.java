package me.darkwinged.Essentials.Commands;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static org.bukkit.Bukkit.getServer;

public class cmd_Reload implements CommandExecutor {

    private final Main plugin;
    public cmd_Reload(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("essentials")) {
            if (sender.hasPermission(Permissions.Reload) || sender.hasPermission(Permissions.GlobalOverwrite)) {
                disable();
                enable();
                sender.sendMessage(Utils.chat("&eConfiguration files have has been reloaded."));
            } else {
                sender.sendMessage(ErrorMessages.NoPermission);
            }
        }
        return false;
    }

    private void disable() {
        // Console Stop Message
        getServer().getConsoleSender().sendMessage(Utils.chat("&cEssentialsZ plugin has been disabled!"));
        getServer().getConsoleSender().sendMessage(Utils.chat("&cDarkwinged says 'goodbye'!"));
        // Saving the accounts for all of the players
        plugin.saveAccounts();
        // Saving the accounts file
        plugin.AccountsFile.saveConfig();
    }

    public void enable() {
        // Console Start Message
        getServer().getConsoleSender().sendMessage(Utils.chat("&aEssentialsZ plugin has been enabled!"));
        getServer().getConsoleSender().sendMessage(Utils.chat("&aFun Fact! Essentials was made by darkwinged!"));
        // Loading Files
        plugin.reloadConfig();
        plugin.SpawnFile.reloadConfig();
        plugin.MessagesFile.reloadConfig();
        plugin.AccountsFile.reloadConfig();
        plugin.LoadWarpsFile();
        plugin.ChatFilterFile.reloadConfig();
        plugin.BlockedCommandsFile.reloadConfig();
        plugin.MoneyPouchesFile.reloadConfig();

        // Adding EconomyManager / loading Accounts
        plugin.loadAccounts();
    }
}
