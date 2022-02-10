package net.reprogrammed.mmc.enchants;

import java.util.ArrayList;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class XpCollector extends Enchant {

	@Override
	public String getName() {
		return "XP Collector";
	}

	@Override
	public ArrayList<String> enchantableWords() {
		ArrayList<String> e = new ArrayList<String>();
		e.add("sword");
		e.add("axe");
		e.add("pickaxe");
		e.add("shovel");
		return e;
	}

	@Override
	public int GetXPLevel() {
		return 5;
	}
	
	@Override
	public void DamageEvent(EntityDamageByEntityEvent event) {
		ExperienceOrb orb = (ExperienceOrb)event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.EXPERIENCE_ORB);
		orb.setExperience(2);
		if(event.getEntity().getCustomName() == null || event.getEntity().getCustomName() == "")
		{
			orb.setCustomName(event.getEntity().getName() + " Experience");
		}else {
			orb.setCustomName(event.getEntity().getCustomName() + " Experience");
		}
		orb.setCustomNameVisible(true);
	}
	
	@Override
	public void BreakBlock(BlockBreakEvent event) {
		event.setExpToDrop(event.getExpToDrop() + 1);
	}

}
