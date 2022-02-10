package net.reprogrammed.mmc.blocks;

import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.reprogrammed.mmc.Items;

public class Stove extends Block {

	@Override
	public ItemStack getRootItem() {
		return Items.Stove;
	}

	@Override
	public Material getDisplayMaterial() {
		return Material.QUARTZ_BRICKS;
	}

	@Override
	public int getDisplayCustomModelData() {
		return 1001;
	}

	@Override
	public String getName() {
		return "Stove";
	}
	
	@Override
	public void AddToArmorStand(ArmorStand stand) {
		super.AddToArmorStand(stand);
		
		stand.setCustomName("oven");
		stand.setCustomNameVisible(false);
	}
	
	@Override
	public void Interact(PlayerInteractEvent event) {
		event.setCancelled(true);
		Dispenser d = (Dispenser)event.getClickedBlock().getState();
		String log = d.getLock();
		d.setLock("");
		d.setCustomName("Stove");
		d.update();
		event.getPlayer().openInventory(d.getInventory());
		d.setLock(log);
		d.update();
	}

}
