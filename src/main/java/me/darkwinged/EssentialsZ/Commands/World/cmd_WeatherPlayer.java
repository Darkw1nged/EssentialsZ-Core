package me.darkwinged.EssentialsZ.Commands.World;

import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Main;
import org.bukkit.WeatherType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_WeatherPlayer implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("pweather")) {
            if (plugin.getConfig().getBoolean("Commands.Player Weather", true)) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Errors.getErrors(Errors.Console));
                    return true;
                }
                Player player = (Player) sender;
                if (player.hasPermission(Permissions.PlayerWeather) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (args[0].equalsIgnoreCase("clear") || args[0].equalsIgnoreCase("sun") || args[0].equalsIgnoreCase("sunny")) {
                        player.setPlayerWeather(WeatherType.CLEAR);
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                        plugin.MessagesFile.getConfig().getString("Weather")
                                                .replaceAll("%type%", "clear"),
                                null, null, null, false));
                    } else if (args[0].equalsIgnoreCase("rain") || args[0].equalsIgnoreCase("storm")) {
                        player.setPlayerWeather(WeatherType.DOWNFALL);
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                        plugin.MessagesFile.getConfig().getString("Weather")
                                                .replaceAll("%type%", "rain"),
                                null, null, null, false));
                    }
                } else
                    player.sendMessage(Errors.getErrors(Errors.NoPermission));

            }
        }
        return false;
    }

}
