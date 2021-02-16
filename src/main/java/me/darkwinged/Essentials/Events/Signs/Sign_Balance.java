package me.darkwinged.Essentials.Events.Signs;

import me.darkwinged.Essentials.Main;
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

public class Sign_Balance implements Listener {

    private Main plugin;
    public Sign_Balance(Main plugin) { this.plugin = plugin; }

    @EventHandler
    public void SignCreate(SignChangeEvent event) {
        if (plugin.getConfig().getBoolean("Economy", true)) {
            Player player = event.getPlayer();
            if (player.hasPermission(Permissions.BalanceSign) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String line0 = event.getLine(0);
                if (line0 == null)
                    return;
                if (line0.equalsIgnoreCase("[balance]")) {
                    event.setLine(0, Utils.chat("&1[Balance]"));
                }
            }
        }

    }

    @EventHandler
    public void SignInteract(PlayerInteractEvent event) {
        if (plugin.getConfig().getBoolean("Economy", true)) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Block block = event.getClickedBlock();
                if (block == null)
                    return;
                if (!isSign(block)) {
                    Sign sign = (Sign)block.getState();
                    String line0 = sign.getLine(0);
                    if (line0.equalsIgnoreCase("[Balance]")) {
                        Player player = event.getPlayer();
                        player.performCommand("bal");
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
