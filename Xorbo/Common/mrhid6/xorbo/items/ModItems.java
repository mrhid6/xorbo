package mrhid6.xorbo.items;

import mrhid6.xorbo.BlockIds;
import mrhid6.xorbo.ItemIds;
import mrhid6.xorbo.armour.TriniumArmour;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.EnumHelper;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModItems {

	public static Item debug;
	public static Item stearilliumOre;
	public static Item zoroBucket;
	public static Item zoroCable;
	public static Item zoroIngot;
	public static Item zoroStaff;
	
	public static Item triniumHelm;
	public static Item triniumPlate;
	public static Item triniumLegs;
	public static Item triniumBoots;

	static EnumArmorMaterial ArmorMaterial = EnumHelper.addArmorMaterial("triniumArmorMaterial", 40, new int[]{5, 14, 9, 5}, 15);
	
	public static void init() {

		zoroIngot = new ItemTexturedBase(ItemIds.getID("zoroIngot"), 64, "zoroIngot");
		LanguageRegistry.addName(zoroIngot, "Zoro Ingot");

		zoroCable = new ItemTexturedBase(ItemIds.getID("zoroCable"), 64, "zoroCable");
		LanguageRegistry.addName(zoroCable, "Zoro Cable");

		zoroBucket = new ItemZoroBucket(ItemIds.getID("zoroBucket"), BlockIds.getID("zoroFlowing"), "zorobucket");
		LanguageRegistry.addName(zoroBucket, "Volatile Zoro");

		zoroStaff = new ItemZoroStaff(ItemIds.getID("zoroStaff"), "zoroStaff");
		LanguageRegistry.addName(zoroStaff, "Zoro Staff");
		
		debug = new ItemDebug(ItemIds.getID("debugTool"), "debug");
		LanguageRegistry.addName(debug, "debug");

		stearilliumOre = new ItemStearilliumOre(ItemIds.getID("stearilliumOre"), "stearilliumore");
		LanguageRegistry.addName(stearilliumOre, "stearilliumore");
		
		triniumHelm = new TriniumArmour(ItemIds.getID("triniumHelm"), ArmorMaterial, ModLoader.addArmor("Xorbo"), 0).setUnlocalizedName("triniumhelm");
		LanguageRegistry.addName(triniumHelm, "Trinium Helm");
		
		triniumPlate = new TriniumArmour(ItemIds.getID("triniumPlate"), ArmorMaterial, ModLoader.addArmor("Xorbo"), 1).setUnlocalizedName("triniumplate");
		LanguageRegistry.addName(triniumPlate, "Trinium Plate");
		
		triniumLegs = new TriniumArmour(ItemIds.getID("triniumLegs"), ArmorMaterial, ModLoader.addArmor("Xorbo"), 2).setUnlocalizedName("triniumlegs");
		LanguageRegistry.addName(triniumLegs, "Trinium Legs");
		
		triniumBoots = new TriniumArmour(ItemIds.getID("triniumBoots"), ArmorMaterial, ModLoader.addArmor("Xorbo"), 3).setUnlocalizedName("triniumboots");
		LanguageRegistry.addName(triniumBoots, "Trinium Boots");
	}
}
