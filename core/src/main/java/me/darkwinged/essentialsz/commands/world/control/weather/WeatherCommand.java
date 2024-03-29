package me.darkwinged.essentialsz.commands.world.control.weather;

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

public class WeatherCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("weather")) {
            if (plugin.getConfig().getBoolean("Commands.Weather", true)) {
                if (!(sender instanceof Player)) {
                    if (args.length < 2) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.SpecifyWorld));
                        sender.sendMessage(ErrorManager.getErrors(Errors.WeatherType));
                        return true;
                    }
                    World world = Bukkit.getWorld(args[0]);
                    if (world == null) {
                        sender.sendMessage(ErrorManager.getErrors(Errors.InvalidWorld));
                        return true;
                    }
                    if (args[1].equalsIgnoreCase("clear") || args[1].equalsIgnoreCase("sun") || args[1].equalsIgnoreCase("sunny")) {
                        world.setStorm(false);
                        world.setThundering(false);
                        sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                        plugin.MessagesFile.getConfig().getString("World Weather")
                                                .replaceAll("%world%", world.getName()).replaceAll("%type%", "clear"),
                                null, null, null, false));
                    } else if (args[1].equalsIgnoreCase("rain") || args[1].equalsIgnoreCase("storm")) {
                        world.setStorm(true);
                        world.setThundering(false);
                        sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                        plugin.MessagesFile.getConfig().getString("World Weather")
                                                .replaceAll("%world%", world.getName()).replaceAll("%type%", "rain"),
                                null, null, null, false));
                    } else if (args[1].equalsIgnoreCase("thunder") || args[1].equalsIgnoreCase("lightning")) {
                        world.setStorm(true);
                        world.setThundering(true);
                        sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                        plugin.MessagesFile.getConfig().getString("World Weather")
                                                .replaceAll("%world%", world.getName()).replaceAll("%type%", "storm"),
                                null, null, null, false));
                    }
                    return true;
                }
                Player player = (Player) sender;
                if (player.hasPermission(Permissions.WorldWeather) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (args.length == 2) {
                        World world = Bukkit.getWorld(args[0]);
                        if (world == null) {
                            player.sendMessage(ErrorManager.getErrors(Errors.InvalidWorld));
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("clear") || args[1].equalsIgnoreCase("sun") || args[1].equalsIgnoreCase("sunny")) {
                            world.setStorm(false);
                            world.setThundering(false);
                            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                            plugin.MessagesFile.getConfig().getString("World Weather")
                                                    .replaceAll("%world%", world.getName()).replaceAll("%type%", "clear"),
                                    null, null, null, false));
                        } else if (args[1].equalsIgnoreCase("rain") || args[1].equalsIgnoreCase("storm")) {
                            world.setStorm(true);
                            world.setThundering(false);
                            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                            plugin.MessagesFile.getConfig().getString("World Weather")
                                                    .replaceAll("%world%", world.getName()).replaceAll("%type%", "rain"),
                                    null, null, null, false));
                        } else if (args[1].equalsIgnoreCase("thunder") || args[1].equalsIgnoreCase("lightning")) {
                            world.setStorm(true);
                            world.setThundering(true);
                            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                            plugin.MessagesFile.getConfig().getString("World Weather")
                                                    .replaceAll("%world%", world.getName()).replaceAll("%type%", "storm"),
                                    null, null, null, false));
                        }
                        return true;
                    } else {
                        World world = player.getWorld();
                        if (args[0].equalsIgnoreCase("clear") || args[0].equalsIgnoreCase("sun") || args[0].equalsIgnoreCase("sunny")) {
                            world.setStorm(false);
                            world.setThundering(false);
                            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                            plugin.MessagesFile.getConfig().getString("Weather")
                                                    .replaceAll("%type%", "clear"),
                                    null, null, null, false));
                        } else if (args[0].equalsIgnoreCase("rain") || args[0].equalsIgnoreCase("storm")) {
                            world.setStorm(true);
                            world.setThundering(false);
                            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                            plugin.MessagesFile.getConfig().getString("Weather")
                                                    .replaceAll("%type%", "rain"),
                                    null, null, null, false));
                        } else if (args[0].equalsIgnoreCase("thunder") || args[0].equalsIgnoreCase("lightning")) {
                            world.setStorm(true);
                            world.setThundering(true);
                            player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                            plugin.MessagesFile.getConfig().getString("Weather")
                                                    .replaceAll("%type%", "storm"),
                                    null, null, null, false));
                        }
                    }
                } else
                    player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));

            }
        }
        return false;
    }

}
