package mrhid6.xorbo.items;

import net.minecraft.client.renderer.texture.IconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mrhid6.xorbo.Config;

public class ItemStearilliumOre extends ItemTexturedBase{
	
	public ItemStearilliumOre(int id, String name) {
		super(id, 64, Config.creativeTabXor, name);
	}
	
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister iconRegister) {
		this.iconIndex = iconRegister.registerIcon("xorbo:stearilliumore");
	}	
}
