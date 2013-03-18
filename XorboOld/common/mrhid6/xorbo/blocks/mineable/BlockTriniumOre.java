package mrhid6.xorbo.blocks.mineable;

import java.util.Random;

import mrhid6.xorbo.CommonProxy;
import mrhid6.xorbo.Xorbo;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;

public class BlockTriniumOre extends OreMain
{
    public BlockTriniumOre(int id, String texture)
    {
        super(id, texture);
        setHardness(5.0F);
        setStepSound(Block.soundStoneFootstep);
        setUnlocalizedName("triniumore");
        setCreativeTab(Xorbo.tabsXor);
    }

    @Override
    public int idDropped(int par1, Random random, int zero)
    {
        return this.blockID;
    }
}
