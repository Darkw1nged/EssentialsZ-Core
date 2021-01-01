package me.darkwinged.Essentials.REWORK.Events.Chat;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.BlockedCommandsFile;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BlockedCommandsEvent implements Listener {

    private BlockedCommandsFile blockedCommandsFile;
    private Main plugin;
    public BlockedCommandsEvent(BlockedCommandsFile blockedCommandsFile, Main plugin) {
        this.blockedCommandsFile = blockedCommandsFile;
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if (plugin.getConfig().getBoolean("Chat", true)) {
            if (plugin.getConfig().getBoolean("Blocked_Commands", true)) {
                Player player = event.getPlayer();
                for (String msg : event.getMessage().split(" ")) {
                    if (blockedCommandsFile.getConfig().get("Commands").toString().contains(msg)) {
                        if (player.hasPermission(Permissions.bypass) || player.hasPermission(Permissions.GlobalOverwrite))
                            return;
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

}
