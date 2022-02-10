package net.reprogrammed.mmc;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import net.reprogrammed.mmc.blocks.AirTank;
import net.reprogrammed.mmc.blocks.AncientEnchantmentTable;
import net.reprogrammed.mmc.blocks.AutomaticChainsaw;
import net.reprogrammed.mmc.blocks.Block;
import net.reprogrammed.mmc.blocks.CreeperTurret;
import net.reprogrammed.mmc.blocks.Farmer;
import net.reprogrammed.mmc.blocks.Fridge;
import net.reprogrammed.mmc.blocks.MetalDetector;
import net.reprogrammed.mmc.blocks.MineralDeposit;
import net.reprogrammed.mmc.blocks.PortableEndPortal;
import net.reprogrammed.mmc.blocks.RicePlant;
import net.reprogrammed.mmc.blocks.SalmonEggs;
import net.reprogrammed.mmc.blocks.Stove;
import net.reprogrammed.mmc.holders.Expireable;
import net.reprogrammed.mmc.holders.MotorBoat;

public class BlockEvents implements Listener {

	private static ArrayList<Block> registeredBlocks = new ArrayList<Block>();
	
	@SuppressWarnings("unchecked")
	public ArrayList<Block> getRegisteredBlocks()
	{
		return (ArrayList<Block>)registeredBlocks.clone();
	}
	
	public BlockEvents()
	{
		RegisterCustomBlocks();
	}
	
