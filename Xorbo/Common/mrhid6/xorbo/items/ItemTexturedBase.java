package mrhid6.xorbo.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemTexturedBase extends Item{

	public ItemTexturedBase(int id, int maxStackSize, CreativeTabs tab, String name){
		super(id);

		setMaxStackSize(maxStackSize);
		setCreativeTab(tab);
		setUnlocalizedName(name);
	}

}
