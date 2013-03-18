package mrhid6.xorbo.triniumlaser;

import mrhid6.xorbo.blocks.BlockMachine;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTriniumLaserBase extends BlockMachine{
	
	public BlockTriniumLaserBase(int id, int texture) {
		super(id, Material.ground, 2.0F, 5F, "triniumlaserbase", texture);
	}
	public TileEntity createNewTileEntity(World var1) {
		return new TETriniumLaserBase();
	}

	public int getRenderType(){
		return BRTriniumLaserBase.renderID;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
	
}
