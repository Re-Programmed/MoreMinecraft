package net.reprogrammed.mmc.blocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.reprogrammed.mmc.Items;

public class MineralDeposit extends Block {

	@Override
	public ItemStack getRootItem() {
		return Items.mineralDust;
	}

	@Override
	public Material getDisplayMaterial() {
		return Material.GRASS_BLOCK;
	}

	@Override
	public int getDisplayCustomModelData() {
		return 1001;
	}

	@Override
	public String getName() {
		return "Mineral Deposit";
	}

	public static void PlaceNew(Location loc)
	{
		(new MineralDeposit()).Place(loc);
	}
	
}
