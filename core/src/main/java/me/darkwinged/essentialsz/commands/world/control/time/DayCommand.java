package me.darkwinged.essentialsz.commands.world.control.time;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.Permission;
import me.darkwinged.essentialsz.commands.Name;
import me.darkwinged.essentialsz.commands.annotation.Permissions;
import me.darkwinged.essentialsz.inject.Inject;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Name("day")
@Permissions(value = {Permission.DAY, Permission.GLOBAL})
public class DayCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    @Inject
    public DayCommand() {}

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (plugin.getConfig().getBoolean("Commands.Time", true)) {
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
                world.setTime(1000);
                sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("World Time")
                                        .replaceAll("%world%", world.getName()).replaceAll("%time%", "day")));
                return true;
            }
            Player player = (Player)sender;
            if (args.length == 2) {
                World world = Bukkit.getWorld(args[0]);
                if (world == null) {
                    sender.sendMessage(ErrorManager.getErrors(Errors.InvalidWorld));
                    return true;
                }
                world.setTime(1000);
                sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("World Time")
                                        .replaceAll("%world%", world.getName()).replaceAll("%time%", "day")));
                return true;
            }
            World world = player.getWorld();
            world.setTime(1000);
            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                            plugin.MessagesFile.getConfig().getString("Time").replaceAll("%time%", "day")));
        } else
            sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));
        return false;
    }

}
