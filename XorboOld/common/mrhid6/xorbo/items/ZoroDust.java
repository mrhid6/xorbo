package mrhid6.xorbo.items;

import mrhid6.xorbo.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;

public class ZoroDust extends ItemTex
{
    public ZoroDust(int id)
    {
        super(id);
    }

    public ZoroDust(int id, int maxStackSize, String name)
    {
        super(id,maxStackSize,"xorbo:zorodust",name);
    }

    public ZoroDust(int id, int maxStackSize, CreativeTabs tab, String name)
    {
    	super(id,maxStackSize,tab,"xorbo:zorodust",name);
    }

    public String getTextureFile()
    {
        return CommonProxy.DUSTS_PNG;
    }
}
