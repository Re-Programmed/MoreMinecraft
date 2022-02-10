package net.reprogrammed.mmc.blocks;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import net.reprogrammed.mmc.Items;

public class MetalDetector extends Block {

	@Override
	public ItemStack getRootItem() {
		return Items.MetalDetector;
	}

	@Override
	public Material getDisplayMaterial() {
		return Material.ACACIA_PRESSURE_PLATE;
	}

	@Override
	public int getDisplayCustomModelData() {
		return 1001;
	}

	@Override
	public String getName() {
		return "Metal Detector";
	}

	@Override
	public void AddToArmorStand(ArmorStand stand) {
		stand.setCustomName("metal detector");
		stand.setCustomNameVisible(false);
	}
	
}
