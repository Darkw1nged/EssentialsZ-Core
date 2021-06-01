package me.darkwinged.essentialsz.commands.world;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SmiteCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("smite")) {
            if (plugin.getConfig().getBoolean("Commands.Smite", true)) {
                if (sender.hasPermission(Permissions.Smite) || sender.hasPermission(Permissions.GlobalOverwrite)) {
                    if (args.length < 1) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.SpecifyPlayer));
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    Location loc = target.getLocation();
                    loc.getWorld().strikeLightning(loc);
                    loc.getWorld().createExplosion(loc, 2.0F);
                    sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                            plugin.MessagesFile.getConfig().getString("Smite"), target, target, null, false));

                } else
                    sender.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
            }
        }
        return false;
    }

}
