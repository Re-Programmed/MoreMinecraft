package net.reprogrammed.mmc;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManager {


	@SuppressWarnings("deprecation")
	public static void SetScoreboardVal(Player p, String objective, int scoreval)
	{
		org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();
    	Scoreboard board = manager.getMainScoreboard();
    	Objective obj = board.getObjective(objective);
    	
    	if(obj == null)
    	{
    		obj = board.registerNewObjective(objective, "dummy");
    	}
    	
    	Score score = obj.getScore(p);
    	score.setScore(scoreval);
	}
	
	@SuppressWarnings("deprecation")
	public static ArrayList<String> getPlayersInBoard(String objective, int valcheck)
	{
		ArrayList<String> str = new ArrayList<String>();
		org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();
    	Scoreboard board = manager.getMainScoreboard();
    	Objective obj = board.getObjective(objective);
    	
    	if(obj == null)
    	{
    		obj = board.registerNewObjective(objective, "dummy");
    	}
    	
    	for(String s : board.getEntries())
    	{
    		if(obj.getScore(s) != null)
    		{
    			if(obj.getScore(s).getScore() == valcheck)
    			{
    				str.add(objective);
    			}
    		}
    	}
    	
    	return str;
	}
	
	@SuppressWarnings("deprecation")
	public static ArrayList<Entity> getEntitiesInBoard(String objective)
	{
		ArrayList<Entity> e = new ArrayList<Entity>();
		
		org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();
    	Scoreboard board = manager.getMainScoreboard();
    	Objective obj = board.getObjective(objective);
    	
    	if(obj == null)
    	{
    		obj = board.registerNewObjective(objective, "dummy");
    	}
    	
    	for(String s : board.getEntries())
    	{
    		if(obj.getScore(s) != null)
    		{
    			String uuid = s;
    			if (uuid.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")) {
        			if(obj.getScore(s).getScore() == GetScoreIDWay())
        			{
        				e.add(getEntityByUniqueId(UUID.fromString(s)));
        			}
    			}
    		}
    	}
    	
    	return e;
	}
	
	public static Entity getEntityByUniqueId(UUID uniqueId) {
	    for (World world : Bukkit.getWorlds()) {
	        for (Entity entity : world.getEntities()) {
	            if (entity.getUniqueId().equals(uniqueId))
	                return entity;
	         }
	    }

	    return null;
	}
	
	public static int GetScoreIDWay()
	{
		return 8090623;
	}
	
	@SuppressWarnings("deprecation")
	public static void AddScoreboardVal(Player p, String objective, int scoreval)
	{
		org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();
    	Scoreboard board = manager.getMainScoreboard();
    	Objective obj = board.getObjective(objective);
    	
    	if(obj == null)
    	{
    		obj = board.registerNewObjective(objective, "dummy");
    	}
    	
    	Score score = obj.getScore(p);
    	score.setScore(score.getScore() + scoreval);
	}
	
	public static int GetScoreboardVal(Player p, String objective)
	{
		org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();
    	Scoreboard board = manager.getMainScoreboard();
    	Objective obj = board.getObjective(objective);
    	
    	if(obj == null)
    	{
    		return 0;
    	}
    	
    	@SuppressWarnings("deprecation")
		Score score = obj.getScore(p);
    	return score.getScore();
	}
	
}
