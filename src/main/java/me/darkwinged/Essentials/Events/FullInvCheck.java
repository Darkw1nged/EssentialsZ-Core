package me.darkwinged.Essentials.Events;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.MessagesFile;
import me.darkwinged.Essentials.REWORK.Utils.Utils;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class FullInvCheck implements Listener {

    private MessagesFile messagesFile;
    private Main plugin;
    public FullInvCheck(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void FullInv(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (plugin.getConfig().getBoolean("Events.inventory-full", true)) {
            if (player.getInventory().firstEmpty() == -1) {
                player.spigot().sendMessage((BaseComponent) new TextComponent(
                        Utils.chat(messagesFile.getConfig().getString("Messages.inventory full message"))));
            }
        } else {
            return;
        }

    }

}
