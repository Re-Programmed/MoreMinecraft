package net.reprogrammed.mmc;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import net.reprogrammed.mmc.blocks.Fridge;
import net.reprogrammed.mmc.holders.Expireable;
import net.reprogrammed.mmc.item_events.ItemClick;
import net.reprogrammed.mmc.item_events.ItemRecieverClickEvent;

public class ItemEvents implements Listener {

	static ArrayList<ItemClick> clickEvents = new ArrayList<ItemClick>();
	
	public ItemEvents()
	{
		clickEvents.clear();
		clickEvents.add(new ItemRecieverClickEvent());
	}
		
	
	@EventHandler
	public void PlayerClickInventory(InventoryClickEvent event)
	{
		Inventory inv = event.getClickedInventory();
		
		if(inv == null) {return;}
		
		if(inv.getType() == InventoryType.PLAYER)
		{
			ItemStack clickedItem = inv.getItem(event.getSlot());
			
			if(clickedItem == null) {return;}
			
			for(ItemClick click : clickEvents)
			{
				if(click.SourceItem().getType() == clickedItem.getType())
				{
					if(click.SourceItem().getItemMeta().hasCustomModelData())
					{
						if(clickedItem.getItemMeta().hasCustomModelData())
						{
							if(clickedItem.getItemMeta().getCustomModelData() == click.SourceItem().getItemMeta().getCustomModelData())
							{
								TriggerClick(click, event.getClick(), event);
							}
						}
					}else {
						TriggerClick(click, event.getClick(), event);
					}
				}
			}
			
			
		}
	}
	
	public static void AddClickEvent(ItemClick event)
	{
		clickEvents.add(event);
	}
	
	public boolean TriggerClick(ItemClick click, ClickType type, InventoryClickEvent data)
	{
		if(type == ClickType.RIGHT)
		{
			click.Right_Click(data);
			return true;
		}else if(type == ClickType.LEFT)
		{
			click.Left_Click(data);
			return true;
		}else {
			return false;
		}
	}
	
	
	@EventHandler
	public void ConsumeItemEvent(PlayerItemConsumeEvent event)
	{
		for(Expireable e : Fridge.Expireables)
		{
			if(e.mat == event.getItem().getType())
			{
				ItemMeta meta = event.getItem().getItemMeta();

				if(meta.hasLore())
				{
					if(meta.getLore().get(0).contains("Quality: "))
					{
						int decayAmount = Integer.parseInt(meta.getLore().get(1).replace(ChatColor.GRAY + "", ""));
						
						switch(decayAmount)
						{
						case 10:
							event.getPlayer().setFoodLevel(event.getPlayer().getFoodLevel() + 3);
							event.getPlayer().setAbsorptionAmount(event.getPlayer().getAbsorptionAmount() + 2);
							break;
						case 9:
							event.getPlayer().setFoodLevel(event.getPlayer().getFoodLevel() + 2);
							event.getPlayer().setAbsorptionAmount(event.getPlayer().getAbsorptionAmount() + 2);
							break;
						case 8:
							event.getPlayer().setFoodLevel(event.getPlayer().getFoodLevel() + 1);
							event.getPlayer().setAbsorptionAmount(event.getPlayer().getAbsorptionAmount() + 1);
							break;
						case 7:
							break;
						case 6:
							break;
						case 5:
							break;
						case 4:
							event.getPlayer().setFoodLevel(event.getPlayer().getFoodLevel() - 1);
							break;
						case 3:
							event.getPlayer().setFoodLevel(event.getPlayer().getFoodLevel() - 2);
							break;
						case 2:
							event.getPlayer().setFoodLevel(event.getPlayer().getFoodLevel() - 4);
							break;
						case 1:
							event.getPlayer().setFoodLevel(event.getPlayer().getFoodLevel() - 6);
							break;
						default:
							event.getPlayer().setFoodLevel(event.getPlayer().getFoodLevel() - 8);
							break;								
						}
						
						if(event.getPlayer().getAbsorptionAmount() > 20)
						{
							event.getPlayer().setAbsorptionAmount(20);
						}
					}
				}
			}
		}
	}
	
	
	//TODO: OLD CODE
	
	public static ArrayList<Location> activeRedstoneToggles = new ArrayList<Location>();
	
	@EventHandler
	public void Interact(PlayerInteractEvent event)
	{
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			ItemStack item = event.getItem();
			Block block = event.getClickedBlock();
			if(item == null || block == null) {return;}
			if(item.hasItemMeta())
			{
				if(item.getItemMeta().hasCustomModelData())
				{
					if(item.getItemMeta().getCustomModelData() == 10001 && item.getType() == Material.LEVER)
					{
						ItemMeta meta = item.getItemMeta();
						if(block.getType() == Material.REDSTONE_WIRE)
						{
							List<String> lore = meta.getLore();
							String linktxt = ChatColor.GREEN + "Link: " + block.getLocation().getBlockX() + ", " + block.getLocation().getBlockY() + ", " + block.getLocation().getBlockZ() + ", " + block.getLocation().getWorld().getName().replace(" ", "#");
							lore.set(0, linktxt);
														
							meta.setLore(lore);
							
							item.setItemMeta(meta);
							
							event.getPlayer().sendMessage(linktxt);
						}else {
							if(meta.getLore().get(0).contains("Link: "))
							{
								String decodelocation = meta.getLore().get(0).replace(ChatColor.GREEN + "Link: ", "");
																
								ArrayList<String> decodedValues = new ArrayList<String>();
								
								int index = 0;
								
								decodedValues.add("");
								
								for(char c : decodelocation.toCharArray())
								{
									if(c != ',' && c != ' ')
									{
										if(c == '#')
										{
											decodedValues.set(index, decodedValues.get(index) + " ");
										}else {
											decodedValues.set(index, decodedValues.get(index) + c);
										}
									}else {
										if(c == ',')
										{
											decodedValues.add("");
											index++;
										}
									}
								}
								
								Location finalLocation = new Location(Bukkit.getWorld(decodedValues.get(3)), Integer.parseInt(decodedValues.get(0)), Integer.parseInt(decodedValues.get(1)), Integer.parseInt(decodedValues.get(2)));
								
								List<String> lore = meta.getLore();				
								
								if(finalLocation.getBlock().getType() != Material.REDSTONE_WIRE && finalLocation.getBlock().getType() != Material.REDSTONE_TORCH)
								{
									lore.set(0, ChatColor.GRAY + "Unlinked");
									if(activeRedstoneToggles.contains(finalLocation))
									{
										activeRedstoneToggles.remove(finalLocation);
									}
								}else {
									if(lore.get(1).contains("Active"))
									{
										activeRedstoneToggles.remove(finalLocation);
										finalLocation.getBlock().setType(Material.REDSTONE_WIRE);
										lore.set(1, ChatColor.RED + "Inactive");
										event.getPlayer().sendMessage(lore.get(1));
									}else if(lore.get(1).contains("Inactive"))
									{
										finalLocation.getBlock().setType(Material.REDSTONE_TORCH);
										activeRedstoneToggles.add(finalLocation);
										lore.set(1, ChatColor.GREEN + "Active");
										event.getPlayer().sendMessage(lore.get(1));
									}
								}
								
								meta.setLore(lore);
								item.setItemMeta(meta);
							}
						}
						

						
						event.setCancelled(true);
					}
				}
			}
		}
	}
}
