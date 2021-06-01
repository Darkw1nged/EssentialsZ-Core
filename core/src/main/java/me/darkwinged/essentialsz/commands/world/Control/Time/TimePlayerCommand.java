package me.darkwinged.essentialsz.commands.world.Control.Time;


import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimePlayerCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ptime")) {
            if (plugin.getConfig().getBoolean("Commands.Time Weather", true)) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ErrorManager.getErrors(Errors.Console));
                    return true;
                }
                Player player = (Player) sender;
                World world = player.getWorld();
                if (player.hasPermission(Permissions.PlayerTime) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    long time = Long.parseLong(args[1]);
                    world.setTime(time);
                    sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                    plugin.MessagesFile.getConfig().getString("World Time")
                                            .replaceAll("%world%", world.getName()).replaceAll("%time%", String.valueOf(time)),
                            null, null, null, false));
                } else
                    player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));

            } else
                sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));
        }
        return false;
    }

}
