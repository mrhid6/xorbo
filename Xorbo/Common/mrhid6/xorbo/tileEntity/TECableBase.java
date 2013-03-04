package mrhid6.xorbo.tileEntity;

import java.util.List;

import mrhid6.xorbo.block.BlockCableBase;
import mrhid6.xorbo.interfaces.ICustomCollision;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TECableBase extends TileEntity{

	protected byte connections = -1;
	protected byte connectionMask = 0;
	private boolean isLoaded = false;
	
	
	public static double getCableThickness() {
		return 6.0D / 16.0D;
	}
	
	


}
