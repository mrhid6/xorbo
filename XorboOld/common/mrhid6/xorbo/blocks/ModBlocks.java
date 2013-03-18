package mrhid6.xorbo.blocks;

import mrhid6.xorbo.blocks.custom.BlockStearilliumGrinder;
import mrhid6.xorbo.blocks.custom.BlockTriniumChiller;
import mrhid6.xorbo.blocks.custom.BlockTriniumCore;
import mrhid6.xorbo.blocks.custom.BlockTriniumSun;
import mrhid6.xorbo.blocks.mineable.BlockStearilliumOre;
import mrhid6.xorbo.blocks.mineable.BlockTriniumOre;
import mrhid6.xorbo.blocks.mineable.BlockZoroOre;
import mrhid6.xorbo.items.ModItems;
import mrhid6.xorbo.lib.BlockIds;
import mrhid6.xorbo.tileentities.TEStearilliumGrinder;
import mrhid6.xorbo.tileentities.TETriniumChillerBase;
import mrhid6.xorbo.tileentities.TETriniumChillerCore;
import mrhid6.xorbo.tileentities.TETriniumCore;
import mrhid6.xorbo.tileentities.TileZoroFurnace;
import mrhid6.xorbo.tileentities.engine.TileZoroGen;
import mrhid6.xorbo.triniumlaser.BlockTriniumLaserBase;
import mrhid6.xorbo.triniumlaser.BlockTriniumLaserTurret;
import mrhid6.xorbo.triniumlaser.TETriniumLaserBase;
import mrhid6.xorbo.triniumlaser.TETriniumLaserTurret;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModBlocks
{
	//ores
	public static Block zoroOre;
	public static Block stearilliumOre;
	public static Block triniumOre;

	//Aesthetic blocks
	public static Block zoroBrick;
	public static Block triniumBrick;
	public static Block stearilliumSmoothStone;
	public static Block heartThroLog;
	public static Block heartThroLeaves;

	//machines
	public static Block zoroFurnaceBlock;
	public static Block zoroGenBlock;
	public static Block triniumSun;
	public static Block stearilliumGrinder;
	public static Block triniumCore;
	public static Block triniumChiller;

	//mining laser
	public static Block triniumLaserBase;
	public static Block triniumLaserTurret;

	public static void init()
	{

		stearilliumOre = new BlockStearilliumOre(BlockIds.getID("StearilliumOre"), "stearillium");
		LanguageRegistry.addName(stearilliumOre, "Stearillium Ore");
		GameRegistry.registerBlock(stearilliumOre, "stearilliumore");
		MinecraftForge.setBlockHarvestLevel(stearilliumOre, "pickaxe", 3);

		zoroOre = new BlockZoroOre(BlockIds.getID("ZoroOre"), "zoro");
		LanguageRegistry.addName(zoroOre, "Zoro Ore");
		GameRegistry.registerBlock(zoroOre, "zoroore");
		MinecraftForge.setBlockHarvestLevel(zoroOre, "pickaxe", 2);

		triniumOre = new BlockTriniumOre(BlockIds.getID("TriniumOre"), "trinium");
		LanguageRegistry.addName(triniumOre, "Trinium Ore");
		GameRegistry.registerBlock(triniumOre, "triniumore");
		MinecraftForge.setBlockHarvestLevel(triniumOre, "pickaxe", 3);


		zoroBrick = new BlockFancy(BlockIds.getID("ZoroBrick"), "zorobrick","zorobrick");
		LanguageRegistry.addName(zoroBrick, "Zoro Brick");
		GameRegistry.registerBlock(zoroBrick, "zorobrick");

		triniumBrick = new BlockFancy(BlockIds.getID("TriniumBrick"),"triniumbrick", "triniumbrick");
		LanguageRegistry.addName(triniumBrick, "Trinium Brick");
		GameRegistry.registerBlock(triniumBrick, "triniumbrick");
		
		stearilliumSmoothStone = new BlockFancy(BlockIds.getID("StearilliumSmoothStone"), "stearilliumstone", "stearilliumsmoothstone");
		LanguageRegistry.addName(stearilliumSmoothStone, "Stearillium Smooth Stone");
		GameRegistry.registerBlock(stearilliumSmoothStone, "stearilliumsmoothstone");

		zoroFurnaceBlock = new BlockZoroFurnace(BlockIds.getID("zoroFurnace"), "zoroFurnace", "zorofurnace", true);
		GameRegistry.registerBlock(zoroFurnaceBlock, ItemBlock.class, "zorofurnace");
		LanguageRegistry.addName(zoroFurnaceBlock, "Zoro Furnace");
		GameRegistry.registerTileEntity(TileZoroFurnace.class, "zorofurnacecontainer");


		zoroGenBlock = new BlockZoroGen(BlockIds.getID("zoroGen"), "zoroGen", "zorogen", true);
		GameRegistry.registerBlock(zoroGenBlock, ItemBlock.class, "zorogen");
		LanguageRegistry.addName(zoroGenBlock, "Zoro Generator");
		GameRegistry.registerTileEntity(TileZoroGen.class, "zorogencontainer");


		heartThroLog = new HazelspringLog(BlockIds.getID("HeartThroLog"),"hazelspringlog", "hazelspringLog");
		LanguageRegistry.addName(heartThroLog, "HeartThro Log");
		GameRegistry.registerBlock(heartThroLog, "hearththrolog");

		heartThroLeaves = new HeartThroLeaves(BlockIds.getID("HeartThroLeaves"),"heartthroleaves");
		LanguageRegistry.addName(heartThroLeaves, "HeartThro Leaves");
		GameRegistry.registerBlock(heartThroLeaves, "hearththroleaves");

		/*triniumSun = new BlockTriniumSun(BlockIds.getID("TriniumSun"), 1);
		LanguageRegistry.addName(triniumSun, "Trinium Sun");
		GameRegistry.registerBlock(triniumSun, "triniumsun");
		GameRegistry.registerTileEntity(TETriniumSun.class, "triniumsuncontainer");*/

		stearilliumGrinder = new BlockStearilliumGrinder(BlockIds.getID("StearilliumGrinder"), 1);
		LanguageRegistry.addName(stearilliumGrinder, "Stearillium Grinder");
		GameRegistry.registerBlock(stearilliumGrinder, "stearilliumgrinder");
		GameRegistry.registerTileEntity(TEStearilliumGrinder.class, "stearilliumgcontainer");

		triniumCore = new BlockTriniumCore(BlockIds.getID("TriniumCore"), 1);
		LanguageRegistry.addName(triniumCore, "Trinium Core");
		GameRegistry.registerBlock(triniumCore, "triniumcore");
		GameRegistry.registerTileEntity(TETriniumCore.class, "triniumcorecontainer");

		triniumChiller = new BlockTriniumChiller(BlockIds.getID("TriniumChiller"), 9);
		LanguageRegistry.addName(triniumChiller, "Trinium Chiller");
		GameRegistry.registerBlock(triniumChiller, "triniumchiller");
		GameRegistry.registerTileEntity(TETriniumChillerCore.class, "triniumchillercontainer");
		GameRegistry.registerTileEntity(TETriniumChillerBase.class, "triniumchillerbasecontainer");

		triniumLaserBase = new BlockTriniumLaserBase(BlockIds.getID("TriniumLaserBase"), 1);
		LanguageRegistry.addName(triniumLaserBase, "Trinium Laser Base");
		GameRegistry.registerBlock(triniumLaserBase, "triniumlaserbase");
		GameRegistry.registerTileEntity(TETriniumLaserBase.class, "triniumlaserbasecontainer");

		triniumLaserTurret = new BlockTriniumLaserTurret(BlockIds.getID("TriniumLaserTurret"), 1);
		LanguageRegistry.addName(triniumLaserTurret, "Trinium Laser Turret");
		GameRegistry.registerBlock(triniumLaserTurret, "triniumlaserturret");
		GameRegistry.registerTileEntity(TETriniumLaserTurret.class, "triniumlaserturretcontainer");
	}

	public static void initSmelting()
	{
		FurnaceRecipes.smelting().addSmelting(zoroOre.blockID, new ItemStack(ModItems.zoroIngot, 1), 0.1f);
	}
}
