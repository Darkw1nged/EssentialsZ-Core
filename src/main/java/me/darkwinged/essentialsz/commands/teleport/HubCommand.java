package me.darkwinged.essentialsz.commands.teleport;

import me.darkwinged.essentialsz.libaries.lang.Errors;
import me.darkwinged.essentialsz.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class HubCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("hub")) {
            if (plugin.getConfig().getBoolean("Teleportation.enabled", true)) {
                if (plugin.getConfig().getBoolean("Teleportation.Settings.Hub.enabled", true)) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(Errors.getErrors(Errors.Console));
                        return true;
                    }
                    Player player = (Player)sender;
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(b);
                    try {
                        out.writeUTF("Connect");
                        out.writeUTF(plugin.getConfig().getString("Teleportation.Settings.Hub.Server Name"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());

                } else {
                    sender.sendMessage(Errors.getErrors(Errors.DisabledCommand));
                }
            }
        }
        return true;
    }

}
