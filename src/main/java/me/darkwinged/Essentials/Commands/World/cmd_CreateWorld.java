package me.darkwinged.Essentials.Commands.World;

import me.darkwinged.Essentials.Libaries.Lang.Errors;
import me.darkwinged.Essentials.Libaries.Lang.Permissions;
import me.darkwinged.Essentials.Libaries.Lang.Utils;
import me.darkwinged.Essentials.Main;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_CreateWorld implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("world")) {
            if (plugin.getConfig().getBoolean("Commands.World Creation", true)) {
                if (!(sender instanceof Player)) {
                    if (args.length != 5) {
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
                    if (args.length != 5) {
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
        }
        return false;
    }
}
