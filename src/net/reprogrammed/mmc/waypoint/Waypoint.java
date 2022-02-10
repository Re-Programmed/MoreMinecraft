package net.reprogrammed.mmc.waypoint;

import org.bukkit.Location;

public class Waypoint {

	public final Location location;
	public final String name;
	public final boolean public_point;
	public final String[] players;
	
	public Waypoint(Location location, String name, boolean public_point, String... players)
	{
		this.location = location;
		this.name = name;
		this.public_point = public_point;
		this.players = players;
	}
	
	public void Update()
	{
		
	}
}
