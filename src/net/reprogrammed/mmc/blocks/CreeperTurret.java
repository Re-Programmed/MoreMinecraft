package net.reprogrammed.mmc.blocks;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Chest;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import net.reprogrammed.mmc.Items;
import net.reprogrammed.mmc.Main;

public class CreeperTurret extends Block {

	@Override
	public ItemStack getRootItem() {
		return Items.creeperTurret;
	}

	@Override
	public Material getDisplayMaterial() {
		return Material.END_ROD;
	}

	@Override
	public int getDisplayCustomModelData() {
		return 1001;
	}

	@Override
	public String getName() {
		return "Creeper Turret";
	}
	
	@Override
	public void AddToArmorStand(ArmorStand stand)
	{
		stand.setCustomName("Creeper Turret");
		stand.setCustomNameVisible(false);
	}

	public static void ShootEntity(ArmorStand source, LivingEntity le)
	{
		Chest c = null;
		
		if(Main.AddToLocationAsNew(source.getLocation(), 1f, 0, 0).getBlock().getType() == Material.CHEST)
		{
			c = (Chest)Main.AddToLocationAsNew(source.getLocation(), 1f, 0, 0).getBlock().getState();
		}else if(Main.AddToLocationAsNew(source.getLocation(), -1f, 0, 0).getBlock().getType() == Material.CHEST)
		{
			c = (Chest)Main.AddToLocationAsNew(source.getLocation(), -1f, 0, 0).getBlock().getState();
		}else if(Main.AddToLocationAsNew(source.getLocation(), 0, 0, 1f).getBlock().getType() == Material.CHEST)
		{
			c = (Chest)Main.AddToLocationAsNew(source.getLocation(), 0, 0, 1f).getBlock().getState();
		}else if(Main.AddToLocationAsNew(source.getLocation(), 0, 0, -1f).getBlock().getType() == Material.CHEST)
		{
			c = (Chest)Main.AddToLocationAsNew(source.getLocation(), 0, 0, -1f).getBlock().getState();
		}
		
		if(c == null) {return;}
		if(c.getBlockInventory().contains(Material.IRON_NUGGET, 15))
		{
			c.getBlockInventory().removeItem(new ItemStack(Material.IRON_NUGGET, 15));
		}else {
			return;
		}
		
		source.getWorld().playSound(source.getLocation(), Sound.BLOCK_BAMBOO_BREAK, 1f, 1f);
		source.getWorld().playSound(source.getLocation(), Sound.BLOCK_BAMBOO_BREAK, 1f, 1f);
		source.getWorld().playSound(source.getLocation(), Sound.BLOCK_BAMBOO_BREAK, 1f, 1f);
		source.getWorld().playSound(source.getLocation(), Sound.BLOCK_BAMBOO_BREAK, 1f, 1f);
		source.getWorld().playSound(source.getLocation(), Sound.BLOCK_BAMBOO_BREAK, 1f, 1f);
		source.getWorld().playSound(source.getLocation(), Sound.BLOCK_BAMBOO_BREAK, 1f, 1f);
		source.getWorld().playSound(source.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.1f, 1f);
		source.getWorld().playSound(source.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.1f, 1f);
		
		boolean noitem = false;
		
		if(le.getHealth() - 7f <= 0f)
		{
			for(ItemStack i : c.getBlockInventory().getStorageContents())
			{
				if(i == null || (i.getType() == Material.GUNPOWDER && i.getAmount() <= 62))
				{
					noitem = true;
					c.getBlockInventory().addItem(new ItemStack(Material.GUNPOWDER, 2));
					break;
				}
			}
		}
		
		le.damage(7f);
		
		if(noitem)
		{
			for(org.bukkit.entity.Entity e : le.getNearbyEntities(1f, 1f, 1f))
			{
				if(e.getType() == EntityType.DROPPED_ITEM)
				{
					Item i = (Item)e;
					
					if(i.getItemStack().getType() == Material.GUNPOWDER)
					{
						e.remove();
					}
				}
			}
		}
		
		if(le.getLocation().getX() < source.getLocation().getX())
		{
			source.getWorld().spawnParticle(Particle.SMOKE_NORMAL, Main.AddToLocationAsNew(source.getLocation(), -1f, 0, 0), 100, 0.01f, 0.5f, 0.5f, 0.5f);
		}else if(le.getLocation().getX() > source.getLocation().getX())
		{
			source.getWorld().spawnParticle(Particle.SMOKE_NORMAL, Main.AddToLocationAsNew(source.getLocation(), 1f, 0, 0), 100, 0.01f, 0.5f, 0.5f, 0.5f);
		}
		
		if(le.getLocation().getZ() < source.getLocation().getZ())
		{
			source.getWorld().spawnParticle(Particle.SMOKE_NORMAL, Main.AddToLocationAsNew(source.getLocation(), 0, 0, -1f), 100, 0.01f, 0.5f, 0.5f, 0.5f);
		}else if(le.getLocation().getZ() > source.getLocation().getZ())
		{
			source.getWorld().spawnParticle(Particle.SMOKE_NORMAL, Main.AddToLocationAsNew(source.getLocation(), 0, 0, 1f), 100, 0.01f, 0.5f, 0.5f, 0.5f);
		}

	}
	
}
