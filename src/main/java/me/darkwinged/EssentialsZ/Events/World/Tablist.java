package me.darkwinged.EssentialsZ.Events.World;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import me.darkwinged.EssentialsZ.Libaries.Lang.CustomConfig;
import me.darkwinged.EssentialsZ.Libaries.Lang.Utils;
import me.darkwinged.EssentialsZ.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Tablist implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("Chat.enabled", true)) {
            if (plugin.getConfig().getBoolean("Chat.Settings.Tablist.enabled", true)) {
                if (Bukkit.getPluginManager().getPlugin("ProtocolLib") == null) return;
                Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                    public void run() {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            CustomConfig Data = Utils.getDataFile(player);
                            List<UUID> ghost = new ArrayList<>();
                            if (Data.getConfig().getBoolean("isVanished", true)) {
                                ghost.add(player.getUniqueId());
                            }

                            PacketContainer pc = plugin.protocolManager.createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
                            String tps = plugin.essentialsZAPI.utils.getServerTPS();
                            int intOnline = Bukkit.getOnlinePlayers().size() - ghost.size();
                            String online = ""+intOnline;
                            String bal = ""+plugin.economyManager.getAccount(player);
                            float getXP = player.getTotalExperience();
                            float getXPLevel = player.getLevel();
                            String xp = String.format("%.0f", getXP);
                            String xpLevel = String.format("%.0f", getXPLevel);
                            String ping = ""+ plugin.essentialsZAPI.utils.getPlayerPing(player);


                            String Header = Utils.chat(plugin.getConfig().get("Chat.Settings.Tablist.TBLayout.header").toString())
                                    .replaceAll("%player%", player.getName())
                                    .replaceAll("%tps%", tps)
                                    .replaceAll("%online%", online)
                                    .replaceAll("%balance%", bal)
                                    .replaceAll("%xp%", xp)
                                    .replaceAll("xpLVL", xpLevel)
                                    .replaceAll("%ping%", ping);
                            String Footer = Utils.chat(plugin.getConfig().get("Chat.Settings.Tablist.TBLayout.footer").toString())
                                    .replaceAll("%player%", player.getName())
                                    .replaceAll("%tps%", tps)
                                    .replaceAll("%online%", online)
                                    .replaceAll("%balance%", bal)
                                    .replaceAll("%xp%", xp)
                                    .replaceAll("%xpLVL%", xpLevel)
                                    .replaceAll("%ping%", ping);

                            pc.getChatComponents().write(0, WrappedChatComponent.fromText(Utils.chat(Header)))
                                    .write(1, WrappedChatComponent.fromText(Utils.chat(Footer)));
                            try {
                                plugin.protocolManager.sendServerPacket(player, pc);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }, 0L, 20L);
            }
        }
    }

}
