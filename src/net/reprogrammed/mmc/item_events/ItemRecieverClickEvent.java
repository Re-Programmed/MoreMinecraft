package net.reprogrammed.mmc.item_events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import net.reprogrammed.mmc.Items;

public class ItemRecieverClickEvent extends ItemClick {

	@Override
	public void Left_Click(InventoryClickEvent event) {	
		ItemStack item = event.getClickedInventory().getItem(event.getSlot());
		
		if(item.getAmount() != 1) {return;}
		
		int count = GetRecieverCount(item);
		Material type = GetRecieverSuckType(item);
		
		boolean added = false;
		
		if(count >= 1028) {return;}
		
		for(ItemStack i : event.getClickedInventory().getContents())
		{
			if(i == null) {continue;}
			if(i.getType() == type)
			{
				if(i.hasItemMeta())
				{
					if(i.getItemMeta().hasCustomModelData())
					{
						continue;
					}
				}
				SetRecieverData(i, i.getAmount() + count, item);
				count = i.getAmount() + count;
				added = true;
			}
		}
		
		if(added)
		{
			event.getClickedInventory().remove(type);
			event.setCancelled(true);
		}else {
			
		}
	}

	@Override
	public void Right_Click(InventoryClickEvent event) {
		ItemStack item = event.getClickedInventory().getItem(event.getSlot());
		int count = GetRecieverCount(item);
		
		if(item.getAmount() != 1) {return;}
				
		if(count == 0)
		{
			if(event.getWhoClicked().getItemOnCursor() != null && event.getWhoClicked().getItemOnCursor().getType() != Material.AIR)
			{
				event.setCancelled(true);
				SetRecieverData(event.getWhoClicked().getItemOnCursor(), 0, item);
			}else {
				return;
			}
		}else{
			if(event.getWhoClicked().getItemOnCursor() == null || event.getWhoClicked().getItemOnCursor().getType() == Material.AIR)
			{
				Material type = GetRecieverSuckType(item);
				if(type == Material.AIR) {return;}
				if(count >= 64)
				{
					event.getWhoClicked().setItemOnCursor(new ItemStack(type, 64));
					SetRecieverData(type, count - 64, item);
				}else {
					event.getWhoClicked().setItemOnCursor(new ItemStack(GetRecieverSuckType(item), count));
					SetRecieverData(type, 0, item);
				}
				event.setCancelled(true);
			}
		}
	}

	@Override
	public ItemStack SourceItem() {
		return Items.itemReciever;
	}
	
	public static boolean SetRecieverData(ItemStack recieveItem, int count, ItemStack reciever)
	{
		if(recieveItem.getItemMeta().hasCustomModelData()){return false;}
		
		ItemMeta meta = reciever.getItemMeta();
		
		List<String> lore = new ArrayList<String>();
		
		lore.add(ChatColor.GREEN + "Type: " + recieveItem.getType().name());
		lore.add(ChatColor.GOLD + "Count: " + count);
		
		meta.setLore(lore);
		
		reciever.setItemMeta(meta);
		return true;
	}
	
	public static void SetRecieverData(Material recieveItem, int count, ItemStack reciever)
	{
		ItemMeta meta = reciever.getItemMeta();
		
		List<String> lore = new ArrayList<String>();
		
		lore.add(ChatColor.GREEN + "Type: " + recieveItem.name());
		lore.add(ChatColor.GOLD + "Count: " + count);
		
		meta.setLore(lore);
		
		reciever.setItemMeta(meta);
	}
	
	public static int GetRecieverCount(ItemStack reciever)
	{
		if(reciever.getItemMeta().hasLore())
		{
			if(reciever.getItemMeta().getLore().size() == 2)
			{
				if(reciever.getItemMeta().getLore().get(1).toLowerCase().contains("count"))
				{
					try
					{
						return Integer.parseInt(reciever.getItemMeta().getLore().get(1).toLowerCase().replace(ChatColor.GOLD + "count: ", ""));
					}catch(Exception e)
					{
						return 0;
					}
				}
			}
		}
		
		return 0;
	}
	
	public static Material GetRecieverSuckType(ItemStack reciever)
	{
		if(reciever.getItemMeta().hasLore())
		{
			if(reciever.getItemMeta().getLore().size() == 2)
			{
				if(reciever.getItemMeta().getLore().get(0).toLowerCase().contains("type"))
				{
					try
					{
						return Material.matchMaterial(reciever.getItemMeta().getLore().get(0).toLowerCase().replace(ChatColor.GREEN + "type: ", ""));
					}catch(Exception e)
					{
						return Material.AIR;
					}
				}
			}
		}
		
		return Material.AIR;
	}

}
