package me.darkwinged.essentialsz.commands.decorator;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public final class PermissionDecorator extends CommandDecorator
{
    private final String[] permissions;

    public PermissionDecorator(CommandExecutor executor, String... permissions)
    {
        super(executor);
        this.permissions = permissions;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        for(String permission : permissions)
            if(!sender.hasPermission(permission))
            {
                // TODO message provider
                return true;
            }

        return super.onCommand(sender, command, label, args);
    }
}
