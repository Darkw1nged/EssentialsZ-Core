package me.darkwinged.essentialsz.events.signs;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import me.darkwinged.essentialsz.libaries.lang.Utils;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignWarp implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void WarpsSignCreate(SignChangeEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission(Permissions.WarpsSign) || player.hasPermission(Permissions.GlobalOverwrite)) {
            String line0 = event.getLine(0);
            if (line0 == null) return;
            if (line0.equalsIgnoreCase("[warps]")) {
                event.setLine(0, plugin.essentialsZAPI.utils.chat("&1[Warps]"));
            }
        }
    }

    @EventHandler
    public void WarpSignCreate(SignChangeEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission(Permissions.WarpsSign) || player.hasPermission(Permissions.GlobalOverwrite)) {
            String line0 = event.getLine(0);
            if (line0 == null) return;
            if (line0.equalsIgnoreCase("[warp]")) {
                event.setLine(0, Utils.chat("&1[Warp]"));
            }
        }
    }

    @EventHandler
    public void WarpsSignInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            if (block == null) return;
            BlockState blockState = block.getState();
            if (!(blockState instanceof org.bukkit.block.Sign)) return;

            if (plugin.essentialsZAPI.items.isSign(block)) {
                Sign sign = (Sign)block.getState();
                String line0 = sign.getLine(0);
                if (line0.contains("[Warps]")) {
                    Player player = event.getPlayer();
                    player.performCommand("warps");
                }
            }
        }
    }

    @EventHandler
    public void WarpSignInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            if (block == null) return;
            BlockState blockState = block.getState();
            if (!(blockState instanceof org.bukkit.block.Sign)) return;

            if (plugin.essentialsZAPI.items.isSign(block)) {
                Sign sign = (Sign)block.getState();
                String line0 = sign.getLine(0);
                String line1 = sign.getLine(1);
                if (line0.contains("[Warp]")) {
                    Player player = event.getPlayer();
                    player.performCommand("warp" + line1);
                }
            }
        }
    }

}
