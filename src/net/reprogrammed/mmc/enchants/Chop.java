package net.reprogrammed.mmc.enchants;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockBreakEvent;

public class Chop extends Enchant {

	Material[] choppableMats = {Material.OAK_LOG, Material.BIRCH_LOG, Material.SPRUCE_LOG, Material.JUNGLE_LOG, Material.DARK_OAK_LOG, Material.ACACIA_LOG};
	
	@Override
	public String getName() {
		return "Chop";
	}

	@Override
	public void BreakBlock(BlockBreakEvent event) {
		
		boolean go = false;
		
		for(Material mat : choppableMats)
		{
			if(event.getBlock().getType() == mat)
			{
				go = true;
			}
		}
		
		if(!go) {return;}
		
		Block b = event.getBlock();
		for(int i = 0; i < 150; i++)
		{
			boolean done = true;
			
			if(b.getRelative(BlockFace.UP).getType() == b.getType())
			{
				b.breakNaturally();
				b = b.getRelative(BlockFace.UP);
				done = false;
				continue;
			}
			
			if(b.getRelative(BlockFace.DOWN).getType() == b.getType())
			{
				b.breakNaturally();
				b = b.getRelative(BlockFace.DOWN);
				done = false;
				continue;
			}
			
			if(b.getRelative(BlockFace.EAST).getType() == b.getType())
			{
				b.breakNaturally();
				b = b.getRelative(BlockFace.EAST);
				done = false;
				continue;
			}
			
			if(b.getRelative(BlockFace.WEST).getType() == b.getType())
			{
				b.breakNaturally();
				b = b.getRelative(BlockFace.WEST);
				done = false;
				continue;
			}
			
			if(b.getRelative(BlockFace.SOUTH).getType() == b.getType())
			{
				b.breakNaturally();
				b = b.getRelative(BlockFace.SOUTH);
				done = false;
				continue;
			}
			
			if(b.getRelative(BlockFace.NORTH).getType() == b.getType())
			{
				b.breakNaturally();
				b = b.getRelative(BlockFace.NORTH);
				done = false;
				continue;
			}
			
			if(done)
			{
				break;
			}
		}
	}
	
	@Override
	public ArrayList<String> enchantableWords() {
		ArrayList<String> e = new ArrayList<String>();
		e.add("axe");
		return e;
	}



	@Override
	public int GetXPLevel() {
		return 30;
	}
	
}
