package mrhid6.xorbo.blocks.custom;

import mrhid6.xorbo.Xorbo;
import mrhid6.xorbo.tileentities.TETriniumSun;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTriniumSun extends BlockContainer{

	public BlockTriniumSun(int par1, int par2) {
		super(par1,par2, Material.glass);
		setHardness(5.0F);
		setStepSound(Block.soundStoneFootstep);
		setBlockName("triniumsun");
		setCreativeTab(Xorbo.tabsXor);
	}
	public TileEntity createNewTileEntity(World var1) {
		return new TETriniumSun();
	}

	public int getRenderType(){
		return BRTriniumSun.renderID;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}




}
