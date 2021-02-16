package me.darkwinged.Essentials.Commands.World.Gamemodes;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_SpectatorMode implements CommandExecutor {

    private Main plugin;
    public cmd_SpectatorMode(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gmsp")) {
            if (plugin.getConfig().getBoolean("cmd_Gamemode", true)) {
                if (!(sender instanceof Player)) {
                    if (args.length != 1) {
                        Utils.Message(sender, Errors.getErrors(Errors.GamemodeUsage));
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        Utils.Message(sender, Errors.getErrors(Errors.NoPlayerFound));
                        return true;
                    }
                    String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
                            .replaceAll("%gamemode%", "spectator")
                            .replaceAll("%player%", target.getName());
                    sender.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
                    target.setGameMode(GameMode.SPECTATOR);
                }
                Player player = (Player) sender;
                if (args.length != 1) {
                    if (player.hasPermission(Permissions.SpectatorMode) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        String Message = plugin.MessagesFile.getConfig().getString("Gamemode").replaceAll("%gamemode%", "spectator");
                        player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
                        player.setGameMode(GameMode.SPECTATOR);
                    } else {
                        Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                    }
                } else {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (player.hasPermission(Permissions.SpectatorModeOther) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (target == null) {
                            Utils.Message(sender, Errors.getErrors(Errors.NoPlayerFound));
                            return true;
                        }
                        String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
                                .replaceAll("%gamemode%", "spectator")
                                .replaceAll("%player%", target.getName());
                        sender.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
                        target.setGameMode(GameMode.SPECTATOR);
                    }
                }
            }
        }
        return true;
    }
}
