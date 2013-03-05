package mrhid6.xorbo.items;

import mrhid6.xorbo.proxy.commonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemTexturedBase extends Item{

	public ItemTexturedBase(int id, int maxStackSize, CreativeTabs tab, int texture, String name){
		super(id);

		setMaxStackSize(maxStackSize);
		setCreativeTab(tab);
		setIconIndex(texture);
		setItemName(name);
	}

	public String getTextureFile() {
		return commonProxy.ITEMS_PNG;
	}

}
