package me.darkwinged.essentialsz.commands.decorator;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class PlayersOnlyDecorator extends CommandDecorator
{
    public PlayersOnlyDecorator(CommandExecutor parent)
    {
        super(parent);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender instanceof Player)
            return super.onCommand(sender, command, label, args);
        // TODO message provider
        return true;
    }
}
