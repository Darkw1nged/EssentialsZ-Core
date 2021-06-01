package me.darkwinged.essentialsz.commands.decorator.factory;

import me.darkwinged.essentialsz.Permission;
import me.darkwinged.essentialsz.commands.annotation.Permissions;
import me.darkwinged.essentialsz.commands.decorator.PermissionDecorator;
import org.bukkit.command.CommandExecutor;

import java.util.Arrays;

public final class PermissionDecoratorFactory implements CommandDecoratorFactory<PermissionDecorator, Permissions>
{
    @Override
    public PermissionDecorator createDecorator(CommandExecutor parent, Permissions annotation)
    {
        return new PermissionDecorator(parent,
                                       annotation.requiresAll(),
                                       Arrays.stream(annotation.value())
                                             .map(Permission::getPermissionNode)
                                             .toArray(String[]::new));
    }
}
