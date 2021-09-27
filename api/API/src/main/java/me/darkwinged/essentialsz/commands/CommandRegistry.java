package me.darkwinged.essentialsz.commands;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.darkwinged.essentialsz.commands.processor.CommandProcessor;
import me.darkwinged.essentialsz.inject.Inject;
import me.darkwinged.essentialsz.inject.ServicesInjector;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public final class CommandRegistry
{
    @NonNull
    private final ServicesInjector injector;
    private final CommandProcessor processor;

    public void registerCommand(JavaPlugin plugin, Class<? extends CommandExecutor> executorClass)
    {
        Name nameAnnotation = executorClass.getAnnotation(Name.class);
        if(nameAnnotation == null)
            throw new IllegalArgumentException("executor class must have the " + Name.class.getSimpleName() + " annotation");

        PluginCommand command = plugin.getCommand(nameAnnotation.value());
        if(command == null)
            throw new IllegalArgumentException("\"" + nameAnnotation.value() + "\" is not a registered command in " + plugin.getName() + "'s plugin.yml");

        CommandExecutor executor = injector.createInstance(executorClass);
        if(processor != null)
            executor = processor.processCommand(executor);

        command.setExecutor(executor);
    }
}
