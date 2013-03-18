package mrhid6.xorbo.items;

import mrhid6.xorbo.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;

public class ZoroIngot extends ItemTex
{
    public ZoroIngot(int id)
    {
        super(id);
    }

    public ZoroIngot(int id, int maxStackSize, String name)
    {
        super(id,maxStackSize,"xorbo:zoroingot",name);
    }

    public ZoroIngot(int id, int maxStackSize, CreativeTabs tab, String name)
    {
    	super(id,maxStackSize,tab,"xorbo:zoroingot",name);
    }

    public String getTextureFile()
    {
        return CommonProxy.ITEMS_PNG;
    }
}
