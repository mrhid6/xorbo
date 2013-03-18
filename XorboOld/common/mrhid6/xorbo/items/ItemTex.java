package mrhid6.xorbo.items;

import mrhid6.xorbo.Xorbo;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemTex extends Item{
	
	String textureName = "item";
	
	public ItemTex(int id)
	{
		super(id);
		maxStackSize = 64;
		setCreativeTab(Xorbo.tabsXor);
		setUnlocalizedName("testItem");
	}

	public ItemTex(int id, int maxStackSize, String textureName, String name)
	{
		super(id);
		this.textureName = textureName;
		setMaxStackSize(maxStackSize);
		setCreativeTab(Xorbo.tabsXor);
		setUnlocalizedName(name);
	}

	public ItemTex(int id, int maxStackSize, CreativeTabs tab, String textureName, String name)
	{
		super(id);
		this.textureName = textureName;
		setMaxStackSize(maxStackSize);
		setCreativeTab(tab);
		setUnlocalizedName(name);
	}

	@Override
	public void func_94581_a(IconRegister iconRegister)
	{
		iconIndex = iconRegister.func_94245_a(textureName);
	}
}
