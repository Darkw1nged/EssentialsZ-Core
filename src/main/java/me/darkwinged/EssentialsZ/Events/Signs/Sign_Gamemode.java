package me.darkwinged.EssentialsZ.Events.Signs;

import me.darkwinged.EssentialsZ.Main;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Libaries.Lang.Utils;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Sign_Gamemode implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void SignCreate(SignChangeEvent event) {
        if (plugin.getConfig().getBoolean("Economy.Settings.Commands.Gamemode", true)) {
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
        if (plugin.getConfig().getBoolean("Economy.Settings.Commands.Gamemode", true)) {
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
            case WALL_SIGN:
            case SIGN:
                return false;
        }
        return true;
    }

}
