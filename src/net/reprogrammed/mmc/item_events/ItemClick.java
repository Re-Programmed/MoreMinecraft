package net.reprogrammed.mmc.item_events;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.reprogrammed.mmc.ItemEvents;

public abstract class ItemClick {
	
	public ItemClick()
	{
		ItemEvents.AddClickEvent(this);
	}
	
	public abstract void Left_Click(InventoryClickEvent event);
	public abstract void Right_Click(InventoryClickEvent event);
	
	public abstract ItemStack SourceItem();
}
