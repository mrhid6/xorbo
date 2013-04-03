package mrhid6.xorbo;

import mrhid6.xorbo.block.ModBlocks;
import mrhid6.xorbo.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class XorRecipes {

	public static void addRecipes() {
		GameRegistry.addRecipe(new ItemStack(ModBlocks.zoroFurnace, 1), new Object[] { "aaa", "ibi", "sss", 'i', ModItems.zoroIngot, 'b', Block.fenceIron, 's', ModBlocks.zoroBrick, 'a', Block.stone });
		
		GameRegistry.addRecipe(new ItemStack(ModItems.zoroStaff, 1), new Object[] { "a  ", " b ", "  c", 'a', ModItems.zoroIngot, 'b', Item.stick, 'c', Block.cloth});

	}
}
