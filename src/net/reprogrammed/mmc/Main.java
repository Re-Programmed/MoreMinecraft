package net.reprogrammed.mmc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import net.reprogrammed.mmc.blocks.CreeperTurret;
import net.reprogrammed.mmc.blocks.Fridge;
import net.reprogrammed.mmc.blocks.SalmonEggs;
import net.reprogrammed.mmc.enchants.EnchantEvents;
import net.reprogrammed.mmc.holders.Expireable;
import net.reprogrammed.mmc.holders.MotorBoat;

public class Main extends JavaPlugin {

	public static Main INSTANCE;
	
	CustomPopulator pop;
	
	public static final Random random = new Random();
	
	public static HashMap<Material, Material> cookables = new HashMap<Material, Material>();
	
	static int DecayTick = 0;
	
	public static ArrayList<net.reprogrammed.mmc.blocks.Block> UpdateBlocks = new ArrayList<net.reprogrammed.mmc.blocks.Block>();
		
	@Override
	public void onEnable()
	{
		
		cookables.put(Material.BEEF, Material.COOKED_BEEF);
		cookables.put(Material.CHICKEN, Material.COOKED_CHICKEN);
		cookables.put(Material.MUTTON, Material.COOKED_MUTTON);
		cookables.put(Material.COD, Material.COOKED_COD);
		cookables.put(Material.PORKCHOP, Material.COOKED_PORKCHOP);
		cookables.put(Material.SALMON, Material.COOKED_SALMON);
		cookables.put(Material.POTATO, Material.BAKED_POTATO);
		cookables.put(Material.RABBIT, Material.COOKED_RABBIT);
		cookables.put(Material.KELP, Material.DRIED_KELP);

		INSTANCE = this;
		
		Items.RegisterItems();
		
		getServer().getPluginManager().registerEvents(new BlockEvents(), this);
		getServer().getPluginManager().registerEvents(new ItemEvents(), this);

		getServer().getPluginManager().registerEvents(new EnchantEvents(), this);
		
		CreateUpdateMethod();
		
		pop = new CustomPopulator();
				
		for(World world : getServer().getWorlds())
		{
			if(world.getEnvironment() == Environment.NORMAL)
			{
				world.getPopulators().add(pop);
			}
		}
	}
	
