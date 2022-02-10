package net.reprogrammed.mmc.enchants;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import net.reprogrammed.mmc.blocks.AncientEnchantmentTable;

public class EnchantEvents implements Listener {

	@EventHandler
	public void EntityDamageByEntityEvent(EntityDamageByEntityEvent event)
	{
		if(event.getDamager().getType() == EntityType.PLAYER)
		{
			Player p = (Player)event.getDamager();
			
			ItemStack item = p.getEquipment().getItemInMainHand();
			if(item != null && item.getType() != Material.AIR)
			{
				for(Enchants e : AncientEnchantmentTable.enchantsa)
				{
					if(Enchant.HasEnchant(item, e))
					{
						e.enchant.DamageEvent(event);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void BlockBreakEvent(BlockBreakEvent event)
	{
		ItemStack item = event.getPlayer().getEquipment().getItemInMainHand();
		if(item != null && item.getType() != Material.AIR)
		{
			for(Enchants e : AncientEnchantmentTable.enchantsa)
			{
				if(Enchant.HasEnchant(item, e))
				{
					e.enchant.BreakBlock(event);
				}
			}
		}
	}
}
