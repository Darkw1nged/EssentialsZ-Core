package me.darkwinged.essentialsz.commands.processor.annotation;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.darkwinged.essentialsz.commands.decorator.CommandDecorator;
import me.darkwinged.essentialsz.commands.decorator.factory.CommandDecoratorFactory;
import me.darkwinged.essentialsz.commands.processor.CommandProcessor;
import me.darkwinged.essentialsz.inject.Inject;
import me.darkwinged.essentialsz.inject.ServicesInjector;
import org.bukkit.command.CommandExecutor;

import java.lang.annotation.Annotation;
import java.util.*;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public final class CommandAnnotationProcessor implements CommandProcessor
{
    @NonNull
    private final ServicesInjector servicesInjector;

    @SneakyThrows
    @Override
    public CommandExecutor processCommand(CommandExecutor executor)
    {
        Annotation[] annotations = executor.getClass().getAnnotations();

        // only process annotations which can be bound to a decorator
        annotations = Arrays.stream(annotations)
                            .filter((annotation) -> annotation.annotationType().getAnnotation(DecoratorAnnotation.class) != null)
                            .toArray(Annotation[]::new);

        // annotations which appear first (when reading the class header top to bottom) should have precedence
        // to achieve this, wrap decorators in reverse order so the first annotation is the last (and top) decorator made
        List<Annotation> annotationList = Arrays.asList(annotations);
        Collections.reverse(annotationList);

        CommandExecutor parentExecutor = executor;
        for(Annotation annotation : annotationList)
        {
            DecoratorAnnotation decoratorAnnotation =
                    annotation.annotationType()
                              .getAnnotation(DecoratorAnnotation.class);

            Class<? extends CommandDecoratorFactory<?, ?>> factoryClass =
                    decoratorAnnotation.value();

            CommandDecoratorFactory<?, ?> factory = servicesInjector
                    .getRequiredService(factoryClass);

            // set parentExecutor so the next loop will use the created decorator instead of the provided executor
            parentExecutor = createDecorator(factory, parentExecutor, annotation);
        }

        return parentExecutor;
    }

    @SuppressWarnings("unchecked")
    private static <A extends Annotation> CommandDecorator createDecorator(
            CommandDecoratorFactory<?, A> factory,
            CommandExecutor parent,
            Annotation annotation)
    {
        return factory.createDecorator(parent, (A) annotation);
    }
}
