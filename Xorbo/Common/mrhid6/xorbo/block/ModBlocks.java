package mrhid6.xorbo.block;

import mrhid6.xorbo.BlockIds;
import mrhid6.xorbo.block.fancy.BlockHazelspringLog;
import mrhid6.xorbo.block.fancy.BlockStearilliumGlass;
import mrhid6.xorbo.block.fancy.BlockStearilliumStone;
import mrhid6.xorbo.block.fancy.BlockTriniumBrick;
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
import mrhid6.xorbo.tileEntity.TETriniumCable;
import mrhid6.xorbo.tileEntity.TETriniumChillerBase;
import mrhid6.xorbo.tileEntity.TETriniumChillerCore;
import mrhid6.xorbo.tileEntity.TETriniumConverter;
import mrhid6.xorbo.tileEntity.TETriniumMiner;
import mrhid6.xorbo.tileEntity.TEZoroChest;
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

	public static BlockLeaves hazelspringLeaves;
	public static Block hazelspringLog;
	public static Block stearilliumCrafter;
	public static Block stearilliumOre;
	public static Block triniumBrick;
	public static Block triniumCable;
	public static Block triniumConverter;
	public static Block triniumMiner;
	public static Block triniumOre;
	public static BlockLeaves winterbirchLeaves;
	public static Block winterbirchLog;
	public static Block zoroBrick;
	public static Block zoroCable;
	public static Block zoroChest;
	public static Block zoroController;
	public static BlockFluid zoroFlowing;
	public static Block zoroFurnace;
	public static Block zoroGrass;
	public static Block zoroOre;
	public static BlockFluid zoroStill;
	public static Block triniumChiller;
	public static Block stearilliumStone;
	public static Block stearilliumGlass;

	public static void init() {

		zoroCable = new BlockZoroCable(BlockIds.getID("zoroCable"), "zorocable", true);
		LanguageRegistry.addName(zoroCable, "Zoro Cable");
		GameRegistry.registerBlock(zoroCable, zoroCable.getUnlocalizedName());

		GameRegistry.registerTileEntity(TECableBase.class, "te" + zoroCable.getUnlocalizedName());

		triniumCable = new BlockTriniumCable(BlockIds.getID("triniumCable"), "triniumcable", true);
		LanguageRegistry.addName(triniumCable, "Trinium Cable");
		GameRegistry.registerBlock(triniumCable, triniumCable.getUnlocalizedName());

		GameRegistry.registerTileEntity(TETriniumCable.class, "te" + triniumCable.getUnlocalizedName());

		zoroFurnace = new BlockZoroFurnace(BlockIds.getID("zoroFurnace"), "zoroFurnace", "zorofurnace", true);
		LanguageRegistry.addName(zoroFurnace, "Zoro Furnace");
		GameRegistry.registerBlock(zoroFurnace, zoroFurnace.getUnlocalizedName());

		GameRegistry.registerTileEntity(TEZoroFurnace.class, "te" + zoroFurnace.getUnlocalizedName());

		zoroController = new BlockZoroController(BlockIds.getID("zoroController"), "zoroController", "zorocontroller", true);
		LanguageRegistry.addName(zoroController, "Zoro Controller");
		GameRegistry.registerBlock(zoroController, zoroController.getUnlocalizedName());

		GameRegistry.registerTileEntity(TEZoroController.class, "te" + zoroController.getUnlocalizedName());

		zoroChest = new BlockZoroChest(BlockIds.getID("zoroChest"), "zoroChest", "zorochest");
		LanguageRegistry.addName(zoroChest, "Zoro Chest");
		GameRegistry.registerBlock(zoroChest, zoroChest.getUnlocalizedName());

		GameRegistry.registerTileEntity(TEZoroChest.class, "te" + zoroChest.getUnlocalizedName());

		triniumMiner = new BlockTriniumMiner(BlockIds.getID("triniumMiner"), "triniumMiner", "triniumminer", true);
		LanguageRegistry.addName(triniumMiner, "Trinium Miner");
		GameRegistry.registerBlock(triniumMiner, triniumMiner.getUnlocalizedName());

		GameRegistry.registerTileEntity(TETriniumMiner.class, "te" + triniumMiner.getUnlocalizedName());
		
		triniumChiller = new BlockTriniumChiller(BlockIds.getID("triniumChiller"), "triniumChiller");
		LanguageRegistry.addName(triniumChiller, "Trinium Chiller");
		GameRegistry.registerBlock(triniumChiller, "triniumchiller");
		
		GameRegistry.registerTileEntity(TETriniumChillerCore.class, "triniumchillercontainer");
		GameRegistry.registerTileEntity(TETriniumChillerBase.class, "triniumchillerbasecontainer");

		stearilliumCrafter = new BlockStearilliumCrafter(BlockIds.getID("stearilliumCrafter"), "stearilliumCrafter", "stearilliumcrafter", true);
		LanguageRegistry.addName(stearilliumCrafter, "Stearillium Crafter");
		GameRegistry.registerBlock(stearilliumCrafter, stearilliumCrafter.getUnlocalizedName());

		GameRegistry.registerTileEntity(TEStearilliumCrafter.class, "te" + stearilliumCrafter.getUnlocalizedName());

		triniumConverter = new BlockTriniumConverter(BlockIds.getID("triniumConverter"), "triniumConverter", "triniumconverter");
		LanguageRegistry.addName(triniumConverter, "Trinium Converter");
		GameRegistry.registerBlock(triniumConverter, triniumConverter.getUnlocalizedName());

		GameRegistry.registerTileEntity(TETriniumConverter.class, "te" + triniumConverter.getUnlocalizedName());

		zoroGrass = new BlockZoroGrass(BlockIds.getID("zoroGrass"), "zoroGrass", "zorograss");
		LanguageRegistry.addName(zoroGrass, "Zoro Grass");
		GameRegistry.registerBlock(zoroGrass, zoroGrass.getUnlocalizedName());

		zoroBrick = new BlockTriniumBrick(BlockIds.getID("zoroBrick"), "zoroBrick");
		LanguageRegistry.addName(zoroBrick, "Zoro Brick");
		GameRegistry.registerBlock(zoroBrick, zoroBrick.getUnlocalizedName());

		triniumBrick = new BlockTriniumBrick(BlockIds.getID("triniumBrick"), "triniumBrick");
		LanguageRegistry.addName(triniumBrick, "Trinium Brick");
		GameRegistry.registerBlock(triniumBrick, triniumBrick.getUnlocalizedName());
		
		stearilliumStone = new BlockStearilliumStone(BlockIds.getID("stearilliumStone"), "stearilliumStone");
		LanguageRegistry.addName(stearilliumStone, "Stearillium Stone");
		GameRegistry.registerBlock(stearilliumStone, stearilliumStone.getUnlocalizedName());
		
		stearilliumGlass = new BlockStearilliumGlass(BlockIds.getID("stearilliumGlass"), "stearilliumGlass");
		LanguageRegistry.addName(stearilliumGlass, "Stearillium Glass");
		GameRegistry.registerBlock(stearilliumGlass, stearilliumGlass.getUnlocalizedName());

		// Ores

		zoroOre = new BlockZoroOre(BlockIds.getID("zoroOre"), "zoroore");
		LanguageRegistry.addName(zoroOre, "Zoro Ore");
		GameRegistry.registerBlock(zoroOre, zoroOre.getUnlocalizedName());
		MinecraftForge.setBlockHarvestLevel(zoroOre, "pickaxe", 2);

		stearilliumOre = new BlockStearilliumOre(BlockIds.getID("stearilliumOre"), "stearilliumore");
		LanguageRegistry.addName(stearilliumOre, "Stearillium Ore");
		GameRegistry.registerBlock(stearilliumOre, stearilliumOre.getUnlocalizedName());
		MinecraftForge.setBlockHarvestLevel(stearilliumOre, "pickaxe", 3);

		triniumOre = new BlockTriniumOre(BlockIds.getID("triniumOre"), "triniumore");
		LanguageRegistry.addName(triniumOre, "Trinium Ore");
		GameRegistry.registerBlock(triniumOre, triniumOre.getUnlocalizedName());
		MinecraftForge.setBlockHarvestLevel(triniumOre, "pickaxe", 3);

		// Trees

		hazelspringLog = new BlockHazelspringLog(BlockIds.getID("hazelspringLog"), "hazelspringLog", "hazelspringlog");
		LanguageRegistry.addName(hazelspringLog, "Hazelspring Log");
		GameRegistry.registerBlock(hazelspringLog, hazelspringLog.getUnlocalizedName());

		hazelspringLeaves = new HazelspringLeaves(BlockIds.getID("hazelspringLeaves"), "hazelspringleaves");
		LanguageRegistry.addName(hazelspringLeaves, "Hazelspring Leaves");
		GameRegistry.registerBlock(hazelspringLeaves, hazelspringLeaves.getUnlocalizedName());

		winterbirchLog = new BlockWinterbirchLog(BlockIds.getID("winterbirchLog"), "winterbirchLog", "winterbirchlog");
		LanguageRegistry.addName(winterbirchLog, "Winter Birch Log");
		GameRegistry.registerBlock(winterbirchLog, winterbirchLog.getUnlocalizedName());

		winterbirchLeaves = new WinterbirchLeaves(BlockIds.getID("winterbirchLeaves"), "winterbirchleaves");
		LanguageRegistry.addName(winterbirchLeaves, "Winter Birch Leaves");
		GameRegistry.registerBlock(winterbirchLeaves, winterbirchLeaves.getUnlocalizedName());

		OreDictionary.registerOre("logWood", new ItemStack(hazelspringLog, 1));
		OreDictionary.registerOre("logWood", new ItemStack(winterbirchLog, 1));

		// Liquids

		zoroStill = new BlockZoroStill(BlockIds.getID("zoroStill"), "zorojuice");
		zoroFlowing = new BlockZoroFlowing(BlockIds.getID("zoroFlowing"), "zorojuiceflowing");

		GameRegistry.registerBlock(zoroStill, zoroStill.getUnlocalizedName());
		GameRegistry.registerBlock(zoroFlowing, zoroFlowing.getUnlocalizedName());

		LanguageRegistry.addName(zoroStill, "Volatile Zoro");
		LanguageRegistry.addName(zoroFlowing, "Volatile Zoro");

		LiquidDictionary.getOrCreateLiquid("zorojuice", new LiquidStack(zoroStill, LiquidContainerRegistry.BUCKET_VOLUME));

		LiquidContainerRegistry.registerLiquid(new LiquidContainerData(new LiquidStack(zoroStill, LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(ModItems.zoroBucket), new ItemStack(Item.bucketEmpty)));
	}
}
