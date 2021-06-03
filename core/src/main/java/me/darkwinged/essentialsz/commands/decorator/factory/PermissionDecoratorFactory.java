package me.darkwinged.essentialsz.commands.decorator.factory;

import me.darkwinged.essentialsz.Permission;
import me.darkwinged.essentialsz.commands.annotation.Permissions;
import me.darkwinged.essentialsz.commands.decorator.PermissionDecorator;
import me.darkwinged.essentialsz.inject.Inject;
import me.darkwinged.essentialsz.message.MessageService;
import org.bukkit.command.CommandExecutor;

import java.util.Arrays;

public final class PermissionDecoratorFactory implements CommandDecoratorFactory<PermissionDecorator, Permissions>
{
    private final MessageService messageService;

    @Inject
    public PermissionDecoratorFactory(MessageService messageService)
    {
        this.messageService = messageService;
    }

    @Override
    public PermissionDecorator createDecorator(CommandExecutor parent, Permissions annotation)
    {
        return new PermissionDecorator(parent,
                                       annotation.requiresAll(),
                                       messageService,
                                       annotation.noPermissionMessage().getKey(),
                                       Arrays.stream(annotation.value())
                                             .map(Permission::getPermissionNode)
                                             .toArray(String[]::new));
    }
}
