package me.darkwinged.Essentials.Commands.Teleport;

import me.darkwinged.Essentials.Libaries.Lang.Errors;
import me.darkwinged.Essentials.Libaries.Lang.Permissions;
import me.darkwinged.Essentials.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class cmd_Warps implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("warps")) {
            if (plugin.getConfig().getBoolean("Teleportation.enabled", true)) {
                if (plugin.getConfig().getBoolean("Teleportation.Settings.Warps.enabled", true)) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(Errors.getErrors(Errors.Console));
                        return true;
                    }
                    Player player = (Player) sender;
                    String warp_list = "";
                    if (player.hasPermission(Permissions.Warps) || player.hasPermission(Permissions.WarpsOverwrite) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        File folder = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "Warps");
                        if (!folder.exists() || folder.listFiles().length == 0) {
                            player.sendMessage(Errors.getErrors(Errors.NoWarpsFound));
                            return true;
                        }
                        for (File file : new File(String.valueOf(folder)).listFiles()) {
                            FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
                            warp_list = warp_list + configuration.getString("name");
                        }
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("Teleportation.Settings.Warps.Format")
                                .replaceAll("%warps%", warp_list), null, null, null, false));
                    } else
                        player.sendMessage(Errors.getErrors(Errors.NoPermission));
                }
            }

        }
        return true;
    }

}
