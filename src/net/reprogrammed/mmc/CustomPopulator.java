package net.reprogrammed.mmc;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;

import net.reprogrammed.mmc.blocks.MineralDeposit;
import net.reprogrammed.mmc.blocks.RicePlant;
import net.reprogrammed.mmc.blocks.SalmonEggs;

public class CustomPopulator extends BlockPopulator {

	@Override
	public void populate(World world, Random random, Chunk chunk) {
		for(int x = 0; x < 15; x++)
		{
			for(int y = 50; y < 74; y++)
			{
				for(int z = 0; z < 15; z++)
				{
					Block b = chunk.getBlock(x, y, z);
					
					if(b.getType() == Material.GRASS_BLOCK)
					{
						if(random.nextInt(20001) < 10)
						{
							for(int x2 = -4; x2 < 4; x2++)
							{
								for(int y2 = -4; y2 < 4; y2++)
								{
									for(int z2 = -4; z2 < 4; z2++)
									{
										Block b2 = Main.AddToLocationAsNew(b.getLocation(), x2, y2, z2).getBlock();
										
										if(b2.getType() == Material.GRASS_BLOCK)
										{
											if(random.nextInt(101) < 5)
											{
												MineralDeposit.PlaceNew(b2.getLocation());
											}
										}
									}
								}
							}
						}
						
						if(random.nextInt(20001) < 10)
						{
							for(int x2 = -4; x2 < 4; x2++)
							{
								for(int y2 = -4; y2 < 4; y2++)
								{
									for(int z2 = -4; z2 < 4; z2++)
									{
										Block b2 = Main.AddToLocationAsNew(b.getLocation(), x2, y2, z2).getBlock();
										
										if(b2.getType() == Material.GRASS_BLOCK)
										{
											if(random.nextInt(101) < 5)
											{
												RicePlant.PlaceNew(b2.getLocation());
											}
										}
									}
								}
							}
						}
					}
					
					if(b.getType() == Material.WATER)
					{
						if(b.getRelative(BlockFace.DOWN).getType() != Material.WATER)
						{
							if(random.nextInt(40001) < 4)
							{
								for(int x2 = -10; x2 < 10; x2++)
								{
									for(int y2 = -10; y2 < 10; y2++)
									{
										for(int z2 = -10; z2 < 10; z2++)
										{
											Block b2 = Main.AddToLocationAsNew(b.getLocation(), x2, y2, z2).getBlock();
											
											if(b2.getType() == Material.WATER)
											{
												Material t = b2.getRelative(BlockFace.DOWN).getType();
												if(t != Material.WATER && t != Material.SEAGRASS && t != Material.TALL_SEAGRASS && t != Material.GLASS_PANE)
												{
													if(b2.getRelative(BlockFace.EAST).getType() != Material.GLASS_PANE && b2.getRelative(BlockFace.NORTH).getType() != Material.GLASS_PANE && b2.getRelative(BlockFace.SOUTH).getType() != Material.GLASS_PANE && b2.getRelative(BlockFace.WEST).getType() != Material.GLASS_PANE)
													{
														if(random.nextInt(101) < 35)
														{
															SalmonEggs.SpawnSalmonEggs(b2.getLocation());
															SalmonEggs.SpawnSalmon(b2.getLocation());
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

}
