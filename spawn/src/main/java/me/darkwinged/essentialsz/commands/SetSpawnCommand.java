package me.darkwinged.essentialsz.commands;

import me.darkwinged.essentialsz.EssentialsZSpawn;
import me.darkwinged.essentialsz.utils.Utils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    private final EssentialsZSpawn plugin = EssentialsZSpawn.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("setspawn")) {
            if (!plugin.isEnabled) return true;
            if (!(sender instanceof Player)) {
                sender.sendMessage(Utils.chat("&cError! You can not do this command in console!"));
                return true;
            }
            Player player = (Player) sender;
            if (player.hasPermission("EssentialsZ.Spawn.Set") || player.hasPermission("EssentialsZ.*")) {
                String world = player.getWorld().getName();
                double x = player.getLocation().getX();
                double y = player.getLocation().getY();
                double z = player.getLocation().getZ();
                float pitch = player.getLocation().getPitch();
                float yaw = player.getLocation().getYaw();

                plugin.SpawnLocation.getConfig().set("world", world);
                plugin.SpawnLocation.getConfig().set("x", x);
                plugin.SpawnLocation.getConfig().set("y", y);
                plugin.SpawnLocation.getConfig().set("z", z);
                plugin.SpawnLocation.getConfig().set("pitch", pitch);
                plugin.SpawnLocation.getConfig().set("yaw", yaw);
                plugin.SpawnLocation.saveConfig();

                player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                        plugin.MessagesFile.getConfig().getString("Set Spawn Message")));

                Location loc = new Location(player.getWorld(), x, y, z, yaw, pitch);
                Location conor1 = new Location(player.getWorld(), x + plugin.radius, y + plugin.radius, z + plugin.radius, yaw, pitch);
                Location conor2 = new Location(player.getWorld(), x - plugin.radius, y - plugin.radius, z - plugin.radius, yaw, pitch);

                if (!plugin.Location_Spawn.isEmpty()) plugin.Location_Spawn.clear();
                plugin.Location_Spawn.add(loc);
                plugin.Spawn_Conor_One.put(loc, conor1);
                plugin.Spawn_Conor_Two.put(loc, conor2);
            }
        }
        return true;
    }

}
