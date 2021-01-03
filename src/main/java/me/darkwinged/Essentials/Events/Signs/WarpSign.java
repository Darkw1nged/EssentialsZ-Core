package me.darkwinged.Essentials.Events.Signs;

import me.darkwinged.Essentials.Utils.Lang.Permissions;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class WarpSign implements Listener {

    @EventHandler
    public void WarpsSignCreate(SignChangeEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission(Permissions.WarpsSign) || player.hasPermission(Permissions.GlobalOverwrite)) {
            if (event.getLine(0).contains("[WARPS]")) {
                event.setLine(0, Utils.chat("&1[Warps]"));
            }
        }
    }

    @EventHandler
    public void WarpSignCreate(SignChangeEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission(Permissions.WarpsSign) || player.hasPermission(Permissions.GlobalOverwrite)) {
            if (event.getLine(0).contains("[WARP]")) {
                event.setLine(0, Utils.chat("&1[Warp]"));
            }
        }
    }

    @EventHandler
    public void WarpsSignInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            if (block == null)
                return;
            if (!isSign(block)) {
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
            if (block == null)
                return;
            if (!isSign(block)) {
                Sign sign = (Sign)block.getState();
                String line0 = sign.getLine(0);
                String line1 = sign.getLine(1);
                if (line0.contains("[Warp]")) {
                    Player player = event.getPlayer();
                    String warp = line1.toString();
                    player.performCommand("warp" + warp);
                }
            }
        }
    }

    private boolean isSign(Block block) {
        switch (block.getType()) {
            case SPRUCE_SIGN:
            case SPRUCE_WALL_SIGN:
            case ACACIA_SIGN:
            case ACACIA_WALL_SIGN:
            case BIRCH_SIGN:
            case BIRCH_WALL_SIGN:
            case CRIMSON_SIGN:
            case CRIMSON_WALL_SIGN:
            case DARK_OAK_SIGN:
            case DARK_OAK_WALL_SIGN:
            case JUNGLE_SIGN:
            case JUNGLE_WALL_SIGN:
            case OAK_SIGN:
            case OAK_WALL_SIGN:
            case WARPED_SIGN:
            case WARPED_WALL_SIGN:
                return false;
        }
        return true;
    }

}