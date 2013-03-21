package mrhid6.xorbo.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFluid;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.liquids.ILiquid;

public class BlockZoroStill extends BlockFluid implements ILiquid{
	
	public BlockZoroStill(int par1,String name)
    {
        super(par1, Material.water);
        this.setTickRandomly(false);
        this.setUnlocalizedName(name);
        disableStats();
        //setRequiresSelfNotify();
    }
	
	public int stillLiquidId(){
    	return blockID;
    }
    
    public boolean isMetaSensitive(){
    	return false;
    }
    
    public int stillLiquidMeta(){
    	return 0;
    }
    
    public int tickRate(){
    	return 5;
    }
	
    @Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess par1iBlockAccess, int x, int y, int z, int blockSide) {
		return this.field_94336_cN;
	}
	
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random){
		Block.waterStill.randomDisplayTick(par1World, par2, par3, par4, par5Random);
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
        if (par1World.getBlockId(par2, par3, par4) == this.blockID)
        {
            this.setNotStationary(par1World, par2, par3, par4);
        }
    }
    
    public void onBlockAdded(World par1World, int par2, int par3, int par4){
    	
    }

    /**
     * Changes the block ID to that of an updating fluid.
     */
    private void setNotStationary(World world, int par2, int par3, int par4)
    {
        int var5 = world.getBlockMetadata(par2, par3, par4);
        
        world.setBlockAndMetadataWithNotify(par2, par3, par4, ModBlocks.zoroFlowing.blockID, var5,0);
        world.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
        world.scheduleBlockUpdate(par2, par3, par4, this.blockID - 1, this.tickRate());
    }

    /**
     * Checks to see if the block is flammable.
     */
    private boolean isFlammable(World par1World, int par2, int par3, int par4)
    {
        return par1World.getBlockMaterial(par2, par3, par4).getCanBurn();
    }
    
    

	@Override
	@SideOnly(Side.CLIENT)
	public void func_94332_a(IconRegister iconRegister) {
		this.field_94336_cN = iconRegister.func_94245_a("xorbo:zoroStill");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		return this.field_94336_cN;
	}
}