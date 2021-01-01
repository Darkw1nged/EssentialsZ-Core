package me.darkwinged.Essentials.REWORK.Events.Signs;

import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import me.darkwinged.Essentials.REWORK.Utils.Utils;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class CraftSign implements Listener {

    @EventHandler
    public void SignCreate(SignChangeEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission(Permissions.CraftSign) || player.hasPermission(Permissions.GlobalOverwrite)) {
            if (event.getLine(0).contains("[CRAFT]")) {
                event.setLine(0, Utils.chat("&1[Craft]"));
            }
        }
    }

    @EventHandler
    public void SignInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            if (block == null)
                return;
            if (!isSign(block)) {
                Sign sign = (Sign)block.getState();
                String line0 = sign.getLine(0);
                if (line0.contains("[Craft]")) {
                    Player player = event.getPlayer();
                    player.performCommand("craft");
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
