package mrhid6.xorbo.blocks.mineable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

public class OreMain extends BlockOre{

	
	private String textureName;
	public OreMain(int id, String textureName) {
		super(id);
		this.textureName = textureName;
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess par1iBlockAccess, int x, int y, int z, int blockSide) {
		return getBlockTextureFromSideAndMetadata(blockSide,0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		return this.field_94336_cN;
	}
	
	@Override
	public void func_94332_a(IconRegister iconRegister){
		field_94336_cN = iconRegister.func_94245_a("xorbo:ore_"+textureName);
	}

}
