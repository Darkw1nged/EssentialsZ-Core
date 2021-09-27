package me.darkwinged.essentialsz.commands;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.Permission;
import me.darkwinged.essentialsz.commands.annotation.Permissions;
import me.darkwinged.essentialsz.inject.Inject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

@Name("essentials")
@Permissions(value = {Permission.RELOAD, Permission.GLOBAL})
public class ReloadCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    @Inject
    public ReloadCommand() {}

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        enable();
        sender.sendMessage(plugin.essentialsZAPI.utils.chat("&eConfiguration files have has been reloaded."));
        return false;
    }

    public void enable() {
        plugin.reloadConfig();
        plugin.AutoMessagesFile.reloadConfig();
        plugin.BlockedCommandsFile.reloadConfig();
        plugin.ChatFilterFile.reloadConfig();
        plugin.MessagesFile.reloadConfig();
        plugin.CouponsFile.reloadConfig();
        plugin.ChatGamesFile.reloadConfig();
        plugin.ServerCacheFile.reloadConfig();
        plugin.ServerDataFile.reloadConfig();
        plugin.WorthFile.reloadConfig();
    }
}
