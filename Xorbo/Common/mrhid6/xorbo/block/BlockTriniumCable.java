package mrhid6.xorbo.block;

import mrhid6.xorbo.Config;
import mrhid6.xorbo.tileEntity.TECableBase;
import mrhid6.xorbo.tileEntity.TETriniumCable;
import mrhid6.xorbo.tileEntity.TEZoroController;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTriniumCable extends BlockCableBase{

	public BlockTriniumCable(int id, String name, boolean craftable) {
		super(id, name, name, craftable);
	}
	
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		
		TECableBase tile = (TETriniumCable)world.getBlockTileEntity(x, y, z);
		
		if(tile!=null){
			tile.breakBlock();
		}
		
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TETriniumCable();
	}

}
