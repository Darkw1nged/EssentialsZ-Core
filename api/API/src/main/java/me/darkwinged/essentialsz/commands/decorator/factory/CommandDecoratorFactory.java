package me.darkwinged.essentialsz.commands.decorator.factory;

import me.darkwinged.essentialsz.commands.decorator.CommandDecorator;
import org.bukkit.command.CommandExecutor;

import java.lang.annotation.Annotation;

public interface CommandDecoratorFactory<T extends CommandDecorator, A extends Annotation>
{
    T createDecorator(CommandExecutor parent, A annotation);
}
