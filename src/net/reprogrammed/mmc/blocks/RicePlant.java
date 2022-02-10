package net.reprogrammed.mmc.blocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import net.reprogrammed.mmc.Items;
import net.reprogrammed.mmc.Main;

public class RicePlant extends Block {

	@Override
	public ItemStack getRootItem() {
		return Items.Rice;
	}

	@Override
	public Material getDisplayMaterial() {
		return Material.GRASS;
	}

	@Override
	public int getDisplayCustomModelData() {
		return 1001;
	}

	@Override
	public String getName() {
		return "Rice Plant";
	}

	@Override
	public String GetCheckArmorStandName() {
		return "rice plant";
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void AddToArmorStand(ArmorStand stand) {
		super.AddToArmorStand(stand);
		stand.setCustomNameVisible(false);
		stand.setCustomName("rice plant");
		stand.setMaxHealth(100f);
		stand.setHealth(100f);
	}
	
	@Override
	public void Update(ArmorStand as) {
		super.Update(as);
		
		as.setHealth(as.getHealth() - 1f);
		
		if(as.getHealth() <= 3f)
		{
			as.setHealth(100f);
			
			ItemStack item = as.getEquipment().getHelmet();
			Items.setItemCustomModelData(item, item.getItemMeta().getCustomModelData() + 1);
			
			as.getEquipment().setHelmet(item);
		}
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
			ItemStack item = Items.RiceItem.clone();
			
			if(rem.getEquipment().getHelmet().getItemMeta().getCustomModelData() == 1003)
			{
				item.setAmount(2);
				event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), item);
			}
			
			if(rem.getEquipment().getHelmet().getItemMeta().getCustomModelData() > 1003)
			{
				item.setAmount(3);
				event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), item);
			}
			
			ItemStack root = getRootItem().clone();
			root.setAmount(1);
			
			ItemMeta meta = root.getItemMeta();
			meta.setDisplayName(ChatColor.WHITE + "Rice Plant");
			
			root.setItemMeta(meta);
			
			event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), root);

			rem.remove();
		}
		
		event.setDropItems(false);
	}
	
	public static void PlaceNew(Location loc)
	{
		(new RicePlant()).Place(loc);
	}
}
