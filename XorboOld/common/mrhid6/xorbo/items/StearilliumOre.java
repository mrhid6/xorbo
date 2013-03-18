package mrhid6.xorbo.items;

import mrhid6.xorbo.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;

public class StearilliumOre extends ItemTex
{
    public StearilliumOre(int id)
    {
        super(id);
    }

    public StearilliumOre(int id, int maxStackSize, String name)
    {
        super(id,maxStackSize,"xorbo:stearilliumore",name);
    }

    public StearilliumOre(int id, int maxStackSize, CreativeTabs tab, String name)
    {
    	super(id,maxStackSize,tab,"xorbo:stearilliumore",name);
    }

    public String getTextureFile()
    {
        return CommonProxy.ITEMS_PNG;
    }
}
