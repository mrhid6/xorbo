package mrhid6.xorbo.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHazelspringLog extends BlockTexturedBase
{
	public BlockHazelspringLog(int id, String textureName, String name){
		
		super(id, textureName, name, true);
		setHardness(5.0F);
		setStepSound(Block.soundWoodFootstep);
	}

	@Override
	public int idDropped(int par1, Random random, int zero){
		return this.blockID;
	}

	@Override
	public boolean canSustainLeaves(World world, int x, int y, int z){
		return true;
	}
	
	@Override
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		return this.field_94336_cN;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int side)
	{
		return this.field_94336_cN;
	}
}
