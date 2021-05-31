package me.darkwinged.essentialsz.commands.teleport;

import me.darkwinged.essentialsz.libaries.lang.CustomConfig;
import me.darkwinged.essentialsz.libaries.lang.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import me.darkwinged.essentialsz.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("sethome")) {
            if (plugin.getConfig().getBoolean("Teleportation.enabled", true)) {
                if (plugin.getConfig().getBoolean("Teleportation.Settings.Homes.enabled", true)) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(plugin.essentialsZAPI.utils.chat(Errors.getErrors(Errors.Console),
                                null, null, null, false));
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.SetHomes) || player.hasPermission(Permissions.HomesOverwrite) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length < 1) {
                            sender.sendMessage(plugin.essentialsZAPI.utils.chat(Errors.getErrors(Errors.NoHomeNameProvided),
                                    null, null, null, false));
                            return true;
                        }
                        String HomeName = args[0];

                        CustomConfig Data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
                        if (!Data.getCustomConfigFile().exists()) return true;
                        if (Data.getConfig().contains("Homes." + HomeName)) {
                            sender.sendMessage(plugin.essentialsZAPI.utils.chat(Errors.getErrors(Errors.HomeAlreadyExist),
                                    null, null, null, false));
                            return true;
                        }

                        String world = player.getWorld().getName();
                        double x = player.getLocation().getX();
                        double y = player.getLocation().getY();
                        double z = player.getLocation().getZ();
                        float pitch = player.getLocation().getPitch();
                        float yaw = player.getLocation().getYaw();

                        Data.getConfig().set("Homes." + HomeName + ".world", world);
                        Data.getConfig().set("Homes." + HomeName + ".x", x);
                        Data.getConfig().set("Homes." + HomeName + ".y", y);
                        Data.getConfig().set("Homes." + HomeName + ".z", z);
                        Data.getConfig().set("Homes." + HomeName + ".pitch", pitch);
                        Data.getConfig().set("Homes." + HomeName + ".yaw", yaw);
                        Data.saveConfig();

                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("Home")
                                        .replaceAll("%home%", HomeName).replaceAll("%setting%", "created"),
                                player, null, null, false));
                    } else
                        player.sendMessage(Errors.getErrors(Errors.NoPermission));
                } else {
                    sender.sendMessage(Errors.getErrors(Errors.DisabledCommand));
                }
            }
        }
        return true;
    }
}
