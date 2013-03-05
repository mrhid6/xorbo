package mrhid6.xorbo.block;

import mrhid6.xorbo.tileEntity.TECableBase;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModBlocks {
	
	public static Block cable;
	
	public static void init(){
		
		cable = new BlockCableBase(500, 0, "zorocable", false);
		LanguageRegistry.addName(cable, "Zoro Cable");
		GameRegistry.registerBlock(cable,cable.getBlockName());
		
		GameRegistry.registerTileEntity(TECableBase.class, "te"+cable.getBlockName());
		
	}
	
}
