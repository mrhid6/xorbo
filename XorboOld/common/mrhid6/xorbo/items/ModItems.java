package mrhid6.xorbo.items;

import mrhid6.xorbo.blocks.ModBlocks;
import mrhid6.xorbo.configuration.BuildCraftPlugin;
import mrhid6.xorbo.lib.ItemIds;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModItems
{
	public static Item zoroIngot;
	public static Item refinedZoroIngot;
	public static Item triniumIngot;
	public static Item stearilliumOre;
	public static Item stearilliumGear;
	public static Item triniumGear;
	public static Item debugtool;
	
	// Dust Items 
	public static Item zoroDust;
	public static Item triniumDust;

	public static void init()
	{
		zoroIngot = new ZoroIngot(ItemIds.getID("ZoroIngot"), 64, "zoroIngot");
		LanguageRegistry.addName(zoroIngot, "Zoro Ingot");

		refinedZoroIngot = new ZoroIngot(ItemIds.getID("RefinedZoroIngot"), 64, "refinedZoroIngot");
		LanguageRegistry.addName(refinedZoroIngot, "Refined Zoro Ingot");
		
		triniumIngot = new ZoroIngot(ItemIds.getID("TriniumIngot"), 64, "triniumIngot");
		LanguageRegistry.addName(triniumIngot, "Trinium Ingot");

		stearilliumOre = new StearilliumOre(ItemIds.getID("StearilliumOre"), 64, "stearilliumOre");
		LanguageRegistry.addName(stearilliumOre, "Stearillium Ore");
		
		stearilliumGear = new StearilliumOre(ItemIds.getID("StearilliumGear"), 64, "stearilliumGear");
		LanguageRegistry.addName(stearilliumGear, "Stearillium Gear");
		
		triniumGear = new StearilliumOre(ItemIds.getID("TriniumGear"), 64, "triniumGear");
		LanguageRegistry.addName(triniumGear, "Trinium Gear");
		
		debugtool = new DebugTool(ItemIds.getID("debugtool"), 1, "debugtool");
		LanguageRegistry.addName(debugtool, "debugtool");
		
		zoroDust = new ZoroDust(ItemIds.getID("ZoroDust"), 64, "zoroDust");
		LanguageRegistry.addName(zoroDust, "Zoro Dust");
		
		triniumDust = new ZoroDust(ItemIds.getID("TriniumDust"), 64, "triniumDust");
		LanguageRegistry.addName(triniumDust, "Trinium Dust");
	}
	
	public static void addDusts(){
		
	}

	public static void initRecipes()
	{
		//zoro brick recipe
		GameRegistry.addRecipe(new ItemStack(ModBlocks.zoroBrick, 4), new Object[]{
			"sis", "i i", "sis", 's', Block.stoneBrick, 'i', zoroIngot
		});
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.stearilliumSmoothStone, 4), new Object[]{
			"sis", "isi", "sis", 's', Block.stone, 'i', stearilliumOre
		});
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.triniumBrick, 2), new Object[]{
			"ss", "ii", 's', Block.brick, 'i', triniumIngot
		});
		
		//zoro furnace recipe
		GameRegistry.addRecipe(new ItemStack(ModBlocks.zoroFurnaceBlock, 1), new Object[]{
			"aaa", "ibi", "sss", 'i', zoroIngot, 'b', Block.fenceIron,
			's', ModBlocks.zoroBrick, 'a', Block.stone});
		
		//zoro generator recipe
		GameRegistry.addRecipe(new ItemStack(ModBlocks.zoroGenBlock, 1), new Object[]{
			"sis", "ibi", "sis", 'i', refinedZoroIngot, 'b', Item.blazePowder,
			's', Block.stone});

		// Add buildcraft stone gear to xorbo recipes
		if(BuildCraftPlugin.stoneGear!=null){
			
			GameRegistry.addRecipe(new ItemStack(stearilliumGear, 1), new Object[]{
				" # ", "#X#", " # ", '#', stearilliumOre, 'X', BuildCraftPlugin.stoneGear});
		}else{
			GameRegistry.addRecipe(new ItemStack(stearilliumGear, 1), new Object[]{
				" # ", "#X#", " # ", '#', stearilliumOre, 'X', zoroIngot});
		}
		
		GameRegistry.addRecipe(new ItemStack(triniumGear, 1), new Object[]{
			" # ", "#X#", " # ", '#', triniumIngot, 'X', stearilliumGear});
	}
}
