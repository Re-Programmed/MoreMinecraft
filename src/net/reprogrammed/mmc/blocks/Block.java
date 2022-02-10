package net.reprogrammed.mmc.blocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.reprogrammed.mmc.Items;
import net.reprogrammed.mmc.Main;

public abstract class Block {
	
	public abstract ItemStack getRootItem();
	
	public abstract Material getDisplayMaterial();
	public abstract int getDisplayCustomModelData();
	
	public abstract String getName();
	
	public String GetCheckArmorStandName()
	{
		return "";
	}
	
	public void Update(ArmorStand as)
	{
		
	}
	
	public void Interact(PlayerInteractEvent event)
	{
		event.setCancelled(true);
	}
	
	public String getTag()
	{
		return "MORE_MINECRAFT:" + getName().toLowerCase().replace(' ', '_');
	}
	
	public void Place(Location location)
	{		
		location.getBlock().setType(Material.DISPENSER);
		Dispenser d = (Dispenser)location.getBlock().getState();
		
		d.setLock(getTag());
		
		d.update(true);
		
		ArmorStand displayStand = (ArmorStand)location.getWorld().spawnEntity(new Location(location.getWorld(), location.getX() + 0.5f, location.getY(), location.getZ() + 0.5f), EntityType.ARMOR_STAND);
		
		displayStand.setInvisible(true);
		displayStand.setMarker(true);
		displayStand.setFireTicks(999999999);
		displayStand.setSmall(true);
		
		AddToArmorStand(displayStand);
		
		ItemStack disp = Items.CreateItem(getDisplayMaterial(), getName(), 1);
	
		Items.setItemCustomModelData(disp, getDisplayCustomModelData());
		
		displayStand.getEquipment().setHelmet(disp);
	}
	
	public void AddToArmorStand(ArmorStand stand)
	{
		
	}
	
	//TODO: Make this work.
	public void Remove(Location location)
	{
		for(Entity e : location.getWorld().getNearbyEntities(location, 0.3f, 0.3f, 0.3f))
		{
			if(e.getType() == EntityType.ARMOR_STAND)
			{
				e.remove();
			}
		}
		
		location.getBlock().setType(Material.AIR);
	}
	
	public void Remove(BlockBreakEvent event)
	{	
		Location blockCenter = Main.AddToLocationAsNew(event.getBlock().getLocation(), 0.5f, 0, 0.5f);
		ArmorStand rem = null;
		double dist = 1;
		for(Entity e : event.getBlock().getLocation().getWorld().getNearbyEntities(blockCenter, 0.2f, 0.2f, 0.2f))
		{
			if(e.getType() == EntityType.ARMOR_STAND)
			{
				if(e.getLocation().distance(blockCenter) < dist)
				{
					dist = e.getLocation().distance(blockCenter);
					rem = (ArmorStand)e;
				}
			}
		}
		
		if(rem != null)
		{
			rem.remove();
		}
		
		event.setDropItems(false);
		
		event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), getRootItem());
	}
	
}
