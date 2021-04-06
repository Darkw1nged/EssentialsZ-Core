package me.darkwinged.Essentials.Events.Signs;

import me.darkwinged.Essentials.Libaries.Lang.Permissions;
import me.darkwinged.Essentials.Libaries.Lang.Utils;
import me.darkwinged.Essentials.Main;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Sign_Up implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void SignCreate(SignChangeEvent event) {
        if (plugin.getConfig().getBoolean("Teleportation.Settings.Commands.top", true)) {
            Player player = event.getPlayer();
            if (player.hasPermission(Permissions.UpSign) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String line0 = event.getLine(0);
                if (line0 == null) return;
                if (line0.equalsIgnoreCase("[up]")) {
                    event.setLine(0, Utils.chat("&1[Up]"));
                }
            }
        }

    }

    @EventHandler
    public void SignInteract(PlayerInteractEvent event) {
        if (plugin.getConfig().getBoolean("Teleportation.Settings.Commands.top", true)) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Block block = event.getClickedBlock();
                if (block == null)
                    return;
                if (!isSign(block)) {
                    Sign sign = (Sign)block.getState();
                    String line0 = sign.getLine(0);
                    Player player = event.getPlayer();
                    if (line0.contains("[Up]")) {
                        player.performCommand("top");
                    }
                }
            }
        }

    }

    private boolean isSign(Block block) {
        switch (block.getType()) {
            case WALL_SIGN:
            case SIGN:
                return false;
        }
        return true;
    }

}
