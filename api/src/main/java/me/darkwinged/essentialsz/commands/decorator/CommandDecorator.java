package me.darkwinged.essentialsz.commands.decorator;

import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

@RequiredArgsConstructor
public abstract class CommandDecorator implements CommandExecutor
{
    private final CommandExecutor parent;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        return parent.onCommand(sender, command, label, args);
    }
}
