package mrhid6.xorbo.items;

import cpw.mods.fml.common.registry.LanguageRegistry;
import mrhid6.xorbo.BlockIds;
import mrhid6.xorbo.ItemIds;
import mrhid6.xorbo.block.ModBlocks;
import net.minecraft.item.Item;

public class ModItems {
	
	public static Item zoroCable;
	public static Item zoroBucket;
	
	
	public static void init(){
		zoroBucket = new ItemZoroBucket(ItemIds.getID("zoroBucket"),BlockIds.getID("zoroFlowing"),"zorobucket");
		
		LanguageRegistry.addName(zoroBucket, "Volatile Zoro");
	}
}
