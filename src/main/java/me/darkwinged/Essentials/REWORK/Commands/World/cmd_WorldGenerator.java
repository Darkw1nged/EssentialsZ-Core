package me.darkwinged.Essentials.REWORK.Commands.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.ErrorMessages;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_WorldGenerator implements CommandExecutor {

    private Main plugin;
    public cmd_WorldGenerator(Main plugin) { this.plugin = plugin; }


    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("world")) {
            if (plugin.getConfig().getBoolean("World_Creation", true)) {
                if (!(sender instanceof Player)) {
                    if (args.length != 6) {
                        sender.sendMessage(ErrorMessages.WorldGenUsage);
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("create")) {
                        String name = args[1];
                        WorldCreator world = new WorldCreator(name);
                        switch (args[2]) {
                            case "normal":
                                world.environment(World.Environment.NORMAL);
                                simpler(args, world);
                                break;
                            case "nether":
                                world.environment(World.Environment.NETHER);
                                simpler(args, world);
                                break;
                            case "end":
                                world.environment(World.Environment.THE_END);
                                simpler(args, world);
                                break;
                        }
                        world.createWorld();
                    }
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.CreateWorld) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (args.length != 6) {
                        player.sendMessage(ErrorMessages.WorldGenUsage);
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("create")) {
                        String name = args[1];
                        WorldCreator world = new WorldCreator(name);
                        switch (args[2]) {
                            case "normal":
                                world.environment(World.Environment.NORMAL);
                                simpler(args, world);
                                break;
                            case "nether":
                                world.environment(World.Environment.NETHER);
                                simpler(args, world);
                                break;
                            case "end":
                                world.environment(World.Environment.THE_END);
                                simpler(args, world);
                                break;
                        }
                        world.createWorld();
                    }
                } else {
                    player.sendMessage(ErrorMessages.NoPermission);
                }
            }
        }
        return false;
    }

    private void compact(String[] args, WorldCreator world) {
        switch (args[4]) {
            case "true":
                world.hardcore(true);
                switch (args[5]) {
                    case "true":
                        world.generateStructures(true);
                        break;
                    case "false":
                        world.generateStructures(false);
                        break;
                }
                break;
            case "false":
                world.hardcore(false);
                switch (args[5]) {
                    case "true":
                        world.generateStructures(true);
                        break;
                    case "false":
                        world.generateStructures(false);
                        break;
                }
                break;
        }
    }

    private void simpler(String[] args, WorldCreator world) {
        switch (args[3]) {
            case "normal":
                world.type(WorldType.NORMAL);
                compact(args, world);
                break;
            case "flat":
                world.type(WorldType.FLAT);
                compact(args, world);
                break;
            case "amplified":
                world.type(WorldType.AMPLIFIED);
                compact(args, world);
                break;
            case "largebiomes":
                world.type(WorldType.LARGE_BIOMES);
                compact(args, world);
                break;
        }
    }

}
