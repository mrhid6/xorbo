package mrhid6.xorbo.block;

import mrhid6.xorbo.render.RenderCableBase;
import mrhid6.xorbo.tileEntity.TECableBase;
import net.minecraft.block.Block;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModBlocks {
	
	public static Block cable;
	
	public static void init(){
		
		cable = new BlockCableBase(500, 0, "zorocable");
		LanguageRegistry.addName(cable, "My Cable");
		GameRegistry.registerBlock(cable,cable.getBlockName());
		GameRegistry.registerTileEntity(TECableBase.class, "te"+cable.getBlockName());
		ClientRegistry.bindTileEntitySpecialRenderer(TECableBase.class, new RenderCableBase());
		
		
		
	}
	
}
