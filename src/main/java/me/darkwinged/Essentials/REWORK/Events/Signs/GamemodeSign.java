package me.darkwinged.Essentials.REWORK.Events.Signs;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.Permissions;
import me.darkwinged.Essentials.REWORK.Utils.Utils;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class GamemodeSign implements Listener {

    private Main plugin;
    public GamemodeSign(Main plugin) { this.plugin = plugin; }

    @EventHandler
    public void SignCreate(SignChangeEvent event) {
        if (plugin.getConfig().getBoolean("cmd_Gamemode", true)) {
            Player player = event.getPlayer();
            if (player.hasPermission(Permissions.GamemodeSign) || player.hasPermission(Permissions.GlobalOverwrite)) {
                String line0 = event.getLine(0);
                String line1 = event.getLine(1);
                if (line0 == null) return;
                if (line1 == null) return;
                if (line0.equalsIgnoreCase("[Gamemode]")) {
                    if (line1.equalsIgnoreCase("adventure")) {
                        event.setLine(1, Utils.chat("Adventure"));
                    } else if (line1.equalsIgnoreCase("creative")) {
                        event.setLine(1, Utils.chat("Creative"));
                    } else if (line1.equalsIgnoreCase("survival")) {
                        event.setLine(1, Utils.chat("Survival"));
                    } else if (line1.equalsIgnoreCase("spectator")) {
                        event.setLine(1, Utils.chat("Spectator"));
                    }
                    event.setLine(0, Utils.chat("&1[Gamemode]"));
                }
            }
        }

    }

    @EventHandler
    public void SignInteract(PlayerInteractEvent event) {
        if (plugin.getConfig().getBoolean("cmd_Gamemode", true)) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Block block = event.getClickedBlock();
                if (block == null)
                    return;
                if (!isSign(block)) {
                    Sign sign = (Sign)block.getState();
                    String line0 = sign.getLine(0);
                    String line1 = sign.getLine(1);
                    Player player = event.getPlayer();
                    if (line0.contains("[Gamemode]")) {
                        if (line1.equalsIgnoreCase("adventure")) {
                            player.setGameMode(GameMode.ADVENTURE);
                        } else if (line1.equalsIgnoreCase("creative")) {
                            player.setGameMode(GameMode.CREATIVE);
                        } else if (line1.equalsIgnoreCase("survival")) {
                            player.setGameMode(GameMode.SURVIVAL);
                        } else if (line1.equalsIgnoreCase("spectator")) {
                            player.setGameMode(GameMode.SPECTATOR);
                        }
                    }
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
