package mrhid6.xorbo.blocks.mineable;

import java.util.Random;

import mrhid6.xorbo.CommonProxy;
import mrhid6.xorbo.Xorbo;
import mrhid6.xorbo.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.world.World;

public class BlockStearilliumOre extends OreMain
{
    public BlockStearilliumOre(int id, String texture)
    {
        super(id, texture);
        setHardness(7.0F);
        setStepSound(Block.soundStoneFootstep);
        setUnlocalizedName("stearilliumore");
        setCreativeTab(Xorbo.tabsXor);
    }



	@Override
    public int idDropped(int par1, Random random, int zero)
    {
        return ModItems.stearilliumOre.itemID;
    }
}
