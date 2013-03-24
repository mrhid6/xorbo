package mrhid6.xorbo.block;

import mrhid6.xorbo.tileEntity.TECableBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockZoroCable extends BlockCableBase{

	public BlockZoroCable(int id, String name, boolean craftable) {
		super(id, name, name, craftable);
	}
	
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		
		TECableBase tile = (TECableBase)world.getBlockTileEntity(x, y, z);
		
		if(tile!=null){
			tile.breakBlock();
		}
		
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TECableBase();
	}
}
