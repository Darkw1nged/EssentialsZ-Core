package me.darkwinged.Essentials.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.darkwinged.Essentials.Main;
import me.darkwinged.Essentials.REWORK.Utils.Utils;

public class ScoreBoard implements Listener {
	
	private Main plugin;
	public ScoreBoard(Main plugin) { this.plugin = plugin; }
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        createScoreboard(e.getPlayer());
        updateScoreboard();    
    }
   
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        updateScoreboard();
    }
   
    public void createScoreboard(Player player){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("stats", "dummy");
        objective.setDisplayName(Utils.chat(Utils.getSideBar().getString("Sidebar.name")));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = objective.getScore("players:");
        score.setScore(Bukkit.getOnlinePlayers().size());
        player.setScoreboard(board);
    }
   
    public void updateScoreboard(){
        for(Player online : Bukkit.getOnlinePlayers()){
            Score score = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore("players:");
            score.setScore(Bukkit.getOnlinePlayers().size());
        }
    }

}