	private void CreateUpdateMethod()
	{
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			public void run() { 
				try
				{
					for(MotorBoat b : MotorBoat.motorBoats)
					{
						b.Update();
					}
					
					
				}
				catch(Exception e)
				{
					
				}
			}
		}, 2, 2);
		
Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			public void run() { 
				DecayTick++;
				
				if(DecayTick >= 55)
				{
					DecayTick = 0;
					for(Player p : Bukkit.getOnlinePlayers())
					{
						Fridge.ExpireFoods(p);
					}
				}
			}
		}, 100, 100);
		
Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@SuppressWarnings("deprecation")
			public void run() { 
				try
				{
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 35f, 35f, 35f))
						{
							if(e.getType() == EntityType.ARMOR_STAND)
							{
								ArmorStand as = (ArmorStand)e;
								
								if(as.getCustomName() == null || as.getCustomName() == "") {continue;}
								
								if(e.getCustomName().contains(ChatColor.GRAY + "Next: "))
								{
									e.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, e.getLocation(), 15, 1f, 1f, 1f, 0.01f);
								}
								
								if(as.getLocation().getBlock().getType() == Material.DISPENSER)
								{
									for(net.reprogrammed.mmc.blocks.Block block : UpdateBlocks)
									{
										if(as.getCustomName().equalsIgnoreCase(block.GetCheckArmorStandName().toLowerCase()))
										{
											block.Update(as);
										}
									}
								}
									
								if(as.getCustomName().equalsIgnoreCase("auto_saw"))
								{
									if(as.getLocation().getBlock().getType() == Material.DISPENSER)
									{	
										if((new Random()).nextInt(101) < 35)
										{
											boolean go = true;
											for(int x = -3; x < 3; x++)
											{
												for(int y = -3; y < 3; y++)
												{
													for(int z = -3; z < 3; z++)
													{
														if(!go) {break;}
														Location loc = AddToLocationAsNew(as.getLocation().getBlock().getLocation(), x, y, z);
														
														if(loc.getBlock().getType().toString().toLowerCase().contains("log"))
														{
															loc.getBlock().breakNaturally();
															loc.getWorld().playSound(loc.getBlock().getLocation(), Sound.UI_STONECUTTER_TAKE_RESULT, 1f, 1f);

															loc.getBlock().setType(Material.DISPENSER);
															
															Dispenser d = (Dispenser)loc.getBlock().getState();
															
															d.setLock(((Dispenser)as.getLocation().getBlock().getState()).getLock());
															
															d.update(true);
															
															as.getLocation().getWorld().spawnParticle(Particle.FLAME, as.getLocation(), 20, 1, 1, 1, 0.01f);
															as.getLocation().getBlock().setType(Material.AIR);
															as.teleport(Main.AddToLocationAsNew(loc, 0.5f, 0, 0.5f));
															go = false;
														}
													}
													if(!go) {break;}
												}
												if(!go) {break;}
											}
											
											if(go)
											{
												for(int x = -3; x < 3; x++)
												{
													for(int y = -3; y < 3; y++)
													{
														for(int z = -3; z < 3; z++)
														{
															if(!go) {break;}
															Location loc = AddToLocationAsNew(as.getLocation().getBlock().getLocation(), x, y, z);
															
															if(loc.getBlock().getType().toString().toLowerCase().contains("leaves"))
															{
																loc.getBlock().breakNaturally();
																loc.getWorld().playSound(loc.getBlock().getLocation(), Sound.BLOCK_GRASS_BREAK, 1f, 1f);

																loc.getBlock().setType(Material.DISPENSER);
																
																Dispenser d = (Dispenser)loc.getBlock().getState();
																
																d.setLock(((Dispenser)as.getLocation().getBlock().getState()).getLock());
																
																d.update(true);
																
																as.getLocation().getWorld().spawnParticle(Particle.FLAME, as.getLocation(), 20, 1, 1, 1, 0.01f);
																as.getLocation().getBlock().setType(Material.AIR);
																as.teleport(Main.AddToLocationAsNew(loc, 0.5f, 0, 0.5f));
																go = false;
															}
														}
														if(!go) {break;}
													}
													if(!go) {break;}
												}
											}
										}
									}
								}
								
								if(as.getCustomName().equalsIgnoreCase("oven"))
								{
									if(as.getLocation().getBlock().getType() == Material.DISPENSER)
									{
										if(((Dispenser)as.getLocation().getBlock().getState()).getInventory().contains(Material.COAL))
										{
											as.getWorld().spawnParticle(Particle.FLAME, Main.AddToLocationAsNew(as.getLocation().getBlock().getLocation(), 0.5f, 1f, 0.5f), 25, 0.5f, 0f, 0.5f, 0.01f);
										}
										
										for(Entity ei : as.getNearbyEntities(1.5f, 1.5f, 1.5f))
										{
											if(ei.getType() == EntityType.DROPPED_ITEM)
											{
												Item te = (Item)ei;
												
												for(ItemStack i : ((Dispenser)as.getLocation().getBlock().getState()).getInventory().getContents())
												{
													if(i == null)
													{
														((Dispenser)as.getLocation().getBlock().getState()).getInventory().addItem(te.getItemStack());
														te.remove();
														break;
													}
												}
												
											}
										}
									}
									
									
									
									
									if(random.nextInt(51) < 2)
									{
										if(as.getLocation().getBlock().getType() == Material.DISPENSER)
										{
											boolean hasCoal = false;
											
											for(ItemStack i : ((Dispenser)as.getLocation().getBlock().getState()).getInventory())
											{
												if(i == null) {continue;}
												if(i.getType() == Material.COAL)
												{
													hasCoal = true;
													((Dispenser)as.getLocation().getBlock().getState()).getInventory().remove(new ItemStack(Material.COAL, 1));
													break;
												}
											}
											
											if(hasCoal)
											{
												for(ItemStack i : ((Dispenser)as.getLocation().getBlock().getState()).getInventory())
												{
													if(i == null) {continue;}
													for(Map.Entry<Material, Material> keyvaluepair : Main.cookables.entrySet())
													{
														if(i.getType() == keyvaluepair.getKey())
														{
															i.setType(keyvaluepair.getValue());
														}
													}
													
												}	
											}
										}
									}
								}
								
								if(as.getCustomName().equalsIgnoreCase("fridge"))
								{
									if(random.nextInt(6) < 2)
									{
										if(as.getLocation().getBlock().getType() == Material.DISPENSER)
										{
											for(ItemStack i : ((Dispenser)as.getLocation().getBlock().getState()).getInventory())
											{
												if(i == null) {continue;}
												for(Expireable ex : Fridge.Expireables)
												{
													if(i.getType() == ex.mat)
													{
														Fridge.DecayFood(i, ex, -1);
													}
												}
											}
										}
									}
								}
								
								if(as.getCustomName().equalsIgnoreCase("fish eggs"))
								{
									if(random.nextInt(1001) < 2)
									{
										SalmonEggs.SpawnSalmon(AddToLocationAsNew(as.getLocation(), 0, 1, 0));
										as.setHealth(as.getHealth() - 1);
										as.setMaxHealth(as.getHealth());
										
										if(as.getHealth() <= 15)
										{
											as.getWorld().playSound(as.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1f, 1f);
											as.getWorld().spawnParticle(Particle.SLIME, as.getLocation(), 100, 0.01f, 1f, 1f, 1f);
											as.getLocation().getBlock().setType(Material.AIR);
											as.remove();
										}
									}
								}
								
								if(as.getCustomName().equalsIgnoreCase("creeper turret"))
								{									
									for(Entity c : as.getNearbyEntities(20f, 20f, 20f))
									{
										if(c.getType() == EntityType.CREEPER)
										{
											if(!c.isDead())
											{
												LivingEntity le = (LivingEntity)c;
												
												CreeperTurret.ShootEntity(as, le);
											}
										}
									}
								}
								
								if(as.getCustomName().equalsIgnoreCase("metal detector"))
								{									
									if(as.getLocation().distance(p.getLocation()) < 2f)
									{
										if(!p.getInventory().isEmpty())
										{
											as.getWorld().playSound(as.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1f, 1f);
											as.getWorld().spawnParticle(Particle.COMPOSTER, as.getLocation(), 100, 1f, 2f, 1f, 0.01f);
										}
									}
								}
							}
						}
					}
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
		}, 20, 20);
		
	
	}
	
	@Override
	public void onDisable()
	{
		for(World world : Bukkit.getWorlds())
		{
			if(world.getPopulators().contains(pop))
			{
				world.getPopulators().remove(pop);
			}
		}		

	}
	
	public static void Log(String message)
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.sendMessage(message);
		}
	}
	
	public static Location AddToLocationAsNew(Location loc, float x, float y, float z)
	{
		return new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y, loc.getZ() + z);
	}
	
}
