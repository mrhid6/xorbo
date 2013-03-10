package mrhid6.xorbo.block;

import mrhid6.xorbo.render.RenderTEZoroController;
import mrhid6.xorbo.tileEntity.TECableBase;
import mrhid6.xorbo.tileEntity.TEZoroController;
import mrhid6.xorbo.tileEntity.TEZoroFurnace;
import net.minecraft.block.Block;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModBlocks {
	
	public static Block cable;
	public static Block zoroFurnace;
	public static Block zoroController;
	
	
	
	public static void init(){
		
		cable = new BlockCableBase(500, 0, "zorocable", true);
		LanguageRegistry.addName(cable, "Zoro Cable");
		GameRegistry.registerBlock(cable,cable.getBlockName());
		
		GameRegistry.registerTileEntity(TECableBase.class, "te"+cable.getBlockName());
		
		
		zoroFurnace = new BlockZoroFurnace(501, 0, "zorofurnace", true);
		LanguageRegistry.addName(zoroFurnace, "Zoro Furnace");
		GameRegistry.registerBlock(zoroFurnace,zoroFurnace.getBlockName());
		
		GameRegistry.registerTileEntity(TEZoroFurnace.class, "te"+zoroFurnace.getBlockName());
		
		zoroController = new BlockZoroController(502, 1, "zorocontroller", true);
		LanguageRegistry.addName(zoroController, "Zoro Controller");
		GameRegistry.registerBlock(zoroController,zoroController.getBlockName());
		
		GameRegistry.registerTileEntity(TEZoroController.class, "te"+zoroController.getBlockName());
		
		
	}
	
}
