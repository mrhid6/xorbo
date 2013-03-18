package mrhid6.xorbo.blocks.custom;

import mrhid6.xorbo.Xorbo;
import mrhid6.xorbo.blocks.BlockMachine;
import mrhid6.xorbo.tileentities.TEStearilliumGrinder;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockStearilliumGrinder extends BlockMachine{

	public BlockStearilliumGrinder(int par1, int par2) {
		super(par1, Material.glass,5.0F,2.0F,"stearilliumgrinder",0);
	}
	public TileEntity createNewTileEntity(World var1) {
		return new TEStearilliumGrinder();
	}

	public int getRenderType(){
		return BRStearilliumGrinder.renderID;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
}
