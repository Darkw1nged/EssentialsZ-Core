package me.darkwinged.essentialsz.commands.teleport;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class WarpsCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("warps")) {
            if (plugin.getConfig().getBoolean("Teleportation.enabled", true)) {
                if (plugin.getConfig().getBoolean("Teleportation.Settings.Warps.enabled", true)) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.Console));
                        return true;
                    }
                    Player player = (Player) sender;
                    String warp_list = "";
                    if (player.hasPermission(Permissions.Warps) || player.hasPermission(Permissions.WarpsOverwrite) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        File folder = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "Warps");
                        if (!folder.exists() || folder.listFiles().length == 0) {
                            player.sendMessage(ErrorManager.getErrors(Errors.NoWarpsFound));
                            return true;
                        }
                        for (File file : new File(String.valueOf(folder)).listFiles()) {
                            FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
                            warp_list = warp_list + configuration.getString("name");
                        }
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("Teleportation.Settings.Warps.Format")
                                .replaceAll("%warps%", warp_list), null, null, null, false));
                    } else
                        player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
                } else {
                    sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));
                }
            }

        }
        return true;
    }

}
