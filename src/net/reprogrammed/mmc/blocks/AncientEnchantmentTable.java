package net.reprogrammed.mmc.blocks;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import net.reprogrammed.mmc.Items;
import net.reprogrammed.mmc.Main;
import net.reprogrammed.mmc.enchants.Enchant;
import net.reprogrammed.mmc.enchants.Enchants;

public class AncientEnchantmentTable extends Block {

	public static Enchants[] enchantsa = {Enchants.BLAZING, Enchants.XP_COLLECTOR, Enchants.CHOP};
	
	public static Enchants nextEnchant;
	
	@Override
	public ItemStack getRootItem() {
		return Items.enchantATable;
	}

	@Override
	public Material getDisplayMaterial() {
		return Material.ACACIA_PLANKS;
	}

	@Override
	public int getDisplayCustomModelData() {
		return 1001;
	}

	@Override
	public String getName() {
		return "AncientEnch";
	}
	
	@Override
	public void Place(Location location) {
		super.Place(location);
		
		ArmorStand as = (ArmorStand) location.getWorld().spawnEntity(Main.AddToLocationAsNew(location, 0.5f, 1f, 0.5f), EntityType.ARMOR_STAND);
		as.setCustomName(ChatColor.GRAY + "Next: None");
		as.setCustomNameVisible(true);
		as.setInvisible(true);
		as.setInvulnerable(true);
		as.setGravity(false);
		as.setMarker(true);
		as.setSmall(true);
		
		nextEnchant = shuffle(enchantsa).get(0);
		as.setCustomName(ChatColor.GRAY + "Next: " + nextEnchant.enchant.getName() + " - " + nextEnchant.enchant.GetXPLevel() + " Levels");
		as.setCustomNameVisible(true);
		return;
	}

	@Override
	public void Remove(BlockBreakEvent event) {
		super.Remove(event);
		
		for(Entity e : event.getBlock().getWorld().getNearbyEntities(Main.AddToLocationAsNew(event.getBlock().getLocation(), 0.5f, 0f, 0.5f), 0.5f, 2f, 0.5f))
		{
			if(e.getType() == EntityType.ARMOR_STAND)
			{
				if(e.getCustomName() != null)
				{
					if(e.getCustomName().contains(ChatColor.GRAY + "Next: "))
					{
						e.remove();
						break;
					}
				}
			}
		}
	}
	
