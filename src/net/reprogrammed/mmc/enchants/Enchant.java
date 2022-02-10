package net.reprogrammed.mmc.enchants;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public abstract class Enchant {

	public abstract String getName();
	
	public abstract ArrayList<String> enchantableWords();
	
	public void DamageEvent(EntityDamageByEntityEvent event)
	{
		
	}
	
	public void BreakBlock(BlockBreakEvent event)
	{
		
	}
	
	public abstract int GetXPLevel();
	
	public static void ApplyEnchant(ItemStack item, Enchants enchant)
	{
		boolean go = false;
		for(String s : enchant.enchant.enchantableWords())
		{
			if(item.getType().toString().toLowerCase().contains(s.toLowerCase()))
			{
				go = true;
			}
		}
		
		if(!go) {return;}
		
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		
		if(meta.hasLore())
		{
			lore = meta.getLore();
			
			for(String s : lore)
			{
				if(s.toLowerCase().contains(enchant.enchant.getName().toLowerCase()))
				{
					return;
				}
			}
		}
		
		lore.add(ChatColor.GRAY + "E: " + enchant.enchant.getName());
		
		meta.addEnchant(Enchantment.LUCK, 1, true);
				
		meta.setLore(lore);
				
		item.setItemMeta(meta);
	}
	
	public static void RemoveEnchant(ItemStack item, Enchants enchant)
	{
		if(!item.getItemMeta().hasLore()) {return;}
		ItemMeta m = item.getItemMeta();
		List<String> lore = item.getItemMeta().getLore();
		
		for(String s : lore)
		{
			if(s.toLowerCase().contains(enchant.enchant.getName().toLowerCase()))
			{
				lore.remove(s);
				m.setLore(lore);
				item.setItemMeta(m);
				break;
			}
		}
	}
	
	public static boolean HasEnchant(ItemStack item, Enchants enchant) 
	{
		if(!item.getItemMeta().hasLore()) {return false;}
		List<String> lore = item.getItemMeta().getLore();
		
		for(String s : lore)
		{
			if(s.toLowerCase().contains(enchant.enchant.getName().toLowerCase()))
			{
				return true;
			}
		}
		
		return false;
	}
}
