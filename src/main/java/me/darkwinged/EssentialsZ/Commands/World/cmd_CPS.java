package me.darkwinged.EssentialsZ.Commands.World;

import me.darkwinged.EssentialsZ.Events.World.WorldControl;
import me.darkwinged.EssentialsZ.Libaries.Lang.Errors;
import me.darkwinged.EssentialsZ.Libaries.Lang.Permissions;
import me.darkwinged.EssentialsZ.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class cmd_CPS implements CommandExecutor {

    private final Main plugin = Main.getInstance;
    public static Map<UUID, Integer> Check = new HashMap<>();
    private BukkitTask runnable;

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("cps")) {
            if (plugin.getConfig().getBoolean("Commands.CPS", true)) {
                if (!(sender instanceof Player)) {
                    if (args.length != 2) {
                        sender.sendMessage(Errors.getErrors(Errors.SpecifyPlayer));
                        sender.sendMessage(Errors.getErrors(Errors.Length));
                        return true;
                    }
                    int length = Integer.parseInt(args[1]);
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        sender.sendMessage(Errors.getErrors(Errors.NoPlayerFound));
                        return true;
                    }
                    if (Check.containsKey(target.getUniqueId())) {
                        sender.sendMessage(Errors.getErrors(Errors.Cooldown));
                        return true;
                    }
                    Check.put(target.getUniqueId(), 0);
                    runnable = new BukkitRunnable() {
                        public void run() {
                            if (!Check.containsKey(target.getUniqueId())) return;
                            if (Check.get(target.getUniqueId()) >= length) {
                                sender.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                                plugin.MessagesFile.getConfig().getString("CPS")
                                                        .replaceAll("%cps%", ""+WorldControl.player_CPS.get(target.getUniqueId()))
                                                        .replaceAll("%length%", ""+length),
                                        target, target, null, false));
                                Check.remove(target.getUniqueId());
                                runnable.cancel();
                                return;
                            }
                            Check.put(target.getUniqueId(), Check.get(target.getUniqueId()) + 1);
                        }
                    }.runTaskTimer(plugin, 0L, 20L);
                    return true;
                }
                Player player = (Player)sender;
                if (player.hasPermission(Permissions.CPS) || player.hasPermission(Permissions.GlobalOverwrite)) {
                    if (args.length != 2) {
                        player.sendMessage(Errors.getErrors(Errors.SpecifyPlayer));
                        player.sendMessage(Errors.getErrors(Errors.Length));
                        return true;
                    }
                    int length = Integer.parseInt(args[1]);
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        player.sendMessage(Errors.getErrors(Errors.NoPlayerFound));
                        return true;
                    }
                    if (Check.containsKey(target.getUniqueId())) {
                        player.sendMessage(Errors.getErrors(Errors.Cooldown));
                        return true;
                    }
                    Check.put(target.getUniqueId(), 0);
                    runnable = new BukkitRunnable() {
                        public void run() {
                            if (!Check.containsKey(target.getUniqueId())) return;
                            if (Check.get(target.getUniqueId()) >= length) {
                                player.sendMessage(plugin.essentialsZAPI.utils.chat(plugin.MessagesFile.getConfig().getString("Prefix") +
                                        plugin.MessagesFile.getConfig().getString("CPS").replaceAll("%cps%", ""+WorldControl.Highest_CPS.get(target.getUniqueId()))
                                                .replaceAll("%length%", ""+length), target, target, null, false));
                                Check.remove(target.getUniqueId());
                                runnable.cancel();
                                return;
                            }
                            Check.put(target.getUniqueId(), Check.get(target.getUniqueId()) + 1);
                        }
                    }.runTaskTimer(plugin, 0L, 20L);
                } else
                    player.sendMessage(Errors.getErrors(Errors.NoPermission));
            }
        }
        return false;
    }
}
