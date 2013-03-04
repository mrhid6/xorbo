package mrhid6.xorbo.block;

import mrhid6.xorbo.tileEntity.TECableBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCableBase extends BlockTexturedBase{

	public BlockCableBase(int id, int textureid, String name) {
		super(id, textureid, name);
		
	}
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TECableBase();
	}	

}
