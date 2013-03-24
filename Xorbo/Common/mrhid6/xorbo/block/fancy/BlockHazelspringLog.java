package mrhid6.xorbo.block.fancy;

import java.util.Random;

import mrhid6.xorbo.block.BlockTexturedBase;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
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
		
		icons = new Icon[2];
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
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta){
		if(side==1 || side==0){
			return icons[0];
		}
		
		return icons[1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess par1iBlockAccess, int x, int y, int z, int blockSide) {
		
		return getBlockTextureFromSideAndMetadata(blockSide,0);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister)
    {
		icons[0] = iconRegister.registerIcon("xorbo:"+textureName+"_top");
		icons[1] = iconRegister.registerIcon("xorbo:"+textureName+"_side");
    }
}
