package me.darkwinged.essentialsz.commands.processor;

import org.bukkit.command.CommandExecutor;

public interface CommandProcessor
{
    CommandExecutor processCommand(CommandExecutor executor);
}
