package me.darkwinged.essentialsz.inject;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.ServicesManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@RequiredArgsConstructor
public final class BukkitServicesInjector implements ServicesInjector
{
    @NonNull
    private final ServicesManager servicesManager;

    @Override
    public <T> T createInstance(Class<T> clazz)
    {
        Constructor<?> selectedConstructor = null;
        for(Constructor<?> constructor : clazz.getDeclaredConstructors())
        {
            Inject annotation = constructor.getAnnotation(Inject.class);
            if(annotation == null)
                continue;
            if(selectedConstructor != null)
                throw new IllegalArgumentException("the class provided (" + clazz.getName() + ") has more than one @Inject annotation in the class");
            selectedConstructor = constructor;
        }

        if(selectedConstructor == null)
            throw new IllegalArgumentException("the class provided (" + clazz.getName() + ") does not have any constructors with the @Inject annotation");

        Class<?>[] parameterTypes = selectedConstructor.getParameterTypes();
        Object[] parameters = new Object[parameterTypes.length];
        for(int i = 0; i < parameterTypes.length; i++)
            parameters[i] = getRequiredService(parameterTypes[i]);

        T instance;
        try
        {
            instance = clazz.cast(selectedConstructor.newInstance(parameters));
        }
        catch(InstantiationException | IllegalAccessException | InvocationTargetException exception)
        {
            // TODO more descriptive handling of reflective exceptions
            throw new IllegalArgumentException(exception);
        }

        return instance;
    }

    @Override
    public <T> T getService(Class<T> clazz)
    {
        return servicesManager.load(clazz);
    }
}
