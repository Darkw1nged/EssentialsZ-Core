package me.darkwinged.Essentials.REWORK.Commands.World.Gamemodes;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.MessagesFile;
import me.darkwinged.Essentials.REWORK.Utils.ErrorMessages;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import me.darkwinged.Essentials.REWORK.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_SurvivalMode implements CommandExecutor {

    private MessagesFile messagesFile;
    private Main plugin;
    public cmd_SurvivalMode(MessagesFile messagesFile, Main plugin) {
        this.plugin = plugin;
        this.messagesFile = messagesFile;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gms")) {
            if (plugin.getConfig().getBoolean("cmd_Gamemode", true)) {
                if (!(sender instanceof Player)) {
                    if (args.length != 1) {
                        sender.sendMessage(ErrorMessages.GamemodeUsage);
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        sender.sendMessage(ErrorMessages.NoPlayerFound);
                        return true;
                    }
                    String Message = messagesFile.getConfig().getString("Gamemode Other")
                            .replaceAll("%gamemode%", "survival")
                            .replaceAll("%player%", target.getName());
                    sender.sendMessage(Utils.chat(messagesFile.getConfig().getString("Prefix") + Message));
                    target.setGameMode(GameMode.SURVIVAL);
                }
                Player player = (Player) sender;
                if (args.length != 1) {
                    if (player.hasPermission(Permissions.SurvivalMode) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        String Message = messagesFile.getConfig().getString("Gamemode").replaceAll("%gamemode%", "survival");
                        player.sendMessage(Utils.chat(messagesFile.getConfig().getString("Prefix") + Message));
                        player.setGameMode(GameMode.SURVIVAL);
                    } else {
                        player.sendMessage(ErrorMessages.NoPermission);
                    }
                } else {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (player.hasPermission(Permissions.SurvivalModeOther) || player.hasPermission(Permissions.GamemodeGlobal) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (target == null) {
                            sender.sendMessage(ErrorMessages.NoPlayerFound);
                            return true;
                        }
                        String Message = messagesFile.getConfig().getString("Gamemode Other")
                                .replaceAll("%gamemode%", "survival")
                                .replaceAll("%player%", target.getName());
                        sender.sendMessage(Utils.chat(messagesFile.getConfig().getString("Prefix") + Message));
                        target.setGameMode(GameMode.SURVIVAL);
                    }
                }
            }
        }
        return true;
    }
}
