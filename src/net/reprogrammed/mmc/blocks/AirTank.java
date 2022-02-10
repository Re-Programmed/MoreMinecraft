package net.reprogrammed.mmc.blocks;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.reprogrammed.mmc.Items;

public class AirTank extends Block {

	@Override
	public ItemStack getRootItem() {
		return Items.airTank;
	}

	@Override
	public Material getDisplayMaterial() {
		return Material.GRAY_CONCRETE;
	}

	@Override
	public int getDisplayCustomModelData() {
		return 1001;
	}

	@Override
	public String getName() {
		return "Air Tank";
	}

	@Override
	public void Interact(PlayerInteractEvent event)
	{
		event.setCancelled(true);
		event.getPlayer().setRemainingAir(event.getPlayer().getMaximumAir());
		event.getPlayer().playSound(event.getClickedBlock().getLocation(), Sound.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 100f, 1f);
	}
	
}
