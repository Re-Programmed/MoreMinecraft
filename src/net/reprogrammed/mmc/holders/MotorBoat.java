package net.reprogrammed.mmc.holders;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.util.Vector;

import net.reprogrammed.mmc.Items;


public class MotorBoat {

	public static ArrayList<MotorBoat> motorBoats = new ArrayList<MotorBoat>();
	
	public final Boat boat;
	public final ArmorStand stand;
	
	@SuppressWarnings("deprecation")
	public MotorBoat(ArmorStand stand, Boat boat)
	{
		this.boat = boat;
		this.stand = stand;
		boat.setMaxSpeed(1000f);
	}
	
	float timeInside = 0f;
	
	public void Update()
	{
		if(stand.isDead() && !boat.isDead())
		{
			boat.getWorld().dropItemNaturally(boat.getLocation(), Items.motorBoat);
			
			for(Entity e : boat.getNearbyEntities(2f, 2f, 2f))
			{
				if(e.getType() == EntityType.DROPPED_ITEM)
				{
					Item i = (Item)e;
					
					if(!i.getItemStack().getItemMeta().hasCustomModelData())
					{
						i.remove();
					}
				}
			}
			
			boat.remove();
		}
		
		if(boat.isDead() && !stand.isDead())
		{
			stand.getWorld().dropItemNaturally(boat.getLocation(), Items.motorBoat);
			
			for(Entity e : stand.getNearbyEntities(2f, 2f, 2f))
			{
				if(e.getType() == EntityType.DROPPED_ITEM)
				{
					Item i = (Item)e;
					
					if(!i.getItemStack().getItemMeta().hasCustomModelData())
					{
						i.remove();
					}
				}
			}
			
			stand.remove();
		}
		
		if(stand.isDead() || boat.isDead())
		{
			return;
		}
		
		stand.teleport(boat);
		if(boat.getLocation().getBlock().getType() == Material.WATER || new Location(boat.getWorld(), boat.getLocation().getBlockX(), boat.getLocation().getBlockY() - 1, boat.getLocation().getBlockZ()).getBlock().getType() == Material.WATER)
		{
			if(boat.getPassengers().size() != 0)
			{
				if(timeInside == 0)
				{
					boat.getWorld().playSound(boat.getLocation(), Sound.ENTITY_BEE_STING, 15f, 1f);
				}
				if(timeInside < 3000)
				{
					timeInside++;
				}
				Vector dir = boat.getFacing().getDirection().multiply(-0.1f - timeInside/250);
				
				boat.getWorld().playSound(boat.getLocation(), Sound.BLOCK_ANCIENT_DEBRIS_BREAK, (timeInside/10), (timeInside/1000));
				
				boat.setVelocity(new Vector(-dir.getZ(), dir.getY(), dir.getX()));
			}else {
				timeInside = 0f;
				boat.getWorld().playSound(boat.getLocation(), Sound.BLOCK_ANCIENT_DEBRIS_BREAK, 2f, 0.1f);
			}
		}
	}
	
}
