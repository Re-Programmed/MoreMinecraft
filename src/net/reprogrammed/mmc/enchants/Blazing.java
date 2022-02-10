package net.reprogrammed.mmc.enchants;

import java.util.ArrayList;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Blazing extends Enchant {

	@Override
	public String getName() {
		return "Blazing";
	}
	
	@Override
	public void DamageEvent(EntityDamageByEntityEvent event)
	{
		event.getEntity().setFireTicks(100);
		event.getEntity().getWorld().spawnParticle(Particle.FLAME, event.getEntity().getLocation(), 100, 0.5f, 1f, 0.5f, 0.01f);
		event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.BLOCK_FURNACE_FIRE_CRACKLE, 1f, 1f);
	}

	@Override
	public ArrayList<String> enchantableWords() {
		ArrayList<String> e = new ArrayList<String>();
		e.add("sword");
		e.add("axe");
		return e;
	}



	@Override
	public int GetXPLevel() {
		return 2;
	}
	
}
