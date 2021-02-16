package me.darkwinged.Essentials.Commands.Teleport;

import me.darkwinged.Essentials.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class cmd_Hub implements CommandExecutor {

    private Main plugin;
    public cmd_Hub(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("hub")) {
            if (plugin.getConfig().getBoolean("Teleportation", true)) {
                if (!(sender instanceof Player))
                    return true;
                Player p = (Player)sender;
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(b);
                if (plugin.getConfig().getBoolean("cmd_Hub", true)) {
                    try {
                        out.writeUTF("Connect");
                        out.writeUTF(plugin.getConfig().getString("Server Name"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    p.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
                } else {
                    p.sendMessage(ChatColor.RED + "Server has disabled this command!");
                    return true;
                }
            }
        }
        return true;
    }

}
