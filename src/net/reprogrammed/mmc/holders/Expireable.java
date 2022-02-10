package net.reprogrammed.mmc.holders;

import org.bukkit.Material;

public class Expireable {
	public final Material mat;
	public final int decayRate;
	
	public Expireable(Material mat, int decayRate)
	{
		this.mat = mat;
		this.decayRate = decayRate;
		
	}
}
