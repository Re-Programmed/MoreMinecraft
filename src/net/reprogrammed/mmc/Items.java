package net.reprogrammed.mmc;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {

	public static ItemStack salt;
	
	public static ItemStack airTank;
	public static ItemStack creeperTurret;
	
	public static ItemStack motorBoat;
	
	public static ItemStack itemReciever;
	
	public static ItemStack salmonEggs;
	
	public static ItemStack mineralDust;
	
	public static ItemStack enchantATable;
	
	public static ItemStack MetalDetector;
	
	public static ItemStack Fridge;
	
	public static ItemStack PlayerDetector;
	
	public static ItemStack WindowShade;
	
	public static ItemStack Rice;
	
	public static ItemStack RiceItem;
	
	public static ItemStack Stove;
	
	public static ItemStack EdibleRice;
	
	public static ItemStack NetheriteBow;
	
	public static ItemStack AutomaticChainsaw;
	
	public static ItemStack BonemealPowerFarmer;
	public static ItemStack HarvestPowerFarmer;
	public static ItemStack PlantPowerFarmer;
	public static ItemStack TillPowerFarmer;
	
	public static ItemStack Farmer;
	
	public static ItemStack PortableEndPortal;
	
	public static void RegisterItems()
	{
		PortableEndPortal = CreateItem(Material.END_PORTAL_FRAME, "Portable End Portal", 1);
		
		setItemCustomModelData(PortableEndPortal, 1001);
		
		ShapedRecipe ACSR = addShapedRecipe(PortableEndPortal, "portable_end_portal", "ELE", "EEE", "PEP");
		
		ACSR.setIngredient('E', Material.END_STONE);
		ACSR.setIngredient('L', Material.LODESTONE);
		ACSR.setIngredient('P', Material.ENDER_EYE);

		Bukkit.getServer().addRecipe(ACSR);
		
		BonemealPowerFarmer = CreateItem(Material.BOOK, "Bonemeal Upgrade (Farmer)", 1);
		setItemCustomModelData(BonemealPowerFarmer, 7001);
		
		AddLore(BonemealPowerFarmer, "When placed in the farmer block: ", ChatColor.GOLD + "Bonmeals the crop above the farmer.");
		
		ACSR = addShapedRecipe(BonemealPowerFarmer, "bonmeal_power_farmer", "BBB", "BNB", "BBB");
				
		ACSR.setIngredient('B', Material.BONE_MEAL);
		ACSR.setIngredient('N', Material.NETHERITE_SCRAP);
		
		Bukkit.getServer().addRecipe(ACSR);
		
		HarvestPowerFarmer = CreateItem(Material.BOOK, "Harvest Upgrade (Farmer)", 1);
		
		AddLore(HarvestPowerFarmer, "When placed in the farmer block: ", ChatColor.GOLD + "Harvests the crop above the farmer.");
		
		setItemCustomModelData(HarvestPowerFarmer, 7002);
		
		ACSR = addShapedRecipe(HarvestPowerFarmer, "harv_power_farmer", "BBB", "BNB", "BBB");
		
		ACSR.setIngredient('B', Material.IRON_INGOT);
		ACSR.setIngredient('N', Material.GOLDEN_HOE);
		
		Bukkit.getServer().addRecipe(ACSR);
		
		PlantPowerFarmer = CreateItem(Material.BOOK, "Plant Upgrade (Farmer)", 1);
		
		AddLore(PlantPowerFarmer, "When placed in the farmer block: ", ChatColor.GOLD + "Plants seeds from the farmer inventory above it.");
		
		setItemCustomModelData(PlantPowerFarmer, 7003);
		
		ACSR = addShapedRecipe(PlantPowerFarmer, "plant_power_farmer", "BBB", "BNB", "BBB");
		
		ACSR.setIngredient('B', Material.IRON_INGOT);
		ACSR.setIngredient('N', Material.DROPPER);
		
		Bukkit.getServer().addRecipe(ACSR);
		
		TillPowerFarmer = CreateItem(Material.BOOK, "Till Upgrade (Farmer)", 1);
		
		AddLore(TillPowerFarmer, "When placed in the farmer block: ", ChatColor.GOLD + "Tills the land above the farmer.");
		
		setItemCustomModelData(TillPowerFarmer, 7004);
		
		ACSR = addShapedRecipe(TillPowerFarmer, "till_power_farmer", "BBB", "BNB", "BBB");
		
		ACSR.setIngredient('B', Material.STICK);
		ACSR.setIngredient('N', Material.WOODEN_HOE);
		
		Bukkit.getServer().addRecipe(ACSR);
		
		Farmer = CreateItem(Material.ORANGE_CONCRETE, "Farmer", 5);
		setItemCustomModelData(Farmer, 1001);
		
		ACSR = addShapedRecipe(Farmer, "farmer_craft", " D ", "SSS", "RPR");
		
		ACSR.setIngredient('R', Material.REDSTONE);
		ACSR.setIngredient('P', Material.PISTON);
		ACSR.setIngredient('S', Material.SMOOTH_STONE);
		ACSR.setIngredient('D', Material.DROPPER);
		
		Bukkit.getServer().addRecipe(ACSR);
		
		AutomaticChainsaw = CreateItem(Material.STONECUTTER, "Automatic Chainsaw", 1);
		setItemCustomModelData(AutomaticChainsaw, 1001);
		
		ACSR = addShapedRecipe(AutomaticChainsaw, "chain_auto_craft", "PDS", "NNN", "G G");
		
		ACSR.setIngredient('N', Material.NETHERITE_SCRAP);
		ACSR.setIngredient('P', Material.PISTON);
		ACSR.setIngredient('D', Material.DIAMOND_BLOCK);
		ACSR.setIngredient('G', Material.GRAY_CONCRETE_POWDER);
		ACSR.setIngredient('S', Material.STONECUTTER);
		
		Bukkit.getServer().addRecipe(ACSR);
		/*
		NetheriteBow = CreateItem(Material.BOW, "Netherite Bow", 1);
		setItemCustomModelData(NetheriteBow, 1001);
		
		ShapedRecipe NetheriteBowSR = addShapedRecipe(NetheriteBow, "nether_bow_craft", "NNN", "NBN", "NNN");
		
		NetheriteBowSR.setIngredient('N', Material.NETHERITE_INGOT);
		NetheriteBowSR.setIngredient('B', Material.BOW);
		
		Bukkit.getServer().addRecipe(NetheriteBowSR);
		*/
		RiceItem = CreateItem(Material.SUGAR, "Rice", 3);
		setItemCustomModelData(RiceItem, 1001);
		
		EdibleRice = CreateItem(Material.RABBIT_STEW, "Rice Bowl", 2);
		
		setItemCustomModelData(EdibleRice, 1001);
		
		ShapedRecipe RiceEdibleSR = addShapedRecipe(EdibleRice, "rice_edible", "RRR", "RBR", "RRR");
		
		RiceEdibleSR.setIngredient('R', new RecipeChoice.ExactChoice(RiceItem));
		RiceEdibleSR.setIngredient('B', Material.BOWL);
		
		Bukkit.getServer().addRecipe(RiceEdibleSR);
		
		Rice = CreateItem(Material.GRASS, "Rice", 3);
		setItemCustomModelData(Rice, 1001);
		
		Stove = CreateItem(Material.QUARTZ_BRICKS, "Stove", 1);
		setItemCustomModelData(Stove, 1001);
		
		ShapedRecipe StoveSR = addShapedRecipe(Stove, "stove_craft", "III", "CHC", "CCC");
		
		StoveSR.setIngredient('I', Material.IRON_BARS);
		StoveSR.setIngredient('C', Material.GRAY_CONCRETE);
		StoveSR.setIngredient('H', Material.CHEST);
		
		Bukkit.getServer().addRecipe(StoveSR);
		
		WindowShade = CreateItem(Material.OAK_SLAB, "Window Shade (Place in item frame)", 1);
		setItemCustomModelData(WindowShade, 1001);
		
		ShapedRecipe WindowShadeSR = addShapedRecipe(WindowShade, "player_d_craft", "WHW", "O O", "   ");
		
		WindowShadeSR.setIngredient('H', Material.WHITE_WOOL);
		WindowShadeSR.setIngredient('W', Material.RED_WOOL);
		WindowShadeSR.setIngredient('O', Material.OAK_PLANKS);
		
		Bukkit.getServer().addRecipe(WindowShadeSR);
		
		
		Fridge = CreateItem(Material.QUARTZ_BLOCK, "Fridge", 1);
		setItemCustomModelData(Fridge, 1001);
		
		ShapedRecipe Fdecr = addShapedRecipe(Fridge, "fridge_craft", "III", "ICI", "IRI");
		
		Fdecr.setIngredient('I', Material.IRON_BLOCK);
		Fdecr.setIngredient('C', Material.CHEST);
		Fdecr.setIngredient('R', Material.REDSTONE);
		
		Bukkit.getServer().addRecipe(Fdecr);
		
		MetalDetector = CreateItem(Material.ACACIA_PRESSURE_PLATE, "Metal Detector", 1);
		
		setItemCustomModelData(MetalDetector, 1001);
		
		ShapedRecipe Mdecr = addShapedRecipe(MetalDetector, "metal_detector_a", "IOI", "IPI", "III");
		Mdecr.setIngredient('I', Material.IRON_BLOCK);
		Mdecr.setIngredient('O', Material.OBSERVER);
		Mdecr.setIngredient('P', Material.OAK_PRESSURE_PLATE);
		
		Bukkit.getServer().addRecipe(Mdecr);
		
		enchantATable = CreateItem(Material.ACACIA_PLANKS, "Ancient Enchanting Table", 1);
		
		setItemCustomModelData(enchantATable, 1001);
		
		ShapedRecipe enchantATabler = addShapedRecipe(enchantATable, "a_table_ench_craft", " B ", "NON", "OOO");
		enchantATabler.setIngredient('B', Material.BOOK);
		enchantATabler.setIngredient('O', Material.OBSIDIAN);
		enchantATabler.setIngredient('N', Material.NETHERITE_SCRAP);
		
		Bukkit.getServer().addRecipe(enchantATabler);
		
		mineralDust = CreateItem(Material.IRON_NUGGET, "Mineral Dust", 4);
		
		setItemCustomModelData(mineralDust, 1001);
		
		salmonEggs = CreateItem(Material.SWEET_BERRIES, "Salmon Eggs", 2);
		
		salt = CreateItem(Material.SUGAR, "Salt", 3);
		
		CreateCuredMeat(Material.COOKED_BEEF, "Cured Beef", Material.BEEF);
		CreateCuredMeat(Material.COOKED_MUTTON, "Cured Mutton", Material.MUTTON);
		CreateCuredMeat(Material.COOKED_CHICKEN, "Cured Chicken", Material.CHICKEN);
		CreateCuredMeat(Material.COOKED_COD, "Cured Cod", Material.COD);
		CreateCuredMeat(Material.COOKED_PORKCHOP, "Cured Pork", Material.PORKCHOP);
		CreateCuredMeat(Material.COOKED_RABBIT, "Cured Rabbit", Material.RABBIT);
		CreateCuredMeat(Material.COOKED_SALMON, "Cured Salmon", Material.SALMON);
		
		airTank = CreateItem(Material.GRAY_CONCRETE, "Air Tank", 1);
		
		setItemCustomModelData(airTank, 1001);
		
		ShapedRecipe airTanksr = addShapedRecipe(airTank, "air_tank_craft", "iii", "iOi", "III");
		airTanksr.setIngredient('i', Material.IRON_INGOT);
		airTanksr.setIngredient('O', Material.GLASS_BOTTLE);
		airTanksr.setIngredient('I', Material.IRON_BLOCK);
		
		Bukkit.getServer().addRecipe(airTanksr);
		
		motorBoat = CreateItem(Material.OAK_BOAT, "Motor Boat", 1);
		
		setItemCustomModelData(motorBoat, 1001);
		
		ShapedRecipe motorBoatsr = addShapedRecipe(motorBoat, "motor_boat_craft", "III", "PRD", "   ");
		motorBoatsr.setIngredient('D', Material.DIAMOND_BLOCK);
		motorBoatsr.setIngredient('R', Material.REDSTONE_BLOCK);
		motorBoatsr.setIngredient('P', Material.PISTON);
		motorBoatsr.setIngredient('I', Material.IRON_BLOCK);
		
		Bukkit.getServer().addRecipe(motorBoatsr);
		
		creeperTurret = CreateItem(Material.END_ROD, "Creeper Turret", 1);
		
		setItemCustomModelData(creeperTurret, 1001);
		
		ShapedRecipe creeperTurretsr = addShapedRecipe(creeperTurret, "creeper_turret_craft", " D ", " I ", "RIR");
		creeperTurretsr.setIngredient('D', Material.DIAMOND);
		creeperTurretsr.setIngredient('R', Material.REDSTONE_BLOCK);
		creeperTurretsr.setIngredient('I', Material.IRON_BLOCK);
		
		Bukkit.getServer().addRecipe(creeperTurretsr);
		
		itemReciever = CreateItem(Material.ORANGE_GLAZED_TERRACOTTA, "Item Reciever", 1);
		
		ItemMeta metarec = itemReciever.getItemMeta();
		
		List<String> lore = new ArrayList<String>();
		
		lore.add(ChatColor.GREEN + "Type: " + Material.DIRT.name());
		lore.add(ChatColor.GOLD + "Count: 0");
		
		metarec.setLore(lore);
		
		itemReciever.setItemMeta(metarec);
		
		setItemCustomModelData(itemReciever, 1001);

		ShapedRecipe recieversr = addShapedRecipe(itemReciever, "item_reciever_craft", "IGI", "GCG", "IGI");
		recieversr.setIngredient('C', Material.CHEST);
		recieversr.setIngredient('G', Material.GOLD_BLOCK);
		recieversr.setIngredient('I', Material.IRON_BLOCK);
		
		Bukkit.getServer().addRecipe(recieversr);
	}
	
	public static void CreateCuredMeat(Material mat, String name, Material base)
	{
		ItemStack c = CreateItem(mat, name, 1);
		
		ShapedRecipe sr = addShapedRecipe(c, name.toLowerCase().replace(' ', '_') + "_craft", "SSS", "SBS", "SSS");

		sr.setIngredient('S', new RecipeChoice.ExactChoice(salt));
		sr.setIngredient('B', base);
		
		Bukkit.getServer().addRecipe(sr);
	}
	
	public static void setItemCustomModelData(ItemStack item, int data)
	{
		ItemMeta m = item.getItemMeta();
		m.setCustomModelData(data);
		item.setItemMeta(m);
	}
	
	public static ItemStack CreateItem(Material mat, String name, int count)
	{
		ItemStack is = new ItemStack(mat, count);
		
		ItemMeta meta = is.getItemMeta();
		
		meta.setDisplayName(ChatColor.WHITE + name);
		
		is.setItemMeta(meta);
		
		return is;
	}
	
	public static void AddLore(ItemStack item, String... lore)
	{
		ItemMeta meta = item.getItemMeta();
		
		List<String> lore2 = new ArrayList<String>();
		
		for(String l : lore)
		{
			lore2.add(l);
		}
		
		meta.setLore(lore2);
		
		item.setItemMeta(meta);
	}
	
	public static ShapedRecipe addShapedRecipe(ItemStack item, String name, String row1, String row2, String row3)
	{
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft(name), item);
		sr.shape(row1, row2, row3);		
		return sr;
	}
	
}
