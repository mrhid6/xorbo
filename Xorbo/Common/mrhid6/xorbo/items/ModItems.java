package mrhid6.xorbo.items;

import mrhid6.xorbo.Config;
import mrhid6.xorbo.block.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModItems {
	
	public static ItemStack zoroCable;
	
	
	public static void init(){
		
		zoroCable = new ItemStack(new ItemTexturedBase(501,64, Config.creativeTabXor,0,"Zoro Cable"));
		LanguageRegistry.addName(zoroCable, "Zoro Cable");
	}
}
