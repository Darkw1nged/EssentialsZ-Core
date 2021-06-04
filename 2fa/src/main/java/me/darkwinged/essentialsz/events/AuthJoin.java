package me.darkwinged.essentialsz.events;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import me.darkwinged.essentialsz.EssentialsZ2FA;
import me.darkwinged.essentialsz.libaries.Permission;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Optional;

public class AuthJoin implements Listener {

    private final EssentialsZ2FA plugin = EssentialsZ2FA.getInstance;

    public Optional<Block> getNextBlockUnderPlayer(Player player) {
        Location loc = player.getLocation();
        Block block;
        while(loc.getY() >= 0) {
            loc.subtract(0, 0.5, 0);
            block = loc.getBlock();
            if(block.getType() != Material.AIR) {
                return Optional.of(block);
            }
        }
        return Optional.empty();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission(Permission.Authenticator.getPermissionNode())) {
            Location loc = player.getLocation();
            Location blockUnderPlayer = new Location(player.getWorld(), loc.getX(), loc.getY()-1, loc.getZ());

            if (blockUnderPlayer.getBlock().getType().equals(Material.AIR)) {
                Location oldLoc = getNextBlockUnderPlayer(player).get().getLocation();
                Location newLoc = new Location(oldLoc.getWorld(), oldLoc.getX(), oldLoc.getY() + 1, oldLoc.getZ());
                player.teleport(newLoc);
            }

            if (!plugin.getConfig().contains("Data." + player.getUniqueId())) {
                GoogleAuthenticator auth = new GoogleAuthenticator();
                GoogleAuthenticatorKey key = auth.createCredentials();

                for (String message : plugin.getConfig().getStringList("Messages.new_code")) {
                    player.sendMessage(plugin.essentialsZAPI.utils.chat(message).replaceAll("%key%", key.getKey()));
                }

                plugin.getConfig().set("Data." + player.getUniqueId() + ".name", player.getName());
                plugin.getConfig().set("Data." + player.getUniqueId() + ".key", key.getKey());
                plugin.saveConfig();
            } else {
                plugin.AuthLogin.add(player.getUniqueId());
                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("Messages.prefix") +
                        plugin.getConfig().getString("Messages.enter_code")));
            }
        }

    }

    private boolean playerInputCode(Player player, int code) {
        String key = plugin.getConfig().getString("Data." + player.getUniqueId() + ".key");

        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        boolean isValid = gAuth.authorize(key, code);


        if (isValid) {
            plugin.AuthLogin.remove(player.getUniqueId());
            return isValid;
        }

        return isValid;
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (plugin.AuthLogin.contains(player.getUniqueId())) {
            try {
                int code = Integer.parseInt(message);
                if (playerInputCode(player, code)) {
                    plugin.AuthLogin.remove(player.getUniqueId());
                    player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("Messages.prefix") +
                            plugin.getConfig().getString("Messages.access_granted")));
                } else {
                    player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("invalid_code")));

                }
            } catch (Exception e) {
                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.getConfig().getString("invalid_code")));
            }
            event.setCancelled(true);
        }
    }

}