	public void RegisterCustomBlocks()
	{
		registeredBlocks.clear();
		registeredBlocks.add(new AirTank());
		registeredBlocks.add(new CreeperTurret());
		registeredBlocks.add(new SalmonEggs());
		registeredBlocks.add(new AncientEnchantmentTable());
		registeredBlocks.add(new MineralDeposit());
		registeredBlocks.add(new Fridge());
		registeredBlocks.add(new MetalDetector());
		registeredBlocks.add(new Stove());
		registeredBlocks.add(new RicePlant());
		registeredBlocks.add(new AutomaticChainsaw());
		registeredBlocks.add(new Farmer());
		registeredBlocks.add(new PortableEndPortal());
		Main.UpdateBlocks.add(new Farmer());
		Main.UpdateBlocks.add(new PortableEndPortal());
		Main.UpdateBlocks.add(new RicePlant());
	}
	
	
	@EventHandler
	public void Join(PlayerJoinEvent event)
	{
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "recipe give @a *");
	}
	
	@EventHandler
	public void MineBlock(BlockBreakEvent event)
	{
		if(event.getPlayer().getEquipment().getItemInMainHand() != null)
		{
			if(event.getBlock().getType() == Material.STONE)
			{
				for(Expireable e : Fridge.Expireables)
				{
					if(e.mat == event.getPlayer().getEquipment().getItemInMainHand().getType())
					{
						Fridge.DecayFood(event.getPlayer().getEquipment().getItemInMainHand(), e);
					}
				}	
			}
		}
		if(event.getBlock().getType() == Material.GLASS_PANE)
		{
			for(Entity e : event.getBlock().getWorld().getNearbyEntities(Main.AddToLocationAsNew(event.getBlock().getLocation(), 0.5f, 0, 0.5f), 0.3f, 0.3f, 0.3f))
			{
				if(e.getType() == EntityType.ARMOR_STAND)
				{
					ArmorStand as = (ArmorStand)e;
					
					if(as.getCustomName().equalsIgnoreCase("fish eggs"))
					{
						as.remove();
						event.setDropItems(false);
						ItemStack i = Items.salmonEggs.clone();
						if((new Random()).nextBoolean())
						{
							Items.setItemCustomModelData(i, 1002);
						}else {
							Items.setItemCustomModelData(i, 1001);
						}
						event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), i);
					}
				}
			}
		}
		
		if(event.getBlock().getType() == Material.CLAY)
		{
			if((new Random()).nextInt(101) < 15)
			{
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), Items.salt);
				event.setDropItems(false);
			}
		}
		
		if(event.getBlock().getType() == Material.DISPENSER)
		{
			Dispenser d = (Dispenser)event.getBlock().getState();
			
			for(Block b : registeredBlocks)
			{
				if(d.getLock() != null && d.getLock() != "")
				{
					if(d.getLock().equalsIgnoreCase(b.getTag()))
					{
						b.Remove(event);
						break;
					}
				}
			}
		}
	}
	
	@EventHandler
	public void CheckBlockInteraction(PlayerInteractEvent event)
	{
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			if(event.getClickedBlock().getType() == Material.DISPENSER)
			{
				Dispenser d = (Dispenser)event.getClickedBlock().getState();
				
				for(Block b : registeredBlocks)
				{
					if(d.getLock() != null && d.getLock() != "")
					{
						if(d.getLock().equalsIgnoreCase(b.getTag()))
						{
							b.Interact(event);
							break;
						}
					}
				}
			}
		}
	}
		
	/**
	 * @param event
	 */
	@EventHandler
	public void PlaceBoat(PlayerInteractEvent event)
	{
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			if(event.hasItem())
			{
				if(event.getItem().getType() == Items.motorBoat.getType())
				{
					if(event.getItem().hasItemMeta())
					{
						if(event.getItem().getItemMeta().hasCustomModelData())
						{
							if(event.getItem().getItemMeta().getCustomModelData() == Items.motorBoat.getItemMeta().getCustomModelData())
							{
								Boat b = (Boat)event.getClickedBlock().getWorld().spawnEntity(Main.AddToLocationAsNew(event.getClickedBlock().getLocation(), 0, 0.5f, 0), EntityType.BOAT);
								event.setCancelled(true);
								ArmorStand stand = (ArmorStand)event.getClickedBlock().getWorld().spawnEntity(b.getLocation(), EntityType.ARMOR_STAND);
								
								stand.setGravity(false);
								stand.setBasePlate(false);
								stand.setCollidable(false);
								stand.setInvulnerable(true);
								stand.setMarker(true);
								stand.setInvisible(true);
								
								ItemStack boatt = Items.CreateItem(Material.OAK_BOAT, "Motor Boat Texture", 1);
								Items.setItemCustomModelData(boatt, 1001);
								
								stand.getEquipment().setHelmet(boatt);
								
								MotorBoat.motorBoats.add(new MotorBoat(stand, b));
								
								event.getPlayer().getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void PlaceBlock(BlockPlaceEvent event)
	{
		
		if(event.getBlock().getType() == Material.SWEET_BERRY_BUSH || event.getBlock().getType() == Material.OAK_SLAB)
		{
			if(event.getPlayer().getEquipment().getItemInMainHand().hasItemMeta())
			{
				if(event.getPlayer().getEquipment().getItemInMainHand().getItemMeta().hasCustomModelData())
				{
					event.setCancelled(true);
					return;
				}
			}
		}
		if(event.getBlock().getType() == Material.ORANGE_GLAZED_TERRACOTTA)
		{
			if(event.getPlayer().getEquipment().getItemInMainHand().hasItemMeta())
			{
				if(event.getPlayer().getEquipment().getItemInMainHand().getItemMeta().hasCustomModelData())
				{
					event.setCancelled(true);
					return;
				}
			}
		}
		for(Block b : registeredBlocks)
		{
			if(b.getRootItem().getType() == event.getBlock().getType())
			{
				if(event.getPlayer().getEquipment().getItemInMainHand().hasItemMeta())
				{
					if(event.getPlayer().getEquipment().getItemInMainHand().getItemMeta().hasCustomModelData())
					{
						if(b.getRootItem().getItemMeta().getCustomModelData() == event.getPlayer().getEquipment().getItemInMainHand().getItemMeta().getCustomModelData())
						{
							b.Place(event.getBlock().getLocation());
							break;
						}	
					}
				}
			}
		}
	}
	
}
