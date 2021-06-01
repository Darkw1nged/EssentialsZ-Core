package me.darkwinged.essentialsz.commands.world;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SuicideCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("suicide")) {
            if (plugin.getConfig().getBoolean("Commands.Suicide", true)) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ErrorManager.getErrors(Errors.Console));
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.Suicide) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    player.setHealth(0);
                } else
                    player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
            } else
                sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));
        }
        return false;
    }

}
