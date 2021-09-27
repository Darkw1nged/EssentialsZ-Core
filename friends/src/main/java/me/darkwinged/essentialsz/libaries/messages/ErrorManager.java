package me.darkwinged.essentialsz.libaries.messages;

import me.darkwinged.essentialsz.EssentialsZFriends;
import org.bukkit.configuration.file.YamlConfiguration;

public class ErrorManager {

    private static final EssentialsZFriends plugin = EssentialsZFriends.getInstance;
    private static final YamlConfiguration config = plugin.Messages.getConfig();
    private static final String prefix = config.getString("Errors.Prefix");

    public static String getErrors(Errors type) {
        String errors = "";
        switch (type) {
            case NoPermission:
                errors = prefix + config.getString("Error.NoPermission");
                break;
            case Console:
                errors = prefix + config.getString("Error.Console");
                break;
            case SpecifyPlayer:
                errors = prefix + config.getString("Error.SpecifyPlayer");
                break;
            case NoPlayerFound:
                errors = prefix + config.getString("Error.NoPlayerFound");
                break;
        }
        return plugin.essentialsZAPI.utils.chat(errors);
    }

}
