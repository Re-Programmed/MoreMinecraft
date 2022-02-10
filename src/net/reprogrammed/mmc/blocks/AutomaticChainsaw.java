package net.reprogrammed.mmc.blocks;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import net.reprogrammed.mmc.Items;

public class AutomaticChainsaw extends Block {

	@Override
	public ItemStack getRootItem() {
		return Items.AutomaticChainsaw;
	}

	@Override
	public Material getDisplayMaterial() {
		return Material.STONECUTTER;
	}

	@Override
	public int getDisplayCustomModelData() {
		return 1001;
	}

	@Override
	public String getName() {
		return "Automatic Chainsaw";
	}
	
	@Override
	public void AddToArmorStand(ArmorStand stand) {
		stand.setCustomName("auto_saw");
	}
	
	

}
