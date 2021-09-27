package me.darkwinged.essentialsz.commands.world;

import lombok.RequiredArgsConstructor;
import me.darkwinged.essentialsz.Main;
import me.darkwinged.essentialsz.Permission;
import me.darkwinged.essentialsz.commands.Name;
import me.darkwinged.essentialsz.commands.annotation.Permissions;
import me.darkwinged.essentialsz.commands.processor.annotation.PlayersOnly;
import me.darkwinged.essentialsz.inject.Inject;
import me.darkwinged.essentialsz.libaries.lang.Messages.ErrorManager;
import me.darkwinged.essentialsz.libaries.lang.Messages.Errors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Name("hat")
@PlayersOnly
@Permissions(value = {Permission.SUICIDE, Permission.GLOBAL})
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class HatCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (plugin.getConfig().getBoolean("Commands.Hat", true)) {
            Player player = (Player) sender;
            ItemStack inHand = player.getItemInHand();
            if (player.getInventory().getHelmet() != null) {
                player.getInventory().addItem(player.getInventory().getHelmet());
            }
            player.getInventory().setHelmet(inHand);
            player.getInventory().setItemInHand(null);
        } else {
            sender.sendMessage(ErrorManager.getErrors(Errors.DisabledCommand));
        }
        return true;
    }

}
