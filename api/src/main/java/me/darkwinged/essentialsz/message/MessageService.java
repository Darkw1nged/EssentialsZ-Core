package me.darkwinged.essentialsz.message;

import java.util.HashMap;
import java.util.Map;

public final class MessageService
{
    private final Map<String, String> messages = new HashMap<>();

    public String getMessage(MessageKey key)
    {
        return getMessage(key.getKey());
    }

    public String getMessage(String key)
    {
        return messages.get(key);
    }

    public void registerMessage(MessageKey key, String value)
    {
        registerMessage(key.getKey(), value);
    }

    public void registerMessage(String key, String value)
    {
        messages.put(key, value);
    }

    public void unregisterMessage(MessageKey key)
    {
        unregisterMessage(key.getKey());
    }

    public void unregisterMessage(String key)
    {
        messages.remove(key);
    }
}
