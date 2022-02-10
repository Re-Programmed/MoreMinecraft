package net.reprogrammed.mmc.blocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Salmon;
import org.bukkit.inventory.ItemStack;

import net.reprogrammed.mmc.Items;
import net.reprogrammed.mmc.Main;

public class SalmonEggs extends Block {

	@Override
	public ItemStack getRootItem() {
		return getRootItemS();
	}
	
	static ItemStack getRootItemS()
	{
		ItemStack egg = Items.CreateItem(Material.GLASS_PANE, "Salmon Eggs", 1);
		
		Items.setItemCustomModelData(egg, getDisplayCustomModelDataS());
		
		return egg;
	}

	@Override
	public Material getDisplayMaterial() {
		return Material.GLASS_PANE;
	}

	@Override
	public int getDisplayCustomModelData() {
		return getDisplayCustomModelDataS();
	}
	
	static int getDisplayCustomModelDataS() 
	{
		return 1001;
	}

	@Override
	public String getName() {
		return "Salmon Eggs";
	}
	
	@SuppressWarnings("deprecation")
	public static void SpawnSalmon(Location loc)
	{
		loc.getWorld().playSound(loc, Sound.BLOCK_BEEHIVE_EXIT, 1f, 1f);
		loc.getWorld().spawnParticle(Particle.BUBBLE_POP, loc, 100, 0.01f, 1f, 1f, 1f);
		
		Salmon salmon = (Salmon)loc.getWorld().spawnEntity(loc, EntityType.SALMON);
		salmon.setHealth(1);
		salmon.setMaxHealth(1);
		salmon.setCustomName("Baby Salmon");
		salmon.setRemoveWhenFarAway(false);
	}
	
	public static void SpawnSalmonEggs(Location loc)
	{
		loc.getBlock().setType(Material.GLASS_PANE);
		
		ArmorStand stand = (ArmorStand)loc.getWorld().spawnEntity(Main.AddToLocationAsNew(loc, 0.5f, 0, 0.5f), EntityType.ARMOR_STAND);
		
		stand.setCustomNameVisible(false);
		stand.setCustomName("Fish Eggs");
		
		stand.getEquipment().setHelmet(getRootItemS());
		stand.setSmall(true);
		stand.setInvisible(true);
		stand.setBasePlate(false);
		stand.setMarker(true);
		stand.setInvulnerable(true);
	}

}
