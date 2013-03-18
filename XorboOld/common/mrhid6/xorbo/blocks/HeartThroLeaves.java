package mrhid6.xorbo.blocks;

import java.util.List;
import java.util.Random;

import mrhid6.xorbo.Xorbo;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HeartThroLeaves extends BlockLeaves
{
    public HeartThroLeaves(int id,String name)
    {
        super(id);
        setLightOpacity(1);
        setTickRandomly(true);
        setHardness(0.2F);
        setStepSound(Block.soundGrassFootstep);
        this.setUnlocalizedName(name);
        setCreativeTab(Xorbo.tabsXor);
        disableStats();
        this.graphicsLevel = true;
    }

    @Override
    public int idDropped(int par1, Random random, int zero)
    {
        return this.blockID;
    }

    @Override
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        return this.field_94336_cN;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5)
    {
        return !par1iBlockAccess.isBlockOpaqueCube(par2, par3, par4) ? true : par1iBlockAccess.getBlockId(par2, par3, par4) == this.blockID ? this.graphicsLevel : false;
    }

    @Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3,
            int par4, int par5, float par6, int par7)
    {
        if (!par1World.isRemote)
        {
            if (par1World.rand.nextInt(35) == 0)
            {
                int var9 = idDropped(par5, par1World.rand, par7);
                dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(var9, 1, damageDropped(par5)));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess par1iBlockAccess, int par2, int par3, int par4)
    {
        return 0x71cae3;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int par1)
    {
        return 0x71cae3;
    }
    
    public void getSubBlocks(int par1, CreativeTabs tabs, List par3List){
    	par3List.add(new ItemStack(par1, 1, 0));
    }
}
