package me.darkwinged.Essentials.Commands.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Smite implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("smite")) {
            if (plugin.getConfig().getBoolean("Commands.Smite", true)) {
                if (sender.hasPermission(Permissions.Smite) || sender.hasPermission(Permissions.GlobalOverwrite)) {
                    if (args.length < 1) {
                        sender.sendMessage(plugin.essentialsZAPI.utils.chat(Errors.getErrors(Errors.SpecifyPlayer), null, null, null));
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    Location loc = target.getLocation();
                    loc.getWorld().strikeLightning(loc);
                    loc.getWorld().createExplosion(loc, 2.0F);
                    sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                            plugin.MessagesFile.getConfig().getString("Smite"), null, target, null));

                } else
                    sender.sendMessage(plugin.essentialsZAPI.utils.chat(Errors.getErrors(Errors.NoPermission), null, null, null));
            }
        }
        return false;
    }

}
