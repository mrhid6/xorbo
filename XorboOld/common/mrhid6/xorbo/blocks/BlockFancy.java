package mrhid6.xorbo.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFancy extends TexBlock{

	public BlockFancy(int id, String texture, String name) {
		super(id, texture, name, true);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
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

}
