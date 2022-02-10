package net.reprogrammed.mmc.blocks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import net.reprogrammed.mmc.Items;
import net.reprogrammed.mmc.holders.Expireable;

public class Fridge extends Block {

	public static Expireable[] Expireables = {new Expireable(Material.BEETROOT_SOUP, 1), new Expireable(Material.COOKED_MUTTON, 2), new Expireable(Material.MUTTON, 2), new Expireable(Material.RABBIT_STEW, 1), new Expireable(Material.COOKED_RABBIT, 2), new Expireable(Material.RABBIT, 4), new Expireable(Material.PUMPKIN_PIE, 2), new Expireable(Material.BAKED_POTATO, 1), new Expireable(Material.ROTTEN_FLESH, 9), new Expireable(Material.CHICKEN, 4), new Expireable(Material.COOKED_SALMON, 1), new Expireable(Material.COOKED_COD, 1), new Expireable(Material.TROPICAL_FISH, 3), new Expireable(Material.SALMON, 3), new Expireable(Material.COD, 3), new Expireable(Material.COOKED_PORKCHOP, 1), new Expireable(Material.BEEF, 3), new Expireable(Material.PORKCHOP, 3), new Expireable(Material.COOKED_BEEF, 1), new Expireable(Material.COOKED_CHICKEN, 2), new Expireable(Material.MUSHROOM_STEW, 3), new Expireable(Material.BREAD, 1)};
	
	@Override
	public ItemStack getRootItem() {
		return Items.Fridge;
	}

	@Override
	public Material getDisplayMaterial() {
		return Material.QUARTZ_BLOCK;
	}

	@Override
	public int getDisplayCustomModelData() {
		return 1001;
	}

	@Override
	public String getName() {
		return "Fridge";
	}

	@Override
	public void Interact(PlayerInteractEvent event) {
		event.setCancelled(true);
		Dispenser d = (Dispenser)event.getClickedBlock().getState();
		String log = d.getLock();
		d.setLock("");
		d.setCustomName("Fridge");
		d.update();
		event.getPlayer().openInventory(d.getInventory());
		d.setLock(log);
		d.update();
	}
	
	@Override
	public void AddToArmorStand(ArmorStand stand) {
		super.AddToArmorStand(stand);
		
		stand.setCustomName("fridge");
		stand.setCustomNameVisible(false);
	}

	public static void ExpireFoods(Player player)
	{
		Inventory inv = player.getInventory();
		
		if(player.getEquipment().getItemInOffHand() != null)
		{
			ItemStack s = player.getEquipment().getItemInOffHand();
			for(Expireable e : Expireables)
			{
				if(s.getType() == e.mat)
				{
					DecayFood(s, e);
				}
			}
		}
		
		for(ItemStack s : inv.getContents())
		{
			if(s == null) {continue;}
			for(Expireable e : Expireables)
			{
				if(s.getType() == e.mat)
				{
					DecayFood(s, e);
				}
			}
		}
	}
	
	public static void DecayFood(ItemStack s, Expireable e)
	{
		DecayFood(s, e, 0);
	}
	
	public static void DecayFood(ItemStack s, Expireable e, int amount)
	{
		int totalDecayValue = 10;
		
		ItemMeta meta = s.getItemMeta();
		
		List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<String>();
		if(meta.hasLore())
		{
			//Main.Log(lore.get(0).contains("Quality: ") ? "qual" : "none");
			if(lore.get(0).contains("Quality: "))
			{
				totalDecayValue = Integer.parseInt(lore.get(1).replace(ChatColor.GRAY + "", ""));
			}
		}
		
		lore.clear();
		
		switch(totalDecayValue)
		{
		case 10:
			lore.add(ChatColor.DARK_PURPLE + "Quality: " + ChatColor.GOLD + "Perfect");
			break;
		case 9:
			lore.add(ChatColor.DARK_PURPLE + "Quality: " + ChatColor.GOLD + "Less Than Perfect");
			break;
		case 8:
			lore.add(ChatColor.DARK_PURPLE + "Quality: " + ChatColor.GREEN + "Great");
			break;
		case 7:
			lore.add(ChatColor.DARK_PURPLE + "Quality: " + ChatColor.GREEN + "Less Than Great");
			break;
		case 6:
			lore.add(ChatColor.DARK_PURPLE + "Quality: " + ChatColor.DARK_GREEN + "Ok");
			break;
		case 5:
			lore.add(ChatColor.DARK_PURPLE + "Quality: " + ChatColor.DARK_GREEN + "Less Than Ok");
			break;
		case 4:
			lore.add(ChatColor.DARK_PURPLE + "Quality: " + ChatColor.RED + "Bad");
			break;
		case 3:
			lore.add(ChatColor.DARK_PURPLE + "Quality: " + ChatColor.RED + "Dubious");
			break;
		case 2:
			lore.add(ChatColor.DARK_PURPLE + "Quality: " + ChatColor.DARK_RED + "Rotting");
			break;
		case 1:
			lore.add(ChatColor.DARK_PURPLE + "Quality: " + ChatColor.DARK_RED + "Infested (Cannot Salvage)");
			break;
		default:
			lore.add(ChatColor.DARK_PURPLE + "Quality: " + ChatColor.DARK_RED + "Rotten (Cannot Salvage)");
			break;
		}
		

		if(amount == 0)
		{
			if(totalDecayValue - e.decayRate <= 10 && totalDecayValue - e.decayRate >= 0)
			{
				lore.add(ChatColor.GRAY + "" + (totalDecayValue - e.decayRate));
			}else {
				lore.add(ChatColor.GRAY + "" + (totalDecayValue));
			}
		}else {
			if(totalDecayValue - amount <= 10 && totalDecayValue - e.decayRate >= 0)
			{
				lore.add(ChatColor.GRAY + "" + (totalDecayValue - amount));
			}else {
				lore.add(ChatColor.GRAY + "" + (totalDecayValue));
			}
		} 
		
		meta.setLore(lore);
		s.setItemMeta(meta);
	}
	
	
	
}
