package me.darkwinged.essentialsz;

import me.darkwinged.essentialsz.message.MessageKey;

public enum CoreMessage implements MessageKey
{
    NO_PERMISSION("Error.NoPermission");

    private final String key;

    CoreMessage(String key)
    {
        this.key = key;
    }

    @Override
    public String getKey()
    {
        return key;
    }
}
