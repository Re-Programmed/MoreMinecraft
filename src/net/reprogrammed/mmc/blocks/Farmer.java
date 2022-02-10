package net.reprogrammed.mmc.blocks;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.reprogrammed.mmc.Items;
import net.reprogrammed.mmc.Main;

public class Farmer extends Block {

	@Override
	public ItemStack getRootItem() {
		return Items.Farmer;
	}

	@Override
	public Material getDisplayMaterial() {
		return Material.ORANGE_CONCRETE;
	}

	@Override
	public int getDisplayCustomModelData() {
		return 1001;
	}

	@Override
	public void AddToArmorStand(ArmorStand stand) {
		super.AddToArmorStand(stand);
		
		stand.setCustomName("farmer");
		stand.setCustomNameVisible(false);
	}
	

	@Override
	public void Interact(PlayerInteractEvent event) {
		event.setCancelled(true);
		Dispenser d = (Dispenser)event.getClickedBlock().getState();
		String log = d.getLock();
		d.setLock("");
		d.setCustomName("Farmer");
		d.update();
		event.getPlayer().openInventory(d.getInventory());
		d.setLock(log);
		d.update();
	}
	
	@Override
	public String getName() {
		return "Farmer";
	}

	@Override
	public String GetCheckArmorStandName() {
		return "farmer";
	}
	
	@Override
	public void Remove(BlockBreakEvent event) {
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
		
		ItemStack item = getRootItem().clone();
		item.setAmount(1);
		
		event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), item);
	}
	
	@Override
	public void Update(ArmorStand as) {
		super.Update(as);
		
		org.bukkit.block.Block b = as.getLocation().getBlock().getRelative(BlockFace.UP);
		
		if(b != null)
		{
			Dispenser d = (Dispenser)as.getLocation().getBlock().getState();

			if(b.getType() == Material.GRASS_BLOCK || b.getType() == Material.DIRT)
			{
				if(d.getInventory().contains(Items.TillPowerFarmer))
				{
					b.setType(Material.FARMLAND);
					b.getWorld().playSound(b.getLocation(), Sound.ITEM_HOE_TILL, 1, 1);
				}
			}
			
			if(b.getType() == Material.FARMLAND)
			{
				org.bukkit.block.Block bf = b.getRelative(BlockFace.UP);				
				
				if(bf.getType() == Material.WHEAT || bf.getType() == Material.POTATOES || bf.getType() == Material.CARROTS || bf.getType() == Material.BEETROOTS)
				{
					if(d.getInventory().contains(Items.BonemealPowerFarmer))
					{
						if(d.getInventory().contains(Material.BONE_MEAL))
						{
							if((new Random()).nextInt(101) < 55)
							{
								d.getInventory().removeItem(new ItemStack(Material.BONE_MEAL, 1));
							}
							
							bf.applyBoneMeal(BlockFace.UP);


						}
					}
					
					if(d.getInventory().contains(Items.HarvestPowerFarmer))
					{
						ArrayList<ItemStack> dropsSaved = new ArrayList<ItemStack>();
						boolean breakblock = false;
						for(ItemStack is : bf.getDrops())
						{
							dropsSaved.add(is);
							if(is.getAmount() > 1)
							{
								breakblock = true;
							}
						}
						
						if(breakblock)
						{
							int x = 0;
							for(ItemStack i : d.getInventory().getContents())
							{
								if(i != null)
								{
									x++;
								}
							}
							
							if(x <= 8)
							{
								for(ItemStack drop : dropsSaved)
								{
									d.getInventory().addItem(drop);
								}
								bf.getDrops().clear();
								bf.setType(Material.AIR);
							}
							
							
						}
					}

				}else {
					if(d.getInventory().contains(Items.PlantPowerFarmer))
					{
						if(d.getInventory().contains(Material.WHEAT_SEEDS))
						{
							d.getInventory().removeItem(new ItemStack(Material.WHEAT_SEEDS, 1));
							bf.setType(Material.WHEAT);
							bf.getWorld().playSound(bf.getLocation(), Sound.ITEM_CROP_PLANT, 1, 1);
						}
						
						if(d.getInventory().contains(Material.POTATO))
						{
							d.getInventory().removeItem(new ItemStack(Material.POTATO, 1));
							bf.setType(Material.POTATOES);
							bf.getWorld().playSound(bf.getLocation(), Sound.ITEM_CROP_PLANT, 1, 1);
						}
						
						if(d.getInventory().contains(Material.CARROT))
						{
							d.getInventory().removeItem(new ItemStack(Material.CARROT, 1));
							bf.setType(Material.CARROTS);
							bf.getWorld().playSound(bf.getLocation(), Sound.ITEM_CROP_PLANT, 1, 1);
						}
						
						if(d.getInventory().contains(Material.BEETROOT_SEEDS))
						{
							d.getInventory().removeItem(new ItemStack(Material.BEETROOT_SEEDS, 1));
							bf.setType(Material.BEETROOTS);
							bf.getWorld().playSound(bf.getLocation(), Sound.ITEM_CROP_PLANT, 1, 1);
						}
					}
				}
			}
		}
	}
}
	
