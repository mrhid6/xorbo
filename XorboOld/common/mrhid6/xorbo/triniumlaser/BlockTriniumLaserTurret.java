package mrhid6.xorbo.triniumlaser;

import mrhid6.xorbo.Xorbo;
import mrhid6.xorbo.blocks.custom.BRTriniumSun;
import mrhid6.xorbo.tileentities.TETriniumSun;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTriniumLaserTurret extends BlockContainer{
	
	public BlockTriniumLaserTurret(int par1, int par2) {
		super(par1,par2, Material.glass);
		setHardness(5.0F);
		setStepSound(Block.soundStoneFootstep);
		setBlockName("triniumlaserturret");
		setCreativeTab(Xorbo.tabsXor);
		
	}
	public TileEntity createNewTileEntity(World var1) {
		return new TETriniumLaserTurret();
	}

	public int getRenderType(){
		return BRTriniumLaserTurret.renderID;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}



	
}
