package net.reprogrammed.mmc.enchants;

public enum Enchants {

	BLAZING(new Blazing()),
	XP_COLLECTOR(new XpCollector()),
	CHOP(new Chop());
	
	public Enchant enchant;
	
	private Enchants(Enchant enchant)
	{
		this.enchant = enchant;
	}
	
}
