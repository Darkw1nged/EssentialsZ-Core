package me.darkwinged.essentialsz.commands.world;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.Permission;
import me.darkwinged.essentialsz.commands.Name;
import me.darkwinged.essentialsz.commands.annotation.Permissions;
import me.darkwinged.essentialsz.commands.processor.annotation.PlayersOnly;
import me.darkwinged.essentialsz.inject.Inject;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Name("suicide")
@PlayersOnly
@Permissions(value = {Permission.SUICIDE, Permission.GLOBAL})
public class SuicideCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    @Inject
    public SuicideCommand() {}

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (plugin.getConfig().getBoolean("Commands.Suicide", true)) {
            Player player = (Player)sender;
            player.setHealth(0);
        } else
            sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));
        return false;
    }

}
