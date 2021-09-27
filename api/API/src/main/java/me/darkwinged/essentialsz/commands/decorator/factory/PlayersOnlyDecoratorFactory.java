package me.darkwinged.essentialsz.commands.decorator.factory;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.darkwinged.essentialsz.commands.decorator.PlayersOnlyDecorator;
import me.darkwinged.essentialsz.commands.processor.annotation.PlayersOnly;
import me.darkwinged.essentialsz.inject.Inject;
import me.darkwinged.essentialsz.inject.ServicesInjector;
import org.bukkit.command.CommandExecutor;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public final class PlayersOnlyDecoratorFactory implements CommandDecoratorFactory<PlayersOnlyDecorator, PlayersOnly>
{
    @NonNull
    private final ServicesInjector servicesInjector;

    @Override
    public PlayersOnlyDecorator createDecorator(CommandExecutor parent, PlayersOnly annotation)
    {
        return new PlayersOnlyDecorator(parent);
    }
}
