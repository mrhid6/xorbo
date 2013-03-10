package mrhid6.xorbo.block;

import mrhid6.xorbo.tileEntity.TEZoroFurnace;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockZoroFurnace extends BlockTexturedBase{

	
	public BlockZoroFurnace(int id, int textureid, String name,boolean craftable) {
		super(id, textureid, name, craftable,1);
		
		this.setResistance(6.0F);
		this.setHardness(6.0F);

	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TEZoroFurnace();
	}
}
