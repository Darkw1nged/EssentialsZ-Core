package me.darkwinged.Essentials.Commands.World;

import me.darkwinged.Essentials.Main;
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
                        sender.sendMessage(Utils.chat(Errors.getErrors(Errors.WorldGenUsage)));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("create")) {
                        String name = args[1];
                        WorldCreator world = new WorldCreator(name);
                        switch (args[2]) {
                            case "normal":
                                world.environment(World.Environment.NORMAL);
                                switch (args[3]) {
                                    case "normal":
                                        world.type(WorldType.NORMAL);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                    case "flat":
                                        world.type(WorldType.FLAT);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                    case "amplified":
                                        world.type(WorldType.AMPLIFIED);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                    case "largebiomes":
                                        world.type(WorldType.LARGE_BIOMES);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                }
                                break;
                            case "nether":
                                world.environment(World.Environment.NETHER);
                                switch (args[3]) {
                                    case "normal":
                                        world.type(WorldType.NORMAL);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                    case "flat":
                                        world.type(WorldType.FLAT);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                    case "amplified":
                                        world.type(WorldType.AMPLIFIED);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                    case "largebiomes":
                                        world.type(WorldType.LARGE_BIOMES);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                }
                                break;
                            case "end":
                                world.environment(World.Environment.THE_END);
                                switch (args[3]) {
                                    case "normal":
                                        world.type(WorldType.NORMAL);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                    case "flat":
                                        world.type(WorldType.FLAT);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                    case "amplified":
                                        world.type(WorldType.AMPLIFIED);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                    case "largebiomes":
                                        world.type(WorldType.LARGE_BIOMES);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                        world.createWorld();
                    }
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.CreateWorld) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (args.length != 6) {
                        sender.sendMessage(Utils.chat(Errors.getErrors(Errors.WorldGenUsage)));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("create")) {
                        String name = args[1];
                        WorldCreator world = new WorldCreator(name);
                        switch (args[2]) {
                            case "normal":
                                world.environment(World.Environment.NORMAL);
                                switch (args[3]) {
                                    case "normal":
                                        world.type(WorldType.NORMAL);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                    case "flat":
                                        world.type(WorldType.FLAT);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                    case "amplified":
                                        world.type(WorldType.AMPLIFIED);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                    case "largebiomes":
                                        world.type(WorldType.LARGE_BIOMES);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                }
                                break;
                            case "nether":
                                world.environment(World.Environment.NETHER);
                                switch (args[3]) {
                                    case "normal":
                                        world.type(WorldType.NORMAL);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                    case "flat":
                                        world.type(WorldType.FLAT);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                    case "amplified":
                                        world.type(WorldType.AMPLIFIED);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                    case "largebiomes":
                                        world.type(WorldType.LARGE_BIOMES);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                }
                                break;
                            case "end":
                                world.environment(World.Environment.THE_END);
                                switch (args[3]) {
                                    case "normal":
                                        world.type(WorldType.NORMAL);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                    case "flat":
                                        world.type(WorldType.FLAT);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                    case "amplified":
                                        world.type(WorldType.AMPLIFIED);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                    case "largebiomes":
                                        world.type(WorldType.LARGE_BIOMES);
                                        switch (args[4]) {
                                            case "true":
                                                world.generateStructures(true);
                                                break;
                                            case "false":
                                                world.generateStructures(false);
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                        world.createWorld();
                    }
                } else {
                    sender.sendMessage(Utils.chat(Errors.getErrors(Errors.NoPermission)));
                }
            }
        } else if (cmd.getName().equalsIgnoreCase("delworld")) {
            if (!(sender instanceof Player)) {
                if (args.length != 1) {
                    sender.sendMessage(Utils.chat(Errors.getErrors(Errors.InvalidWorld)));
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
                    sender.sendMessage(Utils.chat(Errors.getErrors(Errors.InvalidWorld)));
                    return true;
                }
                if (Bukkit.getWorld(args[0]) != null) {
                    World delete = Bukkit.getWorld(args[0]);
                    File deleteFolder = delete.getWorldFolder();
                    deleteWorld(deleteFolder);
                }
            } else {
                sender.sendMessage(Utils.chat(Errors.getErrors(Errors.NoPermission)));
            }

        }
        return false;
    }

    private boolean deleteWorld(File path) {
        if(path.exists()) {
            File files[] = path.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteWorld(file);
                } else {
                    file.delete();
                }
            }
        }
        return(path.delete());
    }

}
