package mrhid6.xorbo;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabXorbo extends CreativeTabs
{
    private ItemStack item;

    public CreativeTabXorbo(String label)
    {
        super(label);
    }

    public ItemStack getIconItemStack()
    {
        return item;
    }

    public void setIcon(ItemStack item)
    {
        this.item = item;
    }

    public void setIcon(Block item)
    {
        this.item = new ItemStack(item);
    }
}
