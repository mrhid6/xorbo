package mrhid6.xorbo.block;

import java.util.Random;

import mrhid6.xorbo.fx.ZoroBubble;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFluid;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.liquids.ILiquid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockZoroStill extends BlockFluid implements ILiquid
{
    protected BlockZoroStill(int par1, String name)
    {
        super(par1, Material.water);
        this.setTickRandomly(false);
        this.setUnlocalizedName(name);
    }

    public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return this.blockMaterial != Material.lava;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);

        if (par1World.getBlockId(par2, par3, par4) == this.blockID)
        {
            this.setNotStationary(par1World, par2, par3, par4);
        }
    }

    /**
     * Changes the block ID to that of an updating fluid.
     */
    private void setNotStationary(World par1World, int par2, int par3, int par4)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4);
        par1World.setBlock(par2, par3, par4, ModBlocks.zoroFlowing.blockID, l, 2);
        par1World.scheduleBlockUpdate(par2, par3, par4, ModBlocks.zoroFlowing.blockID, this.tickRate(par1World));
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (this.blockMaterial == Material.lava)
        {
            int l = par5Random.nextInt(3);
            int i1;
            int j1;

            for (i1 = 0; i1 < l; ++i1)
            {
                par2 += par5Random.nextInt(3) - 1;
                ++par3;
                par4 += par5Random.nextInt(3) - 1;
                j1 = par1World.getBlockId(par2, par3, par4);

                if (j1 == 0)
                {
                    if (this.isFlammable(par1World, par2 - 1, par3, par4) || this.isFlammable(par1World, par2 + 1, par3, par4) || this.isFlammable(par1World, par2, par3, par4 - 1) || this.isFlammable(par1World, par2, par3, par4 + 1) || this.isFlammable(par1World, par2, par3 - 1, par4) || this.isFlammable(par1World, par2, par3 + 1, par4))
                    {
                        par1World.setBlock(par2, par3, par4, Block.fire.blockID);
                        return;
                    }
                }
                else if (Block.blocksList[j1].blockMaterial.blocksMovement())
                {
                    return;
                }
            }

            if (l == 0)
            {
                i1 = par2;
                j1 = par4;

                for (int k1 = 0; k1 < 3; ++k1)
                {
                    par2 = i1 + par5Random.nextInt(3) - 1;
                    par4 = j1 + par5Random.nextInt(3) - 1;

                    if (par1World.isAirBlock(par2, par3 + 1, par4) && this.isFlammable(par1World, par2, par3, par4))
                    {
                        par1World.setBlock(par2, par3 + 1, par4, Block.fire.blockID);
                    }
                }
            }
        }
    }

    /**
     * Checks to see if the block is flammable.
     */
    private boolean isFlammable(World par1World, int par2, int par3, int par4)
    {
        return par1World.getBlockMaterial(par2, par3, par4).getCanBurn();
    }

	@Override
	public int stillLiquidId() {
		return blockID;
	}

	@Override
	public boolean isMetaSensitive() {
		return false;
	}

	@Override
	public int stillLiquidMeta() {
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return this.blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon("xorbo:zoroStill");
	}
	
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.randomDisplayTick(par1World, par2, par3, par4, par5Random);

		if (par5Random.nextInt(50) == 0)
		{
			ZoroBubble bubble = new ZoroBubble(par1World,(double)((float)par2 + par5Random.nextFloat()), (double)((float)par3 + 2.1F), (double)((float)par4 + par5Random.nextFloat()));
			Minecraft.getMinecraft().effectRenderer.addEffect(bubble);
			Minecraft.getMinecraft().effectRenderer.renderParticles(bubble, 1);
			//par1World.spawnParticle("smoke", (double)((float)par2 + par5Random.nextFloat()), (double)((float)par3 + 1.1F), (double)((float)par4 + par5Random.nextFloat()), 0.0D, 0.0D, 0.0D);
		}
	}
	
	
}
