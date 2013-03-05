package mrhid6.xorbo;

import mrhid6.xorbo.block.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;

public class CreativeTabXor extends CreativeTabs{

	public CreativeTabXor(String label) {
		super(label);
	}
	
	public int getTabIconItemIndex(){
		return ModBlocks.cable.blockID;
	}
	
	public String getTranslatedTabLabel(){
		return "Xorbo";
	}

}