	@Override
	public void Interact(PlayerInteractEvent event) {
		
		ArmorStand as = null;
		
		for(Entity e : event.getClickedBlock().getWorld().getNearbyEntities(Main.AddToLocationAsNew(event.getClickedBlock().getLocation(), 0.5f, 0f, 0.5f), 0.5f, 2f, 0.5f))
		{
			if(e.getType() == EntityType.ARMOR_STAND)
			{
				if(e.getCustomName() != null)
				{
					if(e.getCustomName().contains(ChatColor.GRAY + "Next: "))
					{
						as = (ArmorStand)e;
						
						break;
					}
				}
			}
		}
		
		if(as == null)
		{
			as = (ArmorStand) event.getClickedBlock().getWorld().spawnEntity(Main.AddToLocationAsNew(event.getClickedBlock().getLocation(), 0.5f, 1f, 0.5f), EntityType.ARMOR_STAND);
			as.setCustomName(ChatColor.GRAY + "Next: None");
			as.setCustomNameVisible(true);
			as.setInvisible(true);
			as.setInvulnerable(true);
			as.setGravity(false);
			as.setMarker(true);
			as.setSmall(true);
		}
		
		if(nextEnchant == null)
		{
			nextEnchant = shuffle(enchantsa).get(0);
			as.setCustomName(ChatColor.GRAY + "Next: " + nextEnchant.enchant.getName() + " - " + nextEnchant.enchant.GetXPLevel() + " Levels");
			as.setCustomNameVisible(true);
			event.setCancelled(true);
			return;
		}
		
		event.setCancelled(true);
		
		if(event.getPlayer().getLevel() < 2)
		{
			return;
		}
				
		boolean disenchant = false;
		
		for(Entity e : event.getClickedBlock().getWorld().getNearbyEntities(event.getClickedBlock().getLocation(), 1.5f, 1.5f, 1.5f))
		{
			if(e.getType() == EntityType.DROPPED_ITEM)
			{
				Item i = (Item)e;
				if(i.getItemStack().getType() == Material.LAPIS_LAZULI)
				{
					disenchant = true;
					if(i.getItemStack().getAmount() > 1)
					{
						ItemStack it = i.getItemStack();
						it.setAmount(it.getAmount() - 1);
						i.setItemStack(it);
					}else {
						e.remove();
					}
					break;
				}
			}
		}
		
		for(Entity e : event.getClickedBlock().getWorld().getNearbyEntities(event.getClickedBlock().getLocation(), 1.5f, 1.5f, 1.5f))
		{
			if(e.getType() == EntityType.DROPPED_ITEM)
			{
				Item i = (Item)e;

				ItemStack it = i.getItemStack();
				if(disenchant)
				{
					for(Enchants ec : enchantsa)
					{
						if(Enchant.HasEnchant(it, ec))
						{
							if(event.getPlayer().getLevel() >= ec.enchant.GetXPLevel())
							{
									Enchant.RemoveEnchant(it, ec);
									event.getPlayer().setLevel(event.getPlayer().getLevel() - ec.enchant.GetXPLevel());
									event.getClickedBlock().getWorld().spawnParticle(Particle.FIREWORKS_SPARK, Main.AddToLocationAsNew(event.getClickedBlock().getLocation(), 0.5f, 0f, 0.5f), 100, 1f, 1f, 1f, 0.01f);
									event.getClickedBlock().getWorld().playSound(event.getClickedBlock().getLocation(), Sound.BLOCK_GRINDSTONE_USE, 1f, 1f);

								
							}else {
								event.getClickedBlock().getWorld().spawnParticle(Particle.FLAME, Main.AddToLocationAsNew(event.getClickedBlock().getLocation(), 0.5f, 0f, 0.5f), 100, 1f, 1f, 1f, 0.01f);
								event.getClickedBlock().getWorld().playSound(event.getClickedBlock().getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
							}
						}else {
							event.getClickedBlock().getWorld().spawnParticle(Particle.FLAME, Main.AddToLocationAsNew(event.getClickedBlock().getLocation(), 0.5f, 0f, 0.5f), 100, 1f, 1f, 1f, 0.01f);
							event.getClickedBlock().getWorld().playSound(event.getClickedBlock().getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
						}
					}
				}else {
					
					if(event.getPlayer().getLevel() >= nextEnchant.enchant.GetXPLevel())
					{
						if(!Enchant.HasEnchant(it, nextEnchant))
						{
							Enchant.ApplyEnchant(it, nextEnchant);
							event.getPlayer().setLevel(event.getPlayer().getLevel() - nextEnchant.enchant.GetXPLevel());
							event.getClickedBlock().getWorld().spawnParticle(Particle.COMPOSTER, Main.AddToLocationAsNew(event.getClickedBlock().getLocation(), 0.5f, 0f, 0.5f), 100, 1f, 1f, 1f, 0.01f);
							event.getClickedBlock().getWorld().playSound(event.getClickedBlock().getLocation(), Sound.BLOCK_ANVIL_USE, 1f, 1f);
							
							as.setCustomName(ChatColor.GRAY + "Next: None");
							nextEnchant = null;
							break;	
						}else {
							event.getClickedBlock().getWorld().spawnParticle(Particle.FLAME, Main.AddToLocationAsNew(event.getClickedBlock().getLocation(), 0.5f, 0f, 0.5f), 100, 1f, 1f, 1f, 0.01f);
							event.getClickedBlock().getWorld().playSound(event.getClickedBlock().getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
						}
					}else {
						event.getClickedBlock().getWorld().spawnParticle(Particle.FLAME, Main.AddToLocationAsNew(event.getClickedBlock().getLocation(), 0.5f, 0f, 0.5f), 100, 1f, 1f, 1f, 0.01f);
						event.getClickedBlock().getWorld().playSound(event.getClickedBlock().getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
					}
					
				}
				i.setItemStack(it);
				break;
			}
		}
	}
	
	ArrayList<Enchants> shuffle(Enchants [] arr) {
		ArrayList<Enchants> ench = new ArrayList<Enchants>();
		
        for (int i = 0; i < arr.length; i++) {
            int index = (int) (Math.random() * arr.length);
            ench.add(arr[index]);
        }
        
        return ench;
    }
	
}
