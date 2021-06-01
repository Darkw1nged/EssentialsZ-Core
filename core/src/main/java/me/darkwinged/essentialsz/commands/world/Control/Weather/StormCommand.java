package me.darkwinged.essentialsz.commands.world.Control.Weather;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StormCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("storm")) {
            if (plugin.getConfig().getBoolean("Commands.Weather", true)) {
                if (!(sender instanceof Player)) {
                    if (args.length < 2) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.SpecifyWorld));
                        return true;
                    }
                    World world = Bukkit.getWorld(args[0]);
                    if (world == null) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.InvalidWorld));
                        return true;
                    }
                    world.setStorm(true);
                    world.setThundering(false);
                    sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                    plugin.MessagesFile.getConfig().getString("World Weather")
                                            .replaceAll("%world%", world.getName()).replaceAll("%type%", "clear"),
                            null, null, null, false));
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.Storm) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (args.length == 2) {
                        World world = Bukkit.getWorld(args[0]);
                        if (world == null) {
                            sender.sendMessage(ErrorManager.getErrors(Errors.InvalidWorld));
                            return true;
                        }
                        world.setStorm(true);
                        world.setThundering(false);
                        sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                        plugin.MessagesFile.getConfig().getString("World Weather")
                                                .replaceAll("%world%", world.getName()).replaceAll("%type%", "clear"),
                                null, null, null, false));
                        return true;
                    }
                    World world = player.getWorld();
                    world.setStorm(true);
                    world.setThundering(false);
                    player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                    plugin.MessagesFile.getConfig().getString("World Weather")
                                            .replaceAll("%world%", world.getName()).replaceAll("%type%", "clear"),
                            null, null, null, false));
                    return true;
                } else
                    player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
            } else
                sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));

        }
        return false;
    }

}
