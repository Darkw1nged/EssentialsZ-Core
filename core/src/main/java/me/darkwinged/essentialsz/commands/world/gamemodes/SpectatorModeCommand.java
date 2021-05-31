package me.darkwinged.essentialsz.commands.world.gamemodes;

import me.darkwinged.essentialsz.libaries.lang.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import me.darkwinged.essentialsz.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpectatorModeCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gmsp")) {
            if (plugin.getConfig().getBoolean("Commands.Gamemode", true)) {
                if (!(sender instanceof Player)) {
                    if (args.length != 1) {
                        sender.sendMessage(Errors.getErrors(Errors.GamemodeUsage));
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        sender.sendMessage(Errors.getErrors(Errors.NoPlayerFound));
                        return true;
                    }
                    String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
                            .replaceAll("%gamemode%", "spectator");
                    sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message,
                            target, target, null, false));
                    target.setGameMode(GameMode.SPECTATOR);
                    return true;
                }
                Player player = (Player) sender;
                if (args.length != 1) {
                    if (player.hasPermission(Permissions.SpectatorMode) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        String Message = plugin.MessagesFile.getConfig().getString("Gamemode").replaceAll("%gamemode%", "spectator");
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message,
                                null, null, null, false));
                        player.setGameMode(GameMode.SPECTATOR);
                    } else {
                        player.sendMessage(Errors.getErrors(Errors.NoPermission));
                    }
                } else {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (player.hasPermission(Permissions.SpectatorModeOther) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (target == null) {
                            sender.sendMessage(Errors.getErrors(Errors.NoPlayerFound));
                            return true;
                        }
                        String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
                                .replaceAll("%gamemode%", "spectator");
                        sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message,
                                target, target, null, false));
                        target.setGameMode(GameMode.SPECTATOR);
                    }
                }
            }
        }
        return true;
    }
}
