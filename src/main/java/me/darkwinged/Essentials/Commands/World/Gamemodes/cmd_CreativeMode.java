package me.darkwinged.Essentials.Commands.World.Gamemodes;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Libaries.Lang.Errors;
import me.darkwinged.Essentials.Libaries.Lang.Permissions;
import me.darkwinged.Essentials.Libaries.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_CreativeMode implements CommandExecutor {

    private final Main plugin;
    public cmd_CreativeMode(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gmc")) {
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
                            .replaceAll("%gamemode%", "creative")
                            .replaceAll("%player%", target.getName());
                    sender.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
                    target.setGameMode(GameMode.CREATIVE);
                }
                Player player = (Player) sender;
                if (args.length != 1) {
                    if (player.hasPermission(Permissions.CreativeMode) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        String Message = plugin.MessagesFile.getConfig().getString("Gamemode").replaceAll("%gamemode%", "creative");
                        player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
                        player.setGameMode(GameMode.CREATIVE);
                    } else {
                        Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                    }
                } else {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (player.hasPermission(Permissions.CreativeModeOther) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (target == null) {
                            Utils.Message(sender, Errors.getErrors(Errors.NoPlayerFound));
                            return true;
                        }
                        String Message = plugin.MessagesFile.getConfig().getString("Gamemode Other")
                                .replaceAll("%gamemode%", "creative")
                                .replaceAll("%player%", target.getName());
                        sender.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") + Message));
                        target.setGameMode(GameMode.CREATIVE);
                    }
                }
            }
        }
        return true;
    }
}
