package me.darkwinged.essentialsz.commands.world;

import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import me.darkwinged.essentialsz.libaries.lang.Permissions;
import me.darkwinged.essentialsz.libaries.lang.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class GodCommand implements CommandExecutor, Listener {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("god")) {
            if (plugin.getConfig().getBoolean("Commands.God", true)) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Utils.chat(ErrorManager.getErrors(Errors.Console)));
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.God) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (Utils.GodMode_List.contains(player.getUniqueId())) {
                        Utils.GodMode_List.remove(player.getUniqueId());
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("God Mode")
                                        .replaceAll("%setting%", "disabled"), null, null, null, false));
                    } else {
                        Utils.GodMode_List.add(player.getUniqueId());
                        player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("God Mode")
                                        .replaceAll("%setting%", "enabled"), null, null, null, false));
                    }
                } else
                    player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
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
