package net.reprogrammed.mmc.blocks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import net.reprogrammed.mmc.Items;
import net.reprogrammed.mmc.Main;

public class PortableEndPortal extends Block {

	@Override
	public ItemStack getRootItem() {
		return Items.PortableEndPortal;
	}

	@Override
	public Material getDisplayMaterial() {
		return Material.END_PORTAL_FRAME;
	}

	@Override
	public int getDisplayCustomModelData() {
		return 1001;
	}

	@Override
	public String getName() {
		return "Portable Portal";
	}
	
	@Override
	public void AddToArmorStand(ArmorStand stand) {
		super.AddToArmorStand(stand);
		
		stand.setCustomName("portable_portal");
		stand.setCustomNameVisible(false);
	}
	
	@Override
	public String GetCheckArmorStandName() {
		return "portable_portal";
	}
	
	@Override
	public void Update(ArmorStand as) {
		super.Update(as);
		
		for(Entity p : as.getWorld().getNearbyEntities(Main.AddToLocationAsNew(as.getLocation(), 0f, 1f, 0f), 0.4f, 0.4f, 0.4f))
		{
			for(World w : Bukkit.getWorlds())
			{
				if(w.getEnvironment() == Environment.THE_END)
				{
					Location loc = new Location(w, 0,w.getHighestBlockYAt(new Location(w, 0,0,0)),0);
					p.teleport(loc);
					return;
				}
			}
		}
	}
	
}
