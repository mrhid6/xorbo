package mrhid6.xorbo.block.minable;

import mrhid6.xorbo.block.BlockTexturedBase;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTriniumOre extends BlockTexturedBase{

	public BlockTriniumOre(int id, String name) {
		super(id, name, name, true);
		setHardness(8.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess par1iBlockAccess, int x, int y, int z, int blockSide) {
		return this.blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return this.blockIcon;
	}
	
	
	
}
