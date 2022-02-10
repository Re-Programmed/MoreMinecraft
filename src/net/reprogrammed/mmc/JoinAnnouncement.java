package net.reprogrammed.mmc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.md_5.bungee.api.ChatColor;

public class JoinAnnouncement implements Listener {

	public static int Version = 0;
	
	public static String updatemsg = ChatColor.DARK_PURPLE + "While you were gone: "
			+ ChatColor.GOLD + " - Added join message for new features.";
	
	public static void CheckSendMessage(Player p)
	{
		if(!ScoreboardManager.getPlayersInBoard("update_message_mm", Version).contains(p.getName()))
		{
			p.sendMessage(updatemsg);
			ScoreboardManager.SetScoreboardVal(p, "update_message_mm", Version);
		}
	}
	
	@EventHandler
	public void JoinGame(PlayerJoinEvent event)
	{
		CheckSendMessage(event.getPlayer());
	}
	
}
