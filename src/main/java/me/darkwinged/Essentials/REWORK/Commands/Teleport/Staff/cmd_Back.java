package me.darkwinged.Essentials.REWORK.Commands.Teleport.Staff;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.MessagesFile;
import me.darkwinged.Essentials.REWORK.Utils.ErrorMessages;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import me.darkwinged.Essentials.REWORK.Utils.Utils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class cmd_Back implements CommandExecutor {

    private MessagesFile messagesFile;
    private Main plugin;
    public cmd_Back(Main plugin, MessagesFile messagesFile) {
        this.plugin = plugin;
        this.messagesFile = messagesFile;
    }

    public static HashMap<UUID, Location> back_loc = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("back")) {
            if (plugin.getConfig().getBoolean("cmd_Back", true)) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ErrorMessages.Console);
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.BackTeleport) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (!back_loc.containsKey(player.getUniqueId())) {
                        player.sendMessage(ErrorMessages.NoPreviousLocation);
                        return true;
                    }
                    player.teleport(back_loc.get(player.getUniqueId()));
                    back_loc.remove(player.getUniqueId());
                    player.sendMessage(Utils.chat(messagesFile.getConfig().getString("Prefix") + messagesFile.getConfig().getString("Back Message")));
                } else {
                    player.sendMessage(ErrorMessages.NoPermission);
                }
            }
        }
        return false;
    }

}
