package mrhid6.xorbo.blocks.custom;

import mrhid6.xorbo.blocks.BlockMachine;
import mrhid6.xorbo.tileentities.TETriniumCore;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTriniumCore extends BlockMachine{

	public BlockTriniumCore(int par1, int par2) {
		super(par1, Material.glass,5.0F,2.0F,"triniumcore",0);
	}
	public TileEntity createNewTileEntity(World var1) {
		return new TETriniumCore();
	}

	public int getRenderType(){
		return BRTriniumCore.renderID;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
}
