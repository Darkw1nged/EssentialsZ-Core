package me.darkwinged.EssentialsZ.Commands.World;

import me.darkwinged.EssentialsZ.Main;
import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Libaries.Lang.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class cmd_God implements CommandExecutor, Listener {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("god")) {
            if (plugin.getConfig().getBoolean("Commands.God", true)) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Utils.chat(Errors.getErrors(Errors.Console)));
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.God) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (Utils.GodMode_List.contains(player.getUniqueId())) {
                        Utils.GodMode_List.remove(player.getUniqueId());
                        player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("God Mode")
                                .replaceAll("%setting%", "disabled")));
                    } else {
                        Utils.GodMode_List.add(player.getUniqueId());
                        player.sendMessage(Utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("God Mode")
                                        .replaceAll("%setting%", "enabled")));
                    }
                }
            }
        }
        return false;
    }
    
    
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            Player player = (Player)event.getEntity();
            if (Utils.GodMode_List.contains(player.getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

}
