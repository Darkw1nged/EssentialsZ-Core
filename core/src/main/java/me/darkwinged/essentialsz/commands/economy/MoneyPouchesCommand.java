package me.darkwinged.essentialsz.commands.economy;

import me.darkwinged.essentialsz.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MoneyPouchesCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;

    /**
     * Executes the given command, returning its success
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }

    /*public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("pouches")) {
            if (plugin.getConfig().getBoolean("Economy.enabled", true)) {
                if (plugin.getConfig().getBoolean("Economy.Settings.Money Pouches.enabled", true)) {
                    if (plugin.Module_Economy = false) return true;
                    if (!(sender instanceof Player)) {
                        if (args.length != 2) {
                            sender.sendMessage(ErrorManager.getErrors(Errors.SpecifyPlayer));
                            sender.sendMessage(ErrorManager.getErrors(Errors.NoPouch));
                            return true;
                        }
                        if (FoundTarget(sender, args)) return true;
                        return true;
                    }
                    Player player = (Player)sender;
                    if (player.hasPermission(Permissions.MoneyPouch) || player.hasPermission(Permissions.GlobalOverwrite)) {
                        if (args.length == 2) {
                            if (FoundTarget(player, args)) return true;
                        }
                        if (args.length < 1) {
                            player.sendMessage(ErrorManager.getErrors(Errors.InvalidPouch));
                            return true;
                        }
                        if (!plugin.MoneyPouchesFile.getConfig().contains("Tiers.")) return true;
                        for (String key : plugin.MoneyPouchesFile.getConfig().getConfigurationSection("Tiers.").getKeys(false)) {
                            if (key.equalsIgnoreCase(args[0])) {
                                getPouch(player, key);
                            }
                        }
                        sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                plugin.MessagesFile.getConfig().getString("Money Pouch Given"), player, player, null, false));
                    } else {
                        player.sendMessage(ErrorManager.getErrors(Errors.NoPermission));
                    }
                }
            }
        }
        return false;
    }

    private void getPouch(Player player, String key) {
        ItemStack item = new ItemStack(getMaterial(plugin.MoneyPouchesFile.getConfig().getString("Tiers." + key + ".material")));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.chat(plugin.MoneyPouchesFile.getConfig().getString("Tiers." + key + ".name")));
        meta.setLore(plugin.essentialsZAPI.utils.getConvertedLore(plugin.MoneyPouchesFile.getConfig(), "Tiers." + key));
        item.setItemMeta(meta);

        player.getInventory().addItem(item);
    }

    private boolean FoundTarget(CommandSender sender, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ErrorManager.getErrors(Errors.SpecifyPlayer));
            return true;
        }
        if (!plugin.MoneyPouchesFile.getConfig().contains("Tiers.")) return true;
        for (String key : plugin.MoneyPouchesFile.getConfig().getConfigurationSection("Tiers.").getKeys(false)) {
            if (key.equalsIgnoreCase(args[1])) {
                getPouch(target, key);
            }
        }
        sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                plugin.MessagesFile.getConfig().getString("Money Pouch Given"), target, target, null, false));
        return false;
    }*/

}
