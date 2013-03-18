package mrhid6.xorbo.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HazelspringLog  extends TexBlock
{
	public HazelspringLog(int id, String textureName, String name){
		
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
	public void func_94332_a(IconRegister iconRegister)
    {
		icons[0] = iconRegister.func_94245_a("xorbo:"+textureName+"_top");
		icons[1] = iconRegister.func_94245_a("xorbo:"+textureName+"_side");
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
}
