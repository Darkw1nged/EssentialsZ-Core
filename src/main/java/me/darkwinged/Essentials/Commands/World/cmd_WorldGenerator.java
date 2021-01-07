package me.darkwinged.Essentials.Commands.World;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lang.ErrorMessages;
import me.darkwinged.Essentials.Utils.Lang.Errors;
import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class cmd_WorldGenerator implements CommandExecutor {

    private Main plugin;
    public cmd_WorldGenerator(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("world")) {
            if (plugin.getConfig().getBoolean("World_Creation", true)) {
                if (!(sender instanceof Player)) {
                    if (args.length != 6) {
                        Utils.Message(sender, Errors.getErrors(Errors.WorldGenUsage));
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
                        Utils.Message(sender, Errors.getErrors(Errors.WorldGenUsage));
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
                    Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
                }
            }
        } else if (cmd.getName().equalsIgnoreCase("delworld")) {
            if (!(sender instanceof Player)) {
                if (args.length != 1) {
                    Utils.Message(sender, Errors.getErrors(Errors.InvalidWorld));
                    return true;
                }
                if (Bukkit.getWorld(args[0]) != null) {
                    World delete = Bukkit.getWorld(args[0]);
                    File deleteFolder = delete.getWorldFolder();
                    deleteWorld(deleteFolder);
                }
                return true;
            }
            Player player = (Player)sender;
            if (player.hasPermission(Permissions.RemoveWorld) || player.hasPermission(Permissions.GlobalOverwrite)) {
                if (args.length != 1) {
                    Utils.Message(sender, Errors.getErrors(Errors.InvalidWorld));
                    return true;
                }
                if (Bukkit.getWorld(args[0]) != null) {
                    World delete = Bukkit.getWorld(args[0]);
                    File deleteFolder = delete.getWorldFolder();
                    deleteWorld(deleteFolder);
                }
            } else {
                Utils.Message(sender, Errors.getErrors(Errors.NoPermission));
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

    private boolean deleteWorld(File path) {
        if(path.exists()) {
            File files[] = path.listFiles();
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteWorld(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return(path.delete());
    }

}
