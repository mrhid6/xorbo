package mrhid6.xorbo.configuration;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BuildCraftPlugin {
	public static Item stoneGear = null;
	
	public BuildCraftPlugin(){
		
		checkStoneGear();
	}
	
	public void checkStoneGear(){
		try {
			stoneGear = ((Item)Class.forName("buildcraft.BuildCraftCore").getField("stoneGearItem").get(null));
		} catch (Exception ex) {
			return;
		}
	}
}
