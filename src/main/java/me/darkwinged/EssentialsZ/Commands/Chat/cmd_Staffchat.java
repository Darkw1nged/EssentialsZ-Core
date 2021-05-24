package me.darkwinged.EssentialsZ.Commands.Chat;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Libaries.Lang.Utils;
import me.darkwinged.EssentialsZ.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_Staffchat implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("staffchat")) {
            if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
                if (plugin.getConfig().getBoolean("Chat.Settings.Staff chat.enabled", true)) {
                    if (!(sender instanceof Player)) {
                        if (!(args.length >= 1)) {
                            sender.sendMessage(Errors.getErrors(Errors.MessageEmpty));
                            return true;
                        }
                        String msg = Utils.chat(plugin.getConfig().getString("Chat.Settings.Staff chat.Format").replaceAll("%player%", sender.getName()));
                        for (String s : args) {
                            msg = msg + " " + s;
                        }

                        ByteArrayDataOutput out = ByteStreams.newDataOutput();

                        for (Player online : Bukkit.getOnlinePlayers()) {
                            if (online.hasPermission(Permissions.StaffChat) || online.hasPermission(Permissions.GlobalOverwrite)) {
                                if (!Utils.staff_chat.contains(online.getUniqueId()))
                                    out.writeUTF("MessageRaw");
                                    out.writeUTF(String.valueOf(online));
                                    out.writeUTF("{\"text\":\"" + msg + "\"}");
                            }
                        }
                        return true;
                    }
                    Player player = (Player) sender;
                    if (player.hasPermission(Permissions.StaffChat) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (!(args.length >= 1)) {
                            player.sendMessage(Errors.getErrors(Errors.MessageEmpty));
                            return true;
                        }

                        String msg = Utils.chat(plugin.getConfig().getString("Chat.Settings.Staff chat.Format").replaceAll("%player%", player.getName()));
                        for (String s : args) {
                            msg = msg + " " + s;
                        }
                        ByteArrayDataOutput out = ByteStreams.newDataOutput();
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            if (online.hasPermission(Permissions.StaffChat) || online.hasPermission(Permissions.GlobalOverwrite)) {
                                if (!Utils.staff_chat.contains(online.getUniqueId()))
                                    out.writeUTF("MessageRaw");
                                out.writeUTF(String.valueOf(online));
                                out.writeUTF("{\"text\":\"" + msg + "\"}");
                            }
                        }
                    } else
                        player.sendMessage(Errors.getErrors(Errors.NoPermission));
                }
            }
        }
        return false;
    }

}
