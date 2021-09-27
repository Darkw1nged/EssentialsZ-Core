package me.darkwinged.essentialsz.commands.decorator;

import me.darkwinged.essentialsz.message.MessageService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public final class PermissionDecorator extends CommandDecorator
{
    private final String[] permissions;
    private final boolean requiresAll;
    private final MessageService messageService;
    private final String messageKey;

    public PermissionDecorator(CommandExecutor executor, boolean requiresAll, MessageService messageService, String messageKey, String... permissions)
    {
        super(executor);
        this.requiresAll = requiresAll;
        this.messageService = messageService;
        this.messageKey = messageKey;
        this.permissions = permissions;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        boolean broken = false;
        for(String permission : permissions)
            if(sender.hasPermission(permission) != requiresAll)
            {
                broken = true;
                break;
            }

        if(broken == requiresAll)
        {
            // no permission to execute
            sender.sendMessage(messageService.getMessage(messageKey));
            return true;
        }

        return super.onCommand(sender, command, label, args);
    }
}
