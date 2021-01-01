package me.darkwinged.Essentials.REWORK.Commands;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.*;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.BlockedCommandsFile;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.ChatFilterFile;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.MessagesFile;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Economy.AccountsFile;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Economy.MoneyPouchFile;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Telepotation.SpawnFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static org.bukkit.Bukkit.getServer;

public class cmd_Reload implements CommandExecutor {

    private final Main plugin;
    private final MoneyPouchFile moneyPouchFile;
    private final MessagesFile messagesFile;
    private final AccountsFile accountsFile;
    private final ChatFilterFile chatFilterFile;
    private final BlockedCommandsFile blockedCommandsFile;
    private final SpawnFile spawnFile;
    public cmd_Reload(Main plugin, MoneyPouchFile moneyPouchFile, MessagesFile messagesFile, AccountsFile accountsFile, ChatFilterFile chatFilterFile,
                      BlockedCommandsFile blockedCommandsFile, SpawnFile spawnFile) {
        this.plugin = plugin;
        this.moneyPouchFile = moneyPouchFile;
        this.messagesFile = messagesFile;
        this.accountsFile = accountsFile;
        this.chatFilterFile = chatFilterFile;
        this.blockedCommandsFile = blockedCommandsFile;
        this.spawnFile = spawnFile;
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
        accountsFile.saveConfig();
    }

    public void enable() {
        // Console Start Message
        getServer().getConsoleSender().sendMessage(Utils.chat("&aEssentialsZ plugin has been enabled!"));
        getServer().getConsoleSender().sendMessage(Utils.chat("&aFun Fact! Essentials was made by darkwinged!"));
        // Loading Files
        plugin.reloadConfig();
        spawnFile.reloadConfig();
        messagesFile.reloadConfig();
        accountsFile.reloadConfig();
        plugin.LoadWarpsFile();
        chatFilterFile.reloadConfig();
        blockedCommandsFile.reloadConfig();
        moneyPouchFile.reloadConfig();

        // Adding EconomyManager / loading Accounts
        plugin.loadAccounts();
    }
}
