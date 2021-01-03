package me.darkwinged.Essentials.Events.World;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.Utils.Lag;
import me.darkwinged.Essentials.Utils.EconomyManager;
import me.darkwinged.Essentials.Utils.PlayersPing;
import me.darkwinged.Essentials.Utils.Lang.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Tablist implements Listener {

    private Main plugin;
    public Tablist(Main plugin) { this.plugin = plugin; }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("Chat", true)) {
            if (plugin.getConfig().getBoolean("Tablist", true)) {
                Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                    public void run() {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            PacketContainer pc = plugin.protocolManager.createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
                            String tps = "";
                            if (Lag.getTPS() <= 20) {
                                tps = String.format("%.0f", Lag.getTPS());
                            } else {
                                tps = "20";
                            }
                            int online = Bukkit.getOnlinePlayers().size() - Utils.invisible_list.size();
                            String conOnline = ""+online;
                            String bal = ""+EconomyManager.getBalance(player.getName());
                            float getXP = player.getTotalExperience();
                            float getXPLevel = player.getLevel();
                            String xp = String.format("%.0f", getXP);
                            String xpLevel = String.format("%.0f", getXPLevel);
                            String ping = ""+PlayersPing.getPing(player);


                            String Header = Utils.chat(plugin.getConfig().get("TBLayout.header").toString())
                                    .replaceAll("%player%", player.getName())
                                    .replaceAll("%tps%", tps)
                                    .replaceAll("%online%", conOnline)
                                    .replaceAll("%n", "\n")
                                    .replaceAll("%balance%", bal)
                                    .replaceAll("%xp%", xp)
                                    .replaceAll("xpLVL", xpLevel)
                                    .replaceAll("%ping%", ping);
                            String Footer = Utils.chat(plugin.getConfig().get("TBLayout.footer").toString())
                                    .replaceAll("%player%", player.getName())
                                    .replaceAll("%tps%", tps)
                                    .replaceAll("%online%", conOnline)
                                    .replaceAll("%n", "\n")
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
