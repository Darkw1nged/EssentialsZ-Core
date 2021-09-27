package me.darkwinged.essentialsz.island_chest.gui.struts.listeners;

import me.darkwinged.essentialsz.EssentialsZIslandItems;
import me.darkwinged.essentialsz.island_chest.IChestManager;
import me.darkwinged.essentialsz.island_chest.IChest_Token;
import me.darkwinged.essentialsz.island_chest.gui.IChest_Menu;
import me.darkwinged.essentialsz.libaries.storage.CustomConfig;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IChest_Sign_Listener implements Listener {

    private final EssentialsZIslandItems plugin = EssentialsZIslandItems.getInstance;

    @EventHandler
    public void SignCreate(SignChangeEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Block block = event.getBlock();

        if (block == null) {
            player.sendMessage("Block is null.. i mean fuck off");
            return;
        }

        CustomConfig data = new CustomConfig(plugin, String.valueOf(player.getUniqueId()), "Data");
        if (player.hasPermission(IChestManager.Permissions.ICHEST_CREATE.getPermissionNode()) || player.hasPermission(IChestManager.Permissions.ICHEST_OVERWRITE.getPermissionNode())) {
            String line0 = event.getLine(0);
            if (block != null && line0 != null && line0.equalsIgnoreCase("[IChest]")) {
                org.bukkit.material.Sign sign = (org.bukkit.material.Sign) event.getBlock().getState().getData();
                Block attached = event.getBlock().getRelative(sign.getAttachedFace());

                if (data.getConfig().getInt("iChest.tokens") != 0) {
                    if (attached.getType() == Material.CHEST || attached.getType() == Material.TRAPPED_CHEST) {
                        event.setLine(0, plugin.essentialsZAPI.utils.chat("&e[!] &6IChest"));
                        event.setLine(1, plugin.essentialsZAPI.utils.chat("(Right Click"));
                        event.setLine(2, plugin.essentialsZAPI.utils.chat("to open.)"));

                        List<IChest_Token> tokens = new ArrayList<>();
                        UUID token_ID = UUID.randomUUID();
                        if (IChestManager.iChestTokens.containsKey(uuid)) {
                            tokens.addAll(IChestManager.iChestTokens.get(uuid));
                        }
                        tokens.add(new IChest_Token(player, token_ID, event.getBlock().getLocation(), new IChest_Menu(player)));
                        IChestManager.iChestTokens.put(uuid, tokens);
                        //data.getConfig().set("iChest.tokens", data.getConfig().getInt("iChest.tokens") - 1);
                        //data.saveConfig();
                    } else {
                        event.setLine(0, plugin.essentialsZAPI.utils.chat("&cInvalid iChest!"));
                    }
                } else {
                    event.setLine(0, plugin.essentialsZAPI.utils.chat("&cNo Tokens!"));
                }
            }
        }
    }

    @EventHandler
    public void SignInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            if (!isSign(block)) {
                Sign sign = (Sign)block.getState();
                String line0 = sign.getLine(0);

                Location loc = block.getLocation();
                Player player = event.getPlayer();
                UUID uuid = player.getUniqueId();

                if (line0.contains(plugin.essentialsZAPI.utils.chat( "&e[!] &6IChest" ))) {
                    for (int i=0; i<IChestManager.iChestTokens.get(player.getUniqueId()).size(); i++) {
                        if (loc == IChestManager.iChestTokens.get(uuid).get(i).getLocation()) {
                            IChestManager.iChestActive.put(uuid, IChestManager.iChestTokens.get(uuid).get(i).getMenu());
                            if (IChestManager.iChestTokens.get(uuid).get(i).getOwner() == player) {
                                player.openInventory(IChestManager.iChestActive.get(uuid).getInventory());
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    private boolean isSign(Block block) {
        switch (block.getType()) {
            case SIGN:
            case WALL_SIGN:
                return false;
        }
        return true;
    }

}
