package mrhid6.xorbo.block;

import mrhid6.xorbo.BlockIds;
import mrhid6.xorbo.block.fancy.BlockHazelspringLog;
import mrhid6.xorbo.block.fancy.BlockWinterbirchLog;
import mrhid6.xorbo.block.fancy.BlockZoroGrass;
import mrhid6.xorbo.block.fancy.HazelspringLeaves;
import mrhid6.xorbo.block.fancy.WinterbirchLeaves;
import mrhid6.xorbo.block.minable.BlockStearilliumOre;
import mrhid6.xorbo.block.minable.BlockTriniumOre;
import mrhid6.xorbo.block.minable.BlockZoroOre;
import mrhid6.xorbo.items.ModItems;
import mrhid6.xorbo.tileEntity.TECableBase;
import mrhid6.xorbo.tileEntity.TEStearilliumCrafter;
import mrhid6.xorbo.tileEntity.TETriniumMiner;
import mrhid6.xorbo.tileEntity.TEZoroController;
import mrhid6.xorbo.tileEntity.TEZoroFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFluid;
import net.minecraft.block.BlockLeaves;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModBlocks {

	public static Block cable;
	public static Block zoroFurnace;
	public static Block zoroController;
	public static Block hazelspringLog;
	public static Block winterbirchLog;
	public static BlockLeaves hazelspringLeaves;
	public static BlockLeaves winterbirchLeaves;
	public static BlockFluid zoroStill;
	public static BlockFluid zoroFlowing;
	public static Block zoroGrass;
	public static Block triniumMiner;
	public static Block zoroOre;
	public static Block stearilliumOre;
	public static Block triniumOre;
	public static Block stearilliumCrafter;


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

		triniumMiner = new BlockTriniumMiner(BlockIds.getID("triniumMiner"), "triniumMiner", "triniumminer", true);
		LanguageRegistry.addName(triniumMiner, "Trinium Miner");
		GameRegistry.registerBlock(triniumMiner,triniumMiner.getUnlocalizedName());

		GameRegistry.registerTileEntity(TETriniumMiner.class, "te"+triniumMiner.getUnlocalizedName());
		
		stearilliumCrafter = new BlockStearilliumCrafter(BlockIds.getID("stearilliumCrafter"), "stearilliumCrafter", "stearilliumcrafter", true);
		LanguageRegistry.addName(stearilliumCrafter, "Stearillium Crafter");
		GameRegistry.registerBlock(stearilliumCrafter,stearilliumCrafter.getUnlocalizedName());

		GameRegistry.registerTileEntity(TEStearilliumCrafter.class, "te"+stearilliumCrafter.getUnlocalizedName());
		
		zoroGrass = new BlockZoroGrass(BlockIds.getID("zoroGrass"), "zoroGrass", "zorograss");
		LanguageRegistry.addName(zoroGrass, "Zoro Grass");
		GameRegistry.registerBlock(zoroGrass,zoroGrass.getUnlocalizedName());

		//Ores

		zoroOre = new BlockZoroOre(BlockIds.getID("zoroOre"),"zoroore");
		LanguageRegistry.addName(zoroOre, "Zoro Ore");
		GameRegistry.registerBlock(zoroOre,zoroOre.getUnlocalizedName());
		MinecraftForge.setBlockHarvestLevel(zoroOre, "pickaxe", 2);

		stearilliumOre = new BlockStearilliumOre(BlockIds.getID("stearilliumOre"),"stearilliumore");
		LanguageRegistry.addName(stearilliumOre, "Stearillium Ore");
		GameRegistry.registerBlock(stearilliumOre,stearilliumOre.getUnlocalizedName());
		MinecraftForge.setBlockHarvestLevel(stearilliumOre, "pickaxe", 3);

		triniumOre = new BlockTriniumOre(BlockIds.getID("triniumOre"),"triniumore");
		LanguageRegistry.addName(triniumOre, "Trinium Ore");
		GameRegistry.registerBlock(triniumOre,triniumOre.getUnlocalizedName());
		MinecraftForge.setBlockHarvestLevel(triniumOre, "pickaxe", 3);


		//Trees 

		hazelspringLog = new BlockHazelspringLog(BlockIds.getID("hazelspringLog"), "hazelspringLog", "hazelspringlog");
		LanguageRegistry.addName(hazelspringLog, "Hazelspring Log");
		GameRegistry.registerBlock(hazelspringLog,hazelspringLog.getUnlocalizedName());

		hazelspringLeaves = new HazelspringLeaves(BlockIds.getID("hazelspringLeaves"),"hazelspringleaves");
		LanguageRegistry.addName(hazelspringLeaves, "Hazelspring Leaves");
		GameRegistry.registerBlock(hazelspringLeaves, hazelspringLeaves.getUnlocalizedName());

		winterbirchLog = new BlockWinterbirchLog(BlockIds.getID("winterbirchLog"), "winterbirchLog", "winterbirchlog");
		LanguageRegistry.addName(winterbirchLog, "Winter Birch Log");
		GameRegistry.registerBlock(winterbirchLog,winterbirchLog.getUnlocalizedName());

		winterbirchLeaves = new WinterbirchLeaves(BlockIds.getID("winterbirchLeaves"),"winterbirchleaves");
		LanguageRegistry.addName(winterbirchLeaves, "Winter Birch Leaves");
		GameRegistry.registerBlock(winterbirchLeaves, winterbirchLeaves.getUnlocalizedName());

		OreDictionary.registerOre("logWood",new ItemStack(hazelspringLog,1));
		OreDictionary.registerOre("logWood",new ItemStack(winterbirchLog,1));


		//Liquids


		zoroStill = new BlockZoroStill(BlockIds.getID("zoroStill"),"zorojuice");
		zoroFlowing = new BlockZoroFlowing(BlockIds.getID("zoroFlowing"),"zorojuiceflowing");

		GameRegistry.registerBlock(zoroStill, zoroStill.getUnlocalizedName());
		GameRegistry.registerBlock(zoroFlowing, zoroFlowing.getUnlocalizedName());

		LanguageRegistry.addName(zoroStill, "Volatile Zoro");
		LanguageRegistry.addName(zoroFlowing, "Volatile Zoro");

		LiquidDictionary.getOrCreateLiquid("zorojuice", new LiquidStack(zoroStill, LiquidContainerRegistry.BUCKET_VOLUME));

		LiquidContainerRegistry.registerLiquid(new LiquidContainerData(new LiquidStack(zoroStill, LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(ModItems.zoroBucket), new ItemStack(Item.bucketEmpty)));
	}
}
