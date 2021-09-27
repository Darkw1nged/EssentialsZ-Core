package me.darkwinged.essentialsz.island_chest;

import me.darkwinged.essentialsz.island_chest.gui.IChest_Menu;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IChest_Token {

    private final Player owner;
    private final UUID uuid;
    private final Location loc;
    private final IChest_Menu menu;
    private final Chunk chunk;
    private final List<ItemStack> items;

    public IChest_Token(Player owner, UUID tokenID, Location loc, IChest_Menu menu) {
        this.owner = owner;
        this.uuid = tokenID;
        this.loc = loc;
        this.menu = menu;
        this.chunk = loc.getChunk();
        this.items = new ArrayList<>();
    }

    public Player getOwner() {
        return owner;
    }

    public UUID getTokenID() {
        return uuid;
    }

    public Location getLocation() {
        return loc;
    }

    public IChest_Menu getMenu() {
        return menu;
    }

    public Chunk getChunk() {
        return chunk;
    }

    public void addItems(ItemStack item) {
        items.add(item);
    }

    public void removeItems(ItemStack item) {
        items.remove(item);
    }

    public List<ItemStack> getItems() {
        return items;
    }
}
