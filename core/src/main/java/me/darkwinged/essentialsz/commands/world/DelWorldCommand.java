package me.darkwinged.essentialsz.commands.world;

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

import java.io.File;

public class DelWorldCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("delworld")) {
            if (plugin.getConfig().getBoolean("Commands.World Creation", true)) {
                if (!(sender instanceof Player)) {
                    if (args.length != 1) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.InvalidWorld));
                        return true;
                    }
                    if (Bukkit.getWorld(args[0]) != null) {
                        World delete = Bukkit.getWorld(args[0]);
                        File deleteFolder = delete.getWorldFolder();
                        deleteWorld(deleteFolder);
                    }
                    return true;
                }
                Player player = (Player) sender;
                if (player.hasPermission(Permissions.RemoveWorld) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (args.length != 1) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.InvalidWorld));
                        return true;
                    }
                    if (Bukkit.getWorld(args[0]) != null) {
                        World delete = Bukkit.getWorld(args[0]);
                        File deleteFolder = delete.getWorldFolder();
                        deleteWorld(deleteFolder);
                    }
                } else {
                    sender.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
                }

            }
        }
        return false;
    }

    private boolean deleteWorld(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteWorld(file);
                } else {
                    file.delete();
                }
            }
        }
        return (path.delete());
    }

}
