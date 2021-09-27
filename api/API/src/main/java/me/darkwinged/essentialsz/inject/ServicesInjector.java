package me.darkwinged.essentialsz.inject;

public interface ServicesInjector
{
    <T> T createInstance(Class<T> clazz);

    <T> T getService(Class<T> clazz);

    default <T> T getRequiredService(Class<T> clazz)
    {
        T instance = getService(clazz);
        if(instance == null)
            throw new IllegalStateException("could not find a registered service for class " + clazz.getName());
        return instance;
    }
}
