package me.darkwinged.Essentials.Commands.Chat;

import me.darkwinged.Essentials.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmd_Reply implements CommandExecutor {

    private Main plugin;

    public cmd_Reply(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("reply")) {

        }

        return false;
    }
}