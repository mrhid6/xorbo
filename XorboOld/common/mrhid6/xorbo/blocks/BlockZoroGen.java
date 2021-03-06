package mrhid6.xorbo.blocks;

import java.util.Random;

import mrhid6.xorbo.CommonProxy;
import mrhid6.xorbo.tileentities.engine.TileZoroGen;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockZoroGen extends BlockMachine
{

	public BlockZoroGen(int id, String textureName, String name,boolean craftable){
		super(id, textureName, name, craftable);
		setLightOpacity(1);
		disableStats();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3,
			int par4, Random par5Random) {
		super.randomDisplayTick(par1World, par2, par3, par4, par5Random);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5)
	{
		return !par1iBlockAccess.isBlockOpaqueCube(par2, par3, par4) ? true : par1iBlockAccess.getBlockId(par2, par3, par4) == this.blockID ? true : false;
	}

	@Override
	public TileEntity createNewTileEntity(World world){
		return new TileZoroGen();
	}

	@Override
	public void func_94332_a(IconRegister iconRegister)
	{
		icons[0] = iconRegister.func_94245_a("xorbo:"+textureName+"_top");
		icons[1] = iconRegister.func_94245_a("xorbo:"+textureName+"_side");
		icons[2] = iconRegister.func_94245_a("xorbo:"+textureName+"_front");
	}


	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int blockSide){
		if (blockSide == 1){
			return icons[0];
		}
		else if (blockSide == 0){
			return icons[0];
		}
		else{
			int var6 = par1IBlockAccess.getBlockMetadata(x, y, z);
			return blockSide != var6 ? icons[1] :  icons[2];
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		if (side == 1){
			return icons[0];
		}else if (side == 0){
			return icons[0];
		}else if(side == 3){
			return icons[2];
		}else{
			return icons[1];
		}
	}

}
