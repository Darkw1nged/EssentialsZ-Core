package me.darkwinged.EssentialsZ.Commands.World;

import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Main;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class cmd_TNTFill implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("filltnt")) {
            if (plugin.getConfig().getBoolean("Commands.TNT Fill.enabled", true)) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Errors.getErrors(Errors.Console));
                    return true;
                }
                Player player = (Player)sender;

                int x = plugin.getConfig().getInt("Commands.TNT Fill.x");
                int y = plugin.getConfig().getInt("Commands.TNT Fill.y");
                int z = plugin.getConfig().getInt("Commands.TNT Fill.z");

                if (player.hasPermission(Permissions.TNTFillSurvival) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    (new BukkitRunnable() {
                        public void run() {
                            ArrayList<Dispenser> dispensers = getNearbyDispensers(player.getLocation(), x, y, z);
                            if (dispensers.size() == 0) {
                                player.sendMessage(Errors.getErrors(Errors.NoDispensersNearby));
                                cancel();
                                return;
                            }
                            if (player.getGameMode() == GameMode.CREATIVE) {
                                creativeTnTFill(player, dispensers);
                                cancel();
                                return;
                            }
                            if (player.getGameMode() != GameMode.CREATIVE) {
                                int tnt_in_inventory = getTnTinPlayerInventory(player);
                                if (tnt_in_inventory == 0) {
                                    player.sendMessage(Errors.getErrors(Errors.NoTNT));
                                    cancel();
                                    return;
                                }
                                survivalTnTFill(player, dispensers, tnt_in_inventory);
                                cancel();
                            }
                        }
                    }).runTaskAsynchronously(plugin);
                }

            }
        }
        return false;
    }

    public void creativeTnTFill(Player player, final ArrayList<Dispenser> dispensers) {
        if (player.hasPermission(Permissions.TNTFillCreative) || player.hasPermission(Permissions.GlobalOverwrite)) {
            (new BukkitRunnable() {
                public void run() {
                    for (Dispenser dispenser : dispensers) {
                        Inventory inv = dispenser.getInventory();
                        if (!isInventoryFull(inv)) {
                            int to_add = getAmountOfItemsThatCanBeAdded(inv, Material.TNT);
                            inv.addItem(new ItemStack(Material.TNT, to_add));
                        }
                        dispenser.update(true);
                    }
                    dispensers.clear();
                }
            }).runTask(plugin);
        }
    }

    public ArrayList<Dispenser> getNearbyDispensers(Location centre, int xRadius, int yRadius, int zRadius) {
        World world = centre.getWorld();
        ArrayList<Dispenser> result = new ArrayList<>();
        for (int x = centre.getBlockX() - xRadius / 2; x < centre.getBlockX() + xRadius / 2; x++) {
            for (int z = centre.getBlockZ() - zRadius / 2; z < centre.getBlockZ() + zRadius / 2; z++) {
                for (int y = centre.getBlockY() - yRadius / 2; y < centre.getBlockY() + yRadius / 2; y++) {
                    Block target = world.getBlockAt(x, y, z);
                    if (target.getState() instanceof Dispenser) {
                        Dispenser dispenser = (Dispenser)target.getState();
                        result.add(dispenser);
                    }
                }
            }
        }
        return result;
    }

    public void survivalTnTFill(final Player player, final ArrayList<Dispenser> dispensers, final double tnt_in_inventory) {
        double tnt_per = tnt_in_inventory / dispensers.size();
        final int round = (int)tnt_per;
        if (round < 1) {
            player.sendMessage(Errors.getErrors(Errors.UnableToFill));
            return;
        }
        (new BukkitRunnable() {
            public void run() {
                int used = 0;
                for (Dispenser dispenser : dispensers) {
                    Inventory inv = dispenser.getInventory();
                    if (!isInventoryFull(inv)) {
                        inv.addItem(new ItemStack(Material.TNT));
                        used += round;
                    }
                    dispenser.update(true);
                }
                PlayerInventory playerInv = player.getInventory();
                byte by;
                int i;
                ItemStack[] arrayOfItemStack;
                for (i = (arrayOfItemStack = playerInv.getContents()).length, by = 0; by < i; ) {
                    ItemStack is = arrayOfItemStack[by];
                    if (is != null &&
                            is.getType() == Material.TNT)
                        playerInv.remove(is);
                    by++;
                }
                player.updateInventory();
                ItemStack newTNT = new ItemStack(Material.TNT, (int)(tnt_in_inventory - used));
                if (newTNT.getAmount() >= 1) {
                    playerInv.addItem(newTNT);
                    player.updateInventory();
                    player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("TNT Filled"), null, null, null, false));
                    return;
                }
                player.sendMessage(Errors.getErrors(Errors.UnableToFill));
            }
        }).runTask(plugin);
    }

    public boolean isInventoryFull(Inventory inv) {
        boolean result = false;
        if (inv.firstEmpty() == -1)
            result = true;
        return result;
    }

    public int getAmountOfItemsThatCanBeAdded(Inventory inv, Material material) {
        int result = 0;
        int occupied_slots = 0;
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack is = inv.getItem(i);
            if (is != null) {
                occupied_slots++;
                if (is.getType() == material) {
                    int amount = is.getAmount();
                    result += 64 - amount;
                }
            }
        }
        return result += (inv.getSize() - occupied_slots) * 64;
    }

    public int getTnTinPlayerInventory(Player p) {
        int result = 0;
        PlayerInventory pi = p.getInventory();
        byte b;
        int i;
        ItemStack[] arrayOfItemStack;
        for (i = (arrayOfItemStack = pi.getContents()).length, b = 0; b < i; ) {
            ItemStack is = arrayOfItemStack[b];
            if (is != null &&
                    is.getType() == Material.TNT)
                result += is.getAmount();
            b++;
        }
        return result;
    }

}
