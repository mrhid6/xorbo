package mrhid6.xorbo.items;

import mrhid6.xorbo.BlockIds;
import mrhid6.xorbo.ItemIds;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModItems {

	public static Item debug;
	public static Item stearilliumOre;
	public static Item zoroBucket;
	public static Item zoroCable;
	public static Item zoroIngot;

	public static void init() {

		zoroIngot = new ItemTexturedBase(ItemIds.getID("zoroIngot"), 64, "zoroIngot");
		LanguageRegistry.addName(zoroIngot, "Zoro Ingot");

		zoroCable = new ItemTexturedBase(ItemIds.getID("zoroCable"), 64, "zoroCable");
		LanguageRegistry.addName(zoroCable, "Zoro Cable");

		zoroBucket = new ItemZoroBucket(ItemIds.getID("zoroBucket"), BlockIds.getID("zoroFlowing"), "zorobucket");
		LanguageRegistry.addName(zoroBucket, "Volatile Zoro");

		debug = new ItemDebug(ItemIds.getID("debugTool"), "debug");
		LanguageRegistry.addName(debug, "debug");

		stearilliumOre = new ItemStearilliumOre(ItemIds.getID("stearilliumOre"), "stearilliumore");
		LanguageRegistry.addName(stearilliumOre, "stearilliumore");
	}
}
